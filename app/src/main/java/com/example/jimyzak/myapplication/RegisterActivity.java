package com.example.jimyzak.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RegisterActivity extends AppCompatActivity {
    Personne p = new Personne();
    boolean backtrancking = false;
    View view = null;


    EditText tnom = (EditText) findViewById(R.id.editTextNom);
    EditText tprenom = (EditText) findViewById(R.id.editTextPrenom);
    RadioButton tsexemal = (RadioButton) findViewById(R.id.radioButtonMale);
    RadioButton tsexefemme = (RadioButton) findViewById(R.id.radioButtonFemme);
    EditText tpays = (EditText) findViewById(R.id.spinner);
    EditText tphone = (EditText) findViewById(R.id.editTextPhone);
    EditText temail = (EditText) findViewById(R.id.editTextMail);
    EditText tpass = (EditText) findViewById(R.id.editTextPassword);
    EditText tconfirmpass = (EditText) findViewById(R.id.editTextConfirmePassword);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String[] sexGenre = {""};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button buttonReg = (Button) findViewById(R.id.buttonRegister);
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.setNom(tnom.getText().toString());
                p.setPrenom(tprenom.getText().toString());
                if (tsexemal.isChecked())
                    p.setSexe(tsexemal.getText().toString());
                if (tsexefemme.isChecked())
                    p.setSexe(tsexefemme.getText().toString());
                p.setPays(tpays.getText().toString());
                p.setPhone(String.valueOf(Integer.parseInt(tphone.getText().toString())));
                p.setEmail(temail.getText().toString());
                p.setPassword(tpass.getText().toString());
                p.setConfirmePassword(tconfirmpass.getText().toString());

                if (TextUtils.isEmpty(p.nom)) {
                    tnom.setError(getString(R.string.error_field_required));
                    view = tprenom;
                    backtrancking = true;
                }

                if (TextUtils.isEmpty(p.prenom)) {
                    tprenom.setError(getString(R.string.error_field_required));
                    view = tprenom;
                    backtrancking = true;
                }

                if (!(tsexemal.isChecked()) && !(tsexefemme.isChecked())) {
                    tsexefemme.setError(getString(R.string.error_field_required));
                    view = tsexefemme;
                    backtrancking = true;
                } else if (tsexemal.isChecked()) {
                    sexGenre[0] = "Male";
                } else if (tsexefemme.isChecked()) {
                    sexGenre[0] = "Female";
                }

                if (TextUtils.isEmpty(p.phone)) {
                    tphone.setError(getString(R.string.error_field_required));
                    view = tphone;
                    backtrancking = true;
                } else if (!phoneValidation(p.phone)) {
                    tphone.setError(getString(R.string.error_invalid_email));
                    view = tphone;
                    backtrancking = true;
                }

                if (TextUtils.isEmpty(p.email)) {
                    temail.setError(getString(R.string.error_field_required));
                    view = temail;
                    backtrancking = true;
                } else if (!emailValidation(p.email)) {
                    temail.setError(getString(R.string.error_invalid_email));
                    view = temail;
                    backtrancking = true;
                }

                if (!p.password.equals(p.confirmePassword)) {
                    tconfirmpass.setError(getString(R.string.error_not_same_passwords));
                    view = tconfirmpass;
                    backtrancking = true;
                }

                new MyTask().execute();
            }
        });

        String[] countries = getResources().getStringArray(R.array.country_arrays);
        Spinner sp = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
        sp.setAdapter(dataAdapter);
    }

    private boolean phoneValidation(String phone) {
        return phone.length() >= 6;
    }

    private boolean emailValidation(String email) {
        email.contains("@");
        return Boolean.parseBoolean(email);
    }

    class MyTask extends AsyncTask<String, String, String> {

        private String url = "http://92.243.14.22/person/";

        @Override
        protected String doInBackground(String... voids) {

            try {
                HttpClient client = new DefaultHttpClient();

                HttpPost post = new HttpPost(url);

                // add header

                post.setHeader("Content-Type", "application/json");
                JSONObject obj = new JSONObject();
                obj.put("prenom", p.getPrenom());
                obj.put("nom", p.getNom());
                obj.put("pays", p.getPays());
                obj.put("sex", p.getSexe());
                obj.put("telephone", p.getPhone());
                obj.put("email", p.getEmail());
                obj.put("password", p.getPassword());


                StringEntity entity;
                entity = new StringEntity(obj.toString());

                post.setEntity(entity);

                HttpResponse response = client.execute(post);
                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Post parameters : " + post.getEntity());
                System.out.println("Response Code : " +
                        response.getStatusLine().getStatusCode());

                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

                System.out.println(result.toString());
                return result.toString();
            } catch (Exception e) {

            }
            return null;
        }

        ProgressDialog progressDialog;

        public void showProgressDialog(boolean isVisible) {
            if (isVisible) {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(RegisterActivity.this);
                    progressDialog.setMessage(RegisterActivity.this.getResources().getString(R.string.please_wait));
                    progressDialog.setCancelable(false);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            progressDialog = null;
                        }
                    });
                    progressDialog.show();
                }
            } else {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog(true);

        }

        @Override
        protected void onPostExecute(String theResponse) {
            super.onPostExecute(theResponse);
            showProgressDialog(false);
            Toast.makeText(RegisterActivity.this, R.string.inscription_ok, Toast.LENGTH_LONG).show();
        }
    }
}

