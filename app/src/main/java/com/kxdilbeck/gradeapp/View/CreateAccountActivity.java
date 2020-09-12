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

public class CreateAccountActivity extends AppCompatActivity {
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private CreateAccountController createAccountController;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mUsernameEditText = findViewById(R.id.enterUsernameEditText);
        mPasswordEditText = findViewById(R.id.enterPasswordEditText);
        mFirstNameEditText = findViewById(R.id.enterFirstNameEditText);
        mLastNameEditText = findViewById(R.id.enterLastNameEditText);
        createAccountController = new CreateAccountController(getApplicationContext());
    }

    public static Intent getIntent(Context context){
        return new Intent(context.getApplicationContext(), CreateAccountActivity.class);
    }

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

    public void cancel(View v){
        startActivity(LoginActivity.getIntent(getApplicationContext()));
    }
}
