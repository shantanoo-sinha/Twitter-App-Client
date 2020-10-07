package com.shantanoo.twitter.appclient.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shantanoo.twitter.appclient.R;
import com.shantanoo.twitter.appclient.models.Tweet;

/**
 * Created by Shantanoo on 10/5/2020.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private ImageView ivProfileImage;
    private TextView tvScreenName;
    private TextView tvUserName;
    private TextView tvTweetText;
    private TextView tvRelativeTime;
    private boolean isTweetTextClicked = false;

    public ViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        this.context = context;

        ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
        tvScreenName = itemView.findViewById(R.id.tvScreenName);
        tvUserName = itemView.findViewById(R.id.tvUserName);
        tvTweetText = itemView.findViewById(R.id.tvTweetText);
        tvRelativeTime = itemView.findViewById(R.id.tvRelativeTime);

        tvTweetText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTweetTextClicked) {
                    tvTweetText.setMaxLines(2);
                    isTweetTextClicked = false;
                } else {
                    tvTweetText.setMaxLines(Integer.MAX_VALUE);
                    isTweetTextClicked = true;
                }
            }
        });
    }

    public void bind(Tweet tweet) {
        tvUserName.setText(tweet.getUser().getName());
        tvScreenName.setText(context.getString(R.string.at) + tweet.getUser().getScreenName());
        tvTweetText.setText(tweet.getText());
        tvRelativeTime.setText(tweet.getFormattedTimestamp());

        Glide.with(context).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);
    }
}
