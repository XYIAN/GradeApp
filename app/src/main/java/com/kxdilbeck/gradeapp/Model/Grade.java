package com.kxdilbeck.gradeapp.Model;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;

import java.util.Objects;

@Entity(tableName = AppDatabase.GRADE_TABLE,
        foreignKeys = {
            @ForeignKey(entity = User.class, parentColumns = "mUserId",
                    childColumns = "mStudentId", onDelete = ForeignKey.CASCADE),
            @ForeignKey(entity = GradeCategory.class, parentColumns = "mGradeCategoryId",
                    childColumns = "mGradeCategoryId", onDelete = ForeignKey.SET_NULL)
        },

        indices = {
            @Index(value = "mStudentId"),
            @Index(value = "mGradeCategoryId"),
        }
)
public class Grade {
    @PrimaryKey(autoGenerate = true)
    private int mGradeId;
    @Nullable
    double mScore;
    private int mStudentId;
    @Nullable
    private int mGradeCategoryId;

    public Grade(@Nullable double mScore, int mStudentId, @Nullable int mGradeCategoryId) {
        this.mScore = mScore;
        this.mStudentId = mStudentId;
        this.mGradeCategoryId = mGradeCategoryId;
    }

    public int getGradeId() {
        return mGradeId;
    }

    public void setGradeId(int mGradeId) {
        this.mGradeId = mGradeId;
    }

    public double getScore() {
        return mScore;
    }

    public void setScore(double mScore) {
        this.mScore = mScore;
    }

    public int getStudentId() {
        return mStudentId;
    }

    public void setStudentId(int mStudentId) {
        this.mStudentId = mStudentId;
    }

    public int getGradeCategoryId() {
        return mGradeCategoryId;
    }

    public void setGradeCategoryId(int mGradeCategoryId) {
        this.mGradeCategoryId = mGradeCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return mGradeId == grade.mGradeId &&
                mScore == grade.mScore &&
                mStudentId == grade.mStudentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mGradeId, mScore, mStudentId);
    }
}
