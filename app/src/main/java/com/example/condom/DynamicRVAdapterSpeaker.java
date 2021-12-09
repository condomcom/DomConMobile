package com.example.condom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DynamicRVAdapterSpeaker extends RecyclerView.Adapter<DynamicRVAdapterSpeaker.DynamicRVHolder>{
    private ArrayList<DynamicSpeakerItem> dynamicSpeakerItems;

    public DynamicRVAdapterSpeaker(ArrayList<DynamicSpeakerItem> dynamicSpeakerItems){
        this.dynamicSpeakerItems = dynamicSpeakerItems;
    }

    public class DynamicRVHolder extends RecyclerView.ViewHolder{
        private ImageView avatar;
        private TextView name;
        private TextView profession;
        private ConstraintLayout constraintLayout;

        public DynamicRVHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.circleImageView_speaker);
            name = itemView.findViewById(R.id.text_speaker_name);
            profession = itemView.findViewById(R.id.text_speaker_profession);
            constraintLayout = itemView.findViewById(R.id.constraintLayout_speaker_item);
        }
    }

    @NonNull
    @Override
    public DynamicRVAdapterSpeaker.DynamicRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_speaker_item, parent, false);
        DynamicRVHolder dynamicRVHolder = new DynamicRVHolder(view);

        return dynamicRVHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRVAdapterSpeaker.DynamicRVHolder holder, int position) {
        DynamicSpeakerItem currentItem = dynamicSpeakerItems.get(position);
        holder.avatar.setImageResource(currentItem.getSpeakerImage());
        holder.name.setText(currentItem.getSpeakerName());
        holder.profession.setText(currentItem.getSpeakerProfession());
    }

    @Override
    public int getItemCount() {
        return dynamicSpeakerItems.size();
    }
}
