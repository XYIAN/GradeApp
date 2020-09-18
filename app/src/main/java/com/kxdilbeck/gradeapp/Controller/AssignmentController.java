package com.kxdilbeck.gradeapp.Controller;

import android.content.Context;
import android.util.Log;

import com.kxdilbeck.gradeapp.Model.Assignment;
import com.kxdilbeck.gradeapp.Model.Course;
import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.AssignmentDAO;
import com.kxdilbeck.gradeapp.Model.Database.CourseDAO;
import com.kxdilbeck.gradeapp.Model.Database.GradeCategoryDAO;
import com.kxdilbeck.gradeapp.Model.Database.GradeDAO;
import com.kxdilbeck.gradeapp.Model.Grade;
import com.kxdilbeck.gradeapp.Model.GradeCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is an utility class for getting information related to assignments.
 * It also provides methods for checking whether certain fields are valid for assignments.
 */
public class AssignmentController {
    private AssignmentDAO mAssignmentDAO;
    private GradeCategoryDAO mGradeCategoryDAO;
    private GradeController mGradeController;
    private CourseDAO mCourseDAO;
    private GradeDAO mGradeDAO;

    /**
     * Constructor
     * @param context
     */
    public AssignmentController(Context context){
        mAssignmentDAO = AppDatabase.getDb(context.getApplicationContext()).getAssignmentDAO();
        mGradeCategoryDAO = AppDatabase.getDb(context.getApplicationContext()).getGradeCategoryDAO();
        mGradeDAO = AppDatabase.getDb(context.getApplicationContext()).getGradeDAO();
        mCourseDAO = AppDatabase.getDb(context.getApplicationContext()).getCourseDAO();
        mGradeController = new GradeController(context);
    }

    /**
     * Alternative constructor (can be used for testing/debugging).
     * @param db
     */
    public AssignmentController(AppDatabase db){
        mAssignmentDAO = db.getAssignmentDAO();
        mGradeCategoryDAO = db.getGradeCategoryDAO();
        mGradeDAO = db.getGradeDAO();
        mGradeController = new GradeController(db);
    }

    /**
     * Get a Grade category title
     * @param categoryId
     * @return String that is the title
     */
    public String getCategoryTitle(int categoryId){
        return mGradeCategoryDAO.getCategory(categoryId).getTitle();
    }

    /**
     * Checks that a positive value was entered for max score
     * @param score
     * @return boolean
     */
    public boolean checkScore(String score){
        return !score.equals("") ? Double.parseDouble(score) >= 0 : false;
    }

    /**
     * Checks that a positive value was entered for score
     * @param maxScore
     * @return boolean
     */
    public boolean checkMaxScore(String maxScore){
        return !maxScore.equals("") ? Double.parseDouble(maxScore) > 0 : false;
    }

    /**
     * Checks if there is something
     * @param date
     * @return boolean
     */
    public boolean checkDate(String date){
        return !date.equals("");
    }

    /**
     * Checks if there is something
     * @param category
     * @return boolean
     */
    public boolean checkCategory(String category){
        return !category.equals("");
    }

    /**
     * Checks if there is a weight and that it is less than 100%
     * @param weight
     * @return boolean
     */
    public boolean checkWeight(String weight){
        return !weight.equals("") ? Double.parseDouble(weight) <= 100.0 : false;
    }

    /**
     * Adds an assignment to the AppDatabase, creates a category if there is not already one.
     * @param courseId
     * @param userId
     * @param score
     * @param maxScore
     * @param weight
     * @param dueDate
     * @param assignedDate
     * @param title
     * @param details
     */
    //@TODO drastically decrease the amount of arguments needed.
    public void addAssignment(int courseId, int userId, double score, double maxScore, Double weight, String dueDate, String assignedDate, String title, String details){
        Integer categoryId = mGradeCategoryDAO.getGradeCategoryIdByTitle(userId, title, courseId);

        if(categoryId == null){
            categoryId = mGradeCategoryDAO.insert(new GradeCategory(title, weight)).get(0).intValue();
        }

        int gradeId = mGradeDAO.insert(new Grade(score, userId, categoryId)).get(0).intValue();
        details =  details.equals("") ? "None" : details; // Tells the user there are no details.

        mAssignmentDAO.insert(new Assignment(courseId, gradeId, dueDate, assignedDate, score, maxScore, details));
    }

