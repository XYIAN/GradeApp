package com.kxdilbeck.gradeapp.Model.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kxdilbeck.gradeapp.Model.Enrollment;

@Dao
public interface EnrollmentDAO {
    @Insert
    void insert(Enrollment ... enrollments);

    @Update
    void update(Enrollment enrollment);

    @Delete
    void delete(Enrollment enrollment);
}
