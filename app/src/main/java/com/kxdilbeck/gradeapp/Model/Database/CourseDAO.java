package com.kxdilbeck.gradeapp.Model.Database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kxdilbeck.gradeapp.Model.Course;

import java.util.Dictionary;
import java.util.List;

@Dao
public interface CourseDAO {
    @Insert
    List<Long> insert(Course ... courses);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM " + AppDatabase.COURSE_TABLE + " WHERE mCourseId = :courseId")
    Course getCourse(int courseId);

    @Query("SELECT * FROM " + AppDatabase.COURSE_TABLE)
    List<Course> getAllCourses();
}

