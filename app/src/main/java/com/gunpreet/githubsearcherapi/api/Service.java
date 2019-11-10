package com.gunpreet.githubsearcherapi.api;

import com.gunpreet.githubsearcherapi.models.Profile;
import com.gunpreet.githubsearcherapi.models.SharedData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Service {
    SharedData data = new SharedData();
    String getLanguage=data.mLanguage;
    String getSince=data.mSince;
    String url2="&since=";
    String url= "developers?language=".concat(getLanguage).concat(url2).concat(getSince);
    String curl=url;
    @GET
    Call<List<Profile>> getProfiles(
            @Url String url
    );
}
