package com.kxdilbeck.gradeapp.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.kxdilbeck.gradeapp.Controller.CreateAccountController;
import com.kxdilbeck.gradeapp.Model.User;
import com.kxdilbeck.gradeapp.R;

/**
 * CreateAccountActivity is the view for creating an account. It provides a gui
 * for entering new account credentials, and making the account.
 */
public class CreateAccountActivity extends AppCompatActivity {
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private CreateAccountController createAccountController;

    /**
     * Creates the view for CreateAccountActivity
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mUsernameEditText = findViewById(R.id.enterUsernameEditText);
        mPasswordEditText = findViewById(R.id.enterPasswordEditText);
        mFirstNameEditText = findViewById(R.id.enterFirstNameEditText);
        mLastNameEditText = findViewById(R.id.enterLastNameEditText);
        createAccountController = new CreateAccountController(getApplicationContext());
    }

    /**
     * Makes an intent for CreateAccountActivity
     * @param context
     * @return Intent
     */
    public static Intent getIntent(Context context){
        return new Intent(context.getApplicationContext(), CreateAccountActivity.class);
    }

    /**
     * Verifies that the account credentials entered into the editTexts are valid.  If so
     * it creates the new account, and returns to the login screen. If not it marks invalid
     * fields as incorrect.
     * @param v
     */
    public void create(View v){
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        String firstName = mFirstNameEditText.getText().toString();
        String lastName = mLastNameEditText.getText().toString();
        boolean valid = true;

        if(!createAccountController.checkUsername(username)){
            valid = false;
            mUsernameEditText.setError("Invalid Username");
        }

        if(!createAccountController.checkPassword(password)){
            valid = false;
            mPasswordEditText.setError("Invalid Password");
        }

        if(!createAccountController.checkFirstName(firstName)){
            valid = false;
            mFirstNameEditText.setError("First Name required");
        }

        if(!createAccountController.checkLastName(lastName)){
            valid = false;
            mLastNameEditText.setError("Last Name required");
        }

        if(valid){
            createAccountController.addUser(new User(username, password, firstName, lastName, 0));
            startActivity(LoginActivity.getIntent(getApplicationContext()));
        }
    }

    /**
     * Provides a way back to login if going the the CreateAccountActivity was not wanted.
     * @param v
     */
    public void cancel(View v){
        startActivity(LoginActivity.getIntent(getApplicationContext()));
    }
}
