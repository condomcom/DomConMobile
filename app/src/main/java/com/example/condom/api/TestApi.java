package com.example.condom.api;

import com.example.condom.modelIP.Activity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TestApi {
    @GET("activities/")
    Call<List<Activity>> getActivities();
}
