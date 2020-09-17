package com.kxdilbeck.gradeapp.Controller;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.User;

/**
 * LoginController provides login utilities for the view, and ensures authentication.
 */
public class LoginController {
    private UserDAO mUserDAO;
    private User currentUser;

    /**
     * Constructor for LoginController
     * @param context
     */
    public LoginController(Context context){
        mUserDAO = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build().getUserDAO();

        //creates user in db if there is not one.  @TODO create separate class for pre population of data
        if(!context.getDatabasePath(AppDatabase.DBNAME).exists()){
            User user = new User("admin", "password", "first", "last", 1);
            mUserDAO.insert(user);
        }
    }

    /**
     * Checks whether a user has enter the right credentials to get into their account.
     * @param username
     * @param password
     * @return boolean value
     */
    public boolean checkAuthentication(String username, String password){
        currentUser = mUserDAO.getAuthentication(username, password);

        return currentUser == null ? false : currentUser.getUsername().equals(username) && currentUser.getPassword().equals(password);
    }

    /**
     * Gets the current users id.
     * @return int value of userId
     */
    public int getUserId(){
        return currentUser.getUserId();
    }

}