    /**
     * Adds an assignment to the AppDatabase, creates a category if there is not already one.
     * @param courseId
     * @param userId
     * @param score
     * @param maxScore
     * @param weight
     * @param dueDate
     * @param assignedDate
     * @param title
     * @param details
     */
    //@TODO drastically decrease the amount of arguments needed.
    public void editAssignment(int courseId, int userId, double score, double maxScore, Double weight, String dueDate, String assignedDate, String title, String details, int assignmentId){
        Integer categoryId = mGradeCategoryDAO.getGradeCategoryIdByTitle(userId, title, courseId);
        Integer gradeId = null;
        Assignment assignmentOld =  mAssignmentDAO.getAssignment(assignmentId);

        if(categoryId == null){
            categoryId = mGradeCategoryDAO.insert(new GradeCategory(title, weight)).get(0).intValue();
            gradeId = mGradeDAO.insert(new Grade(score, userId, categoryId)).get(0).intValue();
        }else{
            Grade grade = new Grade(score, userId, categoryId);
            grade.setGradeId(assignmentOld.getGradeId());
            mGradeDAO.update(grade);
            gradeId = assignmentOld.getGradeId();
        }

        Assignment assignmentNew = new Assignment(courseId, gradeId, dueDate, assignedDate, score, maxScore, details);
        assignmentNew.setAssignmentId(assignmentId);
        mAssignmentDAO.update(assignmentNew);
    }

    /**
     * This method gets all the grade values needed for the recylcer view.
     * @param courseId
     * @param userId
     * @return List<String[]> (first element in each string[] is the grad string, second element is the assignmentId)
     */
    public List<String[]> getDataForRecyclerView(int courseId, int userId){
        List<String[]> allDataForRecyclerView = new ArrayList<>();

        List<String[]> grades = new ArrayList<>();
        List<Integer> categoryIds = mGradeController.getCourseGradeCategories(courseId, userId);
        List<String> categoryGrades = new ArrayList<>();
        String categoryTitle = "";

        // loops through all the categories in a course
        for(int i = 0; i < categoryIds.size(); i++){
            String[] categoryGrade = mGradeController.getGradeByCategory(courseId, userId, categoryIds.get(i));

            categoryTitle = getCategoryTitle(categoryIds.get(i));
            // appends the category grade to the data list
            categoryGrade[0] = "[" + categoryTitle + " Category\t" + categoryGrade[0] + ": " + String.format("%.3f", Double.parseDouble(categoryGrade[1])) + "]";
            categoryGrade[1] = "-1";

            allDataForRecyclerView.add(categoryGrade);

            // Gets all the assignments for a specific category
            List<Assignment> assignments = mGradeController.getAssignmentsByCategory(courseId, userId, categoryIds.get(i));
            for(int j = 0; j < assignments.size(); j++){
                String[] grade = mGradeController.getAssignmentGrade(assignments.get(j));
                grade[0] = "\t"+ categoryTitle + assignments.get(j).getAssignmentId() + " " + grade[0] + ": " + String.format("%.3f", Double.parseDouble(grade[1]));
                grade[1] = assignments.get(j).getAssignmentId() + "";
                // append the assignment grade to the data list
                allDataForRecyclerView.add(grade);
            }
        }

        return allDataForRecyclerView;
    }

    /**
     * Removes the assignment with this id from the db.
     * @param assignmentId
     */
    public void delete(int assignmentId){
        mAssignmentDAO.delete(mAssignmentDAO.getAssignment(assignmentId));
    }

    /**
     *
     * @param userId
     * @param title
     * @param courseId
     * @return
     */
    public Integer getGradeCategoryIdByTitle(int userId, String title, int courseId) {
        return mGradeCategoryDAO.getGradeCategoryIdByTitle(userId, title, courseId);
    }

    /**
     * Gets a assignment given a specific assignment id
     * @param assignmentId
     */
    public Assignment getAssignment(int assignmentId){
        return mAssignmentDAO.getAssignment(assignmentId);
    }

    public GradeCategory getAssignmentCategory(int assignmentId){
        return mAssignmentDAO.getAssignmentGradeCategory(assignmentId);
    }

    public Course getCourseById(int courseId){
        return mCourseDAO.getCourse(courseId);
    }
    
}
