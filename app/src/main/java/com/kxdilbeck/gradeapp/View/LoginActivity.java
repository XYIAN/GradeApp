package com.kxdilbeck.project1.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kxdilbeck.project1.MainActivity;
import com.kxdilbeck.project1.Model.Database.AppDatabase;
import com.kxdilbeck.project1.Model.Database.UserDAO;
import com.kxdilbeck.project1.Model.User;
import com.kxdilbeck.project1.R;

public class LoginActivity extends AppCompatActivity {
    EditText mUsernameEditText;
    EditText mPasswordEditText;
    UserDAO mUserDAO;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build().getUserDAO();

        mUsernameEditText = findViewById(R.id.usernameEditText);
        mPasswordEditText = findViewById(R.id.passwordEditText);
        //If the database is not created we want to add in a user.
        if(!getApplicationContext().getDatabasePath(AppDatabase.DBNAME).exists()){
            User user = new User("admin", "password", "first", "last", 1);
            mUserDAO.insert(user);
        }

    }

    // verifies user login
    private boolean verify(String username, String password){
        User user = mUserDAO.getAuthentication(username, password);
        return password.equals(user.getPassword()) && username.equals(user.getUsername());
    }
    
    public void login(View v){
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if(verify(username, password)){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            //@TODO create failed login editText.
            Toast toast = Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
