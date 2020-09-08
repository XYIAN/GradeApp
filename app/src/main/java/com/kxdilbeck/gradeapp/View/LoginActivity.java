package com.kxdilbeck.gradeapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.kxdilbeck.gradeapp.Model.Database.AppDatabase;
import com.kxdilbeck.gradeapp.Model.Database.UserDAO;
import com.kxdilbeck.gradeapp.Model.User;
import com.kxdilbeck.gradeapp.R;

public class LoginActivity extends AppCompatActivity {
    EditText mUsernameEditText;
    EditText mPasswordEditText;
    UserDAO mUserDAO;
    public static final String CREDENTIALS = "CREDENTIALS";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build().getUserDAO();

        mUsernameEditText = findViewById(R.id.user);
        mPasswordEditText = findViewById(R.id.password);
        //If the database is not created we want to add in a user.
        if(!getApplicationContext().getDatabasePath(AppDatabase.DBNAME).exists()){
            User user = new User("admin", "password", "first", "last", 1);
            mUserDAO.insert(user);
        }

    }

    // verifies user login
    private int verify(String username, String password){

        User user = mUserDAO.getAuthentication(username, password);

        if(user == null){
            return -1;
        }

        return password.equals(user.getPassword()) && username.equals(user.getUsername()) ? user.getUserId() : -1;
    }
    
    public void login(View v){
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        int temp = verify(username, password);
        if(temp > -1){
            //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            //startActivity(intent);

            // saves the userID in shard preferences.
            getApplication().getSharedPreferences(CREDENTIALS, MODE_PRIVATE).edit().putInt("USERID", temp).commit();
            Toast toast = Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG);
            toast.show();
        }else{
            //@TODO create failed login editText.
            Toast toast = Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
