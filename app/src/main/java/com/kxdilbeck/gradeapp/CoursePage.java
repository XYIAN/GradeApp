package com.kxdilbeck.gradeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/*

 */
public class CoursePage extends AppCompatActivity {
    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_page_main);
    }

    TextView courses = (TextView) findViewById(R.id.CourseDisplay);



}