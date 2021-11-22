package com.example.condom.api;

import com.example.condom.modelIP.Activity;
import com.example.condom.modelIP.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TestApi {
    @GET("activities/")
    Call<List<Activity>> getActivities();

    @GET("speakers/")
    Call<List<User>> getSpeakers();
}
