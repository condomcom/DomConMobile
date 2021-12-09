package com.example.condom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.condom.authentication.AuthorizationFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerViewNav;
    private NavRVAdapter adapterNav;
    private ArrayList<NavRVItem> navRVItemArrayList;
    private RecyclerView recyclerViewDynamic;
    private ArrayList<newSpeakerRVItem> newSpeakerRVItemArrayList;
    private SpeakerRVAdapter speakerRVAdapter;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        navRVItemArrayList = new ArrayList<>();
        newSpeakerRVItemArrayList = new ArrayList<>();

        navRVItemArrayList.add(new NavRVItem(R.drawable.speaker, "Спикеры"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.work, "Выступления"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.challenge, "Активности"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.favourite, "Избранное"));

        recyclerViewNav = view.findViewById(R.id.nav_recyclerView);
        recyclerViewDynamic = view.findViewById(R.id.item_recyclerView);

        adapterNav = new NavRVAdapter(navRVItemArrayList);
        recyclerViewDynamic.setLayoutManager(new LinearLayoutManager(getActivity()));
        speakerRVAdapter = new SpeakerRVAdapter(recyclerViewDynamic, getActivity(), newSpeakerRVItemArrayList);

        adapterNav = new NavRVAdapter(navRVItemArrayList);
        recyclerViewDynamic.setLayoutManager(new LinearLayoutManager(getActivity()));
        speakerRVAdapter = new SpeakerRVAdapter(recyclerViewDynamic, getActivity(), newSpeakerRVItemArrayList);

        recyclerViewNav.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNav.setAdapter(adapterNav);
        recyclerViewDynamic.setAdapter(speakerRVAdapter);

        newSpeakerRVItemArrayList.add(new newSpeakerRVItem(0,"Василий Петрович",R.drawable.rofl_photo, "Разработчик"));
        newSpeakerRVItemArrayList.add(new newSpeakerRVItem(0,"Василий Петрович",R.drawable.rofl_photo, "Разработчик"));
        newSpeakerRVItemArrayList.add(new newSpeakerRVItem(0,"Василий Петрович",R.drawable.rofl_photo, "Разработчик"));
        newSpeakerRVItemArrayList.add(new newSpeakerRVItem(0,"Василий Петрович",R.drawable.rofl_photo, "Разработчик"));
        newSpeakerRVItemArrayList.add(new newSpeakerRVItem(0,"Василий Петрович",R.drawable.rofl_photo, "Разработчик"));

        return view;
    }
}