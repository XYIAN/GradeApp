package com.kxdilbeck.project1.Model.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kxdilbeck.project1.Model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DBNAME = "db-grade-tracker";
    public static final String USER_TABLE = "User";

    public abstract UserDAO getUserDAO();

}
