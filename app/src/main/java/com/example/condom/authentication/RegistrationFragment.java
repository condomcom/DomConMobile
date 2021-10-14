package com.example.condom.authentication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.example.condom.R;
import com.example.condom.modelIP.User;

import java.util.Calendar;

import okhttp3.MediaType;

public class RegistrationFragment extends Fragment {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private EditText mEmail;
    private EditText mPassword;
    private Button mRegistration;
    private EditText mDateBirth;
    private EditText mPhone;
    private EditText mName;
    private EditText mSurname;
    private EditText mPatronymic;
    private SharedPreferencesHelper mSharedPreferencesHelper;
    TextView testText;

    public static androidx.fragment.app.Fragment newInstance() {
        return new RegistrationFragment();
    }

    private View.OnClickListener mOnRegistrationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isInputValid()) {
                User user = new User(

                        mName.getText().toString(),
                        mSurname.getText().toString(),
                        mPatronymic.getText().toString(),
                        mEmail.getText().toString(),
                        mPhone.getText().toString(),
                        mDateBirth.getText().toString()
                );

                /*ApiUtils.getApiService().users(user).enqueue(new Callback<User>() {
                    Handler handler = new Handler(getActivity().getMainLooper());
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            final String myResponse = response.body().toString();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    testText.setText(myResponse);
                                    showMessage(R.string.registration_success);
                                    getFragmentManager().popBackStack();
                                    Log.v("failure", String.valueOf(response.body()));
                                }
                            });
                        } else {
                            showMessage(R.string.registration_error);
                            Log.v("response code", String.valueOf(response.code()));
                        }
                    }

                        *//*handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (response.isSuccessful()) {
                                    //todo добавить обработку ошибок
                                    testText.setText(myResponse);
                                    showMessage(R.string.registration_success);
                                    getFragmentManager().popBackStack();
                                    //Log.v("failure", String.valueOf(response.body().contentLength()));
                                } else {
                                    showMessage(R.string.registration_error);
                                    Log.v("response code", String.valueOf(response.code()));
                                }
                            }
                        });*//*


                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                t.printStackTrace();
                                showMessage(R.string.request_error);
                                Log.v("failure", String.valueOf(t));
                            }
                        });
                    }
                });*/
            }
            else showMessage(R.string.login_input_error);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        //mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());

        mEmail = view.findViewById(R.id.e_loginReg);
        mPassword = view.findViewById(R.id.e_passwordReg);
        mRegistration = view.findViewById(R.id.b_register);
        mDateBirth = view.findViewById(R.id.e_dateBirth);
        mName = view.findViewById(R.id.e_name);
        mSurname = view.findViewById(R.id.e_surname);
        mPatronymic = view.findViewById(R.id.e_patronymic);
        mPhone = view.findViewById(R.id.e_phone);
        testText = view.findViewById(R.id.test);

        mDateBirth.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "ддммгггг";
            private Calendar calendar = Calendar.getInstance();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]",
                            "");
                    String cleanC = current.replaceAll("[^\\d.]",
                            "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }

                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{

                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        calendar.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900:(year>2100)?2100:year;
                        calendar.set(Calendar.YEAR, year);

                        day = (day > calendar.getActualMaximum(Calendar.DATE))? calendar.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    mDateBirth.setText(current);
                    mDateBirth.setSelection(sel < current.length() ? sel : current.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });

        mRegistration.setOnClickListener(mOnRegistrationClickListener);

        return view;
    }

    private boolean isInputValid() {
        String email = mEmail.getText().toString();
        if (isEmailValid(email)) {
            return true;
        }

        return false;
    }

    private boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /*private boolean isPasswordsValid() {
        String password = mPassword.getText().toString();

        return !TextUtils.isEmpty(password);
    }*/

    private void showMessage(@StringRes int string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }

}

