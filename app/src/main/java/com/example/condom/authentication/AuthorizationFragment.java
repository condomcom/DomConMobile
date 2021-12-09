package com.example.condom.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.condom.HomeFragment;
import com.example.condom.NewHomeActivityActivity;
import com.example.condom.R;
import com.example.condom.navigation.MainActivity;

public class AuthorizationFragment extends Fragment {

    private EditText login;
    private EditText password;
    private Button enter;
    private TextView createAccount;

    public static AuthorizationFragment newInstance() {

        Bundle args = new Bundle();

        AuthorizationFragment fragment = new AuthorizationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private View.OnClickListener mEnterOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //if(isEmailValid() && isPasswordValid()){
                //Intent intent = new Intent(getActivity(), NewHomeActivityActivity.class);
                /*intent.putExtra(MainActivity.EMAIL_KEY,
                        new User(login.getText().toString(), password.getText().toString()));*/
                //AuthorizationFragment.this.startActivity(intent);
            //}
            /*else{
                showMessage(R.string.login_input_error);
            }*/
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment.newInstance())
                    .addToBackStack(HomeFragment.class.getName())
                    .commit();
        }
    };

    private View.OnClickListener mRegistrationOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, RegistrationFragment.newInstance())
                    .addToBackStack(RegistrationFragment.class.getName())
                    .commit();
        }
    };

    private boolean isEmailValid(){
        return !TextUtils.isEmpty(login.getText())
                && Patterns.EMAIL_ADDRESS.matcher(login.getText()).matches();
    }

    private boolean isPasswordValid(){
        return !TextUtils.isEmpty(password.getText());
    }

    private void showMessage(int string){
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_authorization, container, false);

        createAccount = v.findViewById(R.id.t_createAccount);
        login = v.findViewById(R.id.e_login);
        password = v.findViewById(R.id.e_password);
        enter = v.findViewById(R.id.b_enter);

        enter.setOnClickListener(mEnterOnClickListener);

        createAccount.setOnClickListener(mRegistrationOnClickListener);
        return v;
    }
}
