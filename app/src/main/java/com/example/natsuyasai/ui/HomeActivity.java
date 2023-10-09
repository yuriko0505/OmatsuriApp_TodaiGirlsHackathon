package com.example.natsuyasai.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.natsuyasai.R;
import com.google.firebase.FirebaseApp;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        Button createYearButton = findViewById(R.id.button_create_newevent);
        // lambda式
        createYearButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), NewEventActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_right, R.anim.exit_left);
        });
    }
}
