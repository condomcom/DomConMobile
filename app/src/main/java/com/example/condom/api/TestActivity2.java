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
    private String urlUser = "https://condomcom-server.herokuapp.com/api/";
    private String urlActivityUser = "https://condomcom-server.herokuapp.com/api/activities/ovwpnCo2KR-AYGJrEPxxJ/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        testText = findViewById(R.id.test);
        testText = findViewById(R.id.test2);

        Retrofit retrofitUser = new Retrofit.Builder()
                .baseUrl(urlUser)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DomconApi domconApiUser = retrofitUser.create(DomconApi.class);

        Call<List<User>> callUser = domconApiUser.getUsers();
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

        Retrofit retrofitActivityUser = new Retrofit.Builder()
                .baseUrl(urlUser)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DomconApi domconApiActivityUser = retrofitActivityUser.create(DomconApi.class);

        Call<List<ActivityUser>> callActivityUser = domconApiActivityUser.getActivityUser();
        callActivityUser.enqueue(new Callback<List<ActivityUser>>() {
            @Override
            public void onResponse(Call<List<ActivityUser>> call, Response<List<ActivityUser>> response) {
                if(!response.isSuccessful()) {
                    testText2.setText("Code: " + response.code());
                    return;
                }

                List<ActivityUser> activityUserList = response.body();

                for(ActivityUser activityUser : activityUserList){
                    String content = "";
                    content += "UserStrId" + activityUser.getUserStrId() +"\n";
                    content += "ActivityStrId" + activityUser.getActivityStrId() +"\n\n";
                }
            }

            @Override
            public void onFailure(Call<List<ActivityUser>> call, Throwable t) {
                testText2.setText(t.getMessage());
            }
        });
    }
}