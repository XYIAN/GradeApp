package com.kxdilbeck.gradeapp.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.kxdilbeck.gradeapp.Controller.LoginController;
import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.User;
import com.kxdilbeck.gradeapp.Prepopulate;
import com.kxdilbeck.gradeapp.R;

public class LoginActivity extends AppCompatActivity {
    EditText mUsernameEditText;
    EditText mPasswordEditText;
    public static final String CREDENTIALS = "CREDENTIALS";
    private LoginController loginController;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginController = new LoginController(getApplicationContext());
        mUsernameEditText = findViewById(R.id.user);
        mPasswordEditText = findViewById(R.id.password);
    }
    
    public void login(View v){
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if(loginController.checkAuthentication(username, password)){
            //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            //startActivity(intent);

            // saves the userID in shard preferences.
            getApplication().getSharedPreferences(CREDENTIALS, MODE_PRIVATE).edit().putInt("USERID", loginController.getUserId()).commit();
            Toast toast = Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG);
            toast.show();
        }else{
            //@TODO create failed login editText.
            mPasswordEditText.setText("");
            mPasswordEditText.setError("Incorrect Password");

            Toast toast = Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void createAccount(View v){
        startActivity(CreateAccountActivity.getIntent(getApplicationContext()));
    }

    public static Intent getIntent(Context context){
        return new Intent(context.getApplicationContext(), LoginActivity.class);
    }

}
