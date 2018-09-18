package com.rumodigi.fanduelmobilechallenge.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PlayerModel implements Parcelable {
    private String id;
    private String name;
    private double fppg;
    private String imageUrl;

    public PlayerModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getFppg() {
        return fppg;
    }

    public void setFppg(double fppg) {
        this.fppg = fppg;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.imageUrl);
        dest.writeDouble(this.fppg);
    }

    // Parcelling part
    public PlayerModel(Parcel in){
        this.id = in.readString();
        this.name = in.readString();
        this.imageUrl =  in.readString();
        this.fppg = in.readDouble();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public PlayerModel createFromParcel(Parcel in) {
            return new PlayerModel(in);
        }

        public PlayerModel[] newArray(int size) {
            return new PlayerModel[size];
        }
    };
}
