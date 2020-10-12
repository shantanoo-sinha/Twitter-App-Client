package com.shantanoo.twitter.appclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.shantanoo.twitter.appclient.R;
import com.shantanoo.twitter.appclient.models.Tweet;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    private ImageView ivDetailProfileImage;
    private TextView tvDetailScreenName;
    private TextView tvDetailUserName;
    private TextView tvDetailRelativeTime;
    private TextView tvDetailTweetText;
    private ImageView ivDetailImagePreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Display icon in the toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.vector_twitter_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        ivDetailProfileImage = findViewById(R.id.ivDetailProfileImage);
        tvDetailScreenName = findViewById(R.id.tvDetailScreenName);
        tvDetailUserName = findViewById(R.id.tvDetailUserName);
        tvDetailRelativeTime = findViewById(R.id.tvDetailRelativeTimestamp);
        tvDetailTweetText = findViewById(R.id.tvDetailText);
        ivDetailImagePreview = findViewById(R.id.ivDetailImagePreview);

        Tweet tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(getApplicationContext().getString(R.string.tweet)));
        tvDetailUserName.setText(tweet.getUser().getName());
        tvDetailScreenName.setText(getApplicationContext().getString(R.string.at) + tweet.getUser().getScreenName());
        tvDetailTweetText.setText(tweet.getText());
        tvDetailRelativeTime.setText(tweet.getFormattedTimestamp() + " ago");

        int radius = 30; // corner radius, higher value = more rounded
        int margin = 10; // crop margin, set to 0 for corners with no crop

        Glide.with(getApplicationContext())
                .load(tweet.getUser().getProfileImageUrl())
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error)
                .fallback(R.drawable.image_error)
                .transform(new RoundedCornersTransformation(radius, margin))
                .into(ivDetailProfileImage);

        if (tweet.getMediaUrl() != null) {
            ivDetailImagePreview.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load(tweet.getMediaUrl()).into(ivDetailImagePreview);
        } else {
            ivDetailImagePreview.setVisibility(View.GONE);
        }
    }
}