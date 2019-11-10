package com.gunpreet.githubsearcherapi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gunpreet.githubsearcherapi.R;
import com.gunpreet.githubsearcherapi.models.Profile;
import com.gunpreet.githubsearcherapi.models.Repo;
import com.gunpreet.githubsearcherapi.utils.Constants;
import com.squareup.picasso.Picasso;

public class RepoActivity extends AppCompatActivity {

    private TextView mRepoName;
    private TextView mRepoDesc;
    private TextView mRepoLink;
    private ImageView mAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        init();

    }

    private void init() {
        if (getSupportActionBar()!=null)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent i = getIntent();
        Bundle mBundle = i.getExtras();
        final Profile mProfile = (Profile) mBundle.getSerializable(Constants.REPO_INTENT);


        mRepoName = findViewById(R.id.repo_name);
        mRepoDesc = findViewById(R.id.repo_desc);
        mRepoLink = findViewById(R.id.repo_link);
        mAvatar = findViewById(R.id.repo_avatar);

        if (mProfile!=null) {
            mRepoName.setText(mProfile.getRepo().getName());
            mRepoDesc.setText(mProfile.getRepo().getDescription());
            mRepoLink.setText(mProfile.getRepo().getUrl());
            Picasso.with(getApplicationContext())
                    .load(mProfile.getAvatarUrl())
                    .placeholder(R.drawable.load)
                    .into(mAvatar);
        }else{
            Toast.makeText(getApplicationContext(),R.string.loading_error, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


}
