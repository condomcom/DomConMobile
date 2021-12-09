package com.example.condom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DynamicRVAdapterFavorites extends RecyclerView.Adapter<DynamicRVAdapterFavorites.DynamicHolder> {
    private ArrayList<DynamicFavoritesItem> dynamicFavoritesItems;

    public DynamicRVAdapterFavorites(ArrayList<DynamicFavoritesItem> dynamicFavoritesItems) {
        this.dynamicFavoritesItems = dynamicFavoritesItems;
    }

    public class DynamicHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView imageFavorites;
        private TextView description;
        private TextView beginning;

        public DynamicHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title_fav);
            imageFavorites = itemView.findViewById(R.id.image_fav);
            description = itemView.findViewById(R.id.text_description_fav);
            beginning = itemView.findViewById(R.id.text_beginner_fav);

        }
    }

    @NonNull
    @Override
    public DynamicRVAdapterFavorites.DynamicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_favorites, parent, false);
        DynamicHolder dynamicHolder = new DynamicHolder(view);

        return dynamicHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRVAdapterFavorites.DynamicHolder holder, int position) {
        DynamicFavoritesItem currentItem = dynamicFavoritesItems.get(position);

        holder.title.setText(currentItem.getItemTitle());
        holder.imageFavorites.setImageResource(currentItem.getItemImage());
        holder.description.setText(currentItem.getItemDescription());
        holder.beginning.setText(currentItem.getItemBeginning());
    }

    @Override
    public int getItemCount() {
        return dynamicFavoritesItems.size();
    }
}
