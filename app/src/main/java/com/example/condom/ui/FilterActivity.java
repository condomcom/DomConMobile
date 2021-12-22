package com.example.condom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.condom.R;
import com.example.condom.ui.adapters.DynamicRVAdapterPerformance;
import com.example.condom.ui.modelItem.DynamicPerformanceItem;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {
    private Button applyButton;
    private EditText startTime;
    private EditText endTime;
    private EditText date;
    private DynamicRVAdapterPerformance dynamicRVAdapterPerformance;
    private ArrayList<DynamicPerformanceItem> dynamicPerformanceItemsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        dynamicPerformanceItemsArrayList = new ArrayList<>();
        dynamicRVAdapterPerformance = new DynamicRVAdapterPerformance(dynamicPerformanceItemsArrayList, this);

        applyButton = findViewById(R.id.applyButton);
        startTime = findViewById(R.id.edit_text_start_time);
        endTime = findViewById(R.id.edit_text_end_time);
        date = findViewById(R.id.edit_text_date);

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

                Intent intent = new Intent(FilterActivity.this, NewHomeActivityActivity.class);
                startActivity(intent);
            }
        });
    }
}