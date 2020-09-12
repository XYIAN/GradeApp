package com.kxdilbeck.gradeapp.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EnrollmentTest {
    List<Enrollment> enrollments;

    @Before
    public void setup(){
        enrollments = new ArrayList<>();

        enrollments.add(new Enrollment(0, 0, "8/08/08"));
        enrollments.get(0).setEnrollmentId(0);
        enrollments.add(new Enrollment(0, 1, "8/08/08"));
        enrollments.get(1).setEnrollmentId(1);
        enrollments.add(new Enrollment(1, 1, "8/08/08"));
        enrollments.get(2).setEnrollmentId(2);
    }

    @After
    public void teardown(){
        enrollments = null;
    }

    @Test
    public void getEnrollmentId() {
        for(int i = 0; i < enrollments.size(); i++){
            assertEquals(i, enrollments.get(i).getEnrollmentId());
        }
    }

    @Test
    public void setEnrollmentId() {
        for(int i = 0; i < enrollments.size(); i++){
            enrollments.get(i).setEnrollmentId(i*2);
        }

        for(int i = 0; i < enrollments.size(); i++){
            assertEquals(i*2, enrollments.get(i).getEnrollmentId());
        }
    }

    @Test
    public void getStudentId() {
        assertEquals(0, enrollments.get(0).getStudentId());
        assertEquals(enrollments.get(0).getStudentId(), enrollments.get(1).getStudentId());
    }

    @Test
    public void getCourseId() {
        assertEquals(0, enrollments.get(0).getCourseId());
        assertEquals(enrollments.get(1).getCourseId(), enrollments.get(2).getCourseId());
    }

    @Test
    public void getEnrollDate() {
        assertEquals("8/08/08", enrollments.get(0).getEnrollDate());
        assertEquals(enrollments.get(0).getEnrollDate(), enrollments.get(1).getEnrollDate());
    }

    @Test
    public void setEnrollDate() {
        enrollments.get(0).setEnrollDate("05/05/05");
        assertEquals("05/05/05", enrollments.get(0).getEnrollDate());
    }
}