package com.kxdilbeck.gradeapp.DBTests;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.kxdilbeck.gradeapp.Model.Course;
import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.CourseDAO;
import com.kxdilbeck.gradeapp.Model.Database.EnrollmentDAO;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.Enrollment;
import com.kxdilbeck.gradeapp.Model.User;
import com.kxdilbeck.gradeapp.Prepopulate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EnrollmentTableTest {
    private AppDatabase db;
    private EnrollmentDAO enrollmentDAO;
    private CourseDAO courseDAO;
    private UserDAO userDAO;

    @Before
    public void setUp() throws Exception{
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries().build();

        Prepopulate.prepopulate(db);
        enrollmentDAO = db.getEnrollmentDAO();
        courseDAO = db.getCourseDAO();
        userDAO = db.getUserDAO();
    }

    @After
    public void tearDown() throws Exception{
        db.close();
        enrollmentDAO = null;
        courseDAO = null;
        userDAO = null;
    }

    @Test
    public void insert(){
        List<Course> courses = courseDAO.getAllCourses();
        List<User> users = userDAO.getAllUsers();
        Enrollment one = new Enrollment(users.get(0).getUserId(), courses.get(0).getCourseId(), "08/08/08");
        Enrollment two = new Enrollment(users.get(1).getUserId(), courses.get(0).getCourseId(), "08/08/08");
        Enrollment three = new Enrollment(users.get(2).getUserId(), courses.get(0).getCourseId(), "08/08/08");

        // id is auto incrementing primary key so its value is not known until after being inserted.
        one.setEnrollmentId(enrollmentDAO.insert(one).get(0).intValue());
        two.setEnrollmentId(enrollmentDAO.insert(two).get(0).intValue());
        three.setEnrollmentId(enrollmentDAO.insert(three).get(0).intValue());

        List<Enrollment> enrollments = enrollmentDAO.getAllEnrollments();

        assertEquals(14, enrollments.size());
        assertTrue(enrollments.contains(one));
        assertTrue(enrollments.contains(two));
        assertTrue(enrollments.contains(three));
    }

    @Test
    public void update(){
        List<Enrollment> enrollments = enrollmentDAO.getAllEnrollments();
        Enrollment enrollment = new Enrollment(enrollments.get(0).getStudentId(), enrollments.get(0).getCourseId(), enrollments.get(0).getEnrollDate());
        enrollment.setEnrollmentId(enrollments.get(0).getEnrollmentId());
        enrollments.get(0).setEnrollDate("09/09/09");

        assertNotEquals(enrollment, enrollments.get(0));
        assertEquals("09/09/09", enrollments.get(0).getEnrollDate());
    }

    @Test
    public void delete(){
        List<Enrollment> enrollments = enrollmentDAO.getAllEnrollments();

        enrollmentDAO.delete(enrollments.get(0));
        enrollmentDAO.delete(enrollments.get(1));
        enrollmentDAO.delete(enrollments.get(2));

        assertNotEquals(enrollmentDAO.getAllEnrollments().size(), enrollments.size());
        assertEquals(8, enrollmentDAO.getAllEnrollments().size());
    }

}