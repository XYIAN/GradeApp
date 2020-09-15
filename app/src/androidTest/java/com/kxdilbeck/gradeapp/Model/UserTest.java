package com.kxdilbeck.gradeapp.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserTest {
    private  List<User> users;

    @Before
    public void setUp() throws Exception {
        users = new ArrayList<>();

        for(int i=0; i < 5; i++){
            users.add(new User(
                    "Username" + i,
                    "password" + i,
                    "first" + i,
                    "last" + i,
                    1
            ));

            users.get(i).setUserId(i);
        }
    }

    @After
    public void tearDown() throws Exception {
        users = null;
    }

    @Test
    public void getUserId() {
        assertEquals(users.get(0).getUserId(), 0);
        assertEquals(users.get(1).getUserId(), 1);
        assertEquals(users.get(2).getUserId(), 2);
        assertEquals(users.get(3).getUserId(), 3);
        assertEquals(users.get(4).getUserId(), 4);
    }

    @Test
    public void setUserId() {
        users.get(3).setUserId(123);
        users.get(2).setUserId(14);

        assertEquals(123, users.get(3).getUserId());
        assertNotEquals(2, users.get(2).getUserId());
    }

    @Test
    public void getUsername() {
        for(int i = 0; i < 5; i++){
            assertEquals("Username"+i, users.get(i).getUsername());
        }
    }

    @Test
    public void setUsername() {
        for(int i = 0; i < 5; i++){
            users.get(i).setUsername("newUsername"+i);
        }

        for(int i = 0; i < 5; i++){
            assertEquals("newUsername"+i, users.get(i).getUsername());
        }
    }

    @Test
    public void getPassword() {
        for(int i = 0; i < 5; i++){
            assertEquals("password"+i, users.get(i).getPassword());
        }
    }

    @Test
    public void setPassword() {
        for(int i = 0; i < 5; i++){
            users.get(i).setPassword("newPassword"+i);
        }

        for(int i = 0; i < 5; i++){
            assertEquals("newPassword"+i, users.get(i).getPassword());
        }
    }

    @Test
    public void getFirstName() {
        for(int i = 0; i < 5; i++){
            assertEquals("first"+i, users.get(i).getFirstName());
        }
    }

    @Test
    public void setFirstName() {
        for(int i = 0; i < 5; i++){
            users.get(i).setFirstName("newFirst"+i);
        }

        for(int i = 0; i < 5; i++){
            assertEquals("newFirst"+i, users.get(i).getFirstName());
        }
    }

    @Test
    public void getLastName() {
        for(int i = 0; i < 5; i++){
            assertEquals("last"+i, users.get(i).getLastName());
        }
    }

    @Test
    public void setLastName() {
        for(int i = 0; i < 5; i++){
            users.get(i).setLastName("newLast"+i);
        }

        for(int i = 0; i < 5; i++){
            assertEquals("newLast"+i, users.get(i).getLastName());
        }
    }

    @Test
    public void getAccessLevel() {
        for(int i = 0; i < 5; i++){
            assertEquals(1, users.get(i).getAccessLevel());
        }
    }

    @Test
    public void setAccessLevel() {
        for(int i = 0; i < 5; i++){
            users.get(i).setAccessLevel(i*2);
        }

        for(int i = 0; i < 5; i++){
            assertEquals(i*2, users.get(i).getAccessLevel());
        }
    }
}