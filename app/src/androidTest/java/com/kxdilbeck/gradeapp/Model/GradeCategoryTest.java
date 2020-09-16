package com.kxdilbeck.gradeapp.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GradeCategoryTest {
    List<GradeCategory> gradeCategories;

    @Before
    public void setUp() throws Exception {
        gradeCategories = new ArrayList<>();

        gradeCategories.add(new GradeCategory("Test", 50.0));
        gradeCategories.get(0).setGradeCategoryId(0);
        gradeCategories.add(new GradeCategory("Homework", 30.0));
        gradeCategories.get(1).setGradeCategoryId(1);
        gradeCategories.add(new GradeCategory("Quiz", 20.0));
        gradeCategories.get(2).setGradeCategoryId(2);
    }

    @After
    public void tearDown() throws Exception {
        gradeCategories = null;
    }

    @Test
    public void getGradeCategoryId() {
        for(int i = 0; i < gradeCategories.size(); i++){
            assertEquals(i, gradeCategories.get(i).getGradeCategoryId());
        }
    }

    @Test
    public void setGradeCategoryId() {
        for(int i = 0; i < gradeCategories.size(); i++){
            gradeCategories.get(i).setGradeCategoryId(i*2);
        }

        for(int i = 0; i < gradeCategories.size(); i++){
            assertEquals(i*2, gradeCategories.get(i).getGradeCategoryId());
        }
    }

    @Test
    public void getTitle() {
        assertEquals("Test", gradeCategories.get(0).getTitle());
        assertEquals("Quiz", gradeCategories.get(2).getTitle());
    }

    @Test
    public void setTitle() {
        gradeCategories.get(0).setTitle("Exam");
        assertEquals("Exam", gradeCategories.get(0).getTitle());
    }

    @Test
    public void getWeight() {
        assertEquals(50.0, gradeCategories.get(0).getWeight(), 0.01);
        assertEquals(30.0, gradeCategories.get(1).getWeight(), 0.01);
    }

    @Test
    public void setWeight() {
        gradeCategories.get(0).setWeight(42.0);
        assertEquals(42.0, gradeCategories.get(0).getWeight(), 0.01);
    }
}