package com.kxdilbeck.gradeapp.Controller;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.kxdilbeck.gradeapp.Model.Assignment;
import com.kxdilbeck.gradeapp.Model.Database.*;

import java.util.List;

/**
 * Grade Controller provides grading utility methods for the view, or other controllers.
 * It is able to calculate grades based on course, grade category, and assignment.
 */
public class GradeController {
    private UserDAO mUserDAO;
    private CourseDAO mCourseDAO;
    private AssignmentDAO mAssignmentDAO;
    private GradeDAO mGradeDAO;

    /**
     * Constructor for Grade Controller.
     * @param context an application context
     */
    public GradeController(Context context){
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build();

        mUserDAO = db.getUserDAO();
        mCourseDAO = db.getCourseDAO();
        mAssignmentDAO = db.getAssignmentDAO();
        mGradeDAO = db.getGradeDAO();
    }

    /**
     *  Alternative constructor for Grade controller (Can be used in tests and debugging).
     * @param db
     */
    public GradeController(AppDatabase db){
        mUserDAO = db.getUserDAO();
        mCourseDAO = db.getCourseDAO();
        mAssignmentDAO = db.getAssignmentDAO();
        mGradeDAO = db.getGradeDAO();
    }

    /**
     * Gets the overall grade of a specific user for a course.
     * @param userId
     * @param courseId
     * @return A String array with the first element being a letter grade, and the second a numeric grade.
     */
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

    /**
     * Given a numeric grade it gets the corresponding letter grade.
     * @param grade
     * @return returns a char holding the letter grade. ' ' means no grade.
     */
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

    /**
     * Gets all the grade categories for a course.
     * @param courseId
     * @param userId
     * @return returns a list of grade category ids.
     */
    public List<Integer> getCourseGradeCategories(int courseId, int userId){
        return mGradeDAO.getGradeCategoriesForCourse(courseId, userId);
    }

    /**
     * Gets the grade for a category in course for a specific user
     * @param courseId
     * @param userId
     * @param categoryId
     * @return A String array with the first element being a letter grade, and the second a numeric grade.
     */
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

    /**
     * Gets the grade of the assignment.
     * @param assignment
     * @return A String array with the first element being a letter grade, and the second a numeric grade.
     */
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

    /**
     * Gets all the assignments in a category for a course for a specific user.
     * @param courseId
     * @param userId
     * @param categoryId
     * @return A list of assignments.
     */
    public List<Assignment> getAssignmentsByCategory(int courseId, int userId, int categoryId){
        return mAssignmentDAO.getAssignmentsByCategory(courseId, userId, categoryId);
    }
}
