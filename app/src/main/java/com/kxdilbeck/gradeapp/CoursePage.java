package com.kxdilbeck.gradeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kxdilbeck.gradeapp.Controller.CourseController;
import com.kxdilbeck.gradeapp.Controller.GradeController;
import com.kxdilbeck.gradeapp.Model.Course;
import com.kxdilbeck.gradeapp.Model.CourseAdapt;
import com.kxdilbeck.gradeapp.Model.Grade;
import com.kxdilbeck.gradeapp.Model.GradeCategory;
import com.kxdilbeck.gradeapp.View.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/*

 */
public class CoursePage extends AppCompatActivity {
    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_page_main);
        int uId = getSharedPreferences(LoginActivity.CREDENTIALS, MODE_PRIVATE).getInt("USERID", -1);
        CourseController cController = new CourseController(getApplicationContext());
        List<Course> cList = cController.getAllCourses(uId);
        GradeController gradeController = new GradeController(getApplicationContext());
        RecyclerView recyclerView;
        List<String[]> mGrades = new ArrayList<>();
        for(int i = 0 ; i < cList.size(); i++){
            mGrades.add(gradeController.getCourseGrade(uId, cList.get(i).getCourseId()));
        }
        RecyclerView.LayoutManager rLayoutManager;
        RecyclerView.Adapter adapter= new CourseAdapt(cList, mGrades, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public static Intent getIntent(Context context){
        return new Intent(context, CoursePage.class);
    }

    Prepopulate prepopulate(RecyclerView courses) {
        RecyclerView courseView = (RecyclerView) findViewById(R.id.courseRecycler);
        return null;
    }






}