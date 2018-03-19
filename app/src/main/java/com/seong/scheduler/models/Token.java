package com.seong.scheduler.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hans on 2018-03-19.
 */

public class Token implements Parcelable {
    @SerializedName("android")
    @Expose
    private String android;

    @SerializedName("aspn")
    @Expose
    private String aspn;

    @SerializedName("web")
    @Expose
    private String web;

    public String getAndroid() {
        return android;
    }

    public void setAndroid(String android) {
        this.android = android;
    }

    public String getAspn() {
        return aspn;
    }

    public void setAspn(String aspn) {
        this.aspn = aspn;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Token(String android){
        this.android = android;
    }

    public Token(Parcel in){
        this.android = in.readString();
        this.aspn = in.readString();
        this.web = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.android);
        parcel.writeString(this.aspn);
        parcel.writeString(this.web);
    }

    public static final Creator<Token> CREATOR = new Creator<Token>() {
        @Override
        public Token createFromParcel(Parcel in) {
            return new Token(in);
        }

        @Override
        public Token[] newArray(int size) {
            return new Token[size];
        }
    };
}
