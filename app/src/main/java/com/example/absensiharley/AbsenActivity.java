package com.example.absensiharley;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AbsenActivity extends AppCompatActivity {
    EditText editTextNama, editTextTanggal, editTextWaktu, editTextLokasi;
    Button btnAbsen;
    DatabaseHelper db;
    ImageView imageView;
    RadioButton rb1, rb2, rb3;
    RadioGroup absenRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);

        editTextWaktu = findViewById(R.id.inputWaktu);
        editTextNama = findViewById(R.id.inputNama);
        editTextTanggal = findViewById(R.id.inputTanggalWaktu);
        editTextLokasi = findViewById(R.id.inputLokasi);
        btnAbsen = findViewById(R.id.btnAbsen);
        imageView = findViewById(R.id.imageBack);
        absenRadioGroup = findViewById(R.id.absenRadioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);

        setDateTime();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AbsenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnAbsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DatabaseHelper(AbsenActivity.this);
                String name = editTextNama.getText().toString();
                String date = editTextTanggal.getText().toString();
                String time = editTextWaktu.getText().toString();
                String location = editTextLokasi.getText().toString();
                String description = getSelectedAbsen();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(date) || TextUtils.isEmpty(location) || TextUtils.isEmpty(time))  {
                    Toast.makeText(AbsenActivity.this, "Semua field harus diisi.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean checkInsertData = db.addData(name, date, time, location, description);

                if (checkInsertData == true) {
                    Toast.makeText(AbsenActivity.this, "Data absensi berhasil disimpan!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AbsenActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(AbsenActivity.this, "Gagal menyimpan data absensi.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private String getSelectedAbsen() {
        int selectedId = absenRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        return selectedRadioButton.getText().toString();
    }
    private void setDateTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.getDefault());

        editTextTanggal.setText(dateFormatter.format(calendar.getTime()));
        editTextWaktu.setText(timeFormatter.format(calendar.getTime()));
    }
}


