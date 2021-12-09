package com.example.condom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DynamicRVAdapterPerformance extends RecyclerView.Adapter<DynamicRVAdapterPerformance.DynamicHolder> {
    private ArrayList<DynamicPerformanceItem> dynamicPerformanceItems;

    public DynamicRVAdapterPerformance(ArrayList<DynamicPerformanceItem> dynamicRVAdapterPerformance) {
        this.dynamicPerformanceItems = dynamicRVAdapterPerformance;
    }

    public class DynamicHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView imagePerformance;
        private TextView description;
        private TextView beginning;

        public DynamicHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.text_title_perf);
            imagePerformance = itemView.findViewById(R.id.image_perf);
            description = itemView.findViewById(R.id.text_description_perf);
            beginning = itemView.findViewById(R.id.text_beginner_perf);

        }
    }

    @NonNull
    @Override
    public DynamicRVAdapterPerformance.DynamicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_performance_item, parent, false);
        DynamicHolder dynamicHolder = new DynamicHolder(view);

        return dynamicHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRVAdapterPerformance.DynamicHolder holder, int position) {
        DynamicPerformanceItem currentItem = dynamicPerformanceItems.get(position);

        holder.title.setText(currentItem.getItemTitle());
        holder.imagePerformance.setImageResource(currentItem.getItemImage());
        holder.description.setText(currentItem.getItemDescription());
        holder.beginning.setText(currentItem.getItemBeginning());
    }

    @Override
    public int getItemCount() {
        return dynamicPerformanceItems.size();
    }


}
