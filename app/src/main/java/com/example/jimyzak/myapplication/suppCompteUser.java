package com.example.jimyzak.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by msaidi on 30/11/2015.
 */

public class suppCompteUser extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        URL url = null;

        try {
            url = new URL("http://92.243.14.22/person/" + params[0]);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestProperty("Content-Type", "application/xml");
            httpCon.setRequestMethod("DELETE");
            httpCon.connect();
            Log.d("stack", "" + httpCon.getResponseCode());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Loaging ...";
    }
}
