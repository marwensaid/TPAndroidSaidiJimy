package com.example.jimyzak.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marwen on 01/12/15.
 */
public class CompteUserConf extends BaseAdapter {

    private Context context;
    public List<Personne> listCompteUser;
    public int positionA;
    private View.OnClickListener listener;

    public CompteUserConf(Context context, List<Personne> listCompteUser,View.OnClickListener listener) {
        this.context = context;
        this.listCompteUser = listCompteUser;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return listCompteUser.size();
    }

    @Override
    public Object getItem(int position) {
        return listCompteUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ComptePerso comptePerso = null;
        if(view==null){
            view = View.inflate(context, R.layout.content_liste_compte, null);
            comptePerso = new ComptePerso();
            comptePerso.nom_prenom= (TextView)view.findViewById(R.id.txt_view_buzz_username);
            comptePerso.date_creation = (TextView) view.findViewById(R.id.txt_view_buzz_is_connected);
            view.setTag(comptePerso);
            comptePerso.delete_compte = (TextView) view.findViewById(R.id.txt_view_delete_serveur);
        }
        else{
            comptePerso = (ComptePerso) view.getTag();
        }
        Personne personne= listCompteUser.get(position);
        comptePerso.delete_compte.setTag(position);
        comptePerso.delete_compte.setOnClickListener(this.listener);

        comptePerso.nom_prenom.setText(personne.getNom());
        comptePerso.date_creation.setText("deconnecté");
        return view;
    }

}
