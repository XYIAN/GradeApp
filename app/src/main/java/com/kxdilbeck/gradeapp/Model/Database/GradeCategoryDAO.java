package com.kxdilbeck.gradeapp.Model.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kxdilbeck.gradeapp.Model.Grade;
import com.kxdilbeck.gradeapp.Model.GradeCategory;

@Dao
public interface GradeCategoryDAO {
    @Insert
    void insert(GradeCategory ... gradeCategories);

    @Update
    void insert(GradeCategory gradeCategory);

    @Delete
    void delete(GradeCategory gradeCategory);
}
