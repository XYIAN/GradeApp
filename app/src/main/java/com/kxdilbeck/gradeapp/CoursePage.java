package com.kxdilbeck.gradeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        RecyclerView recyclerView = findViewById(R.id.courseRecycler);
        List<String[]> mGrades = new ArrayList<>();
        for(int i = 0 ; i < cList.size(); i++){
            mGrades.add(gradeController.getCourseGrade(uId, cList.get(i).getCourseId()));
        }
        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter adapter= new CourseAdapt(cList, mGrades, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(rLayoutManager);

        //add course button
        Button addCourse = findViewById(R.id.addCourseButton);
        addCourse.setOnClickListener((View.OnClickListener) this);
        //return to login button
        Button Logout = findViewById(R.id.back_login);
        Logout.setOnClickListener((View.OnClickListener) this);
    }

//    @Override
//    public void onClick(View view)
//    {
//        switch (view.getId()) {
//            case R.id.addCourseButton:
//        }
//    }
}//END COURSE PAGE

    public static Intent getIntent(Context context){

        return new Intent(context, CoursePage.class);
    }

    Prepopulate prepopulate(RecyclerView courses) {
        RecyclerView courseView = (RecyclerView) findViewById(R.id.courseRecycler);
        return null;
    }







}