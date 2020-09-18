package com.kxdilbeck.gradeapp.Controller;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.User;
import com.kxdilbeck.gradeapp.Prepopulate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class tests whether the LoginController is working as intended.
 */
public class LoginControllerTest {
    private UserDAO userDAO;
    private LoginController loginController;

    /**
     * Setup data for the test by prepopulating the db, and initializing
     * a LoginController, and userDAO.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AppDatabase db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();

        Prepopulate.prepopulate(db);
        loginController = new LoginController(db);
        userDAO = db.getUserDAO();
    }

    /**
     * When the tests are done it sets all the private variables to null;
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        userDAO = null;
        loginController = null;
    }

    /**
     * Tests whether the LoginController is able to authenticate users correctly.
     */
    @Test
    public void checkAuthentication() {
        assertTrue(loginController.checkAuthentication("Admin2", "Password2"));
        assertFalse(loginController.checkAuthentication("Admin", "Password2"));
        assertFalse(loginController.checkAuthentication("Admin2", "Password"));
        assertFalse(loginController.checkAuthentication("", ""));
        assertTrue(loginController.checkAuthentication("Hacker2", "2Drowssap"));
    }

    /**
     * Tests whether the LoginController is getting the correct userIds.
     */
    @Test
    public void getUserId() {
        assertEquals(-1, loginController.getUserId());

        loginController.checkAuthentication("Admin2", "Password2");
        User user = userDAO.getAuthentication("Admin2", "Password2");

        assertEquals(user.getUserId(), loginController.getUserId());

        loginController.checkAuthentication("Hacker2", "2Drowssap");
        User user2 = userDAO.getAuthentication("Hacker2", "2Drowssap");

        assertNotEquals(user.getUserId(), loginController.getUserId());
        assertEquals(user2.getUserId(), loginController.getUserId());
    }
}