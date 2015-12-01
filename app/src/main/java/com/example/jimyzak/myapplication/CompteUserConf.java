package com.example.jimyzak.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marwen on 01/12/15.
 */
public class CompteUserConf extends BaseAdapter {

    private ListeDesCompteUser listeDesCompteUser;
    public List<Personne> listCompteUser;
    public int positionA;

    public CompteUserConf(ListeDesCompteUser listeDesCompteUser, ArrayList<Personne> listCompteUser) {
        this.listeDesCompteUser = listeDesCompteUser;
        this.listCompteUser = listCompteUser;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


}
