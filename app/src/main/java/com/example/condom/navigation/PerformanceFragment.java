package com.example.condom.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.api.TestApi;
import com.example.condom.favoritesAdapter.PerformancesAdapter;
import com.example.condom.modelIP.Activity;
import com.example.condom.modelIP.ActivityUser;
import com.example.condom.modelItem.PerformancesCardsItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerformanceFragment extends Fragment {
    private ArrayList<PerformancesCardsItem> performancesItems = new ArrayList<>();
    PerformancesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_performance, container, false);

        adapter =new PerformancesAdapter(performancesItems, getActivity());

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPerformance);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://condomcom-server.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TestApi a = retrofit.create(TestApi.class);

        Call call = a.getActivities();

        call.enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                List<Activity> activities = response.body();

                performancesItems.clear();

                for (int i = 0; i < activities.size(); i++) {
                    Activity activity = activities.get(i);

                    if (activity.mFullName != null) {
                        PerformancesCardsItem card = new PerformancesCardsItem(i + "", activity.mShortName,
                                R.drawable.ic_backend, activity.mDescription,
                                "Начало", "4 часа", "0");

                        performancesItems.add(card);
                    }
                }

                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {

            }
        });

        //performancesItems.add(a.mFullName);

        //performancesItems.add(new PerformancesCardsItem("0", "Проблемы embedded или как мы от sqlite ушли", R.drawable.ic_backend,
        // ".",
        //        "Начало в 14:00", "4 часа", "0"));

        return view;
    }
}