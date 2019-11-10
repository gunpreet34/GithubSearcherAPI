package com.gunpreet.githubsearcherapi.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static Retrofit retrofit;
    public static final String BASE_URL = "https://github-trending-api.now.sh";

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}


