package com.example.app_weather_11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {
    EditText title, details;
    Button addNoteBtn;
    String todayDate,currentTime;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

         getSupportActionBar().setTitle("Ghi chú");
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title = findViewById(R.id.addNote);
        details = findViewById(R.id.noteDetails);
        addNoteBtn = findViewById(R.id.addNoteBtn);

        calendar = Calendar.getInstance();
        todayDate = calendar.get(Calendar.YEAR)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(calendar.get(Calendar.HOUR))+":"+pad(calendar.get(Calendar.MINUTE));
        Log.d("Calendar", "Date and Time"+todayDate+"and"+currentTime);

        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteModel noteModel = new NoteModel(title.getText().toString(),details.getText().toString(),todayDate,currentTime);
                NoteDatebase db = new NoteDatebase(AddNoteActivity.this);
                db.AddNote(noteModel);

                Intent intent = new Intent(AddNoteActivity.this,NoteApplication.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Đã lưu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String pad(int i) {
        if (i<0) return "0"+i;
        return String.valueOf(i);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
