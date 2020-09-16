package com.kxdilbeck.gradeapp;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrepopulateTest {
    private AppDatabase db;

    @Test
    public void prepopulate() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries().build();

        Prepopulate.prepopulate(db);

        assertTrue(db.getCourseDAO().getAllCourses().size() >= 7);
        assertTrue(db.getEnrollmentDAO().getAllEnrollments().size() >= 11);
        assertTrue(db.getUserDAO().getAllUsers().size() >= 5);
        assertTrue(db.getGradeCategoryDAO().getAllCategories().size() >= 5);
        assertTrue(db.getGradeDAO().getAllGrades().size() >= 10);
        assertTrue(db.getAssignmentDAO().getAllAssignments().size() >= 10);
    }
}