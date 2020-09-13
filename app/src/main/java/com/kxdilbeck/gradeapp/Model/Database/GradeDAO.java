package com.kxdilbeck.gradeapp.Model.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kxdilbeck.gradeapp.Model.Grade;

import java.util.List;

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
}
