package com.shantanoo.twitter.appclient.models;

import androidx.room.Embedded;

import com.facebook.stetho.common.ArrayListAccumulator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shantanoo on 10/11/2020.
 */
public class TweetWithUser {
    @Embedded
    public User user;

    @Embedded(prefix = "tweet_")
    public Tweet tweet;

    public static List<Tweet> getTweetList(List<TweetWithUser> tweetWithUsers) {
        List<Tweet> tweets = new ArrayList<>();
        for (int i=0; i<tweetWithUsers.size(); i++) {
            Tweet tweet = tweetWithUsers.get(i).tweet;
            tweet.user = tweetWithUsers.get(i).user;
            tweets.add(tweet);
        }
        return tweets;
    }
}
