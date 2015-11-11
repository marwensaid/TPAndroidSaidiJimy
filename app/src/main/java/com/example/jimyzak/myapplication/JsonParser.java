package com.example.jimyzak.myapplication;


import com.androidquery.callback.Transformer;import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.google.gson.Gson;
/**
 * Created by marwen on 11/11/15.
 */
public class JsonParser implements Transformer {
    public <T> T transform(String url, Class<T> type, String encoding, byte[] data, AjaxStatus status) {
        Gson gson = new Gson();
        return gson.fromJson(new String(data), type);
    }

}
