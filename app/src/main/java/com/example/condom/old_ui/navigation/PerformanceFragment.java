package com.example.condom.old_ui.navigation;

import android.annotation.SuppressLint;
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
import com.example.condom.old_ui.navigation.adapters.PerformancesAdapter;
import com.example.condom.api.ApiClient;
import com.example.condom.modelItem.PerformancesCardsItem;
import com.example.condom.old_ui.navigation.dialog.FilterFullscreenDialog;

import com.example.condom.modelIP.Activity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerformanceFragment extends Fragment {
    public static final String EXTRA_DATE = "date";
    public static final String EXTRA_START_TIME = "start time";
    public static final String EXTRA_END_TIME = "end time";
    public static final String EXTRA_SPEAKER = "speaker";
    public static final String EXTRA_COMPANY = "company";
    private ArrayList<PerformancesCardsItem> performancesItems;
    private PerformancesAdapter adapter;
    private ArrayList<PerformancesCardsItem> filterList = new ArrayList<>();
    private List<Activity> activityList = new ArrayList<>();
    private RecyclerView recyclerView;

    private static final String TAG = "PerformanceFragment";

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_performance, container, false);

        performancesItems = new ArrayList<PerformancesCardsItem>();

        recyclerView = view.findViewById(R.id.recyclerViewPerformance);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getAllPerformance();

        return view;
    }

    private void getAllPerformance() {
        Call<List<Activity>> call = ApiClient.getInterface().getActivities();

        call.enqueue(new Callback<List<Activity>>() {
            @Override
            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {
                List<Activity> activities = response.body();

                if(response.isSuccessful()) {
                    Log.i(TAG, "Code: " + response.code());
                    Log.i(TAG, "?????? ?????????? ...");

                    performancesItems.clear();

                    for (int i = 0; i < activities.size(); i++) {
                        Activity activity = activities.get(i);

                        String imageUrl = activity.getImageUrl();

                        if (activity.mFullName != null) {
                            PerformancesCardsItem card = new PerformancesCardsItem(i + "", activity.mShortName,
                                    imageUrl, activity.mDescription,
                                    "???????????? ?? 14:00", "4 ????????", "0");

                            performancesItems.add(card);
                        }

                        PutDataRecyclerView(activityList);
                    }
                }
                else{
                    Log.i(TAG, "?????????????????? ???????????? ?????????????????? ?????????????? ?????????? ...");
                }
            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }

    private void PutDataRecyclerView(List<Activity> activityList) {
        adapter = new PerformancesAdapter(performancesItems, getActivity());
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
        searchView.setQueryHint("??????????");

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
            DialogFragment dialog = FilterFullscreenDialog.newInstance();
            dialog.show(getActivity().getSupportFragmentManager(), "tag");
        }
        return super.onOptionsItemSelected(item);
    }

   /* @Override
    public void OnItemClick(int position) {
        Intent detailIntent = new Intent(getActivity(), CardFullscreenDialog.class);
        PerformancesCardsItem item = performancesItems.get(position);

        detailIntent.putExtra(EXTRA_DATE, "13.11");
        detailIntent.putExtra(EXTRA_START_TIME, "11:00");
        detailIntent.putExtra(EXTRA_END_TIME, "15:00");
        detailIntent.putExtra(EXTRA_SPEAKER, "?????????????????? ????????????");
        detailIntent.putExtra(EXTRA_COMPANY, "????????????");

        startActivity(detailIntent);
    }*/
}