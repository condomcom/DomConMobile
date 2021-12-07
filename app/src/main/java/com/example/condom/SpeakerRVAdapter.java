package com.example.condom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/*class LoadingViewHolder extends RecyclerView.ViewHolder{

    public LoadingViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}*/

class ItemViewHolder extends RecyclerView.ViewHolder{

    TextView profession;
    ImageView imageView;
    TextView name;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        profession = itemView.findViewById(R.id.text_speaker_profession);
        imageView = itemView.findViewById(R.id.circleImageView_speaker);
        name = itemView.findViewById(R.id.text_speaker_name);
    }
}

public class SpeakerRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    private Context context;
    ArrayList<newSpeakerRVItem> speakerRVItemList;
    int lastVisibleItem, totalItemCount;
    boolean isLoading;
    int visibleThreshold;
    LoadMore loadMore;

    public SpeakerRVAdapter(RecyclerView recyclerView, Context context, ArrayList<newSpeakerRVItem> speakerRVItemList) {
        this.context = context;
        this.speakerRVItemList = speakerRVItemList;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if(!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)){
                    if(loadMore != null)
                        loadMore.onLoadMore();
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return speakerRVItemList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoadMore(LoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*if(viewType == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(context).inflate(R.layout.dynamic_rv_speaker_item, parent, false);
        }*/
        View view = LayoutInflater.from(context).inflate(R.layout.dynamic_rv_speaker_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            newSpeakerRVItem item = speakerRVItemList.get(position);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;

            viewHolder.imageView.setImageResource(item.getSpeakerImage());
            viewHolder.name.setText(item.getSpeakerName());
            viewHolder.profession.setText(item.getSpeakerProfession());
        }
    }

    @Override
    public int getItemCount() {
        return speakerRVItemList.size();
    }
}
