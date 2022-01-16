package com.example.condom.old_ui.navigation.authentication;

import androidx.fragment.app.Fragment;

import com.example.condom.ui.LoginTabFragment;

public class AuthorizationActivity extends SingleFragmentActivity {
    @Override
    protected Fragment getFragment() {
        return LoginTabFragment.newInstance();
    }
}
