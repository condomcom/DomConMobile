package com.example.condom.ui.adapters;

import android.text.TextUtils;
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

    /*public Filter getFilter(){

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();

                if(Key.isEmpty()){
                    filterSpeakerList = dynamicSpeakerItems;
                }
                else{
                    ArrayList<DynamicSpeakerItem> listFiltered = new ArrayList<>();

                    for(DynamicSpeakerItem item : dynamicSpeakerItems){
                        if(item.getSpeakerName().toLowerCase().contains(Key.toLowerCase())){
                            listFiltered.add(item);
                        }
                    }

                    filterSpeakerList = listFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterSpeakerList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                filterSpeakerList = (ArrayList<DynamicSpeakerItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }*/

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
