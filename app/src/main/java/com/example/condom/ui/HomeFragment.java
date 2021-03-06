package com.example.condom.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.condom.R;
import com.example.condom.api.ApiClient;
import com.example.condom.api.DomConApi;
import com.example.condom.dataBase.FavoritesDB;
import com.example.condom.modelIP.User;
import com.example.condom.ui.adapters.DynamicRVAdapterActivity;
import com.example.condom.ui.adapters.NavRVAdapter;
import com.example.condom.ui.adapters.DynamicRVAdapterFavorites;
import com.example.condom.ui.adapters.DynamicRVAdapterPerformance;
import com.example.condom.ui.adapters.DynamicRVAdapterSpeaker;
import com.example.condom.ui.modelItem.DynamicActivityItem;
import com.example.condom.ui.modelItem.DynamicFavoritesItem;
import com.example.condom.ui.modelItem.DynamicPerformanceItem;
import com.example.condom.ui.modelItem.DynamicSpeakerItem;
import com.example.condom.ui.modelItem.NavRVItem;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements UpdateRecyclerView {
    private RecyclerView recyclerViewNav;
    private NavRVAdapter adapterNav;
    private ArrayList<NavRVItem> navRVItemArrayList;
    public RecyclerView recyclerViewDynamic;
    private ArrayList<DynamicSpeakerItem> dynamicSpeakerItemArrayList;
    private DynamicRVAdapterSpeaker dynamicRVAdapterSpeaker;
    private ArrayList<DynamicPerformanceItem> dynamicPerformanceItemsArrayList;
    private DynamicRVAdapterPerformance dynamicRVAdapterPerformance;
    private DynamicRVAdapterActivity dynamicRVAdapterActivity;
    private ArrayList<DynamicActivityItem> dynamicActivityItemsArrayList;
    private ArrayList<DynamicFavoritesItem> dynamicFavoritesItemArrayList;
    private DynamicRVAdapterFavorites dynamicRVAdapterFavorites;
    private FavoritesDB favoritesDB;
    private EditText searchView;
    private ImageButton filtered;
    private List<User> userList = new ArrayList<>();
    private static final String TAG = "TAG";

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

        navRVItemArrayList.add(new NavRVItem(R.drawable.speaker, "??????????????"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.work, "??????????????????????"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.challenge, "????????????????????"));
        navRVItemArrayList.add(new NavRVItem(R.drawable.favourite, "??????????????????"));

        recyclerViewNav = view.findViewById(R.id.nav_recyclerView);
        adapterNav = new NavRVAdapter(navRVItemArrayList, getActivity(), this, getContext());

        recyclerViewNav.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNav.setAdapter(adapterNav);

        recyclerViewDynamic = view.findViewById(R.id.item_recyclerView);
        dynamicSpeakerItemArrayList = new ArrayList<>();

        dynamicRVAdapterSpeaker = new DynamicRVAdapterSpeaker(dynamicSpeakerItemArrayList, getActivity());
        recyclerViewDynamic.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerViewDynamic.setAdapter(dynamicRVAdapterSpeaker);

        dynamicPerformanceItemsArrayList = new ArrayList<>();
        dynamicRVAdapterPerformance = new DynamicRVAdapterPerformance(dynamicPerformanceItemsArrayList, getActivity());
        recyclerViewDynamic.setAdapter(dynamicRVAdapterPerformance);

        dynamicActivityItemsArrayList = new ArrayList<>();
        dynamicRVAdapterActivity = new DynamicRVAdapterActivity(dynamicActivityItemsArrayList, getActivity());
        recyclerViewDynamic.setAdapter(dynamicRVAdapterActivity);

        favoritesDB = new FavoritesDB(getActivity());

        searchView = view.findViewById(R.id.search);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                dynamicRVAdapterSpeaker.filter(s);
                dynamicRVAdapterPerformance.filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        filtered = view.findViewById(R.id.b_filter);

        filtered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FilterActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }



    @Override
    public void callbackSpeaker(int position, ArrayList<DynamicSpeakerItem> items) {
        dynamicRVAdapterSpeaker = new DynamicRVAdapterSpeaker(items, getContext());
        dynamicRVAdapterSpeaker.notifyDataSetChanged();
        recyclerViewDynamic.setAdapter(dynamicRVAdapterSpeaker);
    }

    @Override
    public void callbackPerformance(int position, ArrayList<DynamicPerformanceItem> items) {
        dynamicRVAdapterPerformance = new DynamicRVAdapterPerformance(items, getContext());
        dynamicRVAdapterPerformance.notifyDataSetChanged();
        recyclerViewDynamic.setAdapter(dynamicRVAdapterPerformance);
    }

    @Override
    public void callbackActivity(int position, ArrayList<DynamicActivityItem> items) {
        dynamicRVAdapterActivity = new DynamicRVAdapterActivity(items, getContext());
        dynamicRVAdapterActivity.notifyDataSetChanged();
        recyclerViewDynamic.setAdapter(dynamicRVAdapterActivity);
    }

    @Override
    public void callbackFavorites(int position, ArrayList<DynamicFavoritesItem> items) {
        dynamicRVAdapterFavorites = new DynamicRVAdapterFavorites(items, getContext());
        dynamicRVAdapterFavorites.notifyDataSetChanged();
        recyclerViewDynamic.setAdapter(dynamicRVAdapterFavorites);
    }
}