package com.example.condom.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.condom.R;
import com.example.condom.modelIP.User;
import com.example.condom.navigation.dialog.SpeakerFullscreenDialog;
import com.example.condom.modelItem.SpeakersCardsItem;

import java.util.ArrayList;
import java.util.List;

public class SpeakersAdapter extends RecyclerView.Adapter<SpeakersAdapter.ViewHolder> {
    private ArrayList<SpeakersCardsItem> speakersCardsItems;
    private Context context;

    private List<User> userList;

    public SpeakersAdapter(ArrayList<SpeakersCardsItem> speakersCardsItems, Context context){
        this.speakersCardsItems = speakersCardsItems;
        this.context = context;

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
        holder.mProfession.setText(speakersCardsItem.getSpeakerProfession());
        holder.mDescription.setText(speakersCardsItem.getSpeakerDescription());
        holder.mPerfTime.setText(speakersCardsItem.getSpeakerPerfTime());
        holder.mPlace.setText(speakersCardsItem.getSpeakerPlace());
        holder.mPerf.setText(speakersCardsItem.getSpeakerPerf());
        holder.mDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View itemView) {
                DialogFragment dialogFragment = SpeakerFullscreenDialog.newInstance(speakersCardsItem);
                AppCompatActivity activity = ((AppCompatActivity)itemView.getContext());
                dialogFragment.show(activity.getSupportFragmentManager(),null);
            }
        });


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
        Button mDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mProfession = itemView.findViewById(R.id.t_speaker_profession);
            mImageView = itemView.findViewById(R.id.image_speaker);
            mName = itemView.findViewById(R.id.t_speaker_name);
            mDescription = itemView.findViewById(R.id.t_description_speaker);
            mPerfTime = itemView.findViewById(R.id.t_duration_perf);
            mPlace = itemView.findViewById(R.id.t_speaker_place);
            mPerf = itemView.findViewById(R.id.t_speaker_perf);
            mDetails = itemView.findViewById(R.id.b_details_speaker);

        }
    }
}
