package com.kxdilbeck.gradeapp.Controller;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.kxdilbeck.gradeapp.Model.Assignment;
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
        List<Double> earnedPointsByCategory = mAssignmentDAO.getSumOfEarnedPointsByCourse(courseId, userId);
        List<Double> maxPointsByCategory = mAssignmentDAO.getSumOfMaxPointsByCourse(courseId, userId);
        List<Double> weightsByCategory = mGradeDAO.getAllWeightsForCourse(courseId, userId);
        Double grade = 0.0;
        Double weightsTotal = 0.0;

        for(int i = 0; i < earnedPointsByCategory.size(); i ++){
            grade+= earnedPointsByCategory.get(i) / maxPointsByCategory.get(i) * weightsByCategory.get(i);
            weightsTotal+= weightsByCategory.get(i);
        }

        // Converts grade to a percent, and takes account for the possibility of the weights not adding to 1.
        if(weightsTotal != 0) {
            grade = grade / weightsTotal * 100;
        }else{
            grade = Double.NaN;
        }

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

    public List<Integer> getCourseGradeCategories(int courseId, int userId){
        return mGradeDAO.getGradeCategoriesForCourse(courseId, userId);
    }

    public String[] getGradeByCategory(int courseId, int userId, int categoryId){
        List<Assignment> assignments = mAssignmentDAO.getAssignmentsByCategory(courseId, userId, categoryId);
        Double grade = 0.0;

        for(int i = 0; i < assignments.size(); i++){
            grade+= assignments.get(i).getEarnedScore() / assignments.get(i).getMaxScore();
        }

        if(assignments.size() == 0){
            grade = Double.NaN;
        }else{
            grade = grade/assignments.size() * 100;
        }

        String[] resultant = {getLetterGrade(grade) + "", !grade.isNaN() ? grade + "": " "};
        return resultant;
    }

    public String[] getAssignmentGrade(Assignment assignment){
        Double grade = 0.0;

        if(assignment.getMaxScore() !=null && assignment.getMaxScore() != 0 ){
            grade = assignment.getEarnedScore() / assignment.getMaxScore() * 100;
        }else{
            grade = Double.NaN;
        }

        String[] resultant = {getLetterGrade(grade) + "", !grade.isNaN() ? grade + "": " "};
        return resultant;
    }

    public List<Assignment> getAssignmentsByCategory(int courseId, int userId, int categoryId){
        return mAssignmentDAO.getAssignmentsByCategory(courseId, userId, categoryId);
    }

}
