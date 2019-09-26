package com.senasoft.appdoman.controller;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;
import com.senasoft.appdoman.model.User;


import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsAdmin extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnTakeAvatar, btnAddWord;
    private CircleImageView imgAvatar;
    private Button btnRegistrar;
    private EditText editName;
    private RadioButton rbGeneroUno, rbGeneroDos;

    public int RESULT_LOAD_IMAGE = 101;
    private String picturePath;
    private ManagerSQLiteHelper managerSQLiteHelper;

    private String genero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_admin);
        getSupportActionBar().hide();

        initViews();
        managerSQLiteHelper = new ManagerSQLiteHelper(this);

    }

    private void initViews() {

        btnTakeAvatar = findViewById(R.id.btnTakeAvatar);
        btnTakeAvatar.setOnClickListener(this::onClick);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(this::onClick);

        btnAddWord = findViewById(R.id.btnAddWord);
        btnAddWord.setOnClickListener(this::onClick);

        editName = findViewById(R.id.editName);
        imgAvatar = findViewById(R.id.imgAvatarReg);

        rbGeneroUno = findViewById(R.id.rbGeneroUno);
        rbGeneroDos = findViewById(R.id.rbGeneroDos);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnTakeAvatar:
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
                break;
            case R.id.btnRegistrar:
                saveUser();
                break;

            case R.id.rbGeneroUno:
                if (rbGeneroUno.isChecked()) {
                    genero = getResources().getString(R.string.genero1);
                }
                break;
            case R.id.rbGeneroDos:
                if (rbGeneroDos.isChecked()) {
                    genero = getResources().getString(R.string.genero2);
                }
                break;

            case R.id.btnAddWord:
                 startActivity(new Intent(SettingsAdmin.this, AddWordActivity.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            imgAvatar.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        } else {
            Toast.makeText(this, "No se selleciono una imagen", Toast.LENGTH_SHORT).show();
        }

    }

    private void saveUser() {

        User user = new User();

        if (picturePath != null) {
            if (editName.getText().toString().equals("")) {
                editName.setError("Digita un nombre");
            } else {

                user.setUsuName(editName.getText().toString());
                user.setUsuGenero(genero);
                user.setUrlAvatar(picturePath);

                long insert = managerSQLiteHelper.insertDataUser(user);

                if (insert > 0) {
                    Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                    clear();
                } else {
                    Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, "Selecciona un avatar", Toast.LENGTH_SHORT).show();
        }

    }

    public void clear() {
        editName.setText("");
        imgAvatar.setImageResource(R.drawable.btn_img_plus);
    }
}
