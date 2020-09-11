package com.kxdilbeck.gradeapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;

import java.util.Objects;

@Entity(tableName = AppDatabase.ASSIGNMENT_TABLE)
public class Assignment {
    @PrimaryKey(autoGenerate = true)
    private int mAssignmentId;
    private int mCourseId;
    private int mCategoryId;
    private String mDueDate;
    private String mAssignedDate;
    private Double mEarnedScore;
    private Double mMaxScore;
    private String mDetails;

    public Assignment(int mCourseId, int mCategoryId, String mDueDate, String mAssignedDate, Double mEarnedScore, Double mMaxScore, String mDetails) {
        this.mCourseId = mCourseId;
        this.mCategoryId = mCategoryId;
        this.mDueDate = mDueDate;
        this.mAssignedDate = mAssignedDate;
        this.mEarnedScore = mEarnedScore;
        this.mMaxScore = mMaxScore;
        this.mDetails = mDetails;
    }

    public int getAssignmentId() {
        return mAssignmentId;
    }

    public void setAssignmentId(int mAssignmentId) {
        this.mAssignmentId = mAssignmentId;
    }

    public int getCourseId() {
        return mCourseId;
    }

    public int getCategoryId() {
        return mCategoryId;
    }

    public String getDueDate() {
        return mDueDate;
    }

    public void setDueDate(String mDueDate) {
        this.mDueDate = mDueDate;
    }

    public String getAssignedDate() {
        return mAssignedDate;
    }

    public void setAssignedDate(String mAssignedDate) {
        this.mAssignedDate = mAssignedDate;
    }

    public Double getEarnedScore() {
        return mEarnedScore;
    }

    public void setEarnedScore(Double mEarnedScore) {
        this.mEarnedScore = mEarnedScore;
    }

    public Double getMaxScore() {
        return mMaxScore;
    }

    public void setMaxScore(Double mMaxScore) {
        this.mMaxScore = mMaxScore;
    }

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String mDetails) {
        this.mDetails = mDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return mAssignmentId == that.mAssignmentId &&
                mCourseId == that.mCourseId &&
                mCategoryId == that.mCategoryId &&
                Objects.equals(mDueDate, that.mDueDate) &&
                Objects.equals(mAssignedDate, that.mAssignedDate) &&
                Objects.equals(mEarnedScore, that.mEarnedScore) &&
                Objects.equals(mMaxScore, that.mMaxScore) &&
                Objects.equals(mDetails, that.mDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mAssignmentId, mCourseId, mCategoryId, mDueDate, mAssignedDate, mEarnedScore, mMaxScore, mDetails);
    }
}
