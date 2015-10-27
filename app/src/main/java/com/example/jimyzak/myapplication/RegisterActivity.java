package com.example.jimyzak.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
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
                obj.put("prenom", "Marwen");
                obj.put("nom", "Zak");
                obj.put("sex", "male");
                obj.put("telephone", "060606060");
                obj.put("email", "marwen@zak.com");
                obj.put("createdby", "MarwenZak");
                obj.put("password", "1234");


                StringEntity entity;
                entity = new StringEntity(obj.toJSONString());

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

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String theResponse) {
            super.onPostExecute(aVoid);
        }
    }

