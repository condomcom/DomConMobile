package com.example.condom.modelIP;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

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
    public String mImageUrl;
    @SerializedName("startsAt")
    public String mStartsAt;
    @SerializedName("finishesAt")
    public String mFinishedAt;
    @SerializedName("subject")
    public String mSubject;
    @SerializedName("location")
    public String mLocation;
    @SerializedName("type")
    public String mType;

    public Activity(String shortName, String fullName, String description, String conferencesStrId, String ImageUrl, String startsAt, String finishesAt, String subject, String location, String type) {
        mConferenceStrId = conferencesStrId;
        mShortName = shortName;
        mFullName = fullName;
        mDescription = description;
        mImageUrl = ImageUrl;
        mStartsAt = startsAt;
        mFinishedAt = finishesAt;
        mSubject = subject;
        mLocation = location;
        mType = type;
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

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getStartsAt() {
        if (mStartsAt == null)
            return "0:00";
        return mStartsAt.split("T")[1].substring(0,5);
    }

    public String getFinishedAt(){
        if (mFinishedAt == null)
            return "0:00";
        return mFinishedAt.split("T")[1].substring(0,5);
    }

    public String getDate(){
        if (mStartsAt == null)
            return "2022-01-18";
        return mStartsAt.split("T")[0];
    }
}
