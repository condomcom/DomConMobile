package com.example.condom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class NewHomeActivityActivity extends AppCompatActivity {
    private RecyclerView recyclerViewNav;
    private NavRVAdapter adapterNav;
    private ArrayList<NavRVItem> navRVItemArrayList;
    private RecyclerView recyclerViewDynamic;
    private ArrayList<newSpeakerRVItem> newSpeakerRVItemArrayList;
    private SpeakerRVAdapter speakerRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_home_activity);

        navRVItemArrayList = new ArrayList<>();
        newSpeakerRVItemArrayList = new ArrayList<>();

        navRVItemArrayList.add(new NavRVItem(R.drawable.speaker, "Спикеры"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.work, "Выступления"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.challenge, "Активности"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.favourite, "Избранное"));

        recyclerViewNav = findViewById(R.id.nav_recyclerView);
        recyclerViewDynamic = findViewById(R.id.item_recyclerView);

        adapterNav = new NavRVAdapter(navRVItemArrayList);
        recyclerViewDynamic.setLayoutManager(new LinearLayoutManager(this));
        speakerRVAdapter = new SpeakerRVAdapter(recyclerViewDynamic, this, newSpeakerRVItemArrayList);

        recyclerViewNav.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNav.setAdapter(adapterNav);
        recyclerViewDynamic.setAdapter(speakerRVAdapter);

        newSpeakerRVItemArrayList.add(new newSpeakerRVItem(0,"Василий Петрович",R.drawable.rofl_photo, "Разработчик"));
        newSpeakerRVItemArrayList.add(new newSpeakerRVItem(0,"Василий Петрович",R.drawable.rofl_photo, "Разработчик"));
        newSpeakerRVItemArrayList.add(new newSpeakerRVItem(0,"Василий Петрович",R.drawable.rofl_photo, "Разработчик"));
        newSpeakerRVItemArrayList.add(new newSpeakerRVItem(0,"Василий Петрович",R.drawable.rofl_photo, "Разработчик"));
        newSpeakerRVItemArrayList.add(new newSpeakerRVItem(0,"Василий Петрович",R.drawable.rofl_photo, "Разработчик"));

    }
}