package com.gunpreet.githubsearcherapi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gunpreet.githubsearcherapi.R;
import com.gunpreet.githubsearcherapi.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private EditText mLanguageInput;
    private EditText mSinceInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        mLanguageInput = findViewById(R.id.language_input);
        mSinceInput = findViewById(R.id.since_input);

        Button mGetProfilesButton = findViewById(R.id.get_profiles_button);

        mGetProfilesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String language = mLanguageInput.getText().toString();
                String since = mSinceInput.getText().toString();
                Intent i = new Intent(MainActivity.this, ProfilesActivity.class);
                i.putExtra(Constants.LANGUAGE_INTENT, language);
                i.putExtra(Constants.SINCE_INTENT, since);
                startActivity(i);
            }
        });
    }
}
