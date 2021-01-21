package com.example.universityproject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PredsednikKandidati {

    private int id;
    private String ime, prezime, detalji;

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getIme(){
        return this.ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime(){return this.prezime;}

    public void setPrezime(String prezime){this.prezime = prezime;}

    public String getDetalji(){return this.detalji;}

    public void setDetalji(String detalji){this.detalji = detalji;}

    public PredsednikKandidati(){

    }

    public PredsednikKandidati(int id, String ime, String prezime, String detalji){
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.detalji = detalji;
    }

    public static PredsednikKandidati fromJson(JSONObject json){
        PredsednikKandidati element = new PredsednikKandidati();

        try{
            if(json.has("id")){
                element.id = json.getInt("id");
            }
            if(json.has("ime")){
                element.ime = json.getString("ime");
            }
            if(json.has("prezime")){
                element.prezime = json.getString("prezime");
            }
            if(json.has("detalji")){
                element.detalji = json.getString("detalji");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return element;
    }

    public static JSONObject toJson(PredsednikKandidati r){
        JSONObject o = new JSONObject();
        try {
            o.put("id", r.getId());
            o.put("ime", r.getIme());
            o.put("prezime", r.getPrezime());
            o.put("detalji", r.getDetalji());
        }catch(Exception e){
            e.printStackTrace();
        }
        return o;
    }

    public static ArrayList<PredsednikKandidati> fromJsonArray(JSONArray array){
        ArrayList<PredsednikKandidati> kandidati = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                kandidati.add(fromJson(array.getJSONObject(i)));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return kandidati;
    }

}
