package com.example.absensiharley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> id, name, date, time, location, description;
    DatabaseHelper db;
    HistoryAdapter adapter;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        imageView = findViewById(R.id.imageBack);
        db = new DatabaseHelper(this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        date = new ArrayList<>();
        time = new ArrayList<>();
        location = new ArrayList<>();
        description = new ArrayList<>();
        recyclerView = findViewById(R.id.rvHistory);
        adapter = new HistoryAdapter(this, id, name, date, time, location, description);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayData() {
        Cursor cursor = db.getData();
        if (cursor.getCount()==0) {
            Toast.makeText(HistoryActivity.this,"Tidak ada data riwayat absen. Mohon mengisi absen untuk dapat melihatnya.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while(cursor.moveToNext()) {
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                date.add(cursor.getString(2));
                location.add(cursor.getString(4));
                description.add(cursor.getString(5));
                time.add(cursor.getString(3));
            }
        }
    }

}