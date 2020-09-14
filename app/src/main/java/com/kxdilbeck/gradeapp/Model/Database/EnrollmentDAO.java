package com.kxdilbeck.gradeapp.Model.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kxdilbeck.gradeapp.Model.Enrollment;

import java.util.List;

@Dao
public interface EnrollmentDAO {
    @Insert
    List<Long> insert(Enrollment ... enrollments);

    @Update
    void update(Enrollment enrollment);

    @Delete
    void delete(Enrollment enrollment);

    @Query("Select * From " + AppDatabase.ENROLLMENT_TABLE)
    List<Enrollment> getAllEnrollments();
}
