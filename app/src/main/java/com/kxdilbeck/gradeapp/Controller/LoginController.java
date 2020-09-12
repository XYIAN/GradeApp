package com.kxdilbeck.gradeapp.Controller;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.User;

public class LoginController {
    private UserDAO mUserDAO;
    private User currentUser;

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

    public boolean checkAuthentication(String username, String password){
        currentUser = mUserDAO.getAuthentication(username, password);

        return currentUser == null ? false : currentUser.getUsername().equals(username) && currentUser.getPassword().equals(password);
    }

    public int getUserId(){
        return currentUser.getUserId();
    }

}
