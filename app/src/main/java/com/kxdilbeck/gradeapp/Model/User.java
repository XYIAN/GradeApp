package com.kxdilbeck.gradeapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;

import java.util.Objects;


/**
 * This class represents a row in the user table.
 */
@Entity(tableName = AppDatabase.USER_TABLE)
public class User {
    @PrimaryKey(autoGenerate = true)
    private int mUserId;
    private String mUsername;
    private String mPassword;
    private String mFirstName;
    private String mLastName;
    private int mAccessLevel;

    public User(String mUsername, String mPassword, String mFirstName, String mLastName, int mAccessLevel) {
        this.mUsername = mUsername;
        this.mPassword = mPassword;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mAccessLevel = mAccessLevel;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public String getUsername(){
        return mUsername;
    }

    public void setUsername(String mUsername){
        this.mUsername = mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public int getAccessLevel() {
        return mAccessLevel;
    }

    public void setAccessLevel(int mAccessLevel) {
        this.mAccessLevel = mAccessLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return mUserId == user.mUserId &&
                mAccessLevel == user.mAccessLevel &&
                Objects.equals(mUsername, user.mUsername) &&
                Objects.equals(mPassword, user.mPassword) &&
                Objects.equals(mFirstName, user.mFirstName) &&
                Objects.equals(mLastName, user.mLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mUserId, mUsername, mPassword, mFirstName, mLastName, mAccessLevel);
    }

    @Override
    public String toString() {
        return "User{" +
                "mUserId=" + mUserId +
                ", mUsername='" + mUsername + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mAccessLevel=" + mAccessLevel +
                '}';
    }
}
