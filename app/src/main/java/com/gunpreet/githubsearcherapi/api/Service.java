package com.gunpreet.githubsearcherapi.api;

import com.gunpreet.githubsearcherapi.models.ProfileResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Service {

    @GET
    Call<ProfileResponse> getItems(
            @Url String url
    );
}
