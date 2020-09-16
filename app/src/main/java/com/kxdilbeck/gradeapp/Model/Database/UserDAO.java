package com.kxdilbeck.gradeapp.Model.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    List<Long> insert(User... user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUsername = :username AND mPassword = :password")
    User getAuthentication(String username, String password);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<User> getAllUsers();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " Where mUsername = :username")
    User getUserByUsername(String username);

}