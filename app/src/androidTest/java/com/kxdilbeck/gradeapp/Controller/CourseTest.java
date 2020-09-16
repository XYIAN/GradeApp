package com.kxdilbeck.gradeapp.Controller;
import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CourseTest {

    //private UserDAO userDAO;
    private CourseTest courseTest;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //userDAO = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).allowMainThreadQueries().build().getUserDAO();

        courseTest = new CourseTest();
    }

    @After
    public void tearDown() throws Exception {
        //userDAO = null;
        courseTest = null;
    }

    @Test
    public void checkCourse() {
        //main test
        CourseTest courseTest = new CourseTest();
    }
}





