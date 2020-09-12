package com.kxdilbeck.gradeapp.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CourseTest {
    private  List<Course> courses;

    @Before
    public void setUp() throws Exception {
       courses = new ArrayList<>();

       courses.add(new Course("Dr. teacher", "BIO", "title here", "12/12/12", "12/13/12"));
       courses.get(0).setCourseId(0);
       courses.add(new Course("Dr. teacher", "SCIENCE", "title here", "12/12/12", "12/13/12"));
       courses.get(1).setCourseId(1);
       courses.add(new Course("Dr. professor", "HISTORY", "title here", "12/12/12", "12/13/12"));
       courses.get(2).setCourseId(2);
       courses.add(new Course("Dr. pro", "ANATOMY", "title here", "12/12/15", "12/13/12"));
       courses.get(3).setCourseId(3);
       courses.add(new Course("Dr. it", "CS", "title here", "12/12/15", "12/13/12"));
       courses.get(4).setCourseId(4);
    }

    @After
    public void tearDown() throws Exception {
        courses = null;
    }

    @Test
    public void getCourseId() {
        for(int i = 0; i < courses.size(); i++){
            assertEquals(i, courses.get(i).getCourseId());
        }
    }

    @Test
    public void setCourseId() {
        for(int i = 0; i < courses.size(); i++){
            courses.get(i).setCourseId(i*2);
        }

        for(int i = 0; i < courses.size(); i++){
            assertEquals(i*2, courses.get(i).getCourseId());
        }
    }

    @Test
    public void getInstructor() {
        assertEquals("Dr. teacher", courses.get(0).getInstructor());
        assertEquals(courses.get(0).getInstructor(), courses.get(0).getInstructor());
    }

    @Test
    public void setInstructor() {
        courses.get(0).setInstructor("Dr. newTeacher");
        assertNotEquals(courses.get(1).getInstructor(), courses.get(0).getInstructor());
        assertEquals("Dr. newTeacher", courses.get(0).getInstructor());
    }

    @Test
    public void getTitle() {
        assertEquals("BIO", courses.get(0).getTitle());
        assertEquals("SCIENCE", courses.get(1).getTitle());
    }

    @Test
    public void setTitle() {
        courses.get(0).setTitle("BIOLOGY");
        assertNotEquals("BIO", courses.get(0).getTitle());
        assertEquals("BIOLOGY", courses.get(0).getTitle());
    }

    @Test
    public void getDescription() {
        assertEquals("title here", courses.get(0).getDescription());
        assertEquals("title here", courses.get(1).getDescription());
    }

    @Test
    public void setDescription() {
        courses.get(0).setDescription("New Description");

        assertNotEquals("title here", courses.get(0).getDescription());
        assertEquals("New Description", courses.get(0).getDescription());
    }

    @Test
    public void getStartDate() {
        assertEquals("12/12/12", courses.get(0).getStartDate());
    }

    @Test
    public void setStartDate() {
        courses.get(0).setStartDate("12/05/12");
        assertEquals("12/05/12", courses.get(0).getStartDate());
    }

    @Test
    public void getEndDate() {
        assertEquals("12/13/12", courses.get(0).getEndDate());
    }

    @Test
    public void setEndDate() {
        courses.get(0).setEndDate("12/05/12");
        assertEquals("12/05/12", courses.get(0).getEndDate());
    }
}