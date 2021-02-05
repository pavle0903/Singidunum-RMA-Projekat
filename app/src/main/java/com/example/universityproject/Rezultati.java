package com.example.universityproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;

public class Rezultati extends AppCompatActivity {

    ArrayList<PredsednikKandidati> kandidati_p;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rezultati);

        t = findViewById(R.id.rezultatiView);

        getRequest();
    }

    private void getRequest(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.118:5000/json";




        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);

                //t.setText(response);
                try{
                    JSONArray array = new JSONArray(response);
                    kandidati_p = PredsednikKandidati.fromJsonArray(array);
                    prikaz_rezultata();


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

    private void prikaz_rezultata(){

        //TODO: URADITI KAO SCROLLVIEW PRIKAZ REZULTATA

        for(PredsednikKandidati p : kandidati_p){
            System.out.println(p.getGlasova() + "broj glasova");
            System.out.println(p.getIme() + "imena");
            t.setText(p.getGlasova() + "glasova");
        }
    }
}
