package com.example.jimyzak.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by marwen on 13/12/15.
 */
public class ListProduit extends AppCompatActivity {

    private String url = "http://92.243.14.22:1337/product/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produit);
        AQuery aQuery = new AQuery(this);
        final ArrayList<Produit> produitArrayList = new ArrayList<Produit>();

        aQuery.ajax(url, JSONArray.class, new AjaxCallback<JSONArray>() {

            @Override
            public void callback(String url, JSONArray json, AjaxStatus status) {
                ListView lst = (ListView) findViewById(R.id.list_produit);
                if (json != null) {

                    for (int i = 0; i < json.length(); i++) {
                        try {
                            JSONObject o = json.getJSONObject(i);
                            Produit produit = new Produit();
                            if (!o.getString("name").isEmpty() && !o.getString("price").isEmpty() && !o.getString("createdAt").isEmpty()) {
                                produit.setNom(o.getString("nome"));
                                produit.setPrice(o.getString("price"));
                                produit.setCreatedAt(o.getString("createdAt"));

                                if (!o.getString("id").isEmpty()) {
                                    produit.setId(o.getString("id"));
                                } else {
                                    produit.setId("");
                                }

                                if (!o.getString("description").isEmpty()) {
                                    produit.setDescription(o.getString("description"));
                                } else {
                                    produit.setDescription("");
                                }
                                if (!o.getString("calories").isEmpty()) {
                                    produit.setCalories(o.getString("calories"));
                                } else {
                                    produit.setCalories("");
                                }
                                if (!o.getString("type").isEmpty()) {
                                    produit.setType(o.getString("type"));
                                } else {
                                    produit.setType("");
                                }
                                if (!o.getString("picture").isEmpty()) {
                                    produit.setPicture(o.getString("picture"));
                                } else {
                                    produit.setPicture("");
                                }
                                if (!o.getString("discount").isEmpty()) {
                                    produit.setDiscount(o.getString("discount"));
                                } else {
                                    produit.setDiscount("");
                                }
                                if (!o.getString("description").isEmpty()) {
                                    produit.setDescription(o.getString("description"));
                                } else {
                                    produit.setDescription("");
                                }
                                if (!o.getString("updatedAt").isEmpty()) {
                                    produit.setUpdatedAt(o.getString("updatedAt"));
                                } else {
                                    produit.setUpdatedAt("");
                                }

                            } else {
                                continue;
                            }

                            produitArrayList.add(produit);
                        } catch (JSONException e) {

                        }
                    }

                    ProduitItems produitItems = new ProduitItems(ListProduit.this, produitArrayList);
                    lst.setAdapter((ListAdapter) produitItems);
                } else {
                    Log.d("ListProduit :", "stack");
                }
            }
        });
    }
}


class ListProduits extends AppCompatActivity {

    String url = "http://92.243.14.22:1337/product/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produits);
        AQuery aq = new AQuery(this);
        final ArrayList<Produit> produitsArrayList = new ArrayList<Produit>();

        aq.ajax(url, JSONArray.class, new AjaxCallback<JSONArray>() {

            @Override
            public void callback(String url, JSONArray json, AjaxStatus status) {
                ListView listView = (ListView) findViewById(R.id.list_produits);
                if (json != null) {

                    for (int i = 0; i < json.length(); i++) {
                        try {
                            JSONObject jsonObject = json.getJSONObject(i);

                            Produit produit = new Produit();

                            if (!jsonObject.getString("name").isEmpty() && !jsonObject.getString("price").isEmpty() && !jsonObject.getString("createdAt").isEmpty()) {
                                produit.setId(jsonObject.getString("id"));
                                produit.setNom(jsonObject.getString("name"));
                                produit.setDescription(jsonObject.getString("description"));
                                produit.setPrice(jsonObject.getString("price"));
                                produit.setCalories(jsonObject.getString("calories"));
                                produit.setType(jsonObject.getString("type"));
                                produit.setPicture(jsonObject.getString("picture"));
                                produit.setDiscount(jsonObject.getString("discount"));
                                produit.setCreatedAt(jsonObject.getString("createdAt"));
                                produit.setUpdatedAt(jsonObject.getString("updatedAt"));
                            } else {
                                throw new JSONException("values is :" + getStatus());
                            }

                            produitsArrayList.add(produit);
                        } catch (JSONException e) {

                            continue;
                        }
                    }
                    ProduitItems produitItems = new ProduitItems(ListProduits.this, produitsArrayList);
                    listView.setAdapter((ListAdapter) produitItems);
                } else {
                    //ajax error, show error code
                    Log.d("ListProduits :", "stack");
                }
            }
        });
    }

}


