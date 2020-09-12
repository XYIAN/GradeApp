package com.kxdilbeck.gradeapp.Model.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kxdilbeck.gradeapp.Model.Assignment;

@Dao
public interface AssignmentDAO {
    @Insert
    void insert(Assignment ... assignments);

    @Update
    void update(Assignment assignment);

    @Delete
    void delete(Assignment assignment);
}
