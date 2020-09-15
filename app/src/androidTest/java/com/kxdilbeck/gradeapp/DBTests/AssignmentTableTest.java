// THE COMMENT BELOW IS A TEST COMMIT
// Configuation Package

package com.kxdilbeck.gradeapp.DBTests;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.kxdilbeck.gradeapp.Model.Course;
import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.CourseDAO;
import com.kxdilbeck.gradeapp.Prepopulate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AssignmentTableTest {
    private AppDatabase db;
    private CourseDAO courseDAO;

    @Before
    public void setUp() throws Exception{
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries().build();

        Prepopulate.prepopulate(db);
        courseDAO = db.getCourseDAO();
    }

    @After
    public void tearDown() throws Exception{
        db.close();
    }

    @Test
    public void insert(){
        Course one = new Course("Instructor1", "Course1", "Describe", "08/08/08", "08/09/08");
        Course two = new Course("Instructor2", "Course2", "Describe", "08/08/08", "08/09/08");
        Course three = new Course("Instructor3", "Course3", "Describe", "08/08/08", "08/09/08");

        // id is auto incrementing primary key so its value is not known until after being inserted.
        one.setCourseId(courseDAO.insert(one).get(0).intValue());
        two.setCourseId(courseDAO.insert(two).get(0).intValue());
        three.setCourseId(courseDAO.insert(three).get(0).intValue());

        List<Course> courses = courseDAO.getAllCourses();

        assertEquals(10, courses.size());
        assertTrue(courses.contains(one));
        assertTrue(courses.contains(two));
        assertTrue(courses.contains(three));
    }

    @Test
    public void update(){
        List<Course> courses = courseDAO.getAllCourses();
        Course course = new Course(courses.get(0).getInstructor(), courses.get(0).getTitle(), courses.get(0).getDescription(), courses.get(0).getStartDate(), courses.get(0).getEndDate());

        courses.get(0).setInstructor("NewInstructor");
        courses.get(0).setTitle("NewTitle");
        courseDAO.update(courses.get(0));

        assertNotEquals(course, courses.get(0));
        assertEquals("NewInstructor", courses.get(0).getInstructor());
        assertEquals("NewTitle", courses.get(0).getTitle());
    }

    @Test
    public void delete(){
        List<Course> courses = courseDAO.getAllCourses();

        courseDAO.delete(courses.get(0));
        courseDAO.delete(courses.get(1));
        courseDAO.delete(courses.get(2));

        assertNotEquals(courseDAO.getAllCourses().size(), courses.size());
        assertEquals(4, courseDAO.getAllCourses().size());
    }
}
