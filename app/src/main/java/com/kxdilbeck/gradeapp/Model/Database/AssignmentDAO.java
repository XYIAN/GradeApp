package com.kxdilbeck.gradeapp.Model.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kxdilbeck.gradeapp.Model.Assignment;

import java.util.List;

@Dao
public interface AssignmentDAO {
    @Insert
    List<Long> insert(Assignment ... assignments);

    @Update
    void update(Assignment assignment);

    @Delete
    void delete(Assignment assignment);

    @Query("Select * FROM " + AppDatabase.ASSIGNMENT_TABLE)
    List<Assignment> getAllAssignments();

    @Query("SELECT SUM(Assignment.mEarnedScore) FROM " + AppDatabase.ASSIGNMENT_TABLE + " NATURAL JOIN " +
            AppDatabase.GRADE_TABLE + " WHERE mCourseId = :courseId AND mStudentId = :userId" +
            " GROUP BY Grade.mGradeCategoryId")
    List<Double> getSumOfEarnedPointsByCourse(int courseId, int userId);

    @Query("SELECT SUM(Assignment.mMaxScore) FROM " + AppDatabase.ASSIGNMENT_TABLE + " NATURAL JOIN " +
            AppDatabase.GRADE_TABLE + " WHERE mCourseId = :courseId AND mStudentId = :userId" +
            " GROUP BY Grade.mGradeCategoryId")
    List<Double> getSumOfMaxPointsByCourse(int courseId, int userId);
}
