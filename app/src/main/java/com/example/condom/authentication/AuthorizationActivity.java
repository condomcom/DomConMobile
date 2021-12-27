package com.example.condom.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.condom.ui.LoginTabFragment;

public class AuthorizationActivity extends SingleFragmentActivity {
    @Override
    protected Fragment getFragment() {
        return LoginTabFragment.newInstance();
    }
}
