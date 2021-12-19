package com.example.condom.navigation.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.adapters.FullScreenCardAdapter;
import com.example.condom.adapters.PerformancesAdapter;
import com.example.condom.api.DomConApi;
import com.example.condom.modelIP.Activity;
import com.example.condom.modelIP.User;
import com.example.condom.modelItem.FullScreenCardItem;
import com.example.condom.modelItem.PerformancesCardsItem;
import com.example.condom.navigation.PerformanceFragment;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class CardFullscreenDialog extends DialogFragment implements View.OnClickListener {
    private ArrayList<FullScreenCardItem> fullScreenCardItems = new ArrayList<>();
    private FullScreenCardAdapter adapter;

    public static CardFullscreenDialog newInstance(){
        return new CardFullscreenDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreen);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.full_screen_card_dialog, container, false);

        ImageButton close = view.findViewById(R.id.fullscreen_close);
        ImageButton action = view.findViewById(R.id.fullscreen_fav);
        //todo УДАЛИТЬ RECYCLERVIEW!!!!!!!
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFullScreen);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new FullScreenCardAdapter(fullScreenCardItems, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        close.setOnClickListener(this);
        action.setOnClickListener(this);

        /*Intent intent = getActivity().getIntent();
        String id = intent.getStringExtra(PerformanceFragment.EXTRA_ID);
        String title = intent.getStringExtra(PerformanceFragment.EXTRA_TITLE);
        //int img = intent.getIntExtra(PerformanceFragment.EXTRA_IMG);
        String description = intent.getStringExtra(PerformanceFragment.EXTRA_DESCRIPTION);
        String date = intent.getStringExtra(PerformanceFragment.EXTRA_DATE);
        String startTime = intent.getStringExtra(PerformanceFragment.EXTRA_START_TIME);
        String endTime = intent.getStringExtra(PerformanceFragment.EXTRA_END_TIME);
        String speaker = intent.getStringExtra(PerformanceFragment.EXTRA_SPEAKER);
        String company = intent.getStringExtra(PerformanceFragment.EXTRA_COMPANY);

        TextView textTitle = view.findViewById(R.id.t_fullscreen_title_card);
        ImageView imageView = view.findViewById(R.id.image_fullscreen_card);
        TextView textDescription = view.findViewById(R.id.t_fullscreen_description);
        TextView textDate = view.findViewById(R.id.t_starting_date);
        TextView textStartTime = view.findViewById(R.id.t_start_time);
        TextView textEndTime = view.findViewById(R.id.t_end_time);
        TextView textSpeaker = view.findViewById(R.id.t_speaker_full_screen);
        TextView textCompany = view.findViewById(R.id.t_company_full_screen);

        textTitle.setText(title);
        textDate.setText(date);*/

        if(fullScreenCardItems.isEmpty()){
            fullScreenCardItems.add(new FullScreenCardItem("0", "Разработка на моках", R.drawable.ic_c_sharp,
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus tincidunt ipsum et suscipit vehicula. Vivamus vel velit scelerisque, gravida tortor sed, tristique orci. Maecenas libero libero, ultricies eu odio at, sagittis mollis felis. Sed eu tortor quis sem finibus elementum. Aliquam in scelerisque metus, vel pellentesque velit. Nulla nec arcu magna. Aliquam erat volutpat. Fusce sodales augue et ligula maximus, quis consectetur mauris tristique. Vivamus eu lobortis lectus, ac semper lacus.\n" +
                            "Suspendisse potenti. Maecenas vulputate ullamcorper augue, vitae dapibus arcu dictum sit amet. Vestibulum hendrerit libero et cursus fringilla. Duis blandit odio id metus bibendum, sit amet luctus massa blandit. Praesent vehicula consequat tempor. Vivamus consectetur ipsum eu sem sollicitudin fermentum. Vestibulum mattis nisi congue, euismod risus sed, eleifend mauris.",
                    "13.11", "11:00", "15:00", "Александр Петров", "Контур"));
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.fullscreen_close:
                dismiss();
                break;

            case R.id.fullscreen_fav:

                break;
        }
    }
}
