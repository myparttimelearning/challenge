package com.rumodigi.fanduelmobilechallenge.data.entity;

import com.google.gson.annotations.SerializedName;

public class PlayerEntity {
    @SerializedName("id")
    private String id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("fppg")
    private double fppg;
    @SerializedName("images")
    private Images images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getFppg() {
        return fppg;
    }

    public void setFppg(double fppg) {
        this.fppg = fppg;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getImageUrl(){
        return (getImages() != null) ? getImages().getImagesDefault().getUrl() : "";
    }

    private class Images{

        @SerializedName("default")
        private Default imagesDefault;

        private Default getImagesDefault() {
            return imagesDefault;
        }
    }

    private class Default{

        @SerializedName("url")
        private String url;

        private String getUrl() {
            return url;
        }
    }

    private Images getImages() {
        return images;
    }

}
