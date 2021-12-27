package com.example.condom.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.condom.R;
import com.example.condom.authentication.AuthorizationFragment;
import com.example.condom.authentication.SingleFragmentActivity;
import com.example.condom.ui.adapters.LoginAdapter;
import com.google.android.material.tabs.TabLayout;

public class NewAuthorizationActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    float v = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_authorization);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Авторизация"));
        tabLayout.addTab(tabLayout.newTab().setText("Регистрация"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LoginAdapter loginAdapter = new LoginAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(loginAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTranslationY(300);
        tabLayout.setAlpha(v);
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();
    }
}