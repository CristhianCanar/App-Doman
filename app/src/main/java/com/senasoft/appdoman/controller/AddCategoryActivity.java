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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.Category;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddCategoryActivity extends AppCompatActivity {

    private CircleImageView ivSelectImage;
    private ImageView btnSelectImage, btnVolver;
    private EditText editName;
    private Button btnRegister;

    public int RESULT_LOAD_IMAGE = 101;
    private String urlImage = "";
    private boolean control = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        getSupportActionBar().hide();

        initViews();

    }

    private void initViews() {

        ivSelectImage = findViewById(R.id.ivSelectImgCate);
        editName = findViewById(R.id.editNameCateg);

        btnSelectImage = findViewById(R.id.btnSelectImageCate);
        btnSelectImage.setOnClickListener(View -> {
            Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);
        });

        btnVolver = findViewById(R.id.btnVolverAddCat);
        btnVolver.setOnClickListener(View -> finish());

        btnRegister = findViewById(R.id.btnRegistrarCate);
        btnRegister.setOnClickListener(View -> save());


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

            urlImage = cursor.getString(columnIndex);
            cursor.close();
            ivSelectImage.setImageBitmap(BitmapFactory.decodeFile(urlImage));

            control = true;

        } else {
            Toast.makeText(this, "No se selecciono una imagen", Toast.LENGTH_SHORT).show();
        }

    }

    private void save() {

        if (editName.getText().toString().isEmpty() || editName.getText().toString().equals("")) {
            editName.setError("Ingresa el nombre de la categoría");
        } else if (control){

            Category category = new Category();
            ManagerSQLiteHelper managerSQLiteHelper = new ManagerSQLiteHelper(getApplicationContext());

            category.setName(editName.getText().toString());
            category.setUrl_image(urlImage);

            long insert = managerSQLiteHelper.insertCategory(category);

            if (insert > 0 ){
                Toast.makeText(this, "Categoría insertada", Toast.LENGTH_SHORT).show();
                clear();
            }else{
                Toast.makeText(this, "Error en la base de datos", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "Selecciona una imagen o llena el campo nombre", Toast.LENGTH_SHORT).show();
        }

    }

    private void clear() {

        editName.setText("");
        ivSelectImage.setImageResource(R.drawable.btn_img_plus);

    }
}
