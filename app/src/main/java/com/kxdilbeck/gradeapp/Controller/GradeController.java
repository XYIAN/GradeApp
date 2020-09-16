package com.kxdilbeck.gradeapp.Controller;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.kxdilbeck.gradeapp.Model.Database.*;

import java.util.List;


public class GradeController {
    private UserDAO mUserDAO;
    private CourseDAO mCourseDAO;
    private AssignmentDAO mAssignmentDAO;
    private GradeDAO mGradeDAO;

    public GradeController(Context context){
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build();

        mUserDAO = db.getUserDAO();
        mCourseDAO = db.getCourseDAO();
        mAssignmentDAO = db.getAssignmentDAO();
        mGradeDAO = db.getGradeDAO();
    }

    // test/debug constructor
    public GradeController(AppDatabase db){
        mUserDAO = db.getUserDAO();
        mCourseDAO = db.getCourseDAO();
        mAssignmentDAO = db.getAssignmentDAO();
        mGradeDAO = db.getGradeDAO();
    }

    public String[] getCourseGrade(int userId, int courseId){
        List<Double> earnedPointsByCategory = mAssignmentDAO.getSumOfEarnedPointsByCourse(userId, courseId);
        List<Double> maxPointsByCategory = mAssignmentDAO.getSumOfMaxPointsByCourse(userId, courseId);
        List<Double> weightsByCategory = mGradeDAO.getAllWeightsForCourse(courseId, userId);
        Double grade = 0.0;
        Double weightsTotal = 0.0;

        Log.i("DOESITWORK", earnedPointsByCategory + "");

        for(int i = 0; i < earnedPointsByCategory.size(); i ++){
            grade+= earnedPointsByCategory.get(i) / maxPointsByCategory.get(i) * weightsByCategory.get(i);
            weightsTotal+= weightsByCategory.get(i);
        }

        // Converts grade to a percent, and takes account for the possibility of the weights not adding to 1.
        grade = grade/weightsTotal * 100;

        String[] resultant = {getLetterGrade(grade) + "", !grade.isNaN() ? grade + "": " "};

        return resultant;
    }

    private char getLetterGrade(Double grade){
        if(grade >= 90 && grade != null && !grade.isNaN()){
            return 'A';
        }else if(grade >= 80){
            return 'B';
        }else if(grade >= 70){
            return 'C';
        }else if(grade >= 60){
            return 'D';
        }else if(grade < 60){
            return 'F';
        }

        return ' ';
    }

    public void getCourseGradeCategories(int courseId, int userId){

    }

    public void getGradeByCategory(int courseId, int userId, int categoryId){

    }

    public void getAssignmentGrade(int earnedScore, int maxScore){

    }

    public void getAssignmentByCategory(int courseId, int userId, int categoryId){

    }

}
