package com.kxdilbeck.gradeapp.Controller;

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

public class GradeControllerTest {
    private GradeController gradeController;
    private UserDAO userDAO;
    private CourseDAO courseDAO;
    private AssignmentDAO assignmentDAO;
    private GradeCategoryDAO gradeCategoryDAO;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AppDatabase db =  Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        Prepopulate.prepopulate(db);
        userDAO = db.getUserDAO();
        courseDAO = db.getCourseDAO();
        gradeCategoryDAO = db.getGradeCategoryDAO();
        assignmentDAO = db.getAssignmentDAO();

        gradeController = new GradeController(db);
    }

    @After
    public void tearDown() throws Exception {
        gradeController = null;
        userDAO = null;
        courseDAO = null;
        gradeCategoryDAO = null;
        assignmentDAO = null;
    }

    @Test
    public void getCourseGrades() {
        List<User> users = userDAO.getAllUsers();
        List<Course> courses = courseDAO.getAllEnrolledCourses(users.get(0).getUserId());

        String resultant[] = gradeController.getCourseGrade(users.get(0).getUserId(), courses.get(0).getCourseId());
        String resultant2[] = gradeController.getCourseGrade(users.get(1).getUserId(), courses.get(1).getCourseId());

        List<Assignment> assignments = assignmentDAO.getAssignmentsByCategory(courses.get(0).getCourseId(), users.get(0).getUserId(), 1);

        Log.i("DOESITWORK", assignments + "");

        Log.i("DOESITWORK", resultant2[0] + ": " + resultant2[1] + " " + courses.get(1).getCourseId() + " " + courses.get(1).getTitle());

        assertEquals("B", resultant[0]);
        assertEquals(86.667, Double.parseDouble(resultant[1]), 0.01);
    }

    @Test
    public void getCourseGradeCategories() {
    }

    @Test
    public void getGradeByCategory() {
    }

    @Test
    public void getAssignmentGrade() {
    }

    @Test
    public void getAssignmentByCategory() {
    }
}