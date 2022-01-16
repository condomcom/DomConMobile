package com.example.condom.old_ui.navigation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.dataBase.FavoritesDB;
import com.example.condom.modelItem.FavoritesItem;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {
    private Context context;
    private List<FavoritesItem> favoritesItemList;
    private FavoritesDB favoritesDB;

    public FavoritesAdapter(Context context, List<FavoritesItem> favoritesItemList){
        this.context = context;
        this.favoritesItemList = favoritesItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fav_item,
                parent, false);
        favoritesDB = new FavoritesDB(context);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mBeginning.setText(favoritesItemList.get(position).getItemBeginning());
        holder.mImageView.setImageResource(favoritesItemList.get(position).getItemImage());
        holder.mTitle.setText(favoritesItemList.get(position).getItemTitle());
        holder.mDescription.setText(favoritesItemList.get(position).getItemDescription());
        //holder.mDuration.setText(favoritesItemList.get(position).getItemDuration());
    }

    @Override
    public int getItemCount() {
        return favoritesItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mBeginning;
        Button mFavourites;
        ImageView mImageView;
        TextView mTitle;
        TextView mDescription;
        TextView mDuration;
        Button mDetails;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            mBeginning = itemView.findViewById(R.id.t_beginning_fav);
            mFavourites = itemView.findViewById(R.id.favourites_fav);
            mImageView = itemView.findViewById(R.id.image_fav);
            mTitle = itemView.findViewById(R.id.t_title_fav);
            mDescription = itemView.findViewById(R.id.t_description_fav);
            mDuration = itemView.findViewById(R.id.t_duration_fav);
            mDetails = itemView.findViewById(R.id.b_details_fav);

            mFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final FavoritesItem favoritesItem = favoritesItemList.get(position);
                    favoritesDB.removeFav(favoritesItem.getKeyId());
                    removeItem(position);
                }
            });
        }

        private void removeItem(int position) {
            favoritesItemList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, favoritesItemList.size());
        }
    }
}
