package com.kxdilbeck.gradeapp.Model.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kxdilbeck.gradeapp.Model.Course;

import java.util.Dictionary;

@Dao
public interface CourseDAO {
    @Insert
    void insert(Course ... courses);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

}

