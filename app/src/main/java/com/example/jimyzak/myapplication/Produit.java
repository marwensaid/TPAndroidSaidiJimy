package com.example.jimyzak.myapplication;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by marwen on 13/12/15.
 */
public class Produit implements Transformer, Serializable {
    String id;
    String nom;
    String description;
    String price;
    String calories;
    String type;
    String picture;
    String discount;
    String createdAt;
    String updatedAt;

    public Produit(String createdAt, String updatedAt, String discount, String picture, String type, String calories, String price, String description, String nom, String id) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.discount = discount;
        this.picture = picture;
        this.type = type;
        this.calories = calories;
        this.price = price;
        this.description = description;
        this.nom = nom;
        this.id = id;
    }
    public Produit() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public <T> T transform(String url, Class<T> type, String encoding, byte[] data, AjaxStatus status) {
        Gson g = new Gson();
        return g.fromJson(new String(data), type);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='"+id+'\''+
                ", name='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", calories='" + calories + '\'' +
                ", type='" + type + '\'' +
                ", picture='" + picture + '\'' +
                ", discount='" + discount + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}




