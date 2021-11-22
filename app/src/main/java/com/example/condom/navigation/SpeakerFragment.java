package com.example.condom.navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.adapters.PerformancesAdapter;
import com.example.condom.api.TestApi;
import com.example.condom.modelIP.Activity;
import com.example.condom.modelIP.User;
import com.example.condom.modelItem.PerformancesCardsItem;
import com.example.condom.speakers.SpeakersAdapter;
import com.example.condom.speakers.SpeakersCardsItem;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpeakerFragment extends Fragment {
    private ArrayList<SpeakersCardsItem> speakersItems = new ArrayList<>();

    private static final String TAG = "SpeakersFragment";
    private SpeakersAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speaker, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPerformance);
        recyclerView.setHasFixedSize(true);

        adapter = new SpeakersAdapter(speakersItems, getActivity());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://condomcom-server.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TestApi a = retrofit.create(TestApi.class);

        Call call = a.getSpeakers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                Log.i(TAG, "Code: " + response.code());

                speakersItems.clear();

                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);

                    if (user.getRole() == 1) {
                        SpeakersCardsItem item =  new SpeakersCardsItem(i + "", user.getName() + " " + user.getSurname(),
                                0, user.getSpeakerPosition(), user.getSpeakerDescription(),
                                "10:00-12:00", "Аудитория Е228", "JetBrains");

                        speakersItems.add(item);
                    }

                }
                adapter = new SpeakersAdapter(speakersItems, getActivity());
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });

        return view;
    }
}