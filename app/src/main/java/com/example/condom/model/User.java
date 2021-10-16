package com.example.condom.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("id")
    private int mId;
    @SerializedName("strId")
    private String userId;
    @SerializedName("name")
    private String mName;
    @SerializedName("surname")
    private String mSurname;
    @SerializedName("patronymic")
    private String mPatronymic;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("birthDate")
    private String mBirthDate;
    /*@SerializedName("password")
    private String mPassword;*/

    private boolean mHasSuccessLogin;

    public User(String name, String surname, String patronymic,
                String email, String phone, String birthDate){
        mName = name;
        mSurname = surname;
        mPatronymic = patronymic;
        mEmail = email;
        mPhone = phone;
        mBirthDate = birthDate;
    }

    public int getmId() {
        return mId;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getName() {return mName;}

    public void setName(String name){ mName = name;}

    public String getSurname() {return mSurname;}

    public void setSurname(String surname){ mSurname = surname;}

    public String getPatronymic() {return mPatronymic;}

    public void setPatronymic(String patronymic){ mPatronymic = patronymic;}

    public String getPhone() {return mPhone;}

    public void setPhone(String phone){ mPhone = phone;}

    public String getBirthDate() {return mBirthDate;}

    public void setBirthDate(String birthDate){ mBirthDate = birthDate;}
    /*public String getPassword() {
        return mPassword;
    }*/

    /*public void setPassword(String password) {
        mPassword = password;
    }*/


    public boolean hasSuccessLogin() {
        return mHasSuccessLogin;
    }

    public void setHasSuccessLogin(boolean hasSuccessLogin) {
        mHasSuccessLogin = hasSuccessLogin;
    }
}
