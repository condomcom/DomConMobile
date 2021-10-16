package com.example.condom.api;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.condom.R;
import com.example.condom.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestActivity2 extends AppCompatActivity {
    private TextView testText;
    private String url = "https://condomcom-server.herokuapp.com/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        testText = findViewById(R.id.test);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DomconApi domconApi = retrofit.create(DomconApi.class);

        Call<List<User>> call = domconApi.getUsers();
        call.enqueue(new Callback<List<User>>() {
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
                    content += "BirthDate: " + user.getBirthDate() + "\n\n";

                    testText.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                testText.setText(t.getMessage());
            }
        });
    }
}