package com.example.condom.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.api.ApiClient;
import com.example.condom.dataBase.FavoritesDB;
import com.example.condom.modelIP.Activity;
import com.example.condom.modelIP.User;
import com.example.condom.modelItem.PerformancesCardsItem;
import com.example.condom.speakers.SpeakersCardsItem;
import com.example.condom.ui.UpdateRecyclerView;
import com.example.condom.ui.modelItem.DynamicActivityItem;
import com.example.condom.ui.modelItem.DynamicFavoritesItem;
import com.example.condom.ui.modelItem.DynamicPerformanceItem;
import com.example.condom.ui.modelItem.DynamicSpeakerItem;
import com.example.condom.ui.modelItem.NavRVItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavRVAdapter extends RecyclerView.Adapter<NavRVAdapter.NavRVViewHolder>{
    private ArrayList<NavRVItem> items;
    private int index = -1;
    UpdateRecyclerView updateRecyclerView;
    FragmentActivity activity;
    boolean check = true;
    boolean select = true;

    private Context context;
    FragmentTransaction fTrans;

    private ArrayList<DynamicSpeakerItem> dynamicSpeakerItemsList = new ArrayList<>();
    public ArrayList<DynamicFavoritesItem> dynamicFavoritesItemArrayList = new ArrayList<>();
    public DynamicRVAdapterFavorites dynamicRVAdapterFavorites;
    public FavoritesDB favoritesDB;
    private ArrayList<DynamicPerformanceItem> dynamicPerformanceItemArrayList = new ArrayList<>();
    private ArrayList<DynamicActivityItem> dynamicActivityItemArrayList = new ArrayList<>();
    private static final String TAG = "ActivityFragment";

    public NavRVAdapter(ArrayList<NavRVItem> items, FragmentActivity activity, UpdateRecyclerView updateRecyclerView, Context context) {
        this.items = items;
        this.activity = activity;
        this.updateRecyclerView = updateRecyclerView;
        this.context = context;
    }

    @NonNull
    @Override
    public NavRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_rv_item, parent, false);
        NavRVViewHolder navRVViewHolder = new NavRVViewHolder(view);

        return navRVViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NavRVViewHolder holder, @SuppressLint("RecyclerView") int position) {
        NavRVItem currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.text.setText(currentItem.getText());

        /*if(check){
            ArrayList<DynamicSpeakerItem> items = new ArrayList<DynamicSpeakerItem>();
            items.add(new DynamicSpeakerItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));
            items.add(new DynamicSpeakerItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));
            items.add(new DynamicSpeakerItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));
            items.add(new DynamicSpeakerItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));
            items.add(new DynamicSpeakerItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));
            items.add(new DynamicSpeakerItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));

            updateRecyclerView.callbackSpeaker(position, items);
            check = false;
        }*/

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = position;
                notifyDataSetChanged();

                if(position == 0){
                    getAllSpeakers();
                }
                else if(position == 1 ){

                    //ArrayList<DynamicPerformanceItem> items = new ArrayList<>();
                        getAllPerformance();
                    //updateRecyclerView.callbackPerformance(position, items);
                }
                else if(position == 2){
                    getAllActivities();
                }
                else if(position == 3){
                    loadData();
                    updateRecyclerView.callbackFavorites(position, dynamicFavoritesItemArrayList);
                }
            }
        });

            if(index == position){
                holder.linearLayout.setBackgroundResource(R.drawable.nav_rv_active);
            }
            else{
                holder.linearLayout.setBackgroundResource(R.drawable.nav_rv);
            }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void loadData() {
            if (dynamicFavoritesItemArrayList != null) {
                dynamicFavoritesItemArrayList.clear();
            }
            favoritesDB = new FavoritesDB(context);
            SQLiteDatabase sqLiteDatabase = favoritesDB.getReadableDatabase();
            Cursor cursor = favoritesDB.selectAllFavoriteList();
            try {
                while (cursor.moveToNext()) {
                    String title = cursor.getString(cursor.getColumnIndex(FavoritesDB.ITEM_TITLE));
                    String beginner = cursor.getString(cursor.getColumnIndex(FavoritesDB.ITEM_BEGINNING));
                    String description = cursor.getString(cursor.getColumnIndex(FavoritesDB.ITEM_DESCRIPTION));
                    String id = cursor.getString(cursor.getColumnIndex(FavoritesDB.KEY_ID));
                    int image = Integer.parseInt(cursor.getString(cursor.getColumnIndex(FavoritesDB.ITEM_IMAGE)));

                    DynamicFavoritesItem favoritesItem = new DynamicFavoritesItem(id, title, image, description, beginner);
                    dynamicFavoritesItemArrayList.add(favoritesItem);
                }
            } finally {
                if (cursor != null && cursor.isClosed()) cursor.close();
                sqLiteDatabase.close();
            }
    }

    private void getAllActivities() {
        Call<List<Activity>> call = ApiClient.getInterface().getActivities();

        call.enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                List<Activity> activities = response.body();

                if(response.isSuccessful()) {
                    Log.i(TAG, "Code: " + response.code());
                    Log.i(TAG, "Всё путём ...");

                    dynamicActivityItemArrayList.clear();

                    for (int i = 0; i < activities.size(); i++) {
                        Activity activity = activities.get(i);

                        String imageUrl = activity.getImageUrl();

                        if (activity.mFullName != null) {
                            DynamicActivityItem card = new DynamicActivityItem(Integer.MAX_VALUE - i + "", activity.mShortName,
                                    R.drawable.mobile, activity.mDescription,
                                    "14:00", "0", "Ivan Ivanovich", "16:00", "Backend", "333", "13.12.2021");

                            dynamicActivityItemArrayList.add(card);
                        }

                        updateRecyclerView.callbackActivity(2,dynamicActivityItemArrayList);
                    }
                }
                else{
                    Log.i(TAG, "Произошла ошибка повторите попытку позже ...");
                }
            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }

    private void getAllSpeakers() {
        Call<List<User>> call = ApiClient.getInterface().getSpeakers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> speakers = response.body();

                if(response.isSuccessful()) {
                    Log.i(TAG, "Code: " + response.code());
                    Log.i(TAG, "Всё путём ...");

                    dynamicSpeakerItemsList.clear();

                    for (int i = 0; i < speakers.size(); i++) {
                        User user = speakers.get(i);

                        String imageUrl = user.getImageUrl();

                        if (user.getRole() == 1) {
                            DynamicSpeakerItem item = new DynamicSpeakerItem(i, user.getName() + " ", user.getSurname(),
                                    imageUrl, user.getSpeakerPosition(), "Activity Name", "111", "12:00-15:00");

                            dynamicSpeakerItemsList.add(item);
                        }

                        updateRecyclerView.callbackSpeaker(1,dynamicSpeakerItemsList);
                    }
                }
                else{
                    Log.i(TAG, "Произошла ошибка повторите попытку позже ...");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }

    private void getAllPerformance() {

        Call<List<Activity>> callActivity = ApiClient.getInterface().getActivities();

        callActivity.enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                List<Activity> activities = response.body();

                if(response.isSuccessful()) {
                    Log.i(TAG, "Code: " + response.code());
                    Log.i(TAG, "Всё путём ...");

                    dynamicPerformanceItemArrayList.clear();

                    for (int i = 0; i < activities.size(); i++) {
                        Activity activity = activities.get(i);

                        String imageUrl = activity.getImageUrl();

                        if (activity.mFullName != null) {
                            DynamicPerformanceItem item = new DynamicPerformanceItem(i + "", activity.getShortName(), R.drawable.mobile,
                                    activity.getDescription(), "12:00", "0", "Иван Иванов" , "15:00", "Backend", "111", "12.12.2021");

                            dynamicPerformanceItemArrayList.add(item);
                        }

                        updateRecyclerView.callbackPerformance(1,dynamicPerformanceItemArrayList);
                    }
                }
                else{
                    Log.i(TAG, "Произошла ошибка повторите попытку позже ...");
                }
            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }


    public class NavRVViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView imageView;
        LinearLayout linearLayout;

        public NavRVViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text_nav);
            imageView = itemView.findViewById(R.id.image_nav);
            linearLayout = itemView.findViewById(R.id.nav_linear_layout);
        }
    }
}
