package com.example.condom.navigation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.adapters.FavoritesAdapter;
import com.example.condom.dataBase.FavoritesDB;
import com.example.condom.modelItem.FavoritesItem;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {
    private RecyclerView recyclerView;
    private FavoritesDB favoritesDB;
    private List<FavoritesItem> favoritesItemList = new ArrayList<>();
    private FavoritesAdapter favoritesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        favoritesDB = new FavoritesDB(getActivity());
        recyclerView = view.findViewById(R.id.recyclerViewFavorites);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadData();

        return view;
    }

    private void loadData() {
        if(favoritesItemList != null){
            favoritesItemList.clear();
        }
        SQLiteDatabase sqLiteDatabase = favoritesDB.getReadableDatabase();
        Cursor cursor = favoritesDB.selectAllFavoriteList();
        try{
            while (cursor.moveToNext()){
                String title = cursor.getString(cursor.getColumnIndex(FavoritesDB.ITEM_TITLE));
                String beginner = cursor.getString(cursor.getColumnIndex(FavoritesDB.ITEM_BEGINNING));
                String description = cursor.getString(cursor.getColumnIndex(FavoritesDB.ITEM_DESCRIPTION));
                String duration = cursor.getString(cursor.getColumnIndex(FavoritesDB.ITEM_DURATION));
                String id = cursor.getString(cursor.getColumnIndex(FavoritesDB.KEY_ID));
                int image = Integer.parseInt(cursor.getString(cursor.getColumnIndex(FavoritesDB.ITEM_IMAGE)));

                FavoritesItem favoritesItem = new FavoritesItem(id, title, image, description, beginner, duration);
                favoritesItemList.add(favoritesItem);
            }
        }
        finally {
            if(cursor != null && cursor.isClosed()) cursor.close();
            sqLiteDatabase.close();
        }

        favoritesAdapter = new FavoritesAdapter(getActivity(), favoritesItemList);

        recyclerView.setAdapter(favoritesAdapter);
    }
}