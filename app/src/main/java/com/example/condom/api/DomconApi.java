package com.example.condom.api;

import com.example.condom.modelIP.ActivityUser;
import com.example.condom.modelIP.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DomconApi {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("users")
    Call<List<ActivityUser>> getActivityUser();
}
