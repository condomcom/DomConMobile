package com.example.condom.navigation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.api.TestApi;
import com.example.condom.favoritesAdapter.PerformancesAdapter;
import com.example.condom.modelIP.Activity;
import com.example.condom.modelItem.PerformancesCardsItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerformanceFragment extends Fragment {
    private ArrayList<PerformancesCardsItem> performancesItems;
    private PerformancesAdapter adapter;
    private ArrayList<PerformancesCardsItem> filterList = new ArrayList<>();

    private static final String TAG = "TAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_performance, container, false);

        performancesItems = new ArrayList<PerformancesCardsItem>();


        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPerformance);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //adapter.notifyDataSetChanged();

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
                Log.i(TAG, "Code: " + response.code());

                performancesItems.clear();

                for (int i = 0; i < activities.size(); i++) {
                    Activity activity = activities.get(i);

                    if (activity.mFullName != null) {
                        PerformancesCardsItem card = new PerformancesCardsItem(i + "", activity.mShortName,
                                R.drawable.ic_backend, activity.mDescription,
                                "Начало в 14:00", "4 часа", "0");

                        performancesItems.add(card);
                    }
                }
                adapter = new PerformancesAdapter(performancesItems, getActivity());
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();


            }
            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
        //performancesItems.add(a.mFullName);

        //performancesItems.add(new PerformancesCardsItem("0", "Проблемы embedded или как мы от sqlite ушли", R.drawable.ic_backend,
        // ".",
        //        "Начало в 14:00", "4 часа", "0"));

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.performance_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Поиск");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}