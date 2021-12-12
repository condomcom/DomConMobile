package com.example.condom.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.condom.R;
import com.example.condom.ui.adapters.DynamicRVAdapterSpeaker;
import com.example.condom.ui.adapters.NavRVAdapter;
import com.example.condom.ui.modelItem.DynamicSpeakerItem;
import com.example.condom.ui.modelItem.NavRVItem;

import java.util.ArrayList;

public class NewHomeActivityActivity extends AppCompatActivity {
    private RecyclerView recyclerViewNav;
    private NavRVAdapter adapterNav;
    private ArrayList<NavRVItem> navRVItemArrayList;
    private RecyclerView recyclerViewDynamic;
    private ArrayList<DynamicSpeakerItem> dynamicSpeakerItemArrayList;
    private DynamicRVAdapterSpeaker dynamicRVAdapterSpeaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_home_activity);

        /*navRVItemArrayList = new ArrayList<>();
        dynamicRVItemArrayList = new ArrayList<>();

        navRVItemArrayList.add(new NavRVItem(R.drawable.speaker, "Спикеры"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.work, "Выступления"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.challenge, "Активности"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.favourite, "Избранное"));

        recyclerViewNav = findViewById(R.id.nav_recyclerView);
        recyclerViewDynamic = findViewById(R.id.item_recyclerView);

        adapterNav = new NavRVAdapter(navRVItemArrayList);
        recyclerViewDynamic.setLayoutManager(new LinearLayoutManager(this));
        dynamicRVAdapter = new DynamicRVAdapter(recyclerViewDynamic, this, dynamicRVItemArrayList);

        recyclerViewNav.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNav.setAdapter(adapterNav);
        recyclerViewDynamic.setAdapter(dynamicRVAdapter);

        dynamicRVItemArrayList.add(new DynamicRVItem(0,"Василий Петрович",R.drawable.rofl_photo, "Разработчик"));
        dynamicRVItemArrayList.add(new DynamicRVItem(0,"Василий Петрович",R.drawable.rofl_photo, "Разработчик"));
        dynamicRVItemArrayList.add(new DynamicRVItem(0,"Василий Петрович",R.drawable.rofl_photo, "Разработчик"));
        dynamicRVItemArrayList.add(new DynamicRVItem(0,"Василий Петрович",R.drawable.rofl_photo, "Разработчик"));
        dynamicRVItemArrayList.add(new DynamicRVItem(0,"Василий Петрович",R.drawable.rofl_photo, "Разработчик"));*/

    }
}