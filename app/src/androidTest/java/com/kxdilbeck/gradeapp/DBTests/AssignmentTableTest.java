// THE COMMENT BELOW IS A TEST COMMIT
// Configuation Package

package com.kxdilbeck.gradeapp.DBTests;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.kxdilbeck.gradeapp.Model.Assignment;
import com.kxdilbeck.gradeapp.Model.Course;
import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.AssignmentDAO;
import com.kxdilbeck.gradeapp.Model.Database.CourseDAO;
import com.kxdilbeck.gradeapp.Model.Database.GradeCategoryDAO;
import com.kxdilbeck.gradeapp.Model.Database.GradeDAO;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.Grade;
import com.kxdilbeck.gradeapp.Model.GradeCategory;
import com.kxdilbeck.gradeapp.Model.User;
import com.kxdilbeck.gradeapp.Prepopulate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AssignmentTableTest {
    private AppDatabase db;
    private AssignmentDAO assignmentDAO;
    private CourseDAO courseDAO;
    private UserDAO userDAO;
    private GradeDAO gradeDAO;
    private GradeCategoryDAO gradeCategoryDAO;

    @Before
    public void setUp() throws Exception{
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries().build();

        Prepopulate.prepopulate(db);
        assignmentDAO = db.getAssignmentDAO();
        courseDAO = db.getCourseDAO();
        userDAO = db.getUserDAO();
        gradeCategoryDAO = db.getGradeCategoryDAO();
        gradeDAO = db.getGradeDAO();
    }

    @After
    public void tearDown() throws Exception{
        db.close();
    }

    @Test
    public void insert(){
        List<User> users = userDAO.getAllUsers();
        List<GradeCategory> gradeCategories = gradeCategoryDAO.getAllCategories();
        List<Course> courses = courseDAO.getAllCourses();
        Grade grade = new Grade(25.0, users.get(0).getUserId(), gradeCategories.get(0).getGradeCategoryId());
        grade.setGradeId(gradeDAO.insert(grade).get(0).intValue());
        Assignment assignment = new Assignment(courses.get(0).getCourseId(), grade.getGradeId(), "08/08/08", "08/07/08", 25.0, 30.0, "details");

        // id is auto incrementing primary key so its value is not known until after being inserted.
        assignment.setAssignmentId(assignmentDAO.insert(assignment).get(0).intValue());

        List<Assignment> assignments = assignmentDAO.getAllAssignments();
        assertEquals(11, assignments.size());
        assertTrue(assignments.contains(assignment));
    }

    @Test
    public void update(){
        List<Assignment> assignments = assignmentDAO.getAllAssignments();
        Assignment assignment = new Assignment(assignments.get(0).getCourseId(), assignments.get(0).getGradeId(), assignments.get(0).getDueDate(), assignments.get(0).getAssignedDate(),
                assignments.get(0).getEarnedScore(), assignments.get(0).getMaxScore(), assignments.get(0).getDetails());

        assignments.get(0).setDetails("NewInstructor");
        assignments.get(0).setMaxScore(99.0);
        assignmentDAO.update(assignments.get(0));

        assertNotEquals(assignment, assignments.get(0));
        assertEquals("NewInstructor", assignments.get(0).getDetails());
        assertEquals(99.0, assignments.get(0).getMaxScore(), 0.01);
    }

    @Test
    public void delete(){
        List<Assignment> assignments = assignmentDAO.getAllAssignments();

        assignmentDAO.delete(assignments.get(0));
        assignmentDAO.delete(assignments.get(1));
        assignmentDAO.delete(assignments.get(2));

        assertNotEquals(assignmentDAO.getAllAssignments().size(), assignments.size());
        assertEquals(7, assignmentDAO.getAllAssignments().size());
    }
}
