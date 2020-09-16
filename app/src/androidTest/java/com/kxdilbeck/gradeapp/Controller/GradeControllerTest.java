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

public class GradeControllerTest {
    private AppDatabase db;
    private GradeController gradeController;
    private UserDAO userDAO;
    private CourseDAO courseDAO;
    private AssignmentDAO assignmentDAO;
    private GradeCategoryDAO gradeCategoryDAO;
    private GradeDAO gradeDAO;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db =  Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        Prepopulate.prepopulate(db);
        userDAO = db.getUserDAO();
        courseDAO = db.getCourseDAO();
        gradeCategoryDAO = db.getGradeCategoryDAO();
        assignmentDAO = db.getAssignmentDAO();
        gradeDAO = db.getGradeDAO();

        gradeController = new GradeController(db);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
        gradeController = null;
        userDAO = null;
        courseDAO = null;
        gradeCategoryDAO = null;
        assignmentDAO = null;
        gradeDAO = null;
    }

    @Test
    public void getCourseGrades() {
        List<User> users = userDAO.getAllUsers();
        List<Course> courses = courseDAO.getAllEnrolledCourses(users.get(0).getUserId());

        String resultant[] = gradeController.getCourseGrade(users.get(0).getUserId(), courses.get(0).getCourseId());
        String resultant2[] = gradeController.getCourseGrade(users.get(0).getUserId(), courses.get(1).getCourseId());
        String resultant3[] = gradeController.getCourseGrade(users.get(0).getUserId(), courses.get(2).getCourseId());

        assertEquals("B", resultant[0]);
        assertEquals(86.667, Double.parseDouble(resultant[1]), 0.01);
        assertEquals("B", resultant2[0]);
        assertEquals(82.444, Double.parseDouble(resultant2[1]), 0.01);
        assertEquals(" ", resultant3[0]);
        assertEquals(" ", resultant3[1]);
    }

    @Test
    public void getCourseGradeCategories() {
        List<User> users = userDAO.getAllUsers();
        List<Course> courses = courseDAO.getAllEnrolledCourses(users.get(0).getUserId());
        List<Integer>  categories = gradeController.getCourseGradeCategories(courses.get(0).getCourseId() , users.get(0).getUserId());

        assertEquals(gradeDAO.getGradeCategoriesForCourse(courses.get(0).getCourseId() , users.get(0).getUserId()), categories);
    }

    @Test
    public void getGradeByCategory() {
        List<User> users = userDAO.getAllUsers();
        List<Course> courses = courseDAO.getAllEnrolledCourses(users.get(0).getUserId());
        List<Integer>  categories = gradeController.getCourseGradeCategories(courses.get(0).getCourseId() , users.get(0).getUserId());

        String grade[]  = gradeController.getGradeByCategory(courses.get(0).getCourseId(), users.get(0).getUserId(), categories.get(0));

        assertEquals("B", grade[0]);
        assertEquals(83.334, Double.parseDouble(grade[1]), 0.01);
    }

    @Test
    public void getAssignmentGrade() {
        List<Assignment> assignments = assignmentDAO.getAllAssignments();

        String[] one = gradeController.getAssignmentGrade(assignments.get(0));
        String[] two = gradeController.getAssignmentGrade(assignments.get(3));

        assertEquals("B", one[0]);
        assertEquals(83.334, Double.parseDouble(one[1]), 0.01);
        assertEquals("F", two[0]);
        assertEquals(50.000, Double.parseDouble(two[1]), 0.01);
    }

    @Test
    public void getAssignmentsByCategory() {
        List<User> users = userDAO.getAllUsers();
        List<Course> courses = courseDAO.getAllEnrolledCourses(users.get(0).getUserId());
        List<Integer>  categories = gradeController.getCourseGradeCategories(courses.get(0).getCourseId() , users.get(0).getUserId());
        List<Assignment> assignments = gradeController.getAssignmentsByCategory(courses.get(0).getCourseId() , users.get(0).getUserId(), categories.get(0).intValue());

        assertEquals(assignmentDAO.getAssignmentsByCategory(courses.get(0).getCourseId() , users.get(0).getUserId(), categories.get(0).intValue()), assignments);
    }
}