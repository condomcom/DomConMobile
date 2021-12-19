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
import com.example.condom.api.ApiClient;
import com.example.condom.modelIP.User;
import com.example.condom.adapters.SpeakersAdapter;
import com.example.condom.modelItem.SpeakersCardsItem;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpeakerFragment extends Fragment {
    private ArrayList<SpeakersCardsItem> speakersItems = new ArrayList<>();

    private static final String TAG = "SpeakersFragment";
    private SpeakersAdapter adapter;

    private RecyclerView recyclerView;

    private List<User> userList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speaker, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewPerformance);
        recyclerView.setHasFixedSize(true);

        adapter = new SpeakersAdapter(speakersItems, getActivity());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getAllSpeakers();

        return view;
    }

    public void getAllSpeakers(){

        Call<List<User>> call = ApiClient.getInterface().getSpeakers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();

                if(response.isSuccessful()){
                    Log.i(TAG, "Code: " + response.code());
                    Log.i(TAG, "Всё путём ...");

                    speakersItems.clear();

                    for (int i = 0; i < users.size(); i++) {
                        User user = users.get(i);

                        String imageUrl = user.getImageUrl().toString();

                        if (user.getRole() == 1) {

                            SpeakersCardsItem item =  new SpeakersCardsItem(i + "", user.getName() + " " + user.getSurname(),
                                    imageUrl, user.getSpeakerPosition(), user.getSpeakerDescription(),
                                    "10:00-12:00", "Аудитория Е228", "JetBrains");

                            speakersItems.add(item);
                        }
                    }
                    
                    PutDataRecyclerView(userList);
                }
                else {
                        Log.i(TAG, "Произошла ошибка повторите попытку позже ...");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }

    private void PutDataRecyclerView(List<User> userList) {
        adapter = new SpeakersAdapter(speakersItems, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
}