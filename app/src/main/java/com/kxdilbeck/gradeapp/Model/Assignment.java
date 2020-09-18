package com.kxdilbeck.gradeapp.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;

import java.util.Objects;

/**
 * This class represent an Assignment row in the Assignment table of the db.
 */
@Entity(tableName = AppDatabase.ASSIGNMENT_TABLE,
        foreignKeys = {
            @ForeignKey(entity = Course.class, parentColumns = "mCourseId",
                    childColumns = "mCourseId", onDelete = ForeignKey.CASCADE),
            @ForeignKey(entity = Grade.class, parentColumns = "mGradeId",
                    childColumns = "mGradeId", onDelete = ForeignKey.CASCADE)
        },

        indices = {
            @Index(value = "mCourseId"),
            @Index(value = "mGradeId")
        }
)
public class Assignment {
    @PrimaryKey(autoGenerate = true)
    private int mAssignmentId;
    private int mCourseId;
    private int mGradeId;
    private String mDueDate;
    private String mAssignedDate;
    private Double mEarnedScore;
    private Double mMaxScore;
    private String mDetails;

    public Assignment(int mCourseId, int mGradeId, String mDueDate, String mAssignedDate, Double mEarnedScore, Double mMaxScore, String mDetails) {
        this.mCourseId = mCourseId;
        this.mGradeId = mGradeId;
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

    public int getGradeId() {
        return mGradeId;
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
                mGradeId == that.mGradeId &&
                Objects.equals(mDueDate, that.mDueDate) &&
                Objects.equals(mAssignedDate, that.mAssignedDate) &&
                Objects.equals(mEarnedScore, that.mEarnedScore) &&
                Objects.equals(mMaxScore, that.mMaxScore) &&
                Objects.equals(mDetails, that.mDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mAssignmentId, mCourseId, mGradeId, mDueDate, mAssignedDate, mEarnedScore, mMaxScore, mDetails);
    }
}
