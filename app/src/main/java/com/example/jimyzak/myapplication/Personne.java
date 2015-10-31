package com.example.jimyzak.myapplication;

/**
 * Created by jimy zak on 30/10/2015.
 */
public class Personne {
    public String nom;
    public String prenom;
    public String email;
    public String password;
    public String confirmePassword;
    public String pays;
    public String sexe;
    public String phone;

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

    public Personne(String nom, String prenom, String email, String password, String pays, String sexe, String phone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.pays = pays;
        this.sexe = sexe;
        this.phone = phone;
    }
}
