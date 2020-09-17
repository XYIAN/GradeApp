package com.kxdilbeck.gradeapp.Controller;

import android.content.Context;
import android.util.Log;

import androidx.core.widget.TextViewCompat;
import androidx.room.Room;

import com.kxdilbeck.gradeapp.Model.Course;
import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.CourseDAO;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.User;

public class CourseController {
    private CourseDAO mcourseDAO;
    private Course currentCourse;

    public CourseController(Context context){
        mcourseDAO = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DBNAME).allowMainThreadQueries().build().getCourseDAO();
        //creates user in db if there is not one.  @TODO create separate class for pre population of data

    }//end course controller

    public CourseController(AppDatabase db){
        mcourseDAO = db.getCourseDAO();

    };


    public boolean checkCourseID(int id){
        if(currentCourse.getCourseId() == id) {
            return true;
        }
        return false;
    }
    public boolean checkInstructor(String instructor){
        if(currentCourse.getInstructor() == instructor) {
            return true;
        }
        return false;
    }
    public boolean checkCourseStartDate(String sDate){
        if(currentCourse.getStartDate() == sDate){
            return true;
        }
        return false;
    }
    public boolean checkCourseEndDate(String eDate){
        if(currentCourse.getStartDate() == eDate){
            return true;
        }
        return false;
    }
    public boolean checkCourseTitle(String title){
        if(currentCourse.getTitle() == title){
            return true;
        }
        return false;
    }




}
