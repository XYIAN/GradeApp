package com.kxdilbeck.gradeapp.Controller;

import android.content.Context;
import android.util.Log;

import com.kxdilbeck.gradeapp.Model.Assignment;
import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.AssignmentDAO;
import com.kxdilbeck.gradeapp.Model.Database.GradeCategoryDAO;
import com.kxdilbeck.gradeapp.Model.Database.GradeDAO;
import com.kxdilbeck.gradeapp.Model.Grade;
import com.kxdilbeck.gradeapp.Model.GradeCategory;

import java.util.ArrayList;
import java.util.List;

public class AssignmentController {
    private AssignmentDAO mAssignmentDAO;
    private GradeCategoryDAO mGradeCategoryDAO;
    private GradeController mGradeController;
    private GradeDAO mGradeDAO;

    public AssignmentController(Context context){
        mAssignmentDAO = AppDatabase.getDb(context.getApplicationContext()).getAssignmentDAO();
        mGradeCategoryDAO = AppDatabase.getDb(context.getApplicationContext()).getGradeCategoryDAO();
        mGradeDAO = AppDatabase.getDb(context.getApplicationContext()).getGradeDAO();
        mGradeController = new GradeController(context);
    }

    public String getCategoryTitle(int categoryId){
        return mGradeCategoryDAO.getCategory(categoryId).getTitle();
    }

    public boolean checkScore(String score, String maxScore){
        return !score.equals("") ? Double.parseDouble(score) >= 0 : false;
    }

    public boolean checkMaxScore(String maxScore){
        return !maxScore.equals("") ? Double.parseDouble(maxScore) > 0 : false;
    }

    public boolean checkDate(String date){
        return !date.equals("");
    }

    public boolean checkCategory(String category){
        return !category.equals("");
    }

    public boolean checkWeight(String weight){
        return !weight.equals("") ? Double.parseDouble(weight) <= 100.0 : false;
    }

    //@TODO drastically decrease the amount of arguments needed.
    public void addAssignment(int courseId, int userId, double score, double maxScore, Double weight, String dueDate, String assignedDate, String title, String details){
        Integer categoryId = mGradeCategoryDAO.getGradeCategoryIdByTitle(userId, title, courseId);

        Log.i("DOESITWORK", "INSIDE: "  + categoryId + "");

        if(categoryId == null){
            Log.i("DOESITWORK", "HOW: "  + categoryId + "");
            categoryId = mGradeCategoryDAO.insert(new GradeCategory(title, weight)).get(0).intValue();
        }

        Log.i("DOESITWORK", "INSIDE: "  + categoryId + "");

        int gradeId = mGradeDAO.insert(new Grade(score, userId, categoryId)).get(0).intValue();
        details =  details.equals("") ? "None" : details; // Tells the user there are no details.

        mAssignmentDAO.insert(new Assignment(courseId, gradeId, dueDate, assignedDate, score, maxScore, details));
    }

    public List<String[]> getDataForRecyclerView(int courseId, int userId){
        List<String[]> allDataForRecyclerView = new ArrayList<>();

        List<String[]> grades = new ArrayList<>();
        List<Integer> categoryIds = mGradeController.getCourseGradeCategories(courseId, userId);
        List<String> categoryGrades = new ArrayList<>();
        String categoryTitle = "";

        for(int i = 0; i < categoryIds.size(); i++){
            String[] categoryGrade = mGradeController.getGradeByCategory(courseId, userId, categoryIds.get(i));

            categoryTitle = getCategoryTitle(categoryIds.get(i));
            categoryGrade[0] = "[" + categoryTitle + " Category\t" + categoryGrade[0] + ": " + categoryGrade[1] + "]";
            categoryGrade[1] = "-1";

            allDataForRecyclerView.add(categoryGrade);

            List<Assignment> assignments = mGradeController.getAssignmentsByCategory(courseId, userId, categoryIds.get(i));
            for(int j = 0; j < assignments.size(); j++){
                String[] grade = mGradeController.getAssignmentGrade(assignments.get(j));
                grade[0] = "\t"+ categoryTitle + assignments.get(j).getAssignmentId() + " " + grade[0] + ": " + String.format("%.3f", Double.parseDouble(grade[1]));
                grade[1] = assignments.get(j).getAssignmentId() + "";

                allDataForRecyclerView.add(grade);
            }
        }

        return allDataForRecyclerView;
    }

    public Integer getGradeCategoryIdByTitle(int userId, String title, int courseId) {
        return mGradeCategoryDAO.getGradeCategoryIdByTitle(userId, title, courseId);
    }
}
