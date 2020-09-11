package com.kxdilbeck.gradeapp.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AssignmentTest {
    List<Assignment> assignments;

    @Before
    public void setUp() throws Exception {
        assignments = new ArrayList<>();

        assignments.add(new Assignment(12, 4, "8/12/20", "8/06/20", 65.0, 65.0, "good job"));
        assignments.get(0).setAssignmentId(0);
        assignments.add(new Assignment(12, 4, "8/12/20", "8/06/20", 10.0, 65.0, "bad job"));
        assignments.get(1).setAssignmentId(1);
        assignments.add(new Assignment(14, 2, "8/12/20", "8/06/20", 65.0, 75.0, "Review lecture"));
        assignments.get(2).setAssignmentId(2);
        assignments.add(new Assignment(14, 2, "8/12/20", "8/06/20", 70.0, 75.0, "Forgot Question n"));
        assignments.get(3).setAssignmentId(3);
        assignments.add(new Assignment(16, 1, "8/12/20", "8/06/20", 0.0, 30.0, "no submission"));
        assignments.get(4).setAssignmentId(4);
    }

    @After
    public void tearDown() throws Exception {
        assignments = null;
    }

    @Test
    public void getAssignmentId() {
        for(int i = 0; i <assignments.size(); i++){
            assertEquals(i, assignments.get(i).getAssignmentId());
        }
    }

    @Test
    public void setAssignmentId() {
        for(int i = 0; i <assignments.size(); i++){
            assignments.get(i).setAssignmentId(i*2);
        }

        for(int i = 0; i <assignments.size(); i++){
            assertEquals(i*2, assignments.get(i).getAssignmentId());
        }
    }

    @Test
    public void getCourseId() {
        assertEquals(12, assignments.get(0).getCourseId());
        assertEquals( assignments.get(0).getCourseId(),  assignments.get(1).getCourseId());
        assertEquals( assignments.get(2).getCourseId(),  assignments.get(3).getCourseId());
        assertEquals( 16,  assignments.get(4).getCourseId());
    }

    @Test
    public void getCategoryId() {
        assertEquals(4, assignments.get(0).getCategoryId());
        assertEquals( assignments.get(0).getCategoryId(),  assignments.get(1).getCategoryId());
        assertEquals( assignments.get(2).getCategoryId(),  assignments.get(3).getCategoryId());
        assertEquals( 1,  assignments.get(4).getCategoryId());
    }

    @Test
    public void getDueDate() {
        assertEquals("8/12/20", assignments.get(0).getDueDate());
        assertEquals(assignments.get(0).getDueDate(), assignments.get(1).getDueDate());
        assertEquals(assignments.get(2).getDueDate(), assignments.get(3).getDueDate());
    }

    @Test
    public void setDueDate() {
        assignments.get(0).setDueDate("8/14/20");
        assertEquals("8/14/20", assignments.get(0).getDueDate());
        assignments.get(4).setDueDate("8/15/20");
        assertNotEquals("8/12/20", assignments.get(4).getDueDate());
    }

    @Test
    public void getAssignedDate() {
        assertEquals("8/06/20", assignments.get(0).getAssignedDate());
        assertEquals(assignments.get(0).getAssignedDate(), assignments.get(1).getAssignedDate());
        assertEquals(assignments.get(2).getAssignedDate(), assignments.get(3).getAssignedDate());
    }

    @Test
    public void setAssignedDate() {
        assignments.get(0).setAssignedDate("8/14/20");
        assertEquals("8/14/20", assignments.get(0).getAssignedDate());
        assignments.get(4).setAssignedDate("8/15/20");
        assertNotEquals("8/12/20", assignments.get(4).getAssignedDate());
    }

    @Test
    public void getEarnedScore() {
        assertEquals(65.0, assignments.get(0).getEarnedScore(), 0.01);
    }

    @Test
    public void setEarnedScore() {
        assignments.get(0).setEarnedScore(62.0);
        assertEquals(62.0, assignments.get(0).getEarnedScore(), 0.01);
    }

    @Test
    public void getMaxScore() {
        assertEquals(65.0, assignments.get(0).getMaxScore(), 0.01);
    }

    @Test
    public void setMaxScore() {
        assignments.get(0).setMaxScore(665.0);
        assertEquals(665.0, assignments.get(0).getMaxScore(), 0.01);
    }

    @Test
    public void getDetails() {
        assertEquals("good job", assignments.get(0).getDetails());
    }

    @Test
    public void setDetails() {
        assignments.get(0).setDetails("bad job");
        assertEquals("bad job", assignments.get(0).getDetails());
    }
}