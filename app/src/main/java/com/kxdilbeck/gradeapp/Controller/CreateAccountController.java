package com.kxdilbeck.gradeapp.Controller;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.User;

/**
 * CreateAccountController provides creating account utility methods for the view.
 */
public class CreateAccountController {
    private UserDAO mUserDAO;
    private User currentUser;

    /**
     * Constructor for CreateAccountController.
     * @param context
     */
    public CreateAccountController(Context context){
        mUserDAO = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build().getUserDAO();

    }

    /**
     * Alternative constructor for CreateAccountController (Can be used for testing/debugging).
     * @param userDAO
     */
    public CreateAccountController(UserDAO userDAO){
        mUserDAO = userDAO;
    }

    /**
     * Checks if the username is valid format wise, and not already taken.
     * @param username
     * @return boolean value
     */
    public boolean checkUsername(String username){

        // checks if the username is equal to or greater than 6 characters and has uppercase, lowercase, and number characters.
        if(username.length() < 6 || username.equals(username.toLowerCase()) || username.equals(username.toUpperCase()) || !username.matches(".*\\d.*")){
            return false;
        }
        Log.i("TESTING", mUserDAO.getUserByUsername(username) + "");
        return mUserDAO.getUserByUsername(username) == null;
    }

    /**
     * Checks if the password is valid format wise.
     * @param password
     * @return boolean value
     */
    public boolean checkPassword(String password){
        return password.length() > 6 && !password.equals(password.toLowerCase()) && !password.equals(password.toUpperCase()) && password.matches(".*\\d.*");
    }

    /**
     * Checks if there is something for a first name
     * @param firstName
     * @return boolean value
     */
    public boolean checkFirstName(String firstName){
        return firstName.length() != 0;
    }

    /**
     * checks if there is something for a last name
     * @param lastName
     * @return
     */
    public boolean checkLastName(String lastName){
        return lastName.length() != 0;
    }

    /**
     * Inserts the user into the db, and makes note that they are the current user
     * @param user
     */
    public void addUser(User user){
        mUserDAO.insert(user);
        currentUser = user;
    }

    /**
     * Gets the currentUser id if there is one
     * @return int value of userId
     */
    public int getUserId(){
        if(currentUser == null){
            return -1;
        }
        return currentUser.getUserId();
    }
}
