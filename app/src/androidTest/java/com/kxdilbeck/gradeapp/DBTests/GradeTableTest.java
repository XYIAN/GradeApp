package com.kxdilbeck.gradeapp.DBTests;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.GradeCategoryDAO;
import com.kxdilbeck.gradeapp.Model.Database.GradeDAO;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.Grade;
import com.kxdilbeck.gradeapp.Model.GradeCategory;
import com.kxdilbeck.gradeapp.Model.User;
import com.kxdilbeck.gradeapp.Prepopulate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GradeTableTest {
    private AppDatabase db;
    private GradeCategoryDAO gradeCategoryDAO;
    private GradeDAO gradeDAO;
    private UserDAO userDAO;

    @Before
    public void setUp() throws Exception{
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries().build();

        Prepopulate.prepopulate(db);
        gradeDAO = db.getGradeDAO();
        gradeCategoryDAO = db.getGradeCategoryDAO();
        userDAO = db.getUserDAO();
    }

    @After
    public void tearDown() throws Exception{
        db.close();
        gradeDAO = null;
        gradeCategoryDAO = null;
        userDAO = null;
    }

    @Test
    public void insert(){
        List<User> users = userDAO.getAllUsers();
        List<GradeCategory> gradeCategories = gradeCategoryDAO.getAllCategories();
        Grade grade = new Grade(25.0, users.get(0).getUserId(),  gradeCategories.get(0).getGradeCategoryId());

        // id is auto incrementing primary key so its value is not known until after being inserted.
        int sizeBefore = gradeDAO.getAllGrades().size();
        grade.setGradeId(gradeDAO.insert(grade).get(0).intValue());
        List<Grade> grades = gradeDAO.getAllGrades();

        assertEquals(sizeBefore+1, grades.size());
        assertTrue(grades.contains(grade));
    }

    @Test
    public void update(){
        List<Grade> grades = gradeDAO.getAllGrades();
        Grade grade = new Grade(grades.get(0).getScore(), grades.get(0).getStudentId(), grades.get(0).getGradeCategoryId());
        grade.setGradeId(gradeDAO.insert(grade).get(0).intValue());
        grades.get(0).setScore(24.0);

        assertNotEquals(grade, grades.get(0));
        assertEquals(24.0, grades.get(0).getScore(), 0.01);
    }

    @Test
    public void delete(){
        List<Grade> grades = gradeDAO.getAllGrades();

        int sizeBefore = gradeDAO.getAllGrades().size();
        gradeDAO.delete(grades.get(0));
        gradeDAO.delete(grades.get(1));

        assertNotEquals(gradeDAO.getAllGrades().size(), grades.size());
        assertEquals(sizeBefore - 2, gradeDAO.getAllGrades().size());
    }

}