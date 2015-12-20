package com.example.jimyzak.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import static com.example.jimyzak.myapplication.R.string.toolbar_close;
import static com.example.jimyzak.myapplication.R.string.toolbar_open;

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
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.layout_toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, toolbar_open, toolbar_close);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.layout_toolbar);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id != R.id.nav_camara) {
            if (id != R.id.nav_gallery) {
                if (id != R.id.nav_slideshow) {
                    switch (id) {
                        case R.id.nav_send:
                            break;
                    }
                }
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.layout_toolbar);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
