package com.kxdilbeck.gradeapp.DBTests;

import android.app.Instrumentation;
import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.User;

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

        // id a auto incrementing primary key so its value is not known until after being inserted.
        one.setUserId(userDAO.insert(one).get(0).intValue());
        two.setUserId(userDAO.insert(two).get(0).intValue());
        three.setUserId(userDAO.insert(three).get(0).intValue());

        List<User> users = userDAO.getAllUsers();

        assertTrue(users.contains(one));
        assertTrue(users.contains(two));
        assertTrue(users.contains(three));
    }

    @Test
    public void update(){

    }

    @Test
    public void delete(){

    }

}