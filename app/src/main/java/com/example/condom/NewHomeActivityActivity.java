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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_home_activity);

        navRVItemArrayList = new ArrayList<>();

        navRVItemArrayList.add(new NavRVItem(R.drawable.speaker, "Спикеры"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.work, "Вступления"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.challenge, "Активности"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.favourite, "Избранное"));

        recyclerViewNav = findViewById(R.id.nav_recyclerView);
        adapterNav = new NavRVAdapter(navRVItemArrayList);

        recyclerViewNav.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNav.setAdapter(adapterNav);

    }
}