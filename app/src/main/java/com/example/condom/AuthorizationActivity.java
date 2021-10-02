package com.example.condom;

import androidx.fragment.app.Fragment;

public class AuthorizationActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return AuthorizationFragment.newInstance();
    }
}
