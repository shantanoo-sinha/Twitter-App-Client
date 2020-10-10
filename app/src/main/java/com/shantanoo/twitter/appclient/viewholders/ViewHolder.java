package com.shantanoo.twitter.appclient.viewholders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shantanoo.twitter.appclient.DetailActivity;
import com.shantanoo.twitter.appclient.R;
import com.shantanoo.twitter.appclient.models.Tweet;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

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
    private RelativeLayout rlTweetRowLayout;

    public ViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        this.context = context;

        ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
        tvScreenName = itemView.findViewById(R.id.tvScreenName);
        tvUserName = itemView.findViewById(R.id.tvUserName);
        tvTweetText = itemView.findViewById(R.id.tvTweetText);
        tvRelativeTime = itemView.findViewById(R.id.tvRelativeTime);

        rlTweetRowLayout = itemView.findViewById(R.id.rlTweet);
    }

    public void bind(final Tweet tweet) {
        int radius = 30; // corner radius, higher value = more rounded
        int margin = 10; // crop margin, set to 0 for corners with no crop

        tvUserName.setText(tweet.getUser().getName());
        tvScreenName.setText(context.getString(R.string.at) + tweet.getUser().getScreenName());
        tvTweetText.setText(tweet.getText());
        tvRelativeTime.setText(tweet.getFormattedTimestamp());

        Glide.with(context)
                .load(tweet.getUser().getProfileImageUrl())
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error)
                .fallback(R.drawable.image_error)
                .transform(new RoundedCornersTransformation(radius, margin))
                .into(ivProfileImage);

        rlTweetRowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Onclick", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra(context.getString(R.string.tweet), Parcels.wrap(tweet));

                Pair<View, String> pair1 = Pair.create((View) tvScreenName, context.getString(R.string.screen_name));
                Pair<View, String> pair2 = Pair.create((View) tvUserName, context.getString(R.string.user_name));
                Pair<View, String> pair3 = Pair.create((View) tvTweetText, context.getString(R.string.tweet_message));
                Pair<View, String> pair4 = Pair.create((View) tvRelativeTime, context.getString(R.string.relative_timestamp));
                Pair<View, String> pair5 = Pair.create((View) ivProfileImage, context.getString(R.string.profile_image));

                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, pair1, pair2, pair3, pair4, pair5);
                context.startActivity(i, optionsCompat.toBundle());
            }
        });
    }
}
