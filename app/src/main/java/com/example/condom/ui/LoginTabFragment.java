package com.example.condom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.condom.R;
import com.example.condom.databinding.LoginTabFragmentBinding;

public class LoginTabFragment extends Fragment {
    private EditText login, password;
    private Spinner conferences;
    private Button enter;
    private TextView createAccount;
    float v = 0;
    LoginTabFragmentBinding binding;

    public static LoginTabFragment newInstance() {

        Bundle args = new Bundle();

        LoginTabFragment fragment = new LoginTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_tab_fragment, container, false);

        login = view.findViewById(R.id.edit_login);
        password = view.findViewById(R.id.edit_password);
        conferences = view.findViewById(R.id.spinner_conf);
        enter = view.findViewById(R.id.button_enter);
        createAccount = view.findViewById(R.id.text_createAccount);

        login.setTranslationX(800);
        password.setTranslationX(800);
        conferences.setTranslationX(800);
        enter.setTranslationX(800);
        createAccount.setTranslationX(800);

        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        conferences.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        enter.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        createAccount.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();

        enter.setOnClickListener(mEnterOnClickListener);


        return view;
    }

    private View.OnClickListener mEnterOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewHomeActivityActivity.class);
                LoginTabFragment.this.startActivity(intent);

        }
    };
}

