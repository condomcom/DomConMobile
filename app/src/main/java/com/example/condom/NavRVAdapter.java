package com.example.condom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NavRVAdapter extends RecyclerView.Adapter<NavRVAdapter.NavRVViewHolder>{
    private ArrayList<NavRVItem> items;

    public NavRVAdapter(ArrayList<NavRVItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public NavRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_rv_item, parent, false);
        NavRVViewHolder navRVViewHolder = new NavRVViewHolder(view);

        return navRVViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NavRVViewHolder holder, int position) {
        NavRVItem currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.text.setText(currentItem.getText());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class NavRVViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView imageView;

        public NavRVViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text_nav);
            imageView = itemView.findViewById(R.id.image_nav);
        }
    }
}
