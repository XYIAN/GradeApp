package com.kxdilbeck.gradeapp.Model.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kxdilbeck.gradeapp.Model.*;

import java.util.List;

@Dao
public interface GradeCategoryDAO {
    @Insert
    List<Long> insert(GradeCategory ... gradeCategories);

    @Update
    void update(GradeCategory gradeCategory);

    @Delete
    void delete(GradeCategory gradeCategory);

    @Query("Select * From " + AppDatabase.GRADE_CATEGORY_TABLE)
    List<GradeCategory> getAllCategories();
}
