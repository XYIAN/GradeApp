package com.kxdilbeck.gradeapp.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GradeTest {
    private List<Grade> grades;

    @Before
    public void setUp() throws Exception {
        grades = new ArrayList<>();

        grades.add(new Grade(25.0, 0, 0));
        grades.get(0).setGradeId(0);
        grades.add(new Grade(55.0, 0, 1));
        grades.get(1).setGradeId(1);
        grades.add(new Grade(33.0, 1, 0));
        grades.get(2).setGradeId(2);
        grades.add(new Grade(22.0, 2, 2));
        grades.get(3).setGradeId(3);
    }

    @After
    public void tearDown() throws Exception {
        grades = null;
    }

    @Test
    public void getGradeId() {
        for(int i = 0; i < grades.size(); i++){
            assertEquals(i, grades.get(i).getGradeId());
        }
    }

    @Test
    public void setGradeId() {
        for(int i = 0; i < grades.size(); i++){
            grades.get(i).setGradeId(i*2);
        }

        for(int i = 0; i < grades.size(); i++){
            assertEquals(i*2, grades.get(i).getGradeId());
        }
    }

    @Test
    public void getScore() {
        assertEquals(25.0, grades.get(0).getScore(), 0.01);
    }

    @Test
    public void setScore() {
        grades.get(0).setScore(20.0);

        assertEquals(20.0, grades.get(0).getScore(), 0.01);
    }

    @Test
    public void getStudentId() {
        assertEquals(0, grades.get(0).getStudentId());
    }

    @Test
    public void setStudentId() {
        grades.get(0).setStudentId(1);
        assertEquals(1, grades.get(0).getStudentId());
    }
}