package com.example.condom.modelIP;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;

public class Activity implements Serializable {
    @SerializedName("conferenceStrId")
    public String mConferenceStrId;
    @SerializedName("shortName")
    public String mShortName;
    @SerializedName("fullName")
    public String mFullName;
    @SerializedName("description")
    public String mDescription;
    @SerializedName("imageUrl")
    public int mImageUrl;

    public Activity(String shortName, String fullName, String description, String conferencesStrId, int mImageUrl) {
        mConferenceStrId = conferencesStrId;
        mShortName = shortName;
        mFullName = fullName;
        mDescription = description;
        mImageUrl = mImageUrl;
    }

    public String getConferenceStrId() {
        return mConferenceStrId;
    }

    public String getShortName() {
        return mShortName;
    }

    public String getFullName() {
        return mFullName;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getImageUrl() {
        return mImageUrl;
    }
}
