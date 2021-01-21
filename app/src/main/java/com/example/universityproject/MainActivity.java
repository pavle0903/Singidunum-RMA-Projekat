package com.example.universityproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database db = new Database(this);
    UsersRepository repo = new UsersRepository(db);
    ArrayList<Users> users = new ArrayList<Users>();



    // Users ulogovan = new Users();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //repo.addUser("Pavle", "123pavle", "12345");



        Button loginDugme = findViewById(R.id.loginButton);
        Button registerDugme = findViewById(R.id.registerButton);

        //ArrayList<Users> users = new ArrayList<Users>();
        users = repo.getAllUsers();



        loginDugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProcess();
            }
        });
        registerDugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerProcess();
            }
        });
    }

    private void registerProcess(){
        Intent i = new Intent(this, Register.class);

        //i.putExtra("message", msg);
//                Bundle extras = new Bundle();
//                extras.putString("message", ime);
//
//                i.putExtras(extras);
        startActivity(i);
        finish();
    }
    // funkcija koja proverava da li ime i prezime sa logina postoje i pokrece novi activity(home) ili obavestava da ne postoje
    private void loginProcess() {
        EditText ime1 = findViewById(R.id.loginName);
        EditText sifra1 = findViewById(R.id.loginPassword);
        String ime = ime1.getText().toString();
        String sifra = sifra1.getText().toString();
        Boolean ulogovan = false;


        if (ime.equals("")) {
            ime1.setError("Unesite korisnicko ime");
        }
        if (sifra.equals("")) {
            sifra1.setError("Unesite lozinku");
        }

        for (Users u : users) {
            if (u.getUsername().equals(ime) && u.getPassword().equals(sifra)) {
                ulogovan = true;
                Intent i = new Intent(this, HomePage.class);
                String jmbg = u.getJmbg();
                //i.putExtra("message", msg);
                Bundle extras = new Bundle();
                extras.putString("ime", ime);
                extras.putString("jmbg", jmbg);

                i.putExtras(extras);
                startActivity(i);
                finish();

            }
        if (ulogovan == false) {
            Toast toast = Toast.makeText(getApplicationContext(), "Uneti podaci nisu pronadjeni!", Toast.LENGTH_LONG);
            toast.show();
            System.out.println("Pogresni podaci!");
        }
        }
    }
}