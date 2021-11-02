package com.example.condom.modelIP;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ActivityUser implements Serializable {
    @SerializedName("userStrId")
    private String mUserStrId;
    @SerializedName("activityStrId")
    private String mActivityStrId;

    public ActivityUser(String mUserStrId, String mActivityStrId) {
        this.mUserStrId = mUserStrId;
        this.mActivityStrId = mActivityStrId;

    }

    public String getUserStrId() {
        return mUserStrId;
    }

    public String getActivityStrId() {
        return mActivityStrId;
    }
}
