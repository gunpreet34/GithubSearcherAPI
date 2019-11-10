package com.gunpreet.githubsearcherapi.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gunpreet.githubsearcherapi.R;
import com.gunpreet.githubsearcherapi.adapters.ProfileAdapter;
import com.gunpreet.githubsearcherapi.api.Client;
import com.gunpreet.githubsearcherapi.api.Service;
import com.gunpreet.githubsearcherapi.models.Profile;
import com.gunpreet.githubsearcherapi.utils.Constants;
import com.gunpreet.githubsearcherapi.utils.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilesActivity extends AppCompatActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mProfilesRV;
    private TextView mNoInternetTV;
    private String mLanguage;
    private String mSince;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);
        init();
    }

    private void init() {

        Intent i = getIntent();
        mLanguage = i.getStringExtra(Constants.LANGUAGE_INTENT);
        mSince = i.getStringExtra(Constants.SINCE_INTENT);

        if (getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout = findViewById(R.id.profiles_swipe_refresh);

        mProfilesRV = findViewById(R.id.profiles_rv);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mProfilesRV.setLayoutManager(mLinearLayoutManager);
        mProfilesRV.smoothScrollToPosition(0);

        mNoInternetTV = findViewById(R.id.no_internet_text);

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                Toast.makeText(getApplicationContext(), R.string.loading_profiles, Toast.LENGTH_SHORT).show();
                loadProfiles();
            }
        });

        loadProfiles();

    }

    private void loadProfiles() {
        if (!Util.isNetworkConnected(getApplicationContext())){
            setConnected(false);
            mSwipeRefreshLayout.setRefreshing(false);
            return;
        }
        setConnected(true);
        Toast.makeText(getApplicationContext(), R.string.loading_profiles, Toast.LENGTH_SHORT).show();
        try{
            Service mApiService = Client.getClient().create(Service.class);
            Call<List<Profile>> mProfileResponseCall = mApiService.getProfiles(createUrl(mLanguage, mSince));
            mProfileResponseCall.enqueue(new Callback<List<Profile>>() {
                @Override
                public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                    Toast.makeText(getApplicationContext(), R.string.profiles_updated, Toast.LENGTH_SHORT).show();
                    mSwipeRefreshLayout.setRefreshing(false);
                    setProfilesRV(response.body());
                }

                @Override
                public void onFailure(Call<List<Profile>> call, Throwable t) {
                    Log.e("ConnectionError", t.toString());
                    Toast.makeText(getApplicationContext(), R.string.loading_error, Toast.LENGTH_SHORT).show();
                    setConnected(false);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });

        }catch (Exception e){
            Log.e("ConnectionError", e.toString());
            Toast.makeText(getApplicationContext(), R.string.loading_error, Toast.LENGTH_SHORT).show();
            mSwipeRefreshLayout.setRefreshing(false);
        }

    }

    private String createUrl(String language, String since) {
        return "developers?language=" + language + "&since=" + since;
    }

    private void setProfilesRV(List<Profile> profiles) {
        mProfilesRV.setAdapter(new ProfileAdapter(profiles,ProfilesActivity.this));
        mProfilesRV.smoothScrollToPosition(0);

    }

    private void setConnected(Boolean connected){
        if(connected){
            mNoInternetTV.setVisibility(View.GONE);
            mProfilesRV.setVisibility(View.VISIBLE);
        }else{
            mNoInternetTV.setVisibility(View.VISIBLE);
            mProfilesRV.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
