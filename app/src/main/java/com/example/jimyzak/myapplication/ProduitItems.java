package com.example.jimyzak.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marwen on 13/12/15.
 */
public class ProduitItems extends BaseAdapter{

    public Context context;
    public List<Produit> produitList;
    public int firstPosition;

    public ProduitItems(Context context, List<Produit> produitList, int firstPosition) {
        this.context = context;
        this.produitList = produitList;
        this.firstPosition = firstPosition;
    }

    public ProduitItems(ListProduit listProduit, ArrayList<Produit> produitArrayList) {

    }

    public ProduitItems(ListProduits listProduits, ArrayList<Produit> produitsArrayList) {

    }

    @Override
    public int getCount() {
        return produitList.size();
    }

    @Override
    public Object getItem(int position) {
        return produitList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(produitList.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        this.firstPosition = position;
        MonitorViewProduit monitorViewProduit = new MonitorViewProduit();

        if (view == null) {
            view = View.inflate(context, R.layout.content_liste_compte, null);
            monitorViewProduit.nom = (TextView) view.findViewById(R.id.title_nom_prenom);

            view.setTag(monitorViewProduit);
        }else {
            monitorViewProduit = (MonitorViewProduit) view.getTag();
        }
        Produit produit = produitList.get(position);
        monitorViewProduit.nom.setText(produit.getNom());
        return view;
    }
}
