package com.example.condom.api;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.condom.R;
import com.example.condom.modelIP.ActivityUser;
import com.example.condom.modelIP.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestActivity2 extends AppCompatActivity {
    private TextView testText;
    private TextView testText2;
    private String url = "https://condomcom-server.herokuapp.com/api/";
    private DomconApi domconApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        testText = findViewById(R.id.test);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        domconApi = retrofit.create(DomconApi.class);

        //getUser();
        getActivityUser();
        //getActivityUserPost();
    }

    private void getActivityUserPost() {
        ActivityUser activityUser = new ActivityUser(null, null);
        Call<ActivityUser> call = domconApi.getActivityUserPost(activityUser);

        call.enqueue(new Callback<ActivityUser>() {
            @Override
            public void onResponse(Call<ActivityUser> call, Response<ActivityUser> response) {
                if(!response.isSuccessful()) {
                    testText.setText("Code: " + response.code());
                    return;
                }

                ActivityUser postAU = (ActivityUser) response.body();
                String content = "";
                content += "Code: " + response.code() +"\n";
                content += "UserStrId: " + postAU.getUserStrId() + "\n";
                content += "ActivityStrId: " + postAU.getActivityStrId() + "\n";
                testText.append(content);
            }

            @Override
            public void onFailure(Call<ActivityUser> call, Throwable t) {
                testText.setText(t.getMessage());
            }
        });
    }


    private void getUser(){
        Call<List<User>> callUser = domconApi.getUsers();
        callUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(!response.isSuccessful()) {
                    testText.setText("Code: " + response.code());
                    return;
                }

                List<User> userList = response.body();

                for(User user : userList) {
                    String content = "";
                    content += "ID: " + user.getmId() + "\n";
                    content += "User ID: " + user.getUserId() + "\n";
                    content += "Name: " + user.getName() + "\n";
                    content += "Surname: " + user.getName() + "\n";
                    content += "Patronymic: " + user.getPatronymic() + "\n";
                    content += "Email: " + user.getEmail() + "\n";
                    content += "Phone: " + user.getPhone() + "\n";
                    content += "BirthDate: " + user.getBirthDate() + "\n";
                    content += "CreateAt: " + user.getCreatedAt() + "\n";
                    content += "UpdatedAt: " + user.getUpdatedAt() + "\n\n";

                    testText.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                testText.setText(t.getMessage());
            }
        });
    }

    private void getActivityUser() {
        Call<List<ActivityUser>> callActivityUser = domconApi.getActivityUser();
        callActivityUser.enqueue(new Callback<List<ActivityUser>>() {
            @Override
            public void onResponse(Call<List<ActivityUser>> call, Response<List<ActivityUser>> response) {
                if (!response.isSuccessful()) {
                    testText.setText("Code: " + response.code());
                    return;
                }

                List<ActivityUser> activityUserList = response.body();

                for (ActivityUser activityUser : activityUserList) {
                    String content = "";
                    content += "Code: " + response.code() +"\n";
                    content += "UserStrId: " + activityUser.getUserStrId() + "\n";
                    content += "ActivityStrId: " + activityUser.getActivityStrId() + "\n\n";

                    testText.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<ActivityUser>> call, Throwable t) {
                testText.setText(t.getMessage());
            }
        });
    }
}