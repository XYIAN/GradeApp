package com.kxdilbeck.gradeapp.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kxdilbeck.gradeapp.Controller.AssignmentController;
import com.kxdilbeck.gradeapp.Controller.GradeController;
import com.kxdilbeck.gradeapp.Model.Assignment;
import com.kxdilbeck.gradeapp.Model.GradeCategory;
import com.kxdilbeck.gradeapp.R;

/**
 * View for creating an assignment. It provides a gui that allows users to enter information
 * for creating an assignment.
 */
public class CreateAssignmentActivity extends AppCompatActivity {
    private EditText mScoreEditText;
    private EditText mMaxScoreEditText;
    private EditText mAssignedDateEditText;
    private EditText mDueDateEditText;
    private EditText mCategoryEditText;
    private EditText mWeightEditText;
    private EditText mDetailsEditText;
    private Button mCreateEditButton;
    private Button mDeleteButton;
    private TextView mAssignmentTextView;
    private AssignmentController mAssignmentController;
    private int courseId;
    private int userId;
    private int categoryId;
    private int assignmentId;
    private int editMode;


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
        mCreateEditButton = findViewById(R.id.addAssignmentButton);
        mDeleteButton = findViewById(R.id.deleteButton);
        mAssignmentTextView = findViewById(R.id.assignmentTextView);

        userId = getSharedPreferences(LoginActivity.CREDENTIALS, MODE_PRIVATE).getInt("USERID", -1);
        courseId = getIntent().getIntExtra("COURSEID", -1);
        assignmentId =  getIntent().getIntExtra("ASSIGNMENTID", -1);
        editMode = getIntent().getIntExtra("EDIT", -1);

        if(editMode == 1){
            mDeleteButton.setVisibility(View.VISIBLE);
            mCreateEditButton.setText("Edit");
            printFieldsToScreen(assignmentId);
        }else{
            mDeleteButton.setVisibility(View.INVISIBLE);
            mCreateEditButton.setText("Create");
        }

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

    /**
     * Appends the fields to there corresponding edit texts
     * @param assignmentId
     */
    private void printFieldsToScreen(int assignmentId){
        Assignment assignment = mAssignmentController.getAssignment(assignmentId);
        GradeCategory gradeCategory  = mAssignmentController.getAssignmentCategory(assignmentId);

        mAssignmentTextView.setText("Assignment"+ assignment.getAssignmentId());
        mScoreEditText.setText(assignment.getEarnedScore() + "");
        mMaxScoreEditText.setText(assignment.getMaxScore() + "");
        mAssignedDateEditText.setText(assignment.getAssignedDate());
        mDueDateEditText.setText(assignment.getDueDate());
        mCategoryEditText.setText(gradeCategory.getTitle());
        mWeightEditText.setText(gradeCategory.getWeight() + "");
        mDetailsEditText = findViewById(R.id.detailsEditText);

    }


    /**
     * Returns intent for this activity so another app can switch to it
     * @param context
     * @return Intent
     */
    public static Intent getIntent(Context context){
        return new Intent(context.getApplicationContext(), CreateAssignmentActivity.class);
    }

    /**
     * Adds an assignment to the db if it has valid fields.
     * @param v
     */
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

        if(!mAssignmentController.checkScore(score)){
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
            if(editMode == -1) {
                mAssignmentController.addAssignment(courseId, userId, Double.parseDouble(score), Double.parseDouble(maxScore), Double.parseDouble(weight),
                        dueDate, assignedDate, categoryTitle, details);

                Intent intent = AssignmentDashboardActivity.getIntent(getApplicationContext());
                intent.putExtra("COURSEID", courseId);
                startActivity(intent);
            }else{
                mAssignmentController.editAssignment(courseId, userId, Double.parseDouble(score), Double.parseDouble(maxScore), Double.parseDouble(weight),
                        dueDate, assignedDate, categoryTitle, details, assignmentId);

                Intent intent = AssignmentDashboardActivity.getIntent(getApplicationContext());
                intent.putExtra("COURSEID", courseId);
                startActivity(intent);
            }
        }

    }

    /**
     * Delete an assignment and returns to assignment dashboard
     * @param v
     */
    public void delete(View v){
        mAssignmentController.delete(assignmentId);
        Intent intent = AssignmentDashboardActivity.getIntent(getApplicationContext());
        intent.putExtra("COURSEID", courseId);
        startActivity(intent);
    }

    /**
     * Returns back to the assignment dashboard.
     * @param v
     */
    public void cancel(View v){
        Intent intent = AssignmentDashboardActivity.getIntent(getApplicationContext());
        intent.putExtra("COURSEID", courseId);
        startActivity(intent);
    }
}
