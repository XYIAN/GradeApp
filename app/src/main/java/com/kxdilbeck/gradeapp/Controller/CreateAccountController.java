package com.kxdilbeck.gradeapp.Controller;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.User;

public class CreateAccountController {
    private UserDAO mUserDAO;
    private User currentUser;

    public CreateAccountController(Context context){
        mUserDAO = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build().getUserDAO();

    }

    // testing/debug constructor
    public CreateAccountController(UserDAO userDAO){
        mUserDAO = userDAO;
    }

    public boolean checkUsername(String username){

        // checks if the username is equal to or greater than 6 characters and has uppercase, lowercase, and number characters.
        if(username.length() < 6 || username.equals(username.toLowerCase()) || username.equals(username.toUpperCase()) || !username.matches(".*\\d.*")){
            return false;
        }
        Log.i("TESTING", mUserDAO.getUserByUsername(username) + "");
        return mUserDAO.getUserByUsername(username) == null;
    }

    public boolean checkPassword(String password){
        return password.length() > 6 && !password.equals(password.toLowerCase()) && !password.equals(password.toUpperCase()) && password.matches(".*\\d.*");
    }

    public boolean checkFirstName(String firstName){
        return firstName.length() != 0;
    }

    public boolean checkLastName(String lastName){
        return lastName.length() != 0;
    }

    public void addUser(User user){
        mUserDAO.insert(user);
        currentUser = user;
    }

    public int getUserId(){
        if(currentUser == null){
            return -1;
        }
        return currentUser.getUserId();
    }
}
