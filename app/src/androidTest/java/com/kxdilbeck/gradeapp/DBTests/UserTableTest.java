package com.kxdilbeck.gradeapp.DBTests;

import android.app.Instrumentation;
import android.content.Context;
import android.util.Log;

import androidx.room.Query;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.User;
import com.kxdilbeck.gradeapp.Prepopulate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class UserTableTest {
    private AppDatabase db;
    private UserDAO userDAO;

    @Before
    public void setUp() throws Exception{
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries().build();

        Prepopulate.prepopulate(db);
        userDAO = db.getUserDAO();
    }

    @After
    public void tearDown() throws Exception{
        db.close();
    }

    @Test
    public void insert(){
        User one = new User("JohnSmith99", "password","John", "Smith", 1);
        User two = new User("JaneSmith99", "password","Jane", "Smith", 1);
        User three = new User("rambo99", "enter","Rambo", "NoNeed", 3);

        // id is auto incrementing primary key so its value is not known until after being inserted.
        one.setUserId(userDAO.insert(one).get(0).intValue());
        two.setUserId(userDAO.insert(two).get(0).intValue());
        three.setUserId(userDAO.insert(three).get(0).intValue());

        List<User> users = userDAO.getAllUsers();

        assertEquals(8, users.size());
        assertTrue(users.contains(one));
        assertTrue(users.contains(two));
        assertTrue(users.contains(three));
    }

    @Test
    public void update(){
        List<User> users = userDAO.getAllUsers();
        User user = new User(users.get(0).getUsername(), users.get(0).getPassword(), users.get(0).getFirstName(), users.get(0).getLastName(), users.get(0).getAccessLevel());

        users.get(0).setUsername("Adam2");
        users.get(0).setPassword("NewPassword");
        userDAO.update(users.get(0));

        assertNotEquals(user, users.get(0));
        assertEquals("Adam2", users.get(0).getUsername());
        assertEquals("NewPassword", users.get(0).getPassword());
    }

    @Test
    public void delete(){
        List<User> users = userDAO.getAllUsers();

        userDAO.delete(users.get(0));
        userDAO.delete(users.get(1));
        userDAO.delete(users.get(2));

        assertNotEquals(userDAO.getAllUsers().size(), users.size());
        assertEquals(2, userDAO.getAllUsers().size());
    }

    @Test
    public void getAuthentication(){
        List<User> users = userDAO.getAllUsers();

        assertEquals(users.get(0), userDAO.getAuthentication(users.get(0).getUsername(), users.get(0).getPassword()));
        assertNotEquals(users.get(0), userDAO.getAuthentication(users.get(0).getUsername(), "WrongPassword"));
        assertEquals(users.get(1), userDAO.getAuthentication(users.get(1).getUsername(), users.get(1).getPassword()));
    }

    @Test
    public void getAllUsers(){
        assertEquals(5, userDAO.getAllUsers().size());

        User one = new User("JohnSmith99", "password","John", "Smith", 1);
        User two = new User("JaneSmith99", "password","Jane", "Smith", 1);
        User three = new User("rambo99", "enter","Rambo", "NoNeed", 3);
        userDAO.insert(one).get(0);
        userDAO.insert(two).get(0);
        userDAO.insert(three).get(0);

        assertEquals(8,  userDAO.getAllUsers().size());
    }

    @Test
    public void getUserByUsername(){
        List<User> users = userDAO.getAllUsers();

        assertEquals(users.get(0), userDAO.getUserByUsername("Admin2"));
        assertEquals(users.get(1), userDAO.getUserByUsername("JohnS2020"));
    }


}