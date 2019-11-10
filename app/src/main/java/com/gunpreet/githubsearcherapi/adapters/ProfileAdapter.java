package com.gunpreet.githubsearcherapi.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gunpreet.githubsearcherapi.R;
import com.gunpreet.githubsearcherapi.activities.RepoActivity;
import com.gunpreet.githubsearcherapi.models.Profile;
import com.gunpreet.githubsearcherapi.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    private List<Profile> mProfiles;
    private Context mContext;

    public ProfileAdapter(List<Profile> profiles, Context context) {
        mProfiles = profiles;
        mContext = context;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfileViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_layout, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, final int position) {
        holder.mUserName.setText(mProfiles.get(position).getUsername());
        holder.mGithubLink.setText(mProfiles.get(position).getUrl());
        Picasso.with(mContext)
                .load(mProfiles.get(position).getAvatarUrl())
                .placeholder(R.drawable.load)
                .into(holder.mAvatar);
        holder.mProfileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, RepoActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(Constants.REPO_INTENT,mProfiles.get(position));
                i.putExtras(mBundle);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProfiles.size();
    }

    protected class ProfileViewHolder extends RecyclerView.ViewHolder {

        private TextView mUserName;
        private TextView mGithubLink;
        private ImageView mAvatar;
        private View mProfileView;

        public ProfileViewHolder( View itemView) {
            super(itemView);
            mUserName = itemView.findViewById(R.id.username);
            mGithubLink = itemView.findViewById(R.id.github_link);
            mAvatar = itemView.findViewById(R.id.avatar);
            mProfileView = itemView.findViewById(R.id.profile_view);
        }
    }
}
