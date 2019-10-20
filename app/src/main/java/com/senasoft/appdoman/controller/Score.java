package com.senasoft.appdoman.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.Boy;
import com.senasoft.appdoman.model.ConexionSQLiteHelper;
import com.senasoft.appdoman.model.Constantes;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Score extends AppCompatActivity {

    //Declarations

    private TextView tvNameOne;
    private TextView tvNameTwo;
    private TextView tvNameThree;
    private ImageView ivPhotoOne, ivPhotoTwo, ivPhotoThree, btnExitScore;
    private ImageView ivScore1, ivScore2, ivScore3;
    private CardView card1, card2, card3;

    private ArrayList<Boy> boyList = null;
    private ManagerSQLiteHelper managerSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        initViews();

        if (boyList == null) {

            managerSQLiteHelper = new ManagerSQLiteHelper(this);
            boyList = new ArrayList<>(managerSQLiteHelper.listBoysForMaxScore());

        }

        fillUsers();

    }

    public void initViews() {

        ivPhotoOne = findViewById(R.id.ivStarOne);
        ivPhotoTwo = findViewById(R.id.ivStarTwo);
        ivPhotoThree = findViewById(R.id.ivStarThree);

        tvNameOne = findViewById(R.id.tvNameOne);
        tvNameTwo = findViewById(R.id.tvNameTwo);
        tvNameThree = findViewById(R.id.tvNameThree);

        btnExitScore = findViewById(R.id.btnExitScore);
        btnExitScore.setOnClickListener(View -> finish());

        ivScore1 = findViewById(R.id.ivScore1);
        ivScore2 = findViewById(R.id.ivScore2);
        ivScore3 = findViewById(R.id.ivScore3);

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);

    }

    private void fillUsers() {

        if (boyList != null && boyList.size() >= 3) {

            try {
                ivPhotoOne.setImageBitmap(BitmapFactory.decodeFile(boyList.get(0).getUrl_avatar()));
                ivPhotoTwo.setImageBitmap(BitmapFactory.decodeFile(boyList.get(1).getUrl_avatar()));
                ivPhotoThree.setImageBitmap(BitmapFactory.decodeFile(boyList.get(2).getUrl_avatar()));
            } catch (OutOfMemoryError error) {
                error.printStackTrace();
            }

            tvNameOne.setText(boyList.get(0).getName());
            tvNameTwo.setText(boyList.get(1).getName());
            tvNameThree.setText(boyList.get(2).getName());

        } else if (boyList.size() == 1) {

            ivPhotoOne.setImageBitmap(BitmapFactory.decodeFile(boyList.get(0).getUrl_avatar()));
            tvNameOne.setText(boyList.get(0).getName());

            ivPhotoTwo.setVisibility(View.INVISIBLE);
            ivPhotoThree.setVisibility(View.INVISIBLE);
            tvNameTwo.setVisibility(View.INVISIBLE);
            tvNameThree.setVisibility(View.INVISIBLE);

            ivScore2.setVisibility(View.INVISIBLE);
            ivScore3.setVisibility(View.INVISIBLE);

            card2.setVisibility(View.INVISIBLE);
            card3.setVisibility(View.INVISIBLE);

        }else{

            ivPhotoOne.setVisibility(View.INVISIBLE);
            ivPhotoTwo.setVisibility(View.INVISIBLE);
            ivPhotoThree.setVisibility(View.INVISIBLE);

            tvNameOne.setVisibility(View.INVISIBLE);
            tvNameTwo.setVisibility(View.INVISIBLE);
            tvNameThree.setVisibility(View.INVISIBLE);

            ivScore1.setVisibility(View.INVISIBLE);
            ivScore2.setVisibility(View.INVISIBLE);
            ivScore3.setVisibility(View.INVISIBLE);

            card1.setVisibility(View.INVISIBLE);
            card2.setVisibility(View.INVISIBLE);
            card3.setVisibility(View.INVISIBLE);

        }

    }


}
