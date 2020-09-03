package com.kxdilbeck.project1.Model.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kxdilbeck.project1.Model.User;

@Dao
public interface UserDAO {
    @Insert
    void insert(User... user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUsername = :username AND mPassword = :password")
    User getAuthentication(String username, String password);
}
