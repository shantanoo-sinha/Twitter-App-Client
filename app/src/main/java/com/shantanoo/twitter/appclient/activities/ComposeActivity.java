package com.shantanoo.twitter.appclient.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.shantanoo.twitter.appclient.R;
import com.shantanoo.twitter.appclient.TwitterRestApplication;
import com.shantanoo.twitter.appclient.TwitterRestClient;
import com.shantanoo.twitter.appclient.models.Tweet;

import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class ComposeActivity extends AppCompatActivity {

    private static final String TAG = "ComposeActivity";

    private static final int COMPOSE_TWEET_SIZE = 140;

    private EditText etCompose;
    private Button btnTweet;

    private TwitterRestClient twitterClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Display icon in the toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.vector_twitter_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        twitterClient = TwitterRestApplication.getRestClient(this);

        etCompose = findViewById(R.id.etCompose);
        btnTweet = findViewById(R.id.btnTweet);

        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String tweetContent = etCompose.getText().toString();
                if(tweetContent.isEmpty()) {
                    Toast.makeText(ComposeActivity.this, "Tweet content cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(tweetContent.length() > 140) {
                    Toast.makeText(ComposeActivity.this, "Tweet content can not be more than 140 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                twitterClient.publishTweet(tweetContent, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        try {
                            Tweet tweet = Tweet.fromJson(json.jsonObject);
                            Log.d(TAG, "Published Tweet: " + tweet.getText());
                            Intent intent = new Intent();
                            intent.putExtra(getString(R.string.tweet), Parcels.wrap(tweet));
                            setResult(RESULT_OK, intent);
                            finish();
                        } catch (JSONException e) {
                            Log.e(TAG, "Publish Tweet: Exception", e);
                        }
                        Log.d(TAG, "onSuccess: Tweet Published");
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                        Log.e(TAG, "onFailure: Publish Tweet");
                    }
                });
            }
        });
    }
}