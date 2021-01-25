package com.example.universityproject;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

public class Predsednicki extends AppCompatActivity {

    TextView t;
    TextView t2;
    ArrayList<PredsednikKandidati> estates;
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.predsednicki);
       // t = findViewById(R.id.textView7);
       // t2 = findViewById(R.id.viewIme);
        //t = findViewById(R.id.textView2);

        getRequest();
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
        String url = "http://192.168.0.101:5000/json";




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
        String url = "http://192.168.0.101:5000/add";

        PredsednikKandidati example = new PredsednikKandidati(15, "Pavle", "Sarenac", "aaaaaaaaaaaaa");
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
    private void fillDataView(PredsednikKandidati r, View v) {
        TextView predsednikIme = v.findViewById(R.id.viewIme);
        TextView predsednikPrezime = v.findViewById(R.id.viewPrezime);
        TextView predsednikDetalji = v.findViewById(R.id.viewDetalji);
        ImageView img = v.findViewById(R.id.viewSlika);

        String imageUri = "https://assets.bwbx.io/images/users/iqjWHBFdfxIU/ieHreOCQHZnA/v1/1000x-1.jpg";
        Picasso.get().load(imageUri).into(img);
        predsednikIme.setText(r.getIme());
        predsednikPrezime.setText(r.getPrezime());
        predsednikDetalji.setText("Ovde ce biti detalji od kandidatu. \n READ MORE");
        //predsednikDetalji.setText(r.getDetalji());

        mainLayout.addView(v);
        
    }

    private void generateData(){
        LayoutInflater inflater = getLayoutInflater();

        for(PredsednikKandidati r : estates){
            View v = inflater.inflate(R.layout.predsednik_detalji, mainLayout, false);

            fillDataView(r, v);

        }
    }
}
