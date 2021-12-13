package com.example.condom.ui.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.dataBase.FavoritesDB;
import com.example.condom.ui.modelItem.DynamicPerformanceItem;
import com.example.condom.ui.modelItem.DynamicSpeakerItem;

import java.util.ArrayList;

public class DynamicRVAdapterPerformance extends RecyclerView.Adapter<DynamicRVAdapterPerformance.DynamicHolder> {
    private ArrayList<DynamicPerformanceItem> dynamicPerformanceItems;
    private FavoritesDB favoritesDB;
    private Context context;
    private ArrayList<DynamicPerformanceItem> performanceItemArrayListCopy;

    public DynamicRVAdapterPerformance(ArrayList<DynamicPerformanceItem> dynamicRVAdapterPerformance, Context context) {
        this.dynamicPerformanceItems = dynamicRVAdapterPerformance;
        this.context = context;
        this.performanceItemArrayListCopy = new ArrayList<>();
        performanceItemArrayListCopy.addAll(dynamicRVAdapterPerformance);
    }

    public class DynamicHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView imagePerformance;
        private TextView description;
        private TextView beginning;
        private Button favourites;

        public DynamicHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.text_title_perf);
            imagePerformance = itemView.findViewById(R.id.image_perf);
            description = itemView.findViewById(R.id.text_description_perf);
            beginning = itemView.findViewById(R.id.text_beginner_perf);
            favourites = itemView.findViewById(R.id.new_favourites_perf);

            favourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Animation animScaleUp = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.scale_up);
                    final Animation animScaleDown = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.scale_down);

                    int position = getAbsoluteAdapterPosition();
                    DynamicPerformanceItem performancesCardsItem = dynamicPerformanceItems.get(position);

                    if(performancesCardsItem.getFavoriteStatus().equals("0")){
                        performancesCardsItem.setFavoriteStatus("1");
                        favoritesDB.insertIntoDatabase(performancesCardsItem.getItemTitle(), performancesCardsItem.getItemImage(),
                                performancesCardsItem.getItemDescription(), performancesCardsItem.getItemBeginning(),
                                performancesCardsItem.getFavoriteStatus(),
                                performancesCardsItem.getKeyId());
                        favourites.setBackgroundResource(R.drawable.ic_favorite_active);
                        favourites.startAnimation(animScaleUp);
                    }
                    else if(performancesCardsItem.getFavoriteStatus().equals("1")){
                        performancesCardsItem.setFavoriteStatus("0");
                        favoritesDB.removeFav(performancesCardsItem.getKeyId());
                        favourites.setBackgroundResource(R.drawable.ic_favorite_inactive);
                        favourites.startAnimation(animScaleDown);
                    }
                    else{
                        favoritesDB.removeFav(performancesCardsItem.getKeyId());
                        performancesCardsItem.setFavoriteStatus("0");
                    }
                }
            });
        }
    }



    @NonNull
    @Override
    public DynamicRVAdapterPerformance.DynamicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favoritesDB = new FavoritesDB(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = sharedPreferences.getBoolean("firstStart", true);
        if(firstStart) createTableOnFirstStart();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_performance_item, parent, false);
        DynamicHolder dynamicHolder = new DynamicHolder(view);

        return dynamicHolder;
    }

    private void createTableOnFirstStart() {
        favoritesDB.insertEmpty();

        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRVAdapterPerformance.DynamicHolder holder, int position) {
        DynamicPerformanceItem currentItem = dynamicPerformanceItems.get(position);
        readCursorData(currentItem, holder);

        holder.title.setText(currentItem.getItemTitle());
        holder.imagePerformance.setImageResource(currentItem.getItemImage());
        holder.description.setText(currentItem.getItemDescription());
        holder.beginning.setText(currentItem.getItemBeginning());
    }

    private void readCursorData(DynamicPerformanceItem dynamicPerformanceItem,DynamicRVAdapterPerformance.DynamicHolder
            viewHolder) {
        Cursor cursor = favoritesDB.readData(dynamicPerformanceItem.getKeyId());
        SQLiteDatabase sqLiteDatabase = favoritesDB.getReadableDatabase();

        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavoritesDB.FAVORITE_STATUS));
                dynamicPerformanceItem.setFavoriteStatus(item_fav_status);

                //check fav status
                if (item_fav_status != null && item_fav_status.equals("1")) {
                    viewHolder.favourites.setBackgroundResource(R.drawable.ic_favorite_active);
                } else if (item_fav_status != null && item_fav_status.equals("0")) {
                    viewHolder.favourites.setBackgroundResource(R.drawable.ic_favorite_inactive);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            sqLiteDatabase.close();
        }
    }

    @Override
    public int getItemCount() {
        return dynamicPerformanceItems.size();
    }

    public void filter(CharSequence charSequence){
        ArrayList<DynamicPerformanceItem> tempArrayList = new ArrayList<>();
        if(!TextUtils.isEmpty(charSequence)){
            for(DynamicPerformanceItem item : dynamicPerformanceItems){
                if(item.getItemTitle().toLowerCase().contains(charSequence)){
                    tempArrayList.add(item);
                }
            }
        }
        else{
            tempArrayList.addAll(performanceItemArrayListCopy);
        }

        dynamicPerformanceItems.clear();
        dynamicPerformanceItems.addAll(tempArrayList);
        notifyDataSetChanged();
        tempArrayList.clear();
    }
}
