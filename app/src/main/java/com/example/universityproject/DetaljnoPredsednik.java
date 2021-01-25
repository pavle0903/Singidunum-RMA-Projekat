package com.example.universityproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetaljnoPredsednik extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detaljno_predsednik);

        String id = getIntent().getStringExtra("id");

        TextView tv = findViewById(R.id.probaView);
        tv.setText(id);
    }
}
