package com.example.condom.api;

import com.example.condom.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DomconApi {

    @GET("users")
    Call<List<User>> getUsers();
}
