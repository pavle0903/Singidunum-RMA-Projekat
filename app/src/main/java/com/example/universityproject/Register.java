package com.example.universityproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    Database db = new Database(this);
    UsersRepository repo = new UsersRepository(db);
    ArrayList<Users> users = new ArrayList<Users>();
    Boolean pronadjen = false;
    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registracija);

        users = repo.getAllUsers();
        if (users.isEmpty()){
            pronadjen = true;
        }

        Button registruj = findViewById(R.id.registrujSe);

        registruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView imeLabel = findViewById(R.id.registracijaIme);
                String ime = imeLabel.getText().toString();
                TextView sifraLabel = findViewById(R.id.registracijaSifra);
                String sifra = sifraLabel.getText().toString();
                TextView jmbgLabel = findViewById(R.id.registracijaJmbg);
                String jmbg = jmbgLabel.getText().toString();


                for (Users u : users) {
                    System.out.println(jmbg + " aaaaaa" + u.getJmbg());
                    if (u.getJmbg().equals(jmbg)) {
                        Toast toast = Toast.makeText(getApplicationContext(), "JMBG vec postoji!!", Toast.LENGTH_LONG);
                        toast.show();
                        break;

                    } else {
                       // int glas = 0;
                        user = new Users(ime, sifra, jmbg, "Nije", "Nije");
                        repo.addUser(ime, sifra, jmbg, "Nije", "Nije");
                        //prebaciti da ovo vodi na login
                        changeActivity();
                    }
                }
                if(pronadjen == true){
                   //int glas = 0;

                    user = new Users();
                    user.setPred_glas("Nije");
                    user.setParl_glas("Nije");
                    user.setUsername(ime);
                    user.setPassword(sifra);
                    user.setJmbg(jmbg);
                    repo.addUser(ime, sifra, jmbg, "Nije", "Nije");
                    //prebaciti da ovo vodi na login
                    changeActivity();
                }
            }
        });
    }
    private void changeActivity(){
        Intent i = new Intent(this, MainActivity.class);

        //i.putExtra("message", msg);
//        Bundle extras = new Bundle();
//        extras.putString("message", ime);

//        i.putExtras(extras);
        startActivity(i);
        finish();
        }
    }

