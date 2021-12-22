package com.example.condom.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.condom.R;
import com.example.condom.ui.adapters.DynamicRVAdapterPerformance;
import com.example.condom.ui.modelItem.DynamicPerformanceItem;

import java.util.ArrayList;

public class FilterFragment extends Fragment {
    private Button applyButton;
    private EditText startTime;
    private EditText endTime;
    private EditText date;
    private DynamicRVAdapterPerformance dynamicRVAdapterPerformance;
    private ArrayList<DynamicPerformanceItem> dynamicPerformanceItemsArrayList;

    public static FilterFragment newInstance() {
        FilterFragment fragment = new FilterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_filter, container, false);
        dynamicPerformanceItemsArrayList = new ArrayList<>();
        dynamicRVAdapterPerformance = new DynamicRVAdapterPerformance(dynamicPerformanceItemsArrayList, getContext());

        applyButton = view.findViewById(R.id.applyButton);
        startTime = view.findViewById(R.id.edit_text_start_time);
        endTime = view.findViewById(R.id.edit_text_end_time);
        date = view.findViewById(R.id.edit_text_date);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*startTime.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        //todo Auto-generated method stub
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        dynamicRVAdapterPerformance.filteringRecyclerView(s);

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        //todo Auto-generated method stub
                    }
                });*/
                Intent intent = new Intent(getActivity(), HomeFragment.class);
                FilterFragment.this.startActivity(intent);

            }
        });

        return view;
    }
}