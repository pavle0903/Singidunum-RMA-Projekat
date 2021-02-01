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
        //predsednicki.setVisibility(View.GONE);
//        int pred_glasao = getIntent().getIntExtra("pred_glas", 0);
//        int parl_glasao = getIntent().getIntExtra("parl_glas", 0);

        String pred_glasao = getIntent().getStringExtra("pred_glas");

        System.out.println(pred_glasao + "ovo je u home pred");
        if(pred_glasao.equals("Jeste")){
            predsednicki.setEnabled(false);
        }
//        else {
//            predsednicki.setVisibility(View.VISIBLE);
//        }

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

        Button odjava = findViewById(R.id.odjavaButton);

        odjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }
    private void logout(){
        Intent i = new Intent(this, MainActivity.class);
        System.out.println("logoutlogoutlogout");
        startActivity(i);
        finish();
    }
    private void predsednickiPrikaz(){
        Intent i = new Intent(this, Predsednicki.class);


        Bundle extras = new Bundle();
        String ime = getIntent().getStringExtra("ime");
        String jmbg = getIntent().getStringExtra("jmbg");
        extras.putString("ime", ime);
        extras.putString("jmbg", jmbg);
        String sifra = getIntent().getStringExtra("sifra");
        extras.putString("sifra", sifra);
        String pred_glas = getIntent().getStringExtra("pred_glas");
        String parl_glas = getIntent().getStringExtra("parl_glas");
        extras.putString("pred_glas", pred_glas);
        extras.putString("parl_glas", parl_glas);
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
