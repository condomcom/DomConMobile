package com.example.condom.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.favoritesAdapter.PerformancesAdapter;
import com.example.condom.modelItem.PerformancesCardsItem;

import java.util.ArrayList;

public class PerformanceFragment extends Fragment {
    private ArrayList<PerformancesCardsItem> performancesItems = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_performance, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPerformance);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new PerformancesAdapter(performancesItems, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        performancesItems.add(new PerformancesCardsItem("0", "Проблемы embedded или как мы от sqlite ушли", R.drawable.ic_backend, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ultricies ante erat, vitae bibendum erat egestas ut. Nam vel sodales lorem. Donec nec vestibulum mi, nec semper nunc. Integer fermentum leo sit amet erat ultrices, id fringilla nunc sagittis. Etiam rutrum porttitor nunc, a tincidunt est aliquam tincidunt.",
                "Начало в 14:00", "4 часа", "0"));
        performancesItems.add(new PerformancesCardsItem("0", "RTC и Франкенштейн", R.drawable.ic_front, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ultricies ante erat, vitae bibendum erat egestas ut. Nam vel sodales lorem. Donec nec vestibulum mi, nec semper nunc. Integer fermentum leo sit amet erat ultrices, id fringilla nunc sagittis. Etiam rutrum porttitor nunc, a tincidunt est aliquam tincidunt.",
                "Начало в 14:00", "4 часа", "0"));
        performancesItems.add(new PerformancesCardsItem("0", "Проектирование сложных B2B-сервисов в деревне Виллабаджо", R.drawable.ic_illustration, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ultricies ante erat, vitae bibendum erat egestas ut. Nam vel sodales lorem. Donec nec vestibulum mi, nec semper nunc. Integer fermentum leo sit amet erat ultrices, id fringilla nunc sagittis. Etiam rutrum porttitor nunc, a tincidunt est aliquam tincidunt.",
                "Начало в 14:00", "4 часа", "0"));
        performancesItems.add(new PerformancesCardsItem("0", "«Разработка на моках»", R.drawable.ic_mobile_development, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ultricies ante erat, vitae bibendum erat egestas ut. Nam vel sodales lorem. Donec nec vestibulum mi, nec semper nunc. Integer fermentum leo sit amet erat ultrices, id fringilla nunc sagittis. Etiam rutrum porttitor nunc, a tincidunt est aliquam tincidunt.",
                "Начало в 14:00", "4 часа", "0"));
        performancesItems.add(new PerformancesCardsItem("0", "Как игры меняют наше будущее", R.drawable.ic_video_game, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ultricies ante erat, vitae bibendum erat egestas ut. Nam vel sodales lorem. Donec nec vestibulum mi, nec semper nunc. Integer fermentum leo sit amet erat ultrices, id fringilla nunc sagittis. Etiam rutrum porttitor nunc, a tincidunt est aliquam tincidunt.",
                "Начало в 14:00", "4 часа", "0"));

        return view;
    }
}