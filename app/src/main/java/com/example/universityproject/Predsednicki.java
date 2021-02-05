package com.example.universityproject;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class Predsednicki extends AppCompatActivity {

    TextView t;
    TextView t2;
    ArrayList<PredsednikKandidati> estates;
    LinearLayout mainLayout;
    Database db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.predsednicki);
        String jmbg = getIntent().getStringExtra("jmbg");
        db = new Database(this);
        UsersRepository repo = new UsersRepository(db);
       // t = findViewById(R.id.textView7);
       // t2 = findViewById(R.id.viewIme);



        //t = findViewById(R.id.textView2);
        Button tabi = findViewById(R.id.meniButton);
        tabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home(repo);
            }
        });
        getRequest();
//        TabLayout tabs = findViewById(R.id.tabLayout);
//        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getText().toString()){
//                    case "POVRATAK NA MENI":
//                        System.out.println("LOGOUUUUUUUUUUUUUT");
//                        home(repo);
//                        break;
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
        //TabsManager(repo);
//        Button get = findViewById(R.id.getButton);
//        get.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getRequest();
//            }
//        });

//        Button set = findViewById(R.id.setButton);
//        set.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                postRequest();
//            }
//        });

    }

    private void getRequest(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.118:5000/json";




        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
               // t.setText(response);
                try{
                    JSONArray array = new JSONArray(response);
                    estates = PredsednikKandidati.fromJsonArray(array);
                    mainLayout = findViewById(R.id.predsednikLayout);
                    generateData();
                    //for(PredsednikKandidati r : estates){
                        //t.setText(r.getDetalji());
                        // pikaso biblioteka za slike, settag pogledaj sa vezbi, hold click probaj
//                        t2.setText(r.getIme());
//                        System.out.println(r.getIme());
//
//                        String imageUri = "https://assets.bwbx.io/images/users/iqjWHBFdfxIU/ieHreOCQHZnA/v1/1000x-1.jpg";
//                        ImageView ivBasicImage = (ImageView) findViewById(R.id.imageView9);
//                        Picasso.get().load(imageUri).into(ivBasicImage);
                    //}
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });

        queue.add(request);
    }

    //dodaje u listu tj json, omoguciti da admin moze dodavati nove predsednike
    private void postRequest(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.118:5000/add";

        PredsednikKandidati example = new PredsednikKandidati(15, "Pavle", "Sarenac", "aaaaaaaaaaaaa", "slika", 1, "1.1.111", "1", "domaca", "test");
        JSONObject object = PredsednikKandidati.toJson(example);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                params.put("data", object.toString());

                return params;
            }
        };
        queue.add(request);
    }
    private void styleView(View v, int c){ v.setBackgroundColor(c);}

//    private void TabsManager(UsersRepository repo) {
//        TabLayout tabs = findViewById(R.id.tabLayout);
//        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getPosition()){
//                    case 0:
//                        System.out.println("logoutlogoutlogout");
//                        logout();
//
//                    case 1:
//                        home(repo);
//                        break;
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//    }


    private void home(UsersRepository repo){
        Intent i = new Intent(this, HomePage.class);


        Bundle extras = new Bundle();
        String ime = getIntent().getStringExtra("ime");
        String jmbg = getIntent().getStringExtra("jmbg");
        extras.putString("ime", ime);
        extras.putString("jmbg", jmbg);
        String sifra = getIntent().getStringExtra("sifra");
        //String pred_glas = getIntent().getStringExtra("pred_glas");
        String parl_glas = getIntent().getStringExtra("parl_glas");
        extras.putString("sifra", sifra);
        ArrayList<Users> users = new ArrayList<Users>();
        users = repo.getAllUsers();
        for(Users u : users){
            if(u.getUsername().equals(ime)) {


                if (u.getPred_glas().equals("Jeste")) {
                    String glas = u.getPred_glas();
                    extras.putString("pred_glas", glas);
                } else {
                    String pred_glas = getIntent().getStringExtra("pred_glas");
                    extras.putString("pred_glas", pred_glas);
                }
            }
        }

        //extras.putString("pred_glas", glas);
        extras.putString("parl_glas", parl_glas);
        i.putExtras(extras);
        startActivity(i);
        finish();
    }


    private void detaljnoPredsednik(String ID, String ime, String prezime, String detalji, String slika, int glasova, String datumRodjenja, String mandata, String stranka, String potpredsednik){
        Intent i = new Intent(this, DetaljnoPredsednik.class);

        //String id =String.valueOf(ID);

        Bundle extras = new Bundle();
        extras.putString("id", ID);
        extras.putString("ime", ime);
        extras.putString("prezime", prezime);
        extras.putString("detalji", detalji);
        extras.putString("slika", slika);
        extras.putInt("glasova", glasova);
        extras.putString("datumRodjenja", datumRodjenja);
        extras.putString("mandata", mandata);
        extras.putString("stranka", stranka);
        extras.putString("potpredsednik", potpredsednik);

        String ime_ulogovanog = getIntent().getStringExtra("ime");
        String jmbg = getIntent().getStringExtra("jmbg");
        String sifra = getIntent().getStringExtra("sifra");
        String pred_glas = getIntent().getStringExtra("pred_glas");

        extras.putString("ime_ulogovanog", ime_ulogovanog);
        extras.putString("jmbg", jmbg);
        extras.putString("sifra", sifra);
        extras.putString("pred_glas", pred_glas);

       // extras.putString();

        i.putExtras(extras);
        startActivity(i);
        finish();
    }

    private void fillDataView(PredsednikKandidati r, View v) {
        TextView predsednikIme = v.findViewById(R.id.viewIme);
        //TextView predsednikPrezime = v.findViewById(R.id.viewPrezime);
        //TextView predsednikDetalji = v.findViewById(R.id.viewDetalji);
        ImageView img = v.findViewById(R.id.viewSlika);
        int ID = r.getId();
        String ime = r.getIme();
        String prezime = r.getPrezime();
        String detalji = r.getDetalji();
        String slika = r.getSlika();
        int glasova = r.getGlasova();
        String datumRodjenja = r.getDatumRodjenja();
        String mandata = r.getMandata();
        String stranka = r.getStranka();
        String potpredsednik = r.getPotpredsednik();




        //String imageUri = "https://classroommagazines.scholastic.com/content/dam/classroom-magazines/magazines/election/election-2020/meet-the-candidates/joe-biden/election-mtc-biden-tablet.png";
        String imageUri = r.getSlika();
        Picasso.get().load(imageUri).resize(200, 250).into(img);
        predsednikIme.setText(r.getIme());
        //predsednikPrezime.setText(r.getPrezime());

        Button det = v.findViewById(R.id.buttonDetalji);
        det.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id =String.valueOf(ID);

                detaljnoPredsednik(id, ime, prezime, detalji, slika, glasova, datumRodjenja, mandata, stranka, potpredsednik);
            }
        });

        Button vote = v.findViewById(R.id.buttonGlasaj);
        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ime = getIntent().getStringExtra("ime");
                String jmbg = getIntent().getStringExtra("jmbg");
                String sifra = getIntent().getStringExtra("sifra");
                ContentValues cv = new ContentValues();
                cv.put(Users.FIELD_USERNAME, ime);
                cv.put(Users.FIELD_PASSWORD, sifra);
                cv.put(Users.FIEDL_JMBG, jmbg);
                cv.put(Users.FIELD_PRED_GLASAO, "Jeste");
                cv.put(Users.FIELD_PARL_GLASAO, "Nije");
                db.update_predsednicke(cv, jmbg);

                alert();
                String id =String.valueOf(ID);
                vote(id);
            }
        });

        mainLayout.addView(v);

    }

    private void vote(String id){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.118:5000/glas/" + id;



        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

               // params.put("data", object.toString());

                return params;
            }
        };
        queue.add(request);
    }

    private void alert(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                .setIcon(android.R.drawable.ic_dialog_info)
//set title
                .setTitle("Uspesno glasanje!")
//set message
                .setMessage("Uspesno ste izvrsili glasanje! \n Povratak na meni")
//set positive button
                .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        redirectToHome();

                    }
                })
//set negative button
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //set what should happen when negative button is clicked
//                        Toast.makeText(getApplicationContext(),"Nothing Happened", Toast.LENGTH_LONG).show();
//                    }
//                })
                .show();
    }

    private void redirectToHome(){
        Intent i = new Intent(this, HomePage.class);


        Bundle extras = new Bundle();
        String ime = getIntent().getStringExtra("ime");
        String jmbg = getIntent().getStringExtra("jmbg");
        extras.putString("ime", ime);
        extras.putString("jmbg", jmbg);
        String sifra = getIntent().getStringExtra("sifra");
        //String pred_glas = getIntent().getStringExtra("pred_glas");
        String parl_glas = getIntent().getStringExtra("parl_glas");
        extras.putString("sifra", sifra);

        String glas = "Jeste";


        extras.putString("pred_glas", glas);
        extras.putString("parl_glas", parl_glas);
        i.putExtras(extras);
        startActivity(i);
        finish();

    }

    private void generateData(){
        LayoutInflater inflater = getLayoutInflater();

        for(PredsednikKandidati r : estates){
            View v = inflater.inflate(R.layout.predsednik_detalji, mainLayout, false);

            fillDataView(r, v);
            int colorValue = 0;
            colorValue = Color.rgb(234, 234, 234);
            //colorValue = Color.GRAY;
            styleView(v, colorValue);

        }
    }
}
