package com.example.condom.favorites;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;

import java.util.ArrayList;

public class PerformancesAdapter extends RecyclerView.Adapter<PerformancesAdapter.ViewHolder> {
    private ArrayList<PerformancesCardsItem> performancesCardsItems;
    private Context context;
    private FavoritesDB favoritesDB;

    public PerformancesAdapter( ArrayList<PerformancesCardsItem> performancesCardsItems, Context context){
        this.performancesCardsItems = performancesCardsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favoritesDB = new FavoritesDB(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = sharedPreferences.getBoolean("firstStart", true);
        if(firstStart) createTableOnFirstStart();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_perform_item,
                parent, false);
        return new ViewHolder(view);
    }

    private void createTableOnFirstStart() {
        favoritesDB.insertEmpty();
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    @Override
    public void onBindViewHolder(@NonNull PerformancesAdapter.ViewHolder holder, int position) {
        final PerformancesCardsItem performancesCardsItem = performancesCardsItems.get(position);
        readCursorData(performancesCardsItem, holder);

        holder.mBeginning.setText(performancesCardsItem.getItemBeginning());
        holder.mImageView.setImageResource(performancesCardsItem.getItemImage());
        holder.mTitle.setText(performancesCardsItem.getItemTitle());
        holder.mDescription.setText(performancesCardsItem.getItemDescription());
        holder.mDuration.setText(performancesCardsItem.getItemDuration());
    }



    @Override
    public int getItemCount() {
        return performancesCardsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mBeginning;
        Button mFavourites;
        ImageView mImageView;
        TextView mTitle;
        TextView mDescription;
        TextView mDuration;
        Button mDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mBeginning = itemView.findViewById(R.id.t_beginning_perf);
            mFavourites = itemView.findViewById(R.id.favourites_perf);
            mImageView = itemView.findViewById(R.id.image_perf);
            mTitle = itemView.findViewById(R.id.t_title_perf);
            mDescription = itemView.findViewById(R.id.t_description_perf);
            mDuration = itemView.findViewById(R.id.t_duration_perf);
            mDetails = itemView.findViewById(R.id.b_details_perf);

            mFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Animation animScaleUp = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.scale_up);
                    final Animation animScaleDown = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.scale_down);

                    int position = getAbsoluteAdapterPosition();
                    PerformancesCardsItem performancesCardsItem = performancesCardsItems.get(position);

                    if(performancesCardsItem.getFavoriteStatus().equals("0")){
                        performancesCardsItem.setFavoriteStatus("1");
                        favoritesDB.insertIntoDatabase(performancesCardsItem.getItemTitle(), performancesCardsItem.getItemImage(),
                                performancesCardsItem.getItemDescription(), performancesCardsItem.getItemBeginning(),
                                performancesCardsItem.getItemDuration(), performancesCardsItem.getFavoriteStatus(),
                                performancesCardsItem.getKeyId());
                        mFavourites.setBackgroundResource(R.drawable.ic_favorite_active);
                        mFavourites.startAnimation(animScaleUp);
                    }
                    else{
                        performancesCardsItem.setFavoriteStatus("0");
                        favoritesDB.removeFav(performancesCardsItem.getKeyId());
                        mFavourites.setBackgroundResource(R.drawable.ic_favorite_inactive);
                        mFavourites.startAnimation(animScaleDown);
                    }
                }
            });
        }
    }

    private void readCursorData(PerformancesCardsItem performancesCardsItem,
                                ViewHolder viewHolder) {
        Cursor cursor = favoritesDB.readData(performancesCardsItem.getKeyId());
        SQLiteDatabase sqLiteDatabase = favoritesDB.getReadableDatabase();

        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavoritesDB.FAVORITE_STATUS));
                performancesCardsItem.setFavoriteStatus(item_fav_status);

                //check fav status
                if (item_fav_status == null && item_fav_status.equals("1")) {
                    viewHolder.mFavourites.setBackgroundResource(R.drawable.ic_favorite_active);
                } else if (item_fav_status != null && item_fav_status.equals("0")) {
                    viewHolder.mFavourites.setBackgroundResource(R.drawable.ic_favorite_inactive);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            sqLiteDatabase.close();
        }
    }
}
