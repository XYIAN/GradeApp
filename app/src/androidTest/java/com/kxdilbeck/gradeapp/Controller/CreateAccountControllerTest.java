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

/**
 * Tests whether the CreateAccountController works correctly
 */
public class CreateAccountControllerTest {
    private UserDAO userDAO;
    private CreateAccountController createAccountController;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        userDAO = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build().getUserDAO();

        createAccountController = new CreateAccountController(userDAO);
    }

    @After
    public void tearDown() throws Exception {
        userDAO = null;
        createAccountController = null;
    }

    /**
     * Tests whether checkUsername method makes sure that a username has at least 1 uppercase,
     * 1 lowercase, and 1 numeric character and is at least 7 characters long.
     */
    @Test
    public void checkUsername() {
        User user = new User("Username1", "Password1", "first", "last", 0);

        assertTrue(createAccountController.checkUsername("Username1"));
        assertFalse(createAccountController.checkUsername("admin"));

        userDAO.insert(user);

        assertFalse(createAccountController.checkUsername("Username1"));
        userDAO.delete(user);
    }

    /**
     * Tests whether checkPassword method makes sure that a password has at least 1 uppercase,
     * 1 lowercase, and 1 numeric character and is at least 7 characters long.
     */
    @Test
    public void checkPassword() {
        assertTrue(createAccountController.checkPassword("Password123"));
        assertFalse(createAccountController.checkPassword("password"));
    }

    /**
     * Tests that checkFirstName method makes sure something is given for a name.
     */
    @Test
    public void checkFirstName() {
        assertFalse(createAccountController.checkFirstName(""));
        assertTrue(createAccountController.checkFirstName("first"));
    }

    /**
     * Tests that checkLastName method makes sure something is given for a name.
     */
    @Test
    public void checkLastName() {
        assertFalse(createAccountController.checkLastName(""));
        assertTrue(createAccountController.checkLastName("last"));
    }

    /**
     * Tests that the right userId is given.
     */
    @Test
    public void getUserId() {
        User user = new User("username", "Password123", "first", "last", 1);

        assertEquals(-1, createAccountController.getUserId());

        createAccountController.addUser(user);
        user.setUserId(userDAO.insert(user).get(0).intValue());

        assertEquals(user.getUserId(), createAccountController.getUserId());
    }
}