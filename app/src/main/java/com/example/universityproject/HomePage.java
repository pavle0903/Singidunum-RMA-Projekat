package com.example.universityproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        String ime = getIntent().getStringExtra("ime");
        String jmbg = getIntent().getStringExtra("jmbg");
        TextView welcome = findViewById(R.id.welcomeView);
        welcome.setText("Zdravo, "+ ime + "\n" + "(" + jmbg + ")");

        Button predsednicki = findViewById(R.id.predsednickiButton);
        Button parlamentarni = findViewById(R.id.parlamentarniButton);
        Button lokalni = findViewById(R.id.lokalniButton);

        predsednicki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                predsednickiPrikaz();
            }
        });

        parlamentarni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parlamentarniPrikaz();
            }
        });
        lokalni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lokalniPrikaz();
            }
        });
    }
    private void predsednickiPrikaz(){
        Intent i = new Intent(this, Predsednicki.class);


        Bundle extras = new Bundle();
        String ime = getIntent().getStringExtra("ime");
        extras.putString("ime", ime);
        // ovde dobavi i prosledi i provere iz baze da l je vec glasao

        i.putExtras(extras);
        startActivity(i);
        finish();
    }

    private void parlamentarniPrikaz(){
        Intent i = new Intent(this, Parlamentarni.class);


        Bundle extras = new Bundle();
        String ime = getIntent().getStringExtra("ime");
        extras.putString("ime", ime);
        // ovde dobavi i prosledi i provere iz baze da l je vec glasao

        i.putExtras(extras);
        startActivity(i);
        finish();
    }

    private void lokalniPrikaz(){
        Intent i = new Intent(this, Lokalni.class);


        Bundle extras = new Bundle();
        String ime = getIntent().getStringExtra("ime");
        extras.putString("ime", ime);
        // ovde dobavi i prosledi i provere iz baze da l je vec glasao

        i.putExtras(extras);
        startActivity(i);
        finish();
    }
}
