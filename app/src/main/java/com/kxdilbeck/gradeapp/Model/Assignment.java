package com.kxdilbeck.gradeapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;

@Entity(tableName = AppDatabase.ASSIGNMENT_TABLE)
public class Assignment {
    @PrimaryKey(autoGenerate = true)
    private int mAssignmentId;

    public int getAssignmentId() {
        return mAssignmentId;
    }

    public void setAssignmentId(int mAssignmentId) {
        this.mAssignmentId = mAssignmentId;
    }


}
