package com.example.universityproject;

import java.io.Serializable;
import java.util.ArrayList;

public class Users implements Serializable {
    public static final String TABLE_NAME = "users";
    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIEDL_JMBG = "jmbg";

    //private int userId;
    private String username;
    private String password;
    private String jmbg;

    public Users(){}

    public Users(String username, String password, String jmbg) {
        this.username = username;
        this.password = password;
        this.jmbg = jmbg;
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

    @Override
    public String toString() {
        return "Korisnik{" +
                ", username='" + username + '\'' +
                ", password=" + password +
                ", jmbg=" + jmbg +
                '}';
    }
}
