package com.example.jimyzak.myapplication;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by marwen on 13/12/15.
 */
public class Menu implements Transformer , Serializable {

    double price;
    double discount;
    String server;
    String cooker;
    String items;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getCooker() {
        return cooker;
    }

    public void setCooker(String cooker) {
        this.cooker = cooker;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    @Override
    public <T> T transform(String url, Class<T> type, String encoding, byte[] data, AjaxStatus status) {
        Gson g = new Gson();
        return g.fromJson(new String(data), type);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "price=" + price +
                ", discount=" + discount +
                ", server='" + server + '\'' +
                ", cooker='" + cooker + '\'' +
                ", items='" + items + '\'' +
                '}';

    }

}
