package com.example.condom.old_ui.navigation.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.old_ui.navigation.adapters.FullScreenCardAdapter;
import com.example.condom.modelIP.Activity;
import com.example.condom.modelItem.FullScreenCardItem;
import com.google.android.material.appbar.AppBarLayout;


import java.util.ArrayList;
import java.util.List;

public class CardFullscreenDialog extends DialogFragment implements View.OnClickListener {
    private ArrayList<FullScreenCardItem> fullScreenCardItems = new ArrayList<>();
    private FullScreenCardAdapter adapter;
    private List<Activity> activityList = new ArrayList<>();
    private RecyclerView recyclerView;

    public static CardFullscreenDialog newInstance(){
        return new CardFullscreenDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreen);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.full_screen_card_dialog, container, false);

        AppBarLayout appBarLayout = view.findViewById(R.id.appbar_perf);
        ImageButton close = view.findViewById(R.id.fullscreen_close);
        /*ImageButton action = view.findViewById(R.id.fullscreen_fav);
        //todo УДАЛИТЬ RECYCLERVIEW!!!!!!!
        recyclerView = view.findViewById(R.id.recyclerViewFullScreen);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new FullScreenCardAdapter(fullScreenCardItems, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));*/

        close.setOnClickListener(this);
        //action.setOnClickListener(this);


        //getAllCardDialog();

        /*if(fullScreenCardItems.isEmpty()){
            fullScreenCardItems.add(new FullScreenCardItem("0", "Разработка на моках", R.drawable.ic_c_sharp,
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus tincidunt ipsum et suscipit vehicula. Vivamus vel velit scelerisque, gravida tortor sed, tristique orci. Maecenas libero libero, ultricies eu odio at, sagittis mollis felis. Sed eu tortor quis sem finibus elementum. Aliquam in scelerisque metus, vel pellentesque velit. Nulla nec arcu magna. Aliquam erat volutpat. Fusce sodales augue et ligula maximus, quis consectetur mauris tristique. Vivamus eu lobortis lectus, ac semper lacus.\n" +
                            "Suspendisse potenti. Maecenas vulputate ullamcorper augue, vitae dapibus arcu dictum sit amet. Vestibulum hendrerit libero et cursus fringilla. Duis blandit odio id metus bibendum, sit amet luctus massa blandit. Praesent vehicula consequat tempor. Vivamus consectetur ipsum eu sem sollicitudin fermentum. Vestibulum mattis nisi congue, euismod risus sed, eleifend mauris.",
                    "13.11", "11:00", "15:00", "Александр Петров", "Контур"));
        }*/
        return view;
    }

    /*@Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dialog_card_menu, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.close_action);

        super.onCreateOptionsMenu(menu, inflater);
    }*/

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.fullscreen_close:
                dismiss();
                break;
        }
    }
}
