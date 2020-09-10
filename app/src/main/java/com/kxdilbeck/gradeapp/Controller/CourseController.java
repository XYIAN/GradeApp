package com.kxdilbeck.gradeapp.Controller;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.kxdilbeck.gradeapp.Model.Course;
import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.CourseDAO;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.User;

public class CourseController {
    private UserDAO mUserDAO;
    private User currentUser;

    public CourseController(Context context){
        mUserDAO = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build().getUserDAO();

        //creates user in db if there is not one.  @TODO create separate class for pre population of data
        if(!context.getDatabasePath(AppDatabase.DBNAME).exists()){
            CourseDAO courseDAO;

            courseDAO = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DBNAME)
                    .allowMainThreadQueries()
                    .build().getCourseDAO();

            //if(courseDAO.getAllCourses().size() == 0){
            courseDAO.insert(new Course("Dr. teacher", "BIO", "title here", "12/12/12", "12/13/12"));
            courseDAO.insert(new Course("Dr. guy", "SCIENCE", "title here", "12/12/12", "12/13/12"));
            courseDAO.insert(new Course("Dr. professor", "HISTORY", "title here", "12/12/12", "12/13/12"));
            courseDAO.insert(new Course("Dr. pro", "ANATOMY", "title here", "12/12/15", "12/13/12"));
            courseDAO.insert(new Course("Dr. it", "CS", "title here", "12/12/15", "12/13/12"));
            courseDAO.insert(new Course("Dr. llama", "CS", "title here", "12/12/16", "12/13/12"));
            courseDAO.insert(new Course("Dr. Evil", "BIO", "title here", "12/12/15", "12/13/12"));
            //}
        }
    }//end course controller



}
