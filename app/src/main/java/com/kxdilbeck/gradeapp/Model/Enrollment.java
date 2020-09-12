package com.kxdilbeck.gradeapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;

import java.util.Objects;

@Entity(tableName = AppDatabase.ENROLLMENT_TABLE)
public class Enrollment {
    @PrimaryKey(autoGenerate = true)
    private int mEnrollmentId;
    private int mStudentId;
    private int mCourseId;
    private String mEnrollDate;

    public Enrollment(int mStudentId, int mCourseId, String mEnrollDate) {
        this.mStudentId = mStudentId;
        this.mCourseId = mCourseId;
        this.mEnrollDate = mEnrollDate;
    }

    public int getEnrollmentId() {
        return mEnrollmentId;
    }

    public void setEnrollmentId(int mEnrollmentId) {
        this.mEnrollmentId = mEnrollmentId;
    }

    public int getStudentId() {
        return mStudentId;
    }

    public int getCourseId() {
        return mCourseId;
    }

    public String getEnrollDate() {
        return mEnrollDate;
    }

    public void setEnrollDate(String mEnrollDate) {
        this.mEnrollDate = mEnrollDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return mEnrollmentId == that.mEnrollmentId &&
                mStudentId == that.mStudentId &&
                mCourseId == that.mCourseId &&
                mEnrollDate.equals(that.mEnrollDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mEnrollmentId, mStudentId, mCourseId, mEnrollDate);
    }
}
