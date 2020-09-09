package com.kxdilbeck.gradeapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;

import java.util.Objects;

@Entity(tableName = AppDatabase.GRADE_TABLE)
public class Grade {
    @PrimaryKey(autoGenerate = true)
    private int mGradeId;
    private int mScore;
    private int mAssignmentId;
    private int mStudentId;
    private int mCourseId;

    public Grade(int mScore, int mAssignmentId, int mStudentId, int mCourseId) {
        this.mScore = mScore;
        this.mAssignmentId = mAssignmentId;
        this.mStudentId = mStudentId;
        this.mCourseId = mCourseId;
    }

    public int getGradeId() {
        return mGradeId;
    }

    public void setGradeId(int mGradeId) {
        this.mGradeId = mGradeId;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int mScore) {
        this.mScore = mScore;
    }

    public int getAssignmentId() {
        return mAssignmentId;
    }

    public void setAssignmentId(int mAssignmentId) {
        this.mAssignmentId = mAssignmentId;
    }

    public int getStudentId() {
        return mStudentId;
    }

    public void setStudentId(int mStudentId) {
        this.mStudentId = mStudentId;
    }

    public int getCourseId() {
        return mCourseId;
    }

    public void setCourseId(int mCourseId) {
        this.mCourseId = mCourseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return mGradeId == grade.mGradeId &&
                mScore == grade.mScore &&
                mAssignmentId == grade.mAssignmentId &&
                mStudentId == grade.mStudentId &&
                mCourseId == grade.mCourseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mGradeId, mScore, mAssignmentId, mStudentId, mCourseId);
    }
}
