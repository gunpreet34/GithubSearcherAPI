package com.gunpreet.githubsearcherapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Profile implements Serializable {

    @SerializedName("username")
    @Expose
    private String mUsername;

    @SerializedName("avatar")
    @Expose
    private String mAvatarUrl;

    @SerializedName("repo")
    @Expose
    private Repo mRepo;

    @SerializedName("url")
    @Expose
    private String mUrl;

    public Profile(String username, String avatarUrl, Repo repo, String url) {
        mUsername = username;
        mAvatarUrl = avatarUrl;
        mRepo = repo;
        mUrl = url;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public Repo getRepo() {
        return mRepo;
    }

    public void setRepo(Repo repo) {
        mRepo = repo;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
