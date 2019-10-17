package com.senasoft.appdoman.controller;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.Boy;
import com.senasoft.appdoman.model.DatePickerFragment;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private ImageView btnTakeAvatar, btnExitUser;
    private CircleImageView imgAvatar;
    private Button btnRegistrar;
    private EditText editName, editDateBirth;
    private RadioButton rbGeneroUno, rbGeneroDos;

    public int RESULT_LOAD_IMAGE = 101;
    private String picturePath;
    private ManagerSQLiteHelper managerSQLiteHelper;

    private String genero = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        initViews();
        managerSQLiteHelper = new ManagerSQLiteHelper(this);

    }

    private void initViews() {

        btnTakeAvatar = findViewById(R.id.btnTakeAvatar);
        btnTakeAvatar.setOnClickListener(this::onClick);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(this::onClick);

        editDateBirth = findViewById(R.id.editDateBirth);
        editDateBirth.setOnClickListener(this::onClick);

        btnExitUser = findViewById(R.id.btnExitUser);
        btnExitUser.setOnClickListener(this::onClick);

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

            case R.id.editDateBirth:
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Fecha de nacimiento");
                break;

            case R.id.btnExitUser:
                finish();
                break;


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
            Toast.makeText(this, "No se selecciono una imagen", Toast.LENGTH_SHORT).show();
        }

    }

    private void saveUser() {

        Boy boy = new Boy();

        if (picturePath != null) {
            if (editName.getText().toString().equals("")) {
                editName.setError("Digita un nombre");
            } else if (editDateBirth.getText().toString().isEmpty()) {
                editDateBirth.setError("Digita la fecha de nacimiento");
            } else {

                boy.setName(editName.getText().toString());

                if (rbGeneroUno.isChecked()) {
                    genero = "Masculino";
                }

                if (rbGeneroDos.isChecked()) {
                    genero = "Femenino";
                }

                boy.setGenero(genero);
                boy.setFecha_nacimiento(editDateBirth.getText().toString());
                boy.setUrl_avatar(picturePath);

                long insert = managerSQLiteHelper.insertDataUser(boy);

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
        editDateBirth.setText("");
        imgAvatar.setImageResource(R.drawable.btn_img_plus);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateString = format.format(calendar.getTime());

        editDateBirth.setText(currentDateString);

    }

}
