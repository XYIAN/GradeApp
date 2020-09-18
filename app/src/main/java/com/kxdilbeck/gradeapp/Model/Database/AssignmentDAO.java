package com.kxdilbeck.gradeapp.Model.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kxdilbeck.gradeapp.Model.Assignment;

import java.util.List;

/**
 * Interface for the Assignment Table
 */
@Dao
public interface AssignmentDAO {
    @Insert
    List<Long> insert(Assignment ... assignments);

    @Update
    void update(Assignment assignment);

    @Delete
    void delete(Assignment assignment);

    /**
     * Gets an assignment by Id
     * @param assignmentId
     * @return
     */
    @Query("SELECT * FROM " + AppDatabase.ASSIGNMENT_TABLE + " WHERE mAssignmentId = :assignmentId")
    Assignment getAssignment(int assignmentId);

    /**
     * Gets all assignments
     * @return List<Assignment>
     */
    @Query("Select * FROM " + AppDatabase.ASSIGNMENT_TABLE)
    List<Assignment> getAllAssignments();

    /**
     * Gets all the assignments of a user for a specific category in a course.
     * @param courseId
     * @param userId
     * @param gradeCategory
     * @return List<Assignment>
     */
    @Query("SELECT Assignment.* FROM " + AppDatabase.ASSIGNMENT_TABLE + " NATURAL JOIN " +
            AppDatabase.GRADE_TABLE + " WHERE mCourseId = :courseId AND mStudentId = :userId " +
            " AND mGradeCategoryId = :gradeCategory")
    List<Assignment> getAssignmentsByCategory(int courseId, int userId, int gradeCategory);

    /**
     * Gets the sum of points earned for a course by category for a specific user.
     * @param courseId
     * @param userId
     * @return List<Double>
     */
    @Query("SELECT SUM(Assignment.mEarnedScore) FROM " + AppDatabase.ASSIGNMENT_TABLE + " NATURAL JOIN " +
            AppDatabase.GRADE_TABLE + " WHERE mCourseId = :courseId AND mStudentId = :userId" +
            " GROUP BY Grade.mGradeCategoryId")
    List<Double> getSumOfEarnedPointsByCourse(int courseId, int userId);

    /**
     * Gets the sum of max scores for a course by category for a specific user.
     * @param courseId
     * @param userId
     * @return List<Double>
     */
    @Query("SELECT SUM(Assignment.mMaxScore) FROM " + AppDatabase.ASSIGNMENT_TABLE + " NATURAL JOIN " +
            AppDatabase.GRADE_TABLE + " WHERE mCourseId = :courseId AND mStudentId = :userId" +
            " GROUP BY Grade.mGradeCategoryId")
    List<Double> getSumOfMaxPointsByCourse(int courseId, int userId);
}
