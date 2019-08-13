package com.senasoft.appdoman.control;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.control.ui.main.SectionsPagerAdapter;

public class GameActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private LinearLayout linearPuntos;
    private TextView[] puntos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        linearPuntos = findViewById(R.id.idAlinearPuntos);
        agregarIndicadorPuntos(0);

        viewPager.addOnPageChangeListener(viewListener);

    }


    private void agregarIndicadorPuntos(int i) {

        puntos = new TextView[5];
        linearPuntos.removeAllViews();

        for (int x = 0; x < puntos.length; x++) {
            puntos[x] = new TextView(this);
            puntos[x].setText(Html.fromHtml("&#8226;"));
            puntos[x].setTextSize(45);
            puntos[x].setTextColor(getResources().getColor(R.color.colorGris));
            linearPuntos.addView(puntos[x]);
        }

        if (puntos.length > 0)
            puntos[i].setTextColor(getResources().getColor(R.color.colorPrimary));

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            agregarIndicadorPuntos(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}