package com.kxdilbeck.gradeapp.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.kxdilbeck.gradeapp.Controller.CourseController;
import com.kxdilbeck.gradeapp.Controller.GradeController;
import com.kxdilbeck.gradeapp.CoursePage;
import com.kxdilbeck.gradeapp.Model.Course;
import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.CourseDAO;
import com.kxdilbeck.gradeapp.Model.Database.EnrollmentDAO;
import com.kxdilbeck.gradeapp.Model.Enrollment;
import com.kxdilbeck.gradeapp.R;

import java.util.List;

public class CreateCourse extends AppCompatActivity {
    private EditText mCourseName;
    private EditText mInstructor ;
    private EditText sDate;
    private EditText eDate;
    private CourseDAO courseDAO;
    private EnrollmentDAO eDAO;
    private int uId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createcourse);
        uId = getSharedPreferences(LoginActivity.CREDENTIALS, MODE_PRIVATE).getInt("USERID", -1);
        CourseController cController = new CourseController(getApplicationContext());
        courseDAO = AppDatabase.getDb(getApplicationContext()).getCourseDAO();
        eDAO = AppDatabase.getDb(getApplicationContext()).getEnrollmentDAO();
        mCourseName = findViewById(R.id.courseName);
        mInstructor = findViewById(R.id.courseInstructor);
        sDate = findViewById(R.id.courseStartDate);
        eDate = findViewById(R.id.courseEndD);



    }//end onCreate

    public void  create(View v){
        if(!mCourseName.equals("") && !mInstructor.equals("") && !sDate.equals("") && !eDate.equals("")) {
            Course c = new Course(mInstructor.getText().toString(),
                    mCourseName.getText().toString()," ",
                    sDate.getText().toString(), eDate.getText().toString() );
            int courseID = courseDAO.insert(c).get(0).intValue();
            Enrollment e = new Enrollment(uId,courseID,"sometime" );
            eDAO.insert(e);
            startActivity(CoursePage.getIntent(getApplicationContext()));
        }


    }
    public void cancel(View v){
        getSharedPreferences(LoginActivity.CREDENTIALS, MODE_PRIVATE).edit().clear().commit();
        startActivity(LoginActivity.getIntent(getApplicationContext()));//change to login
    }

    public static Intent getIntent(Context context){

        return new Intent(context, CreateCourse.class);
    }
}// class course
