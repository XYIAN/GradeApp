package com.kxdilbeck.gradeapp.Model.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kxdilbeck.gradeapp.Model.Grade;

@Dao
public interface GradeDAO {
    @Insert
    void insert(Grade ... grades);

    @Update
    void update(Grade grade);

    @Delete
    void delete(Grade grade);
}
