package com.senasoft.appdoman.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.AdapterUsers;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;

public class ListUserActivity extends AppCompatActivity {

    private ImageView btnBack;
    private RecyclerView rcList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        getSupportActionBar().hide();


        initViews();
        findRecycler();

    }

    private void initViews() {

        btnBack = findViewById(R.id.btnBackListUser);
        btnBack.setOnClickListener(View -> finish());

        rcList = findViewById(R.id.rcListUsers);

        rcList.setLayoutManager(new LinearLayoutManager(ListUserActivity.this));
        rcList.setHasFixedSize(true);

    }

    private void findRecycler() {

        ManagerSQLiteHelper managerSQLiteHelper = new ManagerSQLiteHelper(ListUserActivity.this);
        AdapterUsers adapterUsers = new AdapterUsers(ListUserActivity.this, managerSQLiteHelper.readDataUser());
        rcList.setAdapter(adapterUsers);

    }

}
