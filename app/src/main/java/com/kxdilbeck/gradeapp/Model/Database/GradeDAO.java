package com.kxdilbeck.gradeapp.Model.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kxdilbeck.gradeapp.Model.*;

import java.util.List;

/**
 * Interface for grade table
 */
@Dao
public interface GradeDAO {
    @Insert
    List<Long> insert(Grade ... grades);

    @Update
    void update(Grade grade);

    @Delete
    void delete(Grade grade);

    @Query("Select * From " + AppDatabase.GRADE_TABLE)
    List<Grade> getAllGrades();

    /**
     * Gets all the grade category weights for a course
     * @param courseId
     * @param userId
     * @return List<Double>
     */
    @Query("SELECT mWeight FROM " + AppDatabase.GRADE_TABLE + " NATURAL JOIN " +
            AppDatabase.GRADE_CATEGORY_TABLE + " NATURAL JOIN " + AppDatabase.ASSIGNMENT_TABLE
            + " WHERE mCourseId = :courseId AND mStudentId = :userId" +
            " GROUP BY Grade.mGradeCategoryId")
    List<Double> getAllWeightsForCourse(int courseId, int userId);

    /**
     * Gets all the grade categories for a course.
     * @param courseId
     * @param userId
     * @return List<Integer>
     */
    @Query("SELECT DISTINCT mGradeCategoryId FROM " + AppDatabase.GRADE_TABLE + " NATURAL JOIN " +
            AppDatabase.ASSIGNMENT_TABLE + " WHERE mCourseId = :courseId AND mStudentId = :userId ")
    List<Integer> getGradeCategoriesForCourse(int courseId, int userId);
}
