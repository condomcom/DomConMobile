package com.example.condom.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.dataBase.FavoritesDB;
import com.example.condom.modelItem.PerformancesCardsItem;
import com.example.condom.navigation.dialog.CardFullscreenDialog;

import java.util.ArrayList;

public class PerformancesAdapter extends RecyclerView.Adapter<PerformancesAdapter.ViewHolder> implements Filterable{
    private ArrayList<PerformancesCardsItem> performancesCardsItems;
    private Context context;
    private FavoritesDB favoritesDB;
    private ArrayList<PerformancesCardsItem> performancesCardsItemsFull;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public PerformancesAdapter( ArrayList<PerformancesCardsItem> performancesCardsItems, Context context){
        this.performancesCardsItemsFull = performancesCardsItems;
        this.context = context;
        this.performancesCardsItems = new ArrayList<>(performancesCardsItemsFull);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favoritesDB = new FavoritesDB(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = sharedPreferences.getBoolean("firstStart", true);
        if(firstStart) createTableOnFirstStart();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_perform_item,
                parent, false);
        return new ViewHolder(view);
    }

    private void createTableOnFirstStart() {
        favoritesDB.insertEmpty();

        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    @Override
    public void onBindViewHolder(@NonNull PerformancesAdapter.ViewHolder holder, int position) {
        final PerformancesCardsItem performancesCardsItem = performancesCardsItems.get(position);
        readCursorData(performancesCardsItem, holder);

        holder.mBeginning.setText(performancesCardsItem.getItemBeginning());
        holder.mImageView.setImageResource(performancesCardsItem.getItemImage());
        holder.mTitle.setText(performancesCardsItem.getItemTitle());
        holder.mDescription.setText(performancesCardsItem.getItemDescription());
        holder.mDuration.setText(performancesCardsItem.getItemDuration());
    }

    @Override
    public int getItemCount() {
        return performancesCardsItems.size();
    }

    @Override
    public Filter getFilter() {
        return newFilter;
    }

    private final Filter newFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<PerformancesCardsItem> filteredNewList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredNewList.addAll(performancesCardsItemsFull);
            }
            else{
                String filteredPattern = constraint.toString().toLowerCase().trim();

                for (PerformancesCardsItem item : performancesCardsItemsFull){
                    if(item.getItemTitle().toLowerCase().contains(filteredPattern))
                        filteredNewList.add(item);
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredNewList;
            results.count = filteredNewList.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            performancesCardsItems.clear();
            performancesCardsItems.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mBeginning;
        Button mFavourites;
        ImageView mImageView;
        TextView mTitle;
        TextView mDescription;
        TextView mDuration;
        Button mDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mBeginning = itemView.findViewById(R.id.t_beginning_perf);
            mFavourites = itemView.findViewById(R.id.favourites_perf);
            mImageView = itemView.findViewById(R.id.image_perf);
            mTitle = itemView.findViewById(R.id.t_title_perf);
            mDescription = itemView.findViewById(R.id.t_description_perf);
            mDuration = itemView.findViewById(R.id.t_duration_perf);
            mDetails = itemView.findViewById(R.id.b_details_perf);

            mFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Animation animScaleUp = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.scale_up);
                    final Animation animScaleDown = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.scale_down);

                    int position = getAbsoluteAdapterPosition();
                    PerformancesCardsItem performancesCardsItem = performancesCardsItems.get(position);

                    if(performancesCardsItem.getFavoriteStatus().equals("0")){
                        performancesCardsItem.setFavoriteStatus("1");
                        favoritesDB.insertIntoDatabase(performancesCardsItem.getItemTitle(), performancesCardsItem.getItemImage(),
                                performancesCardsItem.getItemDescription(), performancesCardsItem.getItemBeginning(),
                                performancesCardsItem.getItemDuration(), performancesCardsItem.getFavoriteStatus(),
                                performancesCardsItem.getKeyId());
                        mFavourites.setBackgroundResource(R.drawable.ic_favorite_active);
                        mFavourites.startAnimation(animScaleUp);
                    }
                    else if(performancesCardsItem.getFavoriteStatus().equals("1")){
                        performancesCardsItem.setFavoriteStatus("0");
                        favoritesDB.removeFav(performancesCardsItem.getKeyId());
                        mFavourites.setBackgroundResource(R.drawable.ic_favorite_inactive);
                        mFavourites.startAnimation(animScaleDown);
                    }
                    else{
                        favoritesDB.removeFav(performancesCardsItem.getKeyId());
                        performancesCardsItem.setFavoriteStatus("0");
                    }
                }
            });

            mDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialogFragment(v);
                }
            });
        }

        public void showDialogFragment(View view){
            DialogFragment dialogFragment = CardFullscreenDialog.newInstance();
            AppCompatActivity activity = ((AppCompatActivity)view.getContext());
            dialogFragment.show(activity.getSupportFragmentManager(),null);
        }
    }

    private void readCursorData(PerformancesCardsItem performancesCardsItem,
                                ViewHolder viewHolder) {
        Cursor cursor = favoritesDB.readData(performancesCardsItem.getKeyId());
        SQLiteDatabase sqLiteDatabase = favoritesDB.getReadableDatabase();

        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavoritesDB.FAVORITE_STATUS));
                performancesCardsItem.setFavoriteStatus(item_fav_status);

                //check fav status
                if (item_fav_status != null && item_fav_status.equals("1")) {
                    viewHolder.mFavourites.setBackgroundResource(R.drawable.ic_favorite_active);
                } else if (item_fav_status != null && item_fav_status.equals("0")) {
                    viewHolder.mFavourites.setBackgroundResource(R.drawable.ic_favorite_inactive);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            sqLiteDatabase.close();
        }
    }
}
