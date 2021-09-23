package com.example.condom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AuthorizationActivity extends AppCompatActivity {

    TextView createAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        createAccount = findViewById(R.id.t_createAccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthorizationActivity.this, RegistrationActivity.class);
                AuthorizationActivity.this.startActivity(intent);
            }
        });
    }
}