package com.example.app_weather_11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

public class NoteApplication extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter_note adapter_note;
    List<NoteModel> noteModelsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_application);

        recyclerView = findViewById(R.id.addRecyclerView);

        NoteDatebase noteDatebase = new NoteDatebase(this);
        noteModelsList = noteDatebase.getNote();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);
        adapter_note = new Adapter_note(this,noteModelsList);
        recyclerView.setAdapter(adapter_note);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        if (item.getItemId() == R.id.add){
            Intent i = new Intent(NoteApplication.this, AddNoteActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}