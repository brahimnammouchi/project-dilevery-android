package com.example.dilevery;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {
    Activity context;
    ArrayList<String> items;
    MyAdapter(Activity c, ArrayList<String> a) {
        super(c, R.layout.commadne_add, a);
        this.context = c;
        this.items = a;
    }

        @Override
                public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View add = inflater.inflate(R.layout.commadne_add,null);
            TextView article = (TextView) add.findViewById(R.id.name);
            article.setText(items.get(position));
            Float valeur = Float.valueOf(items.get(position));
            return add;
        }

    }
