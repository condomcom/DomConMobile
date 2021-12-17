package com.example.condom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.condom.R;

public class DetailsActivity extends AppCompatActivity {
    private String title, description, beginning, speaker, end, direction, place, date;
    private TextView delTitle, delDescription, delBeginning, delSpeaker, delEnd, delDirection, delPlace, delDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        description = intent.getStringExtra("description");
        beginning = intent.getStringExtra("beginning");
        speaker = intent.getStringExtra("speaker");
        end = intent.getStringExtra("end");
        direction = intent.getStringExtra("direction");
        place = intent.getStringExtra("place");
        date = intent.getStringExtra("date");

        delTitle = findViewById(R.id.text_title_details);
        delDescription = findViewById(R.id.text_description_details);
        delBeginning = findViewById(R.id.text_time_start_details);
        delSpeaker = findViewById(R.id.text_speaker_details);
        delEnd = findViewById(R.id.text_time_end_details);
        delDirection = findViewById(R.id.text_direction_details);
        delPlace = findViewById(R.id.text_place_details);
        delDate = findViewById(R.id.text_date_details);

        delTitle.setText(title);
        delDescription.setText(description);
        delBeginning.setText(beginning);
        delSpeaker.setText(speaker);
        delEnd.setText(end);
        delDirection.setText(direction);
        delPlace.setText(place);
        delDate.setText(date);

        delDescription.setMovementMethod(new ScrollingMovementMethod());

    }
}