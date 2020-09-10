package com.kxdilbeck.gradeapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;

@Entity(tableName = AppDatabase.ENROLLMENT_TABLE)
public class Enrollment {
    @PrimaryKey(autoGenerate = true)
    private int mEnrollmentId;

    public int getEnrollmentId() {
        return mEnrollmentId;
    }

    public void setEnrollmentId(int mEnrollmentId) {
        this.mEnrollmentId = mEnrollmentId;
    }
}
