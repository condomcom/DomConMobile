package com.example.condom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements  UpdateRecyclerView{
    private RecyclerView recyclerViewNav;
    private NavRVAdapter adapterNav;
    private ArrayList<NavRVItem> navRVItemArrayList;
    private RecyclerView recyclerViewDynamic;
    private ArrayList<DynamicSpeakerItem> dynamicSpeakerItemArrayList;
    private DynamicRVAdapterSpeaker dynamicRVAdapterSpeaker;
    private ArrayList<DynamicPerformanceItem> dynamicPerformanceItemsArrayList;
    private DynamicRVAdapterPerformance dynamicRVAdapterPerformance;
    private ArrayList<DynamicFavoritesItem> dynamicFavoritesItemArrayList;
    private DynamicRVAdapterFavorites dynamicRVAdapterFavorites;

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

        navRVItemArrayList.add(new NavRVItem(R.drawable.speaker, "Спикеры"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.work, "Выступления"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.challenge, "Активности"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.favourite, "Избранное"));

        recyclerViewNav = view.findViewById(R.id.nav_recyclerView);
        adapterNav = new NavRVAdapter(navRVItemArrayList, getActivity(), this);

        recyclerViewNav.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNav.setAdapter(adapterNav);



        recyclerViewDynamic = view.findViewById(R.id.item_recyclerView);
        dynamicSpeakerItemArrayList = new ArrayList<>();

        dynamicRVAdapterSpeaker = new DynamicRVAdapterSpeaker(dynamicSpeakerItemArrayList);
        recyclerViewDynamic.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerViewDynamic.setAdapter(dynamicRVAdapterSpeaker);

        dynamicPerformanceItemsArrayList = new ArrayList<>();
        dynamicRVAdapterPerformance = new DynamicRVAdapterPerformance(dynamicPerformanceItemsArrayList);
        recyclerViewDynamic.setAdapter(dynamicRVAdapterPerformance);

        return view;
    }

    @Override
    public void callbackSpeaker(int position, ArrayList<DynamicSpeakerItem> items) {

        dynamicRVAdapterSpeaker = new DynamicRVAdapterSpeaker(items);
        dynamicRVAdapterSpeaker.notifyDataSetChanged();
        recyclerViewDynamic.setAdapter(dynamicRVAdapterSpeaker);
    }

    @Override
    public void callbackPerformance(int position, ArrayList<DynamicPerformanceItem> items) {
        dynamicRVAdapterPerformance = new DynamicRVAdapterPerformance(items);
        dynamicRVAdapterPerformance.notifyDataSetChanged();
        recyclerViewDynamic.setAdapter(dynamicRVAdapterPerformance);
    }

    @Override
    public void callbackFavorites(int position, ArrayList<DynamicFavoritesItem> items) {
        dynamicRVAdapterFavorites = new DynamicRVAdapterFavorites(items);
        dynamicRVAdapterFavorites.notifyDataSetChanged();
        recyclerViewDynamic.setAdapter(dynamicRVAdapterFavorites);
    }
}