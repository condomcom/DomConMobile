package com.example.condom.ui.adapters;

import android.text.TextUtils;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.ui.modelItem.DynamicSpeakerItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class DynamicRVAdapterSpeaker extends RecyclerView.Adapter<DynamicRVAdapterSpeaker.DynamicRVHolder>{
    private ArrayList<DynamicSpeakerItem> dynamicSpeakerItems;
    private ArrayList<DynamicSpeakerItem> speakerItemArrayListCopy;

    public DynamicRVAdapterSpeaker(ArrayList<DynamicSpeakerItem> dynamicSpeakerItems){
        this.dynamicSpeakerItems = dynamicSpeakerItems;
        this.speakerItemArrayListCopy = new ArrayList<>();
        speakerItemArrayListCopy.addAll(dynamicSpeakerItems);
    }

    public class DynamicRVHolder extends RecyclerView.ViewHolder{
        private ImageView avatar;
        private TextView name;
        private TextView profession;
        private FloatingActionButton fabMoreDetailed;
        private ConstraintLayout constraintLayoutSpeakerGone;
        private ConstraintLayout constraintLayoutSpeakerItem;

        private TextView title;
        private TextView place;
        private TextView time;
        private View viewTitle;
        private View viewPlace;
        private View viewTime;
        private TextView placeLoc;

        public DynamicRVHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.circleImageView_speaker);
            name = itemView.findViewById(R.id.text_speaker_name);
            profession = itemView.findViewById(R.id.text_speaker_profession);
            title = itemView.findViewById(R.id.text_speaker_title);
            place = itemView.findViewById(R.id.text_speaker_location);
            time = itemView.findViewById(R.id.text_speaker_time);
            fabMoreDetailed = itemView.findViewById(R.id.floatingActionButton_speaker);
            //constraintLayoutSpeakerGone = itemView.findViewById(R.id.constraintLayout_speaker_gone);
            constraintLayoutSpeakerItem = itemView.findViewById(R.id.constraintLayout_speaker);

            placeLoc = itemView.findViewById(R.id.text_speaker_loc);
            viewTitle = itemView.findViewById(R.id.view_pref_title);
            viewPlace = itemView.findViewById(R.id.view_pref_loc);
            viewTime = itemView.findViewById(R.id.view_pref_time);

            fabMoreDetailed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(title.getVisibility() == View.GONE && time.getVisibility() == View.GONE
                            && place.getVisibility() == View.GONE && viewTitle.getVisibility() == View.GONE
                            && viewPlace.getVisibility() == View.GONE && viewTime.getVisibility() == View.GONE
                            && placeLoc.getVisibility() == View.GONE){
                        fabMoreDetailed.setImageResource(R.drawable.ic_arrow_up);
                        TransitionManager.beginDelayedTransition(constraintLayoutSpeakerItem, new AutoTransition());
                        title.setVisibility(View.VISIBLE);
                        place.setVisibility(View.VISIBLE);
                        time.setVisibility(View.VISIBLE);
                        viewTitle.setVisibility(View.VISIBLE);
                        viewTime.setVisibility(View.VISIBLE);
                        viewPlace.setVisibility(View.VISIBLE);
                        placeLoc.setVisibility(View.VISIBLE);
                    }
                    else{
                        fabMoreDetailed.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                        placeLoc.setVisibility(View.GONE);
                        title.setVisibility(View.GONE);
                        place.setVisibility(View.GONE);
                        time.setVisibility(View.GONE);
                        viewTitle.setVisibility(View.GONE);
                        viewTime.setVisibility(View.GONE);
                        viewPlace.setVisibility(View.GONE);
                        TransitionManager.beginDelayedTransition(constraintLayoutSpeakerItem, new AutoTransition());
                    }
                }
            });
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
        holder.title.setText(currentItem.getSpeakerTitle());
        holder.place.setText(currentItem.getSpeakerPlace());
        holder.time.setText(currentItem.getSpeakerTime());
    }

    @Override
    public int getItemCount() {
        return dynamicSpeakerItems.size();
    }

    public void filter(CharSequence charSequence){
        ArrayList<DynamicSpeakerItem> tempArrayList = new ArrayList<>();
        if(!TextUtils.isEmpty(charSequence)){
            for(DynamicSpeakerItem item : dynamicSpeakerItems){
                if(item.getSpeakerName().toLowerCase().contains(charSequence)){
                    tempArrayList.add(item);
                }
            }
        }
        else{
            tempArrayList.addAll(speakerItemArrayListCopy);
        }

        dynamicSpeakerItems.clear();
        dynamicSpeakerItems.addAll(tempArrayList);
        notifyDataSetChanged();
        tempArrayList.clear();
    }
}
