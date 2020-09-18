package com.kxdilbeck.gradeapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;

import java.util.Objects;

/**
 * This class represents a course row in the course table.
 */
@Entity(tableName = AppDatabase.COURSE_TABLE)
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int  mCourseId;
    private String mInstructor;
    private String mTitle;
    private String mDescription;
    private String mStartDate;
    private String mEndDate;

    public Course(String mInstructor, String mTitle, String mDescription, String mStartDate, String mEndDate) {
        this.mInstructor = mInstructor;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
    }

    public int getCourseId() {
        return mCourseId;
    }

    public void setCourseId(int mCourseId) {
        this.mCourseId = mCourseId;
    }

    public String getInstructor() {
        return mInstructor;
    }

    public void setInstructor(String mInstructor) {
        this.mInstructor = mInstructor;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String mStartDate) {
        this.mStartDate = mStartDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String mEndDate) {
        this.mEndDate = mEndDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return mCourseId == course.mCourseId &&
                Objects.equals(mInstructor, course.mInstructor) &&
                Objects.equals(mTitle, course.mTitle) &&
                Objects.equals(mDescription, course.mDescription) &&
                Objects.equals(mStartDate, course.mStartDate) &&
                Objects.equals(mEndDate, course.mEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mCourseId, mInstructor, mTitle, mDescription, mStartDate, mEndDate);
    }
}
