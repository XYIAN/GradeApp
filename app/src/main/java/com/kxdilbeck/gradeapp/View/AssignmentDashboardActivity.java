package com.kxdilbeck.gradeapp.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kxdilbeck.gradeapp.Controller.AssignmentController;
import com.kxdilbeck.gradeapp.Controller.CourseController;
import com.kxdilbeck.gradeapp.Controller.GradeController;
import com.kxdilbeck.gradeapp.CoursePage;
import com.kxdilbeck.gradeapp.Model.Assignment;
import com.kxdilbeck.gradeapp.Model.AssignmentAdapter;
import com.kxdilbeck.gradeapp.Model.Course;
import com.kxdilbeck.gradeapp.Model.CourseAdapt;
import com.kxdilbeck.gradeapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * View for all the assignments in a specific course.
 */
public class AssignmentDashboardActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private GradeController mGradeController;
    private AssignmentController mAssignmentController;
    private TextView mCourseInfoTextView;
    private TextView mCourseTitleTextView;
    private CourseController mCourseController;
    private int userId;
    private int courseId;
    private List<String[]> mData;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_dashboard);

        mGradeController = new GradeController(getApplicationContext());
        mCourseController = new CourseController(getApplicationContext());
        mAssignmentController = new AssignmentController(getApplicationContext());
        mRecyclerView = findViewById(R.id.assignmentRecyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mCourseInfoTextView = findViewById(R.id.courseInstructor);
        mCourseTitleTextView = findViewById(R.id.courseTitle);

        mRecyclerView.removeAllViewsInLayout();

        userId = getSharedPreferences(LoginActivity.CREDENTIALS, MODE_PRIVATE).getInt("USERID", -1);
        courseId = getIntent().getIntExtra("COURSEID", -1);

        Course course = mCourseController.getCourse(courseId);
        SpannableString spanString = new SpannableString(course.getTitle());
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
        mCourseTitleTextView.setText(spanString);
        mCourseInfoTextView.setText(course.getInstructor());

        List<String[]> mData = mAssignmentController.getDataForRecyclerView(courseId, userId);
        mAdapter = new AssignmentAdapter(mData, getApplicationContext(), new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button btn = v.findViewById(R.id.selectCourseButton);
                Integer id = (Integer) btn.getTag();

                Intent intent = CreateAssignmentActivity.getIntent(getApplicationContext());
                intent.putExtra("COURSEID", courseId);
                intent.putExtra("EDIT", 1);
                intent.putExtra("ASSIGNMENTID", id);
                startActivity(intent);
            }
        });

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * clears the recylcerView when app switches back to this activity to prevent duplicate items
     */
    @Override
    public void onRestart(){
        super.onRestart();
        mData.clear();
        mAdapter.notifyDataSetChanged();
    }

    /**
     * switches to the addAssignment activity
     * @param v
     */
    public void addAssignment(View v){
        Intent intent = CreateAssignmentActivity.getIntent(getApplicationContext());
        intent.putExtra("COURSEID", courseId);
        startActivity(intent);
    }

    /**
     * Goes back to the course dashboard
     * @param v
     */
    public void back(View v){
        Intent intent = CoursePage.getIntent(getApplicationContext());
        startActivity(intent);
    }

    /**
     * Returns an intent to get to this activity
     * @param context
     * @return Intent to get to this activity
     */
    public static Intent getIntent(Context context){
        return new Intent(context.getApplicationContext(), AssignmentDashboardActivity.class);
    }
}