package com.example.condom.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.speakers.SpeakersAdapter;
import com.example.condom.speakers.SpeakersCardsItem;


import java.util.ArrayList;

public class SpeakerFragment extends Fragment {
    private ArrayList<SpeakersCardsItem> speakersItems = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speaker, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPerformance);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new SpeakersAdapter(speakersItems, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(speakersItems.isEmpty()){
        speakersItems.add(new SpeakersCardsItem("0", "Александр Петров", R.drawable.rofl_photo, "Programmer",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ultricies ante erat, vitae bibendum erat egestas ut. Nam vel sodales lorem. Donec nec vestibulum mi, nec semper nunc. Integer fermentum leo sit amet erat ultrices, id fringilla nunc sagittis. Etiam rutrum", "14 октября, 16:40 - 17:40", "Кабинет №22"));
        speakersItems.add(new SpeakersCardsItem("0", "Александр Петров", R.drawable.rofl_photo, "Programmer",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ultricies ante erat, vitae bibendum erat egestas ut. Nam vel sodales lorem. Donec nec vestibulum mi, nec semper nunc. Integer fermentum leo sit amet erat ultrices, id fringilla nunc sagittis. Etiam rutrum", "14 октября, 16:40 - 17:40", "Кабинет №22"));
        }

        return view;
    }
}