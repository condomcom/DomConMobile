package com.example.condom;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NavRVAdapter extends RecyclerView.Adapter<NavRVAdapter.NavRVViewHolder>{
    private ArrayList<NavRVItem> items;
    private int index = -1;
    UpdateRecyclerView updateRecyclerView;
    FragmentActivity activity;
    boolean check = true;
    boolean select = true;

    public NavRVAdapter(ArrayList<NavRVItem> items, FragmentActivity activity, UpdateRecyclerView updateRecyclerView) {
        this.items = items;
        this.activity = activity;
        this.updateRecyclerView = updateRecyclerView;
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
            ArrayList<DynamicRVItem> items = new ArrayList<DynamicRVItem>();
            items.add(new DynamicRVItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));
            items.add(new DynamicRVItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));
            items.add(new DynamicRVItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));
            items.add(new DynamicRVItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));
            items.add(new DynamicRVItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));
            items.add(new DynamicRVItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));

            updateRecyclerView.callback(position, items);
            check = false;
        }*/

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = position;
                notifyDataSetChanged();

                if(position == 0){
                    ArrayList<DynamicSpeakerItem> items = new ArrayList<DynamicSpeakerItem>();
                    items.add(new DynamicSpeakerItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));
                    items.add(new DynamicSpeakerItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));
                    items.add(new DynamicSpeakerItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));
                    items.add(new DynamicSpeakerItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));
                    items.add(new DynamicSpeakerItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));
                    items.add(new DynamicSpeakerItem(1, "Иван Петрович", R.drawable.rofl_photo, "Разработчик"));

                    updateRecyclerView.callbackSpeaker(position, items);
                }
                else if(position == 1){
                    ArrayList<DynamicPerformanceItem> items = new ArrayList<>();
                    items.add(new DynamicPerformanceItem(1, "Разработка на моках", R.drawable.mobile,
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ultricies ante erat, vitae bibendum erat egestas ut. Nam vel sodales lorem.", "15:00"));
                    items.add(new DynamicPerformanceItem(2, "Разработка на моках", R.drawable.mobile,
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ultricies ante erat, vitae bibendum erat egestas ut. Nam vel sodales lorem.", "15:00"));
                    items.add(new DynamicPerformanceItem(3, "Разработка на моках", R.drawable.mobile,
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ultricies ante erat, vitae bibendum erat egestas ut. Nam vel sodales lorem.", "15:00"));

                    updateRecyclerView.callbackPerformance(position, items);
                }
                else if(position == 2){
                    ArrayList<DynamicSpeakerItem> items = new ArrayList<DynamicSpeakerItem>();

                    updateRecyclerView.callbackSpeaker(position, items);
                }
                else if(position == 3){
                    ArrayList<DynamicFavoritesItem> items = new ArrayList<DynamicFavoritesItem>();
                    items.add(new DynamicFavoritesItem("1", "Разработка на моках", R.drawable.mobile,
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ultricies ante erat, vitae bibendum erat egestas ut. Nam vel sodales lorem.", "15:00"));

                    updateRecyclerView.callbackFavorites(position, items);
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
