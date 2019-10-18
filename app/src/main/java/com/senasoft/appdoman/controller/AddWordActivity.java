package com.senasoft.appdoman.controller;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.senasoft.appdoman.R;
import com.senasoft.appdoman.model.Category;
import com.senasoft.appdoman.model.ManagerSQLiteHelper;
import com.senasoft.appdoman.model.Word;

import java.io.IOException;

public class AddWordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editWord;
    private Spinner spCatWord;

    private Button btnSave,  btnRecordAudio;
    private ImageView btnCancel;

    private MediaRecorder recorder = null;
    private String fichero;

    private boolean clicRecord = false;
    private boolean controlAudioSave = false;

    private String[] MY_PERMISSONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO};

    private int CODE_PERMISSONS = 111;
    private ManagerSQLiteHelper managerSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        initViews();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(MY_PERMISSONS[0]) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(MY_PERMISSONS[1]) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(MY_PERMISSONS[2]) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, MY_PERMISSONS, CODE_PERMISSONS);
            }
        }
    }

    private void initViews() {

        ManagerSQLiteHelper managerSQLiteHelper = new ManagerSQLiteHelper(getApplicationContext());

        editWord = findViewById(R.id.editPalabraReg);
        spCatWord = findViewById(R.id.spCategoriaReg);

        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(AddWordActivity.this, android.R.layout.simple_dropdown_item_1line, managerSQLiteHelper.readCategory());
        spCatWord.setAdapter(adapter);

        btnSave = findViewById(R.id.btnGuardarReg);
        btnSave.setOnClickListener(this);

        btnCancel = findViewById(R.id.btnCancelarReg);
        btnCancel.setOnClickListener(this);

        btnRecordAudio = findViewById(R.id.btnRecordAudio);
        btnRecordAudio.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnGuardarReg:
                saveWord();
                break;
            case R.id.btnCancelarReg:
                finish();
                break;
            case R.id.btnRecordAudio:

                if (clicRecord && recorder != null) {
                    recorder.stop();

                    btnRecordAudio.setTextColor(getColor(R.color.colorWhithe));
                    btnRecordAudio.setText(getResources().getString(R.string.grabaraudio));
                    btnRecordAudio.setBackgroundResource(R.drawable.bg_button);

                    recorder = null;

                } else {
                    recordAudio();
                    controlAudioSave = true;
                }

                break;
        }

    }

    private void saveWord() {

        managerSQLiteHelper = new ManagerSQLiteHelper(AddWordActivity.this);
        Word word = new Word();

        if (editWord.getText().toString().isEmpty() || editWord.getText().toString().equals("")) {
            editWord.setError("Digite la palabra");
        } else {
            if (controlAudioSave) {

                word.setName(editWord.getText().toString());
                word.setId_categoriy(spCatWord.getSelectedItemPosition() + 1);
                word.setUrl_auidio(fichero);

                long mInsert = managerSQLiteHelper.insertDataWord(word);

                if (mInsert > 0) {
                    clear();
                    Toast.makeText(this, "Palabra registrada", Toast.LENGTH_SHORT).show();
                    controlAudioSave = false;
                } else {
                    Toast.makeText(this, "Palabra no registrada", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Graba primero un audio", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void recordAudio() {

        if (editWord.getText().toString().isEmpty()) {

            editWord.setError("Llena el campo, por favor");
            editWord.requestFocus();

        } else {

            fichero = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio" + editWord.getText().toString() + ".3gp";

            recorder = new MediaRecorder();
            recorder.setOutputFile(fichero);
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);

            try {
                recorder.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            recorder.start();
            btnRecordAudio.setTextColor(getColor(R.color.azuloscuro));
            btnRecordAudio.setText("Grabando...");
            btnRecordAudio.setBackgroundResource(R.drawable.grabandoback);
            clicRecord = true;

        }
    }

    private void clear() {
        editWord.setText("x|");
    }

}