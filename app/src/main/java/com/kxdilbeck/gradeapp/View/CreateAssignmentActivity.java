package com.kxdilbeck.gradeapp.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.kxdilbeck.gradeapp.Controller.AssignmentController;
import com.kxdilbeck.gradeapp.Controller.GradeController;
import com.kxdilbeck.gradeapp.R;

public class CreateAssignmentActivity extends AppCompatActivity {
    private EditText mScoreEditText;
    private EditText mMaxScoreEditText;
    private EditText mAssignedDateEditText;
    private EditText mDueDateEditText;
    private EditText mCategoryEditText;
    private EditText mWeightEditText;
    private EditText mDetailsEditText;
    private AssignmentController mAssignmentController;
    private int courseId;
    private int userId;
    private int categoryId;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        mAssignmentController = new AssignmentController(getApplicationContext());
        mScoreEditText = findViewById(R.id.scoreEditText);
        mMaxScoreEditText = findViewById(R.id.maxScoreEditText);
        mAssignedDateEditText = findViewById(R.id.assignedDateEditText);
        mDueDateEditText = findViewById(R.id.dueDateEditText);
        mCategoryEditText = findViewById(R.id.categoryEditText);
        mWeightEditText = findViewById(R.id.weightEditText);
        mDetailsEditText = findViewById(R.id.detailsEditText);

        userId = getSharedPreferences(LoginActivity.CREDENTIALS, MODE_PRIVATE).getInt("USERID", -1);
        courseId = getIntent().getIntExtra("COURSEID", -1);

        mCategoryEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String title = s.toString();
                Integer temp = mAssignmentController.getGradeCategoryIdByTitle(userId, title, courseId);

                if(temp == null){
                    mWeightEditText.setVisibility(View.VISIBLE);
                    categoryId = -1;
                }else{
                    categoryId = temp;
                    mWeightEditText.setVisibility(View.INVISIBLE);
                    mWeightEditText.setText("0");
                }
            }
        });

    }

    public static Intent getIntent(Context context){
        return new Intent(context.getApplicationContext(), CreateAssignmentActivity.class);
    }

    public void createAssignment(View v){
        String score = mScoreEditText.getText().toString();
        String maxScore  = mMaxScoreEditText.getText().toString();
        String assignedDate = mAssignedDateEditText.getText().toString();
        String dueDate = mDueDateEditText.getText().toString();
        String categoryTitle = mCategoryEditText.getText().toString();
        String weight = mWeightEditText.getText().toString();
        String details = mDetailsEditText.getText().toString();
        boolean valid = true;

        if(!mAssignmentController.checkMaxScore(maxScore)){
            valid = false;
            mMaxScoreEditText.setError("Invalid maxScore");
        }

        if(!mAssignmentController.checkScore(score, maxScore)){
            mScoreEditText.setError("Invalid Score");
            valid = false;
        }

        if(!mAssignmentController.checkDate(assignedDate)){
            mAssignedDateEditText.setError("Invalid Date");
            valid = false;
        }

        if(!mAssignmentController.checkDate(dueDate)){
            mDueDateEditText.setError("Invalid Date");
            valid = false;
        }

        if(!mAssignmentController.checkCategory(categoryTitle)){
            mCategoryEditText.setError("Invalid Category");
            valid = false;
        }

        if(!mAssignmentController.checkWeight(weight)){
            mWeightEditText.setError("Invalid Weight");
            valid = false;
        }

        if(valid){
            mAssignmentController.addAssignment(courseId, userId, Double.parseDouble(score), Double.parseDouble(maxScore), Double.parseDouble(weight),
                    dueDate, assignedDate, categoryTitle, details);

            Intent intent = AssignmentDashboardActivity.getIntent(getApplicationContext());
            intent.putExtra("COURSEID", courseId);
            startActivity(intent);
        }

    }

    public void cancel(View v){
        Intent intent = AssignmentDashboardActivity.getIntent(getApplicationContext());
        intent.putExtra("COURSEID", courseId);
        startActivity(intent);
    }
}
