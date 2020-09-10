package com.kxdilbeck.gradeapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;

import java.util.Objects;

@Entity(tableName = AppDatabase.GRADE_CATEGORY_TABLE)
public class GradeCategory {
    @PrimaryKey(autoGenerate = true)
    private int mGradeCategoryId;
    private String mTitle;
    private int mGradeId;
    private double mWeight;

    public GradeCategory(String mTitle, int mGradeId, double mWeight) {
        this.mTitle = mTitle;
        this.mGradeId = mGradeId;
        this.mWeight = mWeight;
    }

    public int getGradeCategoryId() {
        return mGradeCategoryId;
    }

    public void setGradeCategoryId(int mGradeCategoryId) {
        this.mGradeCategoryId = mGradeCategoryId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getGradeId() {
        return mGradeId;
    }

    public void setGradeId(int mGradeId) {
        this.mGradeId = mGradeId;
    }

    public double getWeight() {
        return mWeight;
    }

    public void setWeight(double mWeight) {
        this.mWeight = mWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeCategory that = (GradeCategory) o;
        return mGradeCategoryId == that.mGradeCategoryId &&
                mGradeId == that.mGradeId &&
                Double.compare(that.mWeight, mWeight) == 0 &&
                mTitle.equals(that.mTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mGradeCategoryId, mTitle, mGradeId, mWeight);
    }
}
