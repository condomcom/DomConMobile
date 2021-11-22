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
import com.example.condom.modelItem.PerformancesCardsItem;
import com.example.condom.navigation.dialog.CardFullscreenDialog;
import com.example.condom.navigation.dialog.FilterFullscreenDialog;

import com.example.condom.api.TestApi;
import com.example.condom.modelIP.Activity;
import com.example.condom.modelItem.PerformancesCardsItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;

public class PerformanceFragment extends Fragment implements PerformancesAdapter.OnItemClickListener{
    public static final String EXTRA_ID = "id";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final int EXTRA_IMG = 0;
    public static final String EXTRA_DATE = "date";
    public static final String EXTRA_START_TIME = "start time";
    public static final String EXTRA_END_TIME = "end time";
    public static final String EXTRA_SPEAKER = "speaker";
    public static final String EXTRA_COMPANY = "company";


    //public static final String EXTRA_ID = "id";



    private ArrayList<PerformancesCardsItem> performancesItems;
    private PerformancesAdapter adapter;
    private ArrayList<PerformancesCardsItem> filterList = new ArrayList<>();

    private static final String TAG = "PerfomanceFragment";

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_performance, container, false);

        performancesItems = new ArrayList<PerformancesCardsItem>();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPerformance);

        recyclerView.setHasFixedSize(true);
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
                Log.i(TAG, "Code: " + response.code());

                performancesItems.clear();

                for (int i = 0; i < activities.size(); i++) {
                    Activity activity = activities.get(i);

                    if (activity.mFullName != null) {
                        PerformancesCardsItem card = new PerformancesCardsItem(i + "", activity.mShortName,
                                R.drawable.ic_c_sharp, activity.mDescription,
                                "Начало в 14:00", "4 часа", "0");

                        performancesItems.add(card);
                    }

                }
                adapter = new PerformancesAdapter(performancesItems, getActivity());
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(PerformanceFragment.this::OnItemClick);

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

    @Override
    public void OnItemClick(int position) {
        Intent detailIntent = new Intent(getActivity(), CardFullscreenDialog.class);
        PerformancesCardsItem item = performancesItems.get(position);

        //detailIntent.putExtra(EXTRA_ID, item.getKeyId());
        //detailIntent.putExtra(EXTRA_TITLE, item.getItemTitle());
        //detailIntent.putExtra(EXTRA_DESCRIPTION, item.getItemDescription());
        //detailIntent.putExtra(String.valueOf(EXTRA_IMG), R.drawable.ic_c_sharp);
        detailIntent.putExtra(EXTRA_DATE, "13.11");
        detailIntent.putExtra(EXTRA_START_TIME, "11:00");
        detailIntent.putExtra(EXTRA_END_TIME, "15:00");
        detailIntent.putExtra(EXTRA_SPEAKER, "Александр Петров");
        detailIntent.putExtra(EXTRA_COMPANY, "Контур");

        startActivity(detailIntent);
    }
}