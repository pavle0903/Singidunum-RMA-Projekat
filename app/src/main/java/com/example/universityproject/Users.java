package com.example.universityproject;

import java.io.Serializable;
import java.util.ArrayList;

public class Users implements Serializable {
    public static final String TABLE_NAME = "users";
    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIEDL_JMBG = "jmbg";
    public static final String FIELD_PRED_GLASAO = "pred_glasao";
    public static final String FIELD_PARL_GLASAO = "parl_glasao";

    //private int userId;
    private String username;
    private String password;
    private String jmbg;
    private String pred_glas;
    private String parl_glas;

    public Users(){}

    public Users(String username, String password, String jmbg, String pred_glas, String parl_glas) {
        this.username = username;
        this.password = password;
        this.jmbg = jmbg;
        this.pred_glas = pred_glas;
        this.parl_glas = parl_glas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getPred_glas(){return pred_glas;}

    public void setPred_glas(String pred_glas){this.pred_glas = pred_glas;}

    public String getParl_glas(){return parl_glas;}

    public void setParl_glas(String parl_glas){this.parl_glas = parl_glas;}

    @Override
    public String toString() {
        return "Korisnik{" +
                ", username='" + username + '\'' +
                ", password=" + password +
                ", jmbg=" + jmbg +
                ", pred_glas" + pred_glas +
                ", parl_glas" + parl_glas +
                '}';
    }
}
