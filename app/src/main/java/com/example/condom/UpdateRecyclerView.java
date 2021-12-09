package com.example.condom;

import java.util.ArrayList;

public interface UpdateRecyclerView {
    void callbackSpeaker(int position, ArrayList<DynamicSpeakerItem> items);

    void callbackPerformance(int position, ArrayList<DynamicPerformanceItem> items);

    void callbackFavorites(int position, ArrayList<DynamicFavoritesItem> items);
}
