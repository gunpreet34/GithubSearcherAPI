package com.gunpreet.githubsearcherapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileResponse {

    @SerializedName("profiles")
    @Expose
    private List<Profile> mProfiles;

    public List<Profile> getItems(){
        return mProfiles;
    }
    public void setItems(List<Profile> profiles){
        this.mProfiles = profiles;
    }
}