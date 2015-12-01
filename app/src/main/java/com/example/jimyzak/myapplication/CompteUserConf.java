package com.example.jimyzak.myapplication;

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
        View view = convertView;
        this.positionA = position;
        ComptePerso comptePerso = null;
        if(view==null){
            view = View.inflate(listeDesCompteUser, R.layout.content_list_accounts, null);
            comptePerso = new ComptePerso();
            comptePerso.nom_prenom= (TextView)view.findViewById(R.id.title_name_firstname);
            comptePerso.bSuppAccount = (Button) view.findViewById(R.id.bSuppAccount);
            comptePerso.bSuppAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("delete compteUser");
                    String id =listCompteUser.get(CompteUserConf.this.positionA).getId();
                    System.out.println("id ==> " + id);
                    suppCompteUser task = new suppCompteUser();
                    task.execute(id);
                    listCompteUser.remove(CompteUserConf.this.positionA);
                    CompteUserConf.this.notifyDataSetChanged();


                }
            });

            view.setTag(comptePerso);
        }
        else{
            comptePerso = (ComptePerso) view.getTag();
        }
        Personne personne= listCompteUser.get(position);
        comptePerso.nom_prenom.setText(personne.getPrenom());
        return view;
    }


}
