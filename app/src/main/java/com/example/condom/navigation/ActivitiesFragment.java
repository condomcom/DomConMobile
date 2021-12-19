package com.example.condom.navigation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.adapters.PerformancesAdapter;
import com.example.condom.api.ApiClient;
import com.example.condom.modelIP.Activity;
import com.example.condom.modelItem.PerformancesCardsItem;
import com.example.condom.navigation.dialog.CardFullscreenDialog;
import com.example.condom.navigation.dialog.FilterFullscreenDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivitiesFragment extends Fragment {

    private ArrayList<PerformancesCardsItem> activitiesItems;
    private PerformancesAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<PerformancesCardsItem> filterList = new ArrayList<>();
    private static final String TAG = "ActivitiesFragment";
    private List<Activity> activityList = new ArrayList<>();

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_performance, container, false);
        activitiesItems = new ArrayList<PerformancesCardsItem>();
        recyclerView = view.findViewById(R.id.recyclerViewPerformance);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getAllActivities();

        return view;
    }

    private void getAllActivities() {
        Call<List<Activity>> call = ApiClient.getInterface().getActivities();

        call.enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                List<Activity> activities = response.body();

                if(response.isSuccessful()) {
                    Log.i(TAG, "Code: " + response.code());
                    Log.i(TAG, "Всё путём ...");

                    activitiesItems.clear();

                    for (int i = 0; i < activities.size(); i++) {
                        Activity activity = activities.get(i);

                        if (activity.mFullName != null) {
                            PerformancesCardsItem card = new PerformancesCardsItem(i + "", activity.mShortName,
                                    R.drawable.ic_c_sharp, activity.mDescription,
                                    "Начало в 14:00", "4 часа", "0");

                            activitiesItems.add(card);
                        }

                        PutDataRecyclerView(activityList);
                    }
                }
                else{
                    Log.i(TAG, "Произошла ошибка повторите попытку позже ...");
                }
            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }

    private void PutDataRecyclerView(List<Activity> activityList) {
        adapter = new PerformancesAdapter(activitiesItems, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.performance_menu, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.search_action);

        SearchView searchView = (SearchView) searchMenuItem.getActionView();
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.filter_action){
            /*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.FullScreenFilter);
            View view = getLayoutInflater().inflate(R.layout.full_screen_filret, null);

            builder.setView(view);
            AlertDialog dialog = builder.create();
            dialog.show();*/
            DialogFragment dialog = FilterFullscreenDialog.newInstance();
            dialog.show(getActivity().getSupportFragmentManager(), "tag");
        }
        return super.onOptionsItemSelected(item);
    }
}