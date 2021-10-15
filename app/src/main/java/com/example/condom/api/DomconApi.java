package com.example.condom.api;

import com.example.condom.modelIP.ActivityUser;
import com.example.condom.modelIP.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DomconApi {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("activities/")
    Call<List<ActivityUser>> getActivityUser();

    @POST("activities/")
    Call<ActivityUser> getActivityUserPost(@Body ActivityUser activityUser);
}