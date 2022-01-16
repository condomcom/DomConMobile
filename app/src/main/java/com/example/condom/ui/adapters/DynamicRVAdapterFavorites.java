package com.example.condom.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.api.ApiClient;
import com.example.condom.dataBase.FavoritesDB;
import com.example.condom.modelIP.Activity;
import com.example.condom.modelItem.FavoritesItem;
import com.example.condom.ui.DetailsActivity;
import com.example.condom.ui.modelItem.DynamicActivityItem;
import com.example.condom.ui.modelItem.DynamicFavoritesItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DynamicRVAdapterFavorites extends RecyclerView.Adapter<DynamicRVAdapterFavorites.DynamicHolder> {
    private ArrayList<DynamicFavoritesItem> dynamicFavoritesItems;
    private FavoritesDB favoritesDB;
    private Context context;
    private static final String TAG = "FavoritesFragment";

    public DynamicRVAdapterFavorites(ArrayList<DynamicFavoritesItem> dynamicFavoritesItems, Context context) {
        this.dynamicFavoritesItems = dynamicFavoritesItems;
        this.context = context;
    }

    public class DynamicHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView imageFavorites;
        private TextView description;
        private TextView beginning;
        private Button favourites;
        private FloatingActionButton details;

        public DynamicHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title_fav);
            imageFavorites = itemView.findViewById(R.id.image_fav);
            description = itemView.findViewById(R.id.text_description_fav);
            beginning = itemView.findViewById(R.id.text_beginner_fav);
            favourites = itemView.findViewById(R.id.new_favourites_fav);
            details = itemView.findViewById(R.id.floatingActionButton_fav);

            favourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final DynamicFavoritesItem favoritesItem = dynamicFavoritesItems.get(position);
                    favoritesDB.removeFav(favoritesItem.getKeyId());
                    removeItem(position);
                }
            });

        }

        private void removeItem(int position) {
            dynamicFavoritesItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, dynamicFavoritesItems.size());
        }
    }

    @NonNull
    @Override
    public DynamicRVAdapterFavorites.DynamicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_favorites, parent, false);
        favoritesDB = new FavoritesDB(context);
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
