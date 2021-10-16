package com.example.condom.speakers;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import java.util.ArrayList;

public class SpeakersAdapter extends RecyclerView.Adapter<SpeakersAdapter.ViewHolder> {
    private ArrayList<SpeakersCardsItem> speakersCardsItems;

    public SpeakersAdapter(ArrayList<SpeakersCardsItem> speakersCardsItems, Context context){
        this.speakersCardsItems = speakersCardsItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_speaker_item,
                parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SpeakersCardsItem speakersCardsItem = speakersCardsItems.get(position);
        holder.mName.setText(speakersCardsItem.getSpeakerName());
        holder.mImageView.setImageResource(speakersCardsItem.getSpeakerImage());
        holder.mProfession.setText(speakersCardsItem.getSpeakerProfession());
        holder.mDescription.setText(speakersCardsItem.getSpeakerDescription());
        holder.mPerfTime.setText(speakersCardsItem.getSpeakerPerfTime());
        holder.mPlace.setText(speakersCardsItem.getSpeakerPlace());
    }

    @Override
    public int getItemCount() {
        return speakersCardsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mProfession;
        ImageView mImageView;
        TextView mName;
        TextView mDescription;
        TextView mPerfTime;
        TextView mPlace;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mProfession = itemView.findViewById(R.id.t_speaker_profession);
            mImageView = itemView.findViewById(R.id.image_speaker);
            mName = itemView.findViewById(R.id.t_speaker_name);
            mDescription = itemView.findViewById(R.id.t_description_speaker);
            mPerfTime = itemView.findViewById(R.id.t_duration_perf);
            mPlace = itemView.findViewById(R.id.t_speaker_place);
        }
    }


}