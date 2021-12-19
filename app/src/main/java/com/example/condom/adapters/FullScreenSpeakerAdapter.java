package com.example.condom.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.modelItem.SpeakersCardsItem;

import java.util.ArrayList;

public class FullScreenSpeakerAdapter extends RecyclerView.Adapter<FullScreenSpeakerAdapter.ViewHolder> {
    private ArrayList<SpeakersCardsItem> speakersCardsItems;
    private Context context;

    public FullScreenSpeakerAdapter(ArrayList<SpeakersCardsItem> speakersCardsItems, Context context){
        this.speakersCardsItems = speakersCardsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fs_card_speaker,
                parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SpeakersCardsItem speakersCardsItem = speakersCardsItems.get(position);
        holder.mName.setText(speakersCardsItem.getSpeakerName());
        holder.mProfession.setText(speakersCardsItem.getSpeakerProfession());
        holder.mDescription.setText(speakersCardsItem.getSpeakerDescription());
        holder.mPerfTime.setText(speakersCardsItem.getSpeakerPerfTime());
        holder.mPlace.setText(speakersCardsItem.getSpeakerPlace());
        holder.mPerf.setText(speakersCardsItem.getSpeakerPerf());

        if(speakersCardsItem.getSpeakerImage() != null &&
                speakersCardsItem.getSpeakerImage().length() > 0){
            Glide.with(context)
                    .load(speakersCardsItem.getSpeakerImage())
                    .into(holder.mImageView);
        }
        else{
            Toast.makeText(context,"Не удалось загрузить картинку", Toast.LENGTH_LONG).show();
            holder.mImageView.setImageResource(R.drawable.ic_speaker);
        }
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
        TextView mPerf;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mProfession = itemView.findViewById(R.id.fs_speaker_profession);
            mImageView = itemView.findViewById(R.id.fs_image_speaker);
            mName = itemView.findViewById(R.id.fs_speaker_name);
            mDescription = itemView.findViewById(R.id.fs_description_speaker);
            mPerfTime = itemView.findViewById(R.id.fs_duration_perf);
            mPlace = itemView.findViewById(R.id.fs_speaker_place);
            mPerf = itemView.findViewById(R.id.fs_speaker_perf);
        }
    }
}
