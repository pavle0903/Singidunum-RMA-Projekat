package com.example.universityproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetaljnoPredsednik extends AppCompatActivity {

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detaljno_predsednik);

        db = new Database(this);
        UsersRepository repo = new UsersRepository(db);

        String id = getIntent().getStringExtra("id");
        String ime = getIntent().getStringExtra("ime");
        String prezime = getIntent().getStringExtra("prezime");
        String detalji = getIntent().getStringExtra("detalji");
        String slika = getIntent().getStringExtra("slika");
        int glasova = getIntent().getIntExtra("glasova", 0);
        String datumRodjenja = getIntent().getStringExtra("datumRodjenja");
        String mandata = getIntent().getStringExtra("mandata");
        String stranka = getIntent().getStringExtra("stranka");
        String potpredsednik = getIntent().getStringExtra("potpredsednik");


        TextView tv = findViewById(R.id.probaView);
        TextView tv1 = findViewById(R.id.imeTextView);
        //TextView tv2 = findViewById(R.id.prezimeTextView);
        tv1.setText(ime);
        //tv2.setText(prezime);
        tv.setText(detalji);

        TextView rodjen = findViewById(R.id.rodjenjeView);
        rodjen.setText(datumRodjenja);
        TextView rodjenje = findViewById(R.id.rodjenjeView);
        rodjenje.setText(datumRodjenja);
        TextView mandat = findViewById(R.id.mandatView);
        mandat.setText(mandata);
        TextView strankaa = findViewById(R.id.strankaView);
        strankaa.setText(stranka);
        TextView vice = findViewById(R.id.viceView);
        vice.setText(potpredsednik);

        ImageView slika_f = findViewById(R.id.predsednikSlika);


        Picasso.get().load(slika).resize(200, 250).into(slika_f);


        Button povratakButton = findViewById(R.id.povratakButton);

        povratakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                povratakPredsednici(repo);
            }
        });
    }
    private void povratakPredsednici(UsersRepository repo){
        Intent i = new Intent(this, Predsednicki.class);
        Bundle extras = new Bundle();

        String ime = getIntent().getStringExtra("ime_ulogovanog");

        ArrayList<Users> users = new ArrayList<Users>();
        users = repo.getAllUsers();
        System.out.println("pred prvi korak" + ime);
        for(Users u : users){
            if(u.getUsername().equals(ime)) {
                //System.out.println("prvi korak" + ime_ulogovanog);

                if (u.getPred_glas().equals("Jeste")) {
                    String glas = u.getPred_glas();
                    System.out.println("drugi korak" + glas);
                    extras.putString("pred_glas", glas);
                } else {
                    String pred_glas = getIntent().getStringExtra("pred_glas");
                    System.out.println("treci korak" + pred_glas);
                    extras.putString("pred_glas", pred_glas);
                }
            }
        }

        //String pred_glas = getIntent().getStringExtra("pred_glas");

        //String ime = getIntent().getStringExtra("ime");
        String jmbg = getIntent().getStringExtra("jmbg");
        extras.putString("ime", ime);
        extras.putString("jmbg", jmbg);
        String sifra = getIntent().getStringExtra("sifra");
        extras.putString("sifra", sifra);


        // ovde dobavi i prosledi i provere iz baze da l je vec glasao

        i.putExtras(extras);
        startActivity(i);
        finish();
    }
}
