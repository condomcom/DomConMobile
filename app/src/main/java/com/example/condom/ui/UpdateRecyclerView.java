package com.example.condom.ui;

import com.example.condom.ui.modelItem.DynamicFavoritesItem;
import com.example.condom.ui.modelItem.DynamicPerformanceItem;
import com.example.condom.ui.modelItem.DynamicSpeakerItem;

import java.util.ArrayList;

public interface UpdateRecyclerView {
    void callbackSpeaker(int position, ArrayList<DynamicSpeakerItem> items);

    void callbackPerformance(int position, ArrayList<DynamicPerformanceItem> items);

    void callbackFavorites(int position, ArrayList<DynamicFavoritesItem> items);
}
