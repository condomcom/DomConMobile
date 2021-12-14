package com.example.condom.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.condom.dataBase.FavoritesDB;
import com.example.condom.ui.UpdateRecyclerView;
import com.example.condom.ui.modelItem.DynamicFavoritesItem;
import com.example.condom.ui.modelItem.DynamicPerformanceItem;
import com.example.condom.ui.modelItem.DynamicSpeakerItem;
import com.example.condom.ui.modelItem.NavRVItem;

import java.util.ArrayList;

public class NavRVAdapter extends RecyclerView.Adapter<NavRVAdapter.NavRVViewHolder>{
    private ArrayList<NavRVItem> items;
    private int index = -1;
    UpdateRecyclerView updateRecyclerView;
    FragmentActivity activity;
    boolean check = true;
    boolean select = true;

    private Context context;
    FragmentTransaction fTrans;

    public ArrayList<DynamicFavoritesItem> dynamicFavoritesItemArrayList = new ArrayList<>();
    public DynamicRVAdapterFavorites dynamicRVAdapterFavorites;
    public FavoritesDB favoritesDB;
    private ArrayList<DynamicPerformanceItem> dynamicPerformanceItemArrayList = new ArrayList<>();

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
                    ArrayList<DynamicSpeakerItem> items = new ArrayList<DynamicSpeakerItem>();
                    items.add(new DynamicSpeakerItem(1, "Пётр Петрович", R.drawable.rofl_photo, "Разработчик", "Разработка на моках", "222", "12:00-15:00"));
                    items.add(new DynamicSpeakerItem(1, "Игорь Петрович", R.drawable.rofl_photo, "Разработчик", "fvffvfv", "fv", "vfv"));
                    items.add(new DynamicSpeakerItem(1, "Григорий Петрович", R.drawable.rofl_photo, "Разработчик", "Разработка на моках", "222", "12:00-15:00"));
                    items.add(new DynamicSpeakerItem(1, "Александр аапапапра", R.drawable.rofl_photo, "Разработчик", "Разработка на моках", "222", "12:00-15:00"));
                    items.add(new DynamicSpeakerItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик", "Разработка на моках", "222", "12:00-15:00"));
                    items.add(new DynamicSpeakerItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик", "Разработка на моках", "222", "12:00-15:00"));

                    updateRecyclerView.callbackSpeaker(position, items);
                }
                else if(position == 1 ){


                    ArrayList<DynamicPerformanceItem> items = new ArrayList<>();
                    if(dynamicPerformanceItemArrayList.isEmpty()) {
                        items.add(new DynamicPerformanceItem("0", "Разработка на моках", R.drawable.mobile,
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ultricies ante erat, vitae bibendum erat egestas ut. Nam vel sodales lorem.", "15:00", "0"));
                        items.add(new DynamicPerformanceItem("1", "Разработка 2", R.drawable.mobile,
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ultricies ante erat, vitae bibendum erat egestas ut. Nam vel sodales lorem.", "15:00", "0"));
                        items.add(new DynamicPerformanceItem("2", "Разработка 3", R.drawable.mobile,
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ultricies ante erat, vitae bibendum erat egestas ut. Nam vel sodales lorem.", "15:00", "0"));
                    }
                    updateRecyclerView.callbackPerformance(position, items);
                }
                else if(position == 2){
                    ArrayList<DynamicSpeakerItem> items = new ArrayList<DynamicSpeakerItem>();

                    updateRecyclerView.callbackSpeaker(position, items);
                }
                else if(position == 3){
                    loadData();
                    updateRecyclerView.callbackFavorites(position, dynamicFavoritesItemArrayList);
                }
            }
        });

        if (select){
            if(position == 0)
                holder.linearLayout.setBackgroundResource(R.drawable.nav_rv_active);
                select = false;
        }
        else{
            if(index == position){
                holder.linearLayout.setBackgroundResource(R.drawable.nav_rv_active);
            }
            else{
                holder.linearLayout.setBackgroundResource(R.drawable.nav_rv);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void loadData() {
            if (dynamicRVAdapterFavorites != null) {
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
            //favoritesAdapter = new DynamicRVAdapterFavorites(dynamicFavoritesItemArrayList, context);
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
