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
import com.kxdilbeck.gradeapp.Model.Database.GradeDAO;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.Grade;
import com.kxdilbeck.gradeapp.Model.User;
import com.kxdilbeck.gradeapp.Prepopulate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InterTableQueries {
    private AppDatabase db;
    private CourseDAO courseDAO;
    private UserDAO userDAO;
    private GradeDAO gradeDAO;
    private AssignmentDAO assignmentDAO;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries().build();
        courseDAO = db.getCourseDAO();
        userDAO = db.getUserDAO();
        assignmentDAO = db.getAssignmentDAO();
        gradeDAO = db.getGradeDAO();

        Prepopulate.prepopulate(db);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
        courseDAO = null;
        userDAO = null;
        gradeDAO = null;
        assignmentDAO = null;
    }

    @Test
    public void getSumOfEarnedPointsByCourse(){
        List<User> users = userDAO.getAllUsers();
        List<Course> courses = courseDAO.getAllEnrolledCourses(users.get(0).getUserId());
        List<Course> courses2 = courseDAO.getAllEnrolledCourses(users.get(1).getUserId());

        List<Double> earnedPoints = assignmentDAO.getSumOfEarnedPointsByCourse(courses.get(0).getCourseId(), users.get(0).getUserId());
        List<Double> earnedPoints2 = assignmentDAO.getSumOfEarnedPointsByCourse(courses2.get(1).getCourseId(), users.get(1).getUserId());
        Double[] expectedEarnedPoints = {25.0, 40.0};
        Double[] expectedEarnedPoints2 = {12.0, 25.0, 50.0};

        assertArrayEquals(expectedEarnedPoints, earnedPoints.toArray());
        assertArrayEquals(expectedEarnedPoints2, earnedPoints2.toArray());
    }

    @Test
    public void getSumOfMaxPointsByCourse(){
        List<User> users = userDAO.getAllUsers();
        List<Course> courses = courseDAO.getAllEnrolledCourses(users.get(0).getUserId());
        List<Course> courses2 = courseDAO.getAllEnrolledCourses(users.get(1).getUserId());

        List<Double> maxPoints = assignmentDAO.getSumOfMaxPointsByCourse(courses.get(0).getCourseId(), users.get(0).getUserId());
        List<Double> maxPoints2 = assignmentDAO.getSumOfMaxPointsByCourse(courses2.get(1).getCourseId(), users.get(1).getUserId());
        Double[] expectedMaxPoints = {30.0, 40.0};
        Double[] expectedMaxPoints2 = {15.0, 30.0, 50.0};

        assertArrayEquals(expectedMaxPoints, maxPoints.toArray());
        assertArrayEquals(expectedMaxPoints2, maxPoints2.toArray());
    }

    @Test
    public void getAllWeightsForCourse(){
        List<User> users = userDAO.getAllUsers();
        List<Course> courses = courseDAO.getAllEnrolledCourses(users.get(0).getUserId());
        List<Course> courses2 = courseDAO.getAllEnrolledCourses(users.get(1).getUserId());

        List<Double> weights = gradeDAO.getAllWeightsForCourse(courses.get(0).getCourseId(), users.get(0).getUserId());
        List<Double> weights2 = gradeDAO.getAllWeightsForCourse(courses2.get(1).getCourseId(), users.get(1).getUserId());
        Double[] expectedWeights = {40.0, 10.0};
        Double[] expectedWeights2 = {10.0, 20.0, 20.0};

        assertArrayEquals(expectedWeights, weights.toArray());
        assertArrayEquals(expectedWeights2, weights2.toArray());
    }

    @Test
    public void getGradeCategoriesForCourse(){
        List<User> users = userDAO.getAllUsers();
        List<Course> courses = courseDAO.getAllEnrolledCourses(users.get(0).getUserId());
        List<Course> courses2 = courseDAO.getAllEnrolledCourses(users.get(1).getUserId());

        List<Integer> categories = gradeDAO.getGradeCategoriesForCourse(courses.get(0).getCourseId(), users.get(0).getUserId());
        List<Integer> categories2 = gradeDAO.getGradeCategoriesForCourse(courses2.get(1).getCourseId(), users.get(1).getUserId());
        Integer[] expectedCategories = {1,2};
        Integer[] expectedCategories2 = {3,4,5};

        assertArrayEquals(expectedCategories, categories.toArray());
        assertArrayEquals(expectedCategories2, categories2.toArray());
    }

    @Test
    public void getAssignmentsByCategory(){
        List<User> users = userDAO.getAllUsers();
        List<Course> courses = courseDAO.getAllEnrolledCourses(users.get(0).getUserId());
        List<Course> courses2 = courseDAO.getAllEnrolledCourses(users.get(1).getUserId());

        List<Integer> categories = gradeDAO.getGradeCategoriesForCourse(courses.get(0).getCourseId(), users.get(0).getUserId());
        List<Integer> categories2 = gradeDAO.getGradeCategoriesForCourse(courses2.get(1).getCourseId(), users.get(1).getUserId());

        List<Assignment> assignments = assignmentDAO.getAssignmentsByCategory(courses.get(0).getCourseId(), users.get(0).getUserId(), categories.get(0));
        int gradeId = gradeDAO.insert( new Grade(50.0, users.get(0).getUserId(), categories.get(0))).get(0).intValue();
        assignmentDAO.insert(new Assignment(courses.get(0).getCourseId(), gradeId, "09/12/2021", "09/09/2021", 15.0,
                30.0, "Done"));
        List<Assignment> assignments2 = assignmentDAO.getAssignmentsByCategory(courses.get(0).getCourseId(), users.get(0).getUserId(), categories.get(0));

        assertEquals(1, assignments.size());
        assertEquals(2, assignments2.size());
        assertTrue(assignments2.get(0).getGradeId() == 1 && assignments2.get(1).getGradeId() == gradeId);
    }

    @Test
    public void getAllEnrolledCourses(){
        List<User> users = userDAO.getAllUsers();
        List<Course> courses = courseDAO.getAllEnrolledCourses(users.get(0).getUserId());
        List<Course> courses2 = courseDAO.getAllEnrolledCourses(users.get(1).getUserId());

        //user 1
        assertEquals(3, courses.size());
        assertEquals(1, courses.get(0).getCourseId());
        assertEquals(3, courses.get(1).getCourseId());
        assertEquals(4, courses.get(2).getCourseId());

        //user 2
        assertEquals(2, courses2.size());
        assertEquals(1, courses2.get(0).getCourseId());
        assertEquals(3, courses2.get(1).getCourseId());
    }
}
