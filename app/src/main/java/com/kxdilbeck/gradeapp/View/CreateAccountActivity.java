package com.kxdilbeck.gradeapp.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kxdilbeck.gradeapp.R;

public class CreateAccountActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public static Intent getIntent(Context context){
        return new Intent(context.getApplicationContext(), CreateAccountActivity.class);
    }
}
