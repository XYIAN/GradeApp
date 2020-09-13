package com.kxdilbeck.gradeapp.DBTests;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.kxdilbeck.gradeapp.Model.Course;
import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.CourseDAO;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.User;
import com.kxdilbeck.gradeapp.Prepopulate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CourseTableTest {
    private AppDatabase db;
    private CourseDAO courseDAO;
    private UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries().build();
        courseDAO = db.getCourseDAO();
        userDAO = db.getUserDAO();

        Prepopulate.prepopulate(db);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
        courseDAO = null;
        userDAO = null;
    }

    @Test
    public void insert() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
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