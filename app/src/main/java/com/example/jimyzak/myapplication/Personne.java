package com.example.jimyzak.myapplication;

import android.util.Log;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TransferQueue;

/**
 * Created by jimy zak on 30/10/2015.
 */
public class Personne implements Serializable, Transformer {


    public String nom;
    public String prenom;
    public String email;
    public String password;
    public String confirmePassword;
    public String pays;
    public String sexe;
    public String phone;
    public String id;
    public String creation;
    public String connectee;
    public String timeCreation;
    public String timeMAJ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreation() {
        return creation;
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    public String getConnectee() {
        return connectee;
    }

    public void setConnectee(String connectee) {
        this.connectee = connectee;
    }

    public String getTimeCreation() {
        return timeCreation;
    }

    public void setTimeCreation(String timeCreation) {
        this.timeCreation = timeCreation;
    }

    public String getTimeMAJ() {
        return timeMAJ;
    }

    public void setTimeMAJ(String timeMAJ) {
        this.timeMAJ = timeMAJ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getConfirmePassword() {
        return confirmePassword;
    }

    public void setConfirmePassword(String confirmePassword) {
        this.confirmePassword = confirmePassword;
    }

    public Personne() {
    }

    public Personne(String nom, String prenom, String email, String password, String pays, String sexe, String phone, String id, String creation, String connectee, String timeCreation, String timeMAJ) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.pays = pays;
        this.sexe = sexe;
        this.phone = phone;
        this.id = id;
        this.creation = creation;
        this.connectee = connectee;
        this.timeCreation = timeCreation;
        this.timeMAJ = timeMAJ;
    }
    public Personne(JSONObject object){

        Log.w("Object json : ", object.toString());

        try {
            this.id = object.getString("id");
            this.nom = object.getString("nom");
            this.prenom = object.getString("prenom");
            this.sexe = object.getString("sexe");
            this.email = object.getString("email");
            this.phone = object.getString("telephone");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "CompteUser{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telephone='" + phone + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", createdby='" + creation + '\'' +
                ", sexe='" + sexe + '\'' +
                ", connected='" + connectee + '\'' +
                ", createdAt='" + timeCreation + '\'' +
                ", updatedAt='" + timeMAJ + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public <T> T transform(String url, Class<T> type, String encoding, byte[] data, AjaxStatus status) {
        Gson g = new Gson();
        return g.fromJson(new String(data), type);
    }
    public static ArrayList<Personne> fromJson(JSONArray jsonObjects) {
        ArrayList<Personne> persons = new ArrayList<>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                persons.add(new Personne(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return persons;
    }
}
