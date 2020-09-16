package com.kxdilbeck.gradeapp.Controller;
import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;


import com.kxdilbeck.gradeapp.Model.Course;
import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CourseTest {

    //private UserDAO userDAO;
    private Course currentCourse;
    private CourseController createCourseController;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //userDAO = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).allowMainThreadQueries().build().getUserDAO();

        //courseTest = new CourseTest();
        createCourseController = new CourseController();
    }

    @After
    public void tearDown() throws Exception {
        //userDAO = null;
        currentCourse = null;
        createCourseController = null;
    }

    @Test
    public void checkCourse() {
        //main test
        Course courseTest = new Course("fill", "title fill", "desc fill", "00/00/00", "11/11/11");//set id ==
        courseTest.setCourseId(0);
        //check id
        assertTrue(createCourseController.checkCourseID(0));
        assertFalse(createCourseController.checkCourseID(1));
        //check instructor
        assertTrue(createCourseController.checkInstructor("fill"));
        //check title
        assertTrue(createCourseController.checkCourseTitle("title fill"));
        //check start date
        assertTrue(createCourseController.checkCourseStartDate("00/00/00"));
        //check end date
        assertTrue(createCourseController.checkCourseEndDate("11/11/11"));
    }

}





