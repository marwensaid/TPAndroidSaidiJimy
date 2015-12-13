package com.example.jimyzak.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by marwen on 13/12/15.
 */
public class MenuItems extends BaseAdapter {
    Context context;
    ArrayList<Menu> menuArrayList;

    public MenuItems(Context context, ArrayList<Menu> menuArrayList) {
        this.context = context;
        this.menuArrayList = menuArrayList;
    }

    @Override
    public int getCount() {
        return menuArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        MonitorViewMenu monitorViewMenu = new MonitorViewMenu();
        while (view == null) {
            view = View.inflate(context, R.layout.content_liste_menu, null);
            monitorViewMenu.libelleMenu = (TextView) view.findViewById(R.id.libelleMenu);
            view.setTag(monitorViewMenu);
        }

        monitorViewMenu = (MonitorViewMenu) view.getTag();
        monitorViewMenu.libelleMenu.setText((int) menuArrayList.get(position).getPrice());
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
