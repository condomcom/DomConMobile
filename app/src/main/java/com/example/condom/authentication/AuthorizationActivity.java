package com.example.condom.authentication;

import androidx.fragment.app.Fragment;

public class AuthorizationActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return AuthorizationFragment.newInstance();
    }
}
