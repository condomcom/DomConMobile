package com.example.condom.api;

import com.example.condom.BuildConfig;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {
    private static OkHttpClient client;
    private static Retrofit retrofit;
    private static Gson gson;
    private static DomconApi api;

    public static Retrofit getRetrofit() {
        if (gson == null) {
            gson = new Gson();
        }
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static DomconApi getApiService() {
        if (api == null) {
            api = getRetrofit().create(DomconApi.class);
        }
        return api;
    }
}
