package com.example.jimyzak.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by marwen on 01/12/15.
 */
public class ListeDesCompteUser extends AppCompatActivity implements View.OnClickListener {
    ListView listeServeurs;
    CompteUserConf userconf;
    ProgressDialog progressDialog;
    Button ajoutuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_des_compte_user);

        listeServeurs = (ListView) findViewById(R.id.list_compte);

        setProgressDialog();

        new GetListPersonsTask().execute();

        ajoutuser = (Button)findViewById(R.id.buttonAjoutUser);
        ajoutuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListeDesCompteUser.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(final View v) {
        if (v.getId() == R.id.txt_view_delete_serveur) {

            new AlertDialog.Builder(this)
                    .setTitle("suppression user")
                    .setMessage("vous êtes sur de vuloir supprimer?")
                    .setPositiveButton("oui", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new DeleteTask().execute((Integer) v.getTag());
                        }

                    })
                    .setNegativeButton("annuler", null)
                    .show();
        }
    }

    public void setProgressDialog() {
        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Patientez...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    /******************
     * ASYNC TASK GET LISTE SEVEURS
     ******************/

    private class GetListPersonsTask extends AsyncTask<String, Void, List<Personne>> {

        @Override
        protected List<Personne> doInBackground(String... params) {

            try {
                JSONArray jsonArray = new JSONArray(ApiCallService.getInstance().doGet(ListeDesCompteUser.this,
                        progressDialog, "http://92.243.14.22:1337/person").getResult());
                return Personne.fromJson(jsonArray);

            } catch (Exception e) {
                Log.w("Erreur", e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<Personne> personneList) {
            super.onPostExecute(personneList);

            userconf = new CompteUserConf(getApplicationContext(), personneList, ListeDesCompteUser.this);

            listeServeurs.setAdapter(userconf);

            Toast.makeText(ListeDesCompteUser.this, "Liste chargée", Toast.LENGTH_LONG).show();
        }
    }

    /******************
     * ASYNC TASK DELETE SEVEUR
     ******************/

    private class DeleteTask extends AsyncTask<Integer, Void, Void> {

        String id;
        Personne Del;
        int pos;

        @Override
        protected Void doInBackground(Integer... params) {

            try {
                pos = params[0];
                id = ((Personne) userconf.getItem(pos)).getId();

                JSONObject jsonObj = new JSONObject(ApiCallService.getInstance().doDelete(ListeDesCompteUser.this, progressDialog, "http://92.243.14.22:1337/person/" + id).getResult());

                Del = new Personne(jsonObj);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (Del != null) {
                Toast.makeText(ListeDesCompteUser.this, "Supression ok " , Toast.LENGTH_LONG).show();
                userconf.listCompteUser.remove(pos);
            }
            userconf.notifyDataSetChanged();
        }
    }
}
