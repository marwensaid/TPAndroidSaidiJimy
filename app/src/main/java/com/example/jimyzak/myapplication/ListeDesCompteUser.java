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
import java.util.logging.Logger;

/**
 * Created by marwen on 01/12/15.
 */
public class ListeDesCompteUser extends AppCompatActivity {
    AQuery aq;
    String url = "http://92.243.14.22/person/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_accounts);
        AQuery aq = new AQuery(this);
        final ArrayList<Personne> listCompteUser = new ArrayList<Personne>();

        aq.ajax(url, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray json, AjaxStatus status) {
                ListView lst = (ListView) findViewById(R.id.list_accounts);
                if (json != null) {
                    //successful ajax call, show status code and json content

                    for (int i = 0; i < json.length(); i++) {
                        try {
                            JSONObject o = json.getJSONObject(i);
                            Personne a = new Personne();
                            if (!o.getString("nom").isEmpty() && !o.getString("prenom").isEmpty() && !o.getString("connectee").isEmpty() && !o.getString("timeCreation").isEmpty()) {
                                a.setNom(o.getString("nom"));
                                a.setPrenom(o.getString("prenom"));
                                a.setConnectee(o.getString("connectee"));
                                a.setTimeCreation(o.getString("timeCreation"));
                                a.setId(o.getString("id"));
                            } else {
                                throw new JSONException("stack //TODO");
                            }

                            listCompteUser.add(a);
                        } catch (JSONException e) {
                            status.getCode();
                        }
                    }
                    CompteUserConf compteUserConf = new CompteUserConf(ListeDesCompteUser.this, listCompteUser);
                    lst.setAdapter(compteUserConf);
                } else {
                    Log.d("listCompteUser", "Stack ");
                }
            }
        });
    }
}
