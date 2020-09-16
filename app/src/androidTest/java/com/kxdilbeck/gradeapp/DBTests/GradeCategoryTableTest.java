package com.kxdilbeck.gradeapp.DBTests;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.kxdilbeck.gradeapp.Model.Course;
import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.CourseDAO;
import com.kxdilbeck.gradeapp.Model.Database.EnrollmentDAO;
import com.kxdilbeck.gradeapp.Model.Database.GradeCategoryDAO;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.Enrollment;
import com.kxdilbeck.gradeapp.Model.GradeCategory;
import com.kxdilbeck.gradeapp.Model.User;
import com.kxdilbeck.gradeapp.Prepopulate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GradeCategoryTableTest {
    private AppDatabase db;
    private GradeCategoryDAO gradeCategoryDAO;

    @Before
    public void setUp() throws Exception{
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries().build();

        Prepopulate.prepopulate(db);
        gradeCategoryDAO = db.getGradeCategoryDAO();
    }

    @After
    public void tearDown() throws Exception{
        db.close();
        gradeCategoryDAO = null;
    }

    @Test
    public void insert(){
        GradeCategory gradeCategory = new GradeCategory("Extra", 5.0);

        // id is auto incrementing primary key so its value is not known until after being inserted.
        int sizeBefore = gradeCategoryDAO.getAllCategories().size();
        gradeCategory.setGradeCategoryId(gradeCategoryDAO.insert(gradeCategory).get(0).intValue());
        List<GradeCategory> gradeCategories = gradeCategoryDAO.getAllCategories();

        assertEquals(sizeBefore+1, gradeCategories.size());
        assertTrue(gradeCategories.contains(gradeCategory));
    }

    @Test
    public void update(){
        List<GradeCategory> categories = gradeCategoryDAO.getAllCategories();
        GradeCategory gradeCategory = new GradeCategory(categories.get(0).getTitle(), categories.get(0).getWeight());
        gradeCategory.setGradeCategoryId(gradeCategoryDAO.insert(gradeCategory).get(0).intValue());
        categories.get(0).setTitle("Mandatory Credit");

        assertNotEquals(gradeCategory, categories.get(0));
        assertEquals("Mandatory Credit", categories.get(0).getTitle());
    }

    @Test
    public void delete(){
        List<GradeCategory> gradeCategories = gradeCategoryDAO.getAllCategories();

        int sizeBefore = gradeCategoryDAO.getAllCategories().size();
        gradeCategoryDAO.delete(gradeCategories.get(0));
        gradeCategoryDAO.delete(gradeCategories.get(1));

        assertNotEquals(gradeCategoryDAO.getAllCategories().size(), gradeCategories.size());
        assertEquals(sizeBefore - 2, gradeCategoryDAO.getAllCategories().size());
    }

}