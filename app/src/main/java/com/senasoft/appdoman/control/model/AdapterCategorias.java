package com.senasoft.appdoman.control.model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.senasoft.appdoman.R;

import java.util.ArrayList;

public class AdapterCategorias extends BaseAdapter {

    Context context;

    String[] title = {"Fase uno - Palabras", "Fase dos - Parejas de datos",
                    "Fase tres - Oraciones sencillas", "Fase cuatro - frases", "Fase cinco - Cuentos"};

    public AdapterCategorias(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int i) {
        return title[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        TextView tvTitle;

        view = LayoutInflater.from(context).inflate(R.layout.item_categorias, viewGroup, false);

        tvTitle = view.findViewById(R.id.tvTitleItem);

        tvTitle.setText(title[i]);

        return view;
    }



}
