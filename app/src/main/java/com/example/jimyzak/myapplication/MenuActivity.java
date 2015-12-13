package com.example.jimyzak.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    public String url="http://92.243.14.22:1337/menu/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AQuery aQuery = new AQuery(this);
        final ArrayList<Menu> menuArrayList = new ArrayList<Menu>();
        aQuery.ajax(url, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray json, AjaxStatus status) {
                ListView lst = (ListView) findViewById(R.id.list_menus);
                if (json != null) {
                    for (int i = 0; i < json.length(); i++) {
                        try {
                            JSONObject o = json.getJSONObject(i);
                            JSONObject oDetail = o.getJSONObject("0");
                            Menu m = new Menu();
                            m.setPrice(oDetail.getDouble("price"));
                            menuArrayList.add(m);
                        } catch (JSONException e) {

                            continue;
                        }
                    }
                    MenuItems menuItems = new MenuItems(MenuActivity.this, menuArrayList);
                    lst.setAdapter(menuItems);
                } else {
                    Log.d("ListeMenus", "Stack : Ajax stack trace");
                }
            }
        });
    }
}
