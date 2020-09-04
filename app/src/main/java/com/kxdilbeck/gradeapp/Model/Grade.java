package com.kxdilbeck.gradeapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;

@Entity(tableName = AppDatabase.GRADE_TABLE)
public class Grade {
    @PrimaryKey(autoGenerate = true)
    private int mGradeId;

    public int getGradeId() {
        return mGradeId;
    }

    public void setGradeId(int mGradeId) {
        this.mGradeId = mGradeId;
    }
}
