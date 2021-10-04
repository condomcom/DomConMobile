package com.example.condom.authentication;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.example.condom.R;

public class RegistrationFragment extends Fragment {

    private EditText mLogin;
    private EditText mPassword;
    private Button mRegistration;
    private SharedPreferencesHelper mSharedPreferencesHelper;

    public static androidx.fragment.app.Fragment newInstance() {
        return new RegistrationFragment();
    }

    private View.OnClickListener mOnRegistrationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isInputValid()) {
                boolean isAdded = mSharedPreferencesHelper.addUser(new User(
                        mLogin.getText().toString(),
                        mPassword.getText().toString()
                ));

                if (isAdded) {
                    showMessage(R.string.logon_registered);
                    getFragmentManager().popBackStack();
                } else {
                    showMessage(R.string.login_input_error);
                }
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());

        mLogin = view.findViewById(R.id.e_loginReg);
        mPassword = view.findViewById(R.id.e_passwordReg);
        mRegistration = view.findViewById(R.id.b_register);

        mRegistration.setOnClickListener(mOnRegistrationClickListener);

        return view;
    }

    private boolean isInputValid() {
        String email = mLogin.getText().toString();
        if (isEmailValid(email) && isPasswordsValid()) {
            return true;
        }

        return false;
    }

    private boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordsValid() {
        String password = mPassword.getText().toString();

        return !TextUtils.isEmpty(password);
    }

    private void showMessage(@StringRes int string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }

}

