package com.example.condom.ui.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.condom.ui.LoginTabFragment;
import com.example.condom.ui.RegistrationTabFragment;

public class LoginAdapter extends FragmentPagerAdapter {

    private Context context;
    private int totalTabs;

    public LoginAdapter(FragmentManager fm, Context context, int totalTabs){
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                LoginTabFragment loginTabFragment = new LoginTabFragment();
                return loginTabFragment;
            case 1:
                RegistrationTabFragment registrationTabFragment = new RegistrationTabFragment();
                return registrationTabFragment;
            default:
                return null;
        }
    }
}
