package com.example.universityproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.google.android.material.tabs.TabLayout;

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

public class Predsednicki extends AppCompatActivity {

    TextView t;
    ArrayList<PredsednikKandidati> estates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.predsednicki);
        t = findViewById(R.id.textView2);

        Button get = findViewById(R.id.getButton);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRequest();
            }
        });

        Button set = findViewById(R.id.setButton);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postRequest();
            }
        });


    }

    private void getRequest(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.118:5000/json";




        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                t.setText(response);
                try{
                    JSONArray array = new JSONArray(response);
                    estates = PredsednikKandidati.fromJsonArray(array);
                    for(PredsednikKandidati r : estates){
                        System.out.println(r.getIme());
                    }
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

    private void postRequest(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.118:5000/add";

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
}
