package com.kxdilbeck.gradeapp.Model.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kxdilbeck.gradeapp.Model.Course;

import java.util.List;

/**
 * Interface for the course table.
 */
@Dao
public interface CourseDAO {
    @Insert
    List<Long> insert(Course ... courses);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

    /**
     * Gets a course by id.
     * @param courseId
     * @return Course
     */
    @Query("SELECT * FROM " + AppDatabase.COURSE_TABLE + " WHERE mCourseId = :courseId")
    Course getCourse(int courseId);

    /**
     * Gets all courses.
     * @return List<Course>
     */
    @Query("SELECT * FROM " + AppDatabase.COURSE_TABLE)
    List<Course> getAllCourses();

    /**
     * Gets all courses that a user is enrolled in.
     * @param studentId
     * @return
     */
    @Query("SELECT Course.* FROM " + AppDatabase.COURSE_TABLE + " NATURAL JOIN " + AppDatabase.ENROLLMENT_TABLE + " WHERE Enrollment.mStudentId = :studentId")
    List<Course> getAllEnrolledCourses(int studentId);
}

