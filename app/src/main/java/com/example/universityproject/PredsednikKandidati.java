package com.example.universityproject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PredsednikKandidati {

    private int id, glasova;
    private String ime, prezime, detalji, slika, datumRodjenja, mandata, stranka, potpredsednik;

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

    public String getSlika(){return this.slika;}

    public void setSlika(String slika){this.slika = slika;}

    public int getGlasova(){return this.glasova;}

    public void setGlasova(int glasova) {this.glasova = glasova;}

    public String getDatumRodjenja(){return this.datumRodjenja;}

    public void setDatumRodjenja(String datumRodjenja){this.datumRodjenja = datumRodjenja;}

    public String getMandata(){return this.mandata;}

    public void setMandata(String mandata){this.mandata = mandata;}

    public String getStranka(){return this.stranka;}

    public void setStranka(String stranka){this.stranka = stranka;}

    public String getPotpredsednik(){return this.potpredsednik;}

    public void setPotpredsednik(String potpredsednik){this.potpredsednik = potpredsednik;}


    public PredsednikKandidati(){

    }

    public PredsednikKandidati(int id, String ime, String prezime, String detalji, String slika, int glasova, String datumRodjenja, String mandata, String stranka, String potpredsednik){
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.detalji = detalji;
        this.slika = slika;
        this.glasova = glasova;
        this.datumRodjenja = datumRodjenja;
        this.mandata = mandata;
        this.stranka = stranka;
        this.potpredsednik = potpredsednik;
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
            if(json.has("slika")){
                element.slika = json.getString("slika");
            }
            if(json.has("glasova")){
                element.glasova = json.getInt("glasova");
            }
            if(json.has("datumRodjenja")){
                element.datumRodjenja = json.getString("datumRodjenja");
            }
            if(json.has("mandata")){
                element.mandata = json.getString("mandata");
            }
            if(json.has("stranka")){
                element.stranka = json.getString("stranka");
            }
            if(json.has("potpredsednik")){
                element.potpredsednik = json.getString("potpredsednik");
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
            o.put("slika", r.getSlika());
            o.put("glasova", r.getGlasova());
            o.put("datumRodjenja", r.getDatumRodjenja());
            o.put("mandata", r.getMandata());
            o.put("stranka", r.getStranka());
            o.put("potpredsednik", r.getPotpredsednik());
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
