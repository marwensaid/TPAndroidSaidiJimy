package com.example.jimyzak.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

/**
 * Created by marwen on 20/12/15.
 */
public class ToolbarNavActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView textNomValue;
    private Button buttonAffichagePersonne;
    private Button buttonAffichageProduits;
    private Button buttonAffichageMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.layout_toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.toolbar_open, R.string.toolbar_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.toolbar_view);
        navigationView.setNavigationItemSelectedListener(this);

        Personne personne = (Personne) getIntent().getSerializableExtra("nom_prenom");
        this.textNomValue =  (TextView)(this.findViewById(R.id.textViewTitleHome));
        this.textNomValue.setText(personne.getPrenom() + " " + personne.getNom());

        this.buttonAffichagePersonne = (Button)findViewById(R.id.buttonAffichageUtilisateurView);
        this.buttonAffichagePersonne.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ToolbarNavActivity.this, ListeDesCompteUser.class));
            }
        });

        this.buttonAffichageProduits = (Button)findViewById(R.id.buttonAffichageProduitView);
        this.buttonAffichageProduits.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ToolbarNavActivity.this, ListProduit.class));
            }
        });

        this.buttonAffichageMenu = (Button)findViewById(R.id.bMenu);
        this.buttonAffichageMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ToolbarNavActivity.this, MenuActivity.class));
            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        return true;
    }
}
