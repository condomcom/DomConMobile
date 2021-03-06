package com.example.condom.ui.adapters;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.dataBase.FavoritesDB;
import com.example.condom.ui.DetailsActivity;
import com.example.condom.ui.modelItem.DynamicActivityItem;
import com.example.condom.ui.modelItem.DynamicSpeakerItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DynamicRVAdapterActivity extends RecyclerView.Adapter<DynamicRVAdapterActivity.DynamicHolder> {
    private ArrayList<DynamicActivityItem> dynamicActivityItems;
    private FavoritesDB favoritesDB;
    private Context context;
    private ArrayList<DynamicActivityItem> activityItemArrayListCopy;
    private Filter fRecords;

    public DynamicRVAdapterActivity(ArrayList<DynamicActivityItem> DynamicRVAdapterActivity, Context context) {
        this.dynamicActivityItems = DynamicRVAdapterActivity;
        this.context = context;
        this.activityItemArrayListCopy = new ArrayList<>();
        activityItemArrayListCopy.addAll(DynamicRVAdapterActivity);
    }

    public class DynamicHolder extends RecyclerView.ViewHolder {
        private TextView title, description, beginning, speaker, end, direction, place, date;
        private ImageView imageActivity;
        private Button favourites;
        private FloatingActionButton details;

        public DynamicHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.text_title_perf);
            imageActivity = itemView.findViewById(R.id.image_perf);
            description = itemView.findViewById(R.id.text_description_perf);
            beginning = itemView.findViewById(R.id.text_beginner_perf);
            favourites = itemView.findViewById(R.id.new_favourites_perf);
            details = itemView.findViewById(R.id.floatingActionButton_pref);
            speaker = itemView.findViewById(R.id.speaker_details);
            end = itemView.findViewById(R.id.end_time_details);
            place = itemView.findViewById(R.id.place_details);
            direction = itemView.findViewById(R.id.direction_details);
            date = itemView.findViewById(R.id.date_details);

            favourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Animation animScaleUp = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.scale_up);
                    final Animation animScaleDown = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.scale_down);

                    int position = getAbsoluteAdapterPosition();
                    DynamicActivityItem activitiesCardsItem = dynamicActivityItems.get(position);

                    if(activitiesCardsItem.getFavoriteStatus().equals("0")){
                        activitiesCardsItem.setFavoriteStatus("1");
                        favoritesDB.insertIntoDatabase(activitiesCardsItem.getItemTitle(), activitiesCardsItem.getItemImage(),
                                activitiesCardsItem.getItemDescription(), activitiesCardsItem.getItemBeginning(),
                                activitiesCardsItem.getFavoriteStatus(),
                                activitiesCardsItem.getKeyId());
                        favourites.setBackgroundResource(R.drawable.ic_favorite_active);
                        favourites.startAnimation(animScaleUp);
                    }
                    else if(activitiesCardsItem.getFavoriteStatus().equals("1")){
                        activitiesCardsItem.setFavoriteStatus("0");
                        favoritesDB.removeFav(activitiesCardsItem.getKeyId());
                        favourites.setBackgroundResource(R.drawable.ic_favorite_inactive);
                        favourites.startAnimation(animScaleDown);
                    }
                    else{
                        favoritesDB.removeFav(activitiesCardsItem.getKeyId());
                        activitiesCardsItem.setFavoriteStatus("0");
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public DynamicRVAdapterActivity.DynamicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(@NonNull DynamicRVAdapterActivity.DynamicHolder holder, int position) {
        DynamicActivityItem currentItem = dynamicActivityItems.get(position);
        readCursorData(currentItem, holder);

        holder.title.setText(currentItem.getItemTitle());
        holder.imageActivity.setImageResource(currentItem.getItemImage());
        holder.description.setText(currentItem.getItemDescription());
        holder.beginning.setText(currentItem.getItemBeginning());
        holder.speaker.setText(currentItem.getItemSpeaker());
        holder.end.setText(currentItem.getItemEnd());
        holder.direction.setText(currentItem.getItemDirection());
        holder.place.setText(currentItem.getItemPlace());
        holder.date.setText(currentItem.getItemDate());

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("title", currentItem.getItemTitle());
                intent.putExtra("speaker", currentItem.getItemSpeaker());
                intent.putExtra("description", currentItem.getItemDescription());
                intent.putExtra("beginning", currentItem.getItemBeginning());
                intent.putExtra("end", currentItem.getItemEnd());
                intent.putExtra("direction", currentItem.getItemDirection());
                intent.putExtra("place", currentItem.getItemPlace());
                intent.putExtra("date", currentItem.getItemDate());
                context.startActivity(intent);
            }
        });
    }

    private void readCursorData(DynamicActivityItem dynamicActivityItem,DynamicRVAdapterActivity.DynamicHolder
            viewHolder) {
        Cursor cursor = favoritesDB.readData(dynamicActivityItem.getKeyId());
        SQLiteDatabase sqLiteDatabase = favoritesDB.getReadableDatabase();

        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavoritesDB.FAVORITE_STATUS));
                dynamicActivityItem.setFavoriteStatus(item_fav_status);

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
        return dynamicActivityItems.size();
    }

    public void filter(CharSequence charSequence){
        ArrayList<DynamicActivityItem> tempArrayList = new ArrayList<>();
        if(!TextUtils.isEmpty(charSequence)){
            for(DynamicActivityItem item : dynamicActivityItems){
                if(item.getItemTitle().toLowerCase().contains(charSequence)){
                    tempArrayList.add(item);
                }
            }
        }
        else{
            tempArrayList.addAll(activityItemArrayListCopy);
        }

        dynamicActivityItems.clear();
        dynamicActivityItems.addAll(tempArrayList);
        notifyDataSetChanged();
        tempArrayList.clear();
    }

    public void filteringRecyclerView(CharSequence charSequence){
        ArrayList<DynamicActivityItem> tempArrayList = new ArrayList<>();
        if(!TextUtils.isEmpty(charSequence)){
            for(DynamicActivityItem item : dynamicActivityItems){
                if(item.getItemBeginning().toLowerCase().contains(charSequence)
                        || item.getItemEnd().toLowerCase().contains(charSequence)
                        || item.getItemDate().toLowerCase().contains(charSequence)){
                    tempArrayList.add(item);
                }
            }
        }
        else{
            tempArrayList.addAll(activityItemArrayListCopy);
        }

        dynamicActivityItems.clear();
        dynamicActivityItems.addAll(tempArrayList);
        notifyDataSetChanged();
        tempArrayList.clear();
    }
}

