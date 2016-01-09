package com.example.jimyzak.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by jimy zak on 09/01/2016.
 */
public class MenuGeneralActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menugeneral);

        Button buttonserv = (Button) findViewById(R.id.buttonserv);
        buttonserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuGeneralActivity.this, ListeDesCompteUser.class));
            }
        });
        Button buttonprod = (Button) findViewById(R.id.buttonprod);
        buttonprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuGeneralActivity.this, ListProduit.class));
            }
        });
    }

}
