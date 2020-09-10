package com.kxdilbeck.gradeapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;

@Entity(tableName = AppDatabase.GRADE_CATEGORY_TABLE)
public class GradeCategory {
    @PrimaryKey(autoGenerate = true)
    private int mGradeCategoryId;

    public int getGradeCategoryId() {
        return mGradeCategoryId;
    }

    public void setGradeCategoryId(int mGradeCategoryId) {
        this.mGradeCategoryId = mGradeCategoryId;
    }
}
