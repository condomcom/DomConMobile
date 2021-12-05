package com.example.condom.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.modelItem.FullScreenCardItem;

import java.util.ArrayList;

public class FullScreenCardAdapter extends RecyclerView.Adapter<FullScreenCardAdapter.ViewHolder> {
    private ArrayList<FullScreenCardItem> fullScreenCardItems;

    public FullScreenCardAdapter(ArrayList<FullScreenCardItem> fullScreenCardItems, Context context){
        this.fullScreenCardItems = fullScreenCardItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fullscreen_card_dialog_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FullScreenCardAdapter.ViewHolder holder, int position) {
        final FullScreenCardItem fullScreenCardItem = fullScreenCardItems.get(position);

        holder.imageView.setImageResource(fullScreenCardItem.getItemImage());
        holder.textTitle.setText(fullScreenCardItem.getItemTitle());
        holder.textDescription.setText(fullScreenCardItem.getItemDescription());
        holder.textDate.setText(fullScreenCardItem.getItemDate());
        holder.textStartTime.setText(fullScreenCardItem.getItemStartTime());
        holder.textEndTime.setText(fullScreenCardItem.getItemEndTime());
        holder.textSpeaker.setText(fullScreenCardItem.getItemSpeaker());
        holder.textCompany.setText(fullScreenCardItem.getItemCompany());
    }

    @Override
    public int getItemCount() {
        return fullScreenCardItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleBar;
        ImageView imageView;
        TextView textTitle;
        TextView textDescription;
        TextView textDate;
        TextView textStartTime;
        TextView textEndTime;
        TextView textSpeaker;
        TextView textCompany;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            /*titleBar = itemView.findViewById(R.id.title_fullscreen);
            imageView = itemView.findViewById(R.id.image_fullscreen_card);
            textTitle = itemView.findViewById(R.id.t_fullscreen_title_card);
            textDescription = itemView.findViewById(R.id.t_fullscreen_description);
            textDate = itemView.findViewById(R.id.t_starting_date);
            textStartTime = itemView.findViewById(R.id.t_start_time);
            textEndTime = itemView.findViewById(R.id.t_end_time);
            textSpeaker = itemView.findViewById(R.id.t_speaker_full_screen);
            textCompany = itemView.findViewById(R.id.t_company_full_screen);*/
        }
    }
}
