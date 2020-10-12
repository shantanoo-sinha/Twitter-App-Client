package com.shantanoo.twitter.appclient;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.facebook.stetho.Stetho;

import db.MyDatabase;

/*
 * This is the Android application itself and is used to configure various settings
 * including the image cache in memory and on disk. This also adds a singleton
 * for accessing the relevant rest client.
 *
 *     TwitterRestClient client = TwitterRestApplication.getRestClient(Context context);
 *     // use client to send requests to API
 *
 */
public class TwitterRestApplication extends Application {

    MyDatabase myDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        // when upgrading versions, kill the original tables by using
		// fallbackToDestructiveMigration()
        myDatabase = Room.databaseBuilder(this, MyDatabase.class,
                MyDatabase.NAME).fallbackToDestructiveMigration().build();

        // use chrome://inspect to inspect your SQL database
        Stetho.initializeWithDefaults(this);
    }

    public static TwitterRestClient getRestClient(Context context) {
        return (TwitterRestClient) TwitterRestClient.getInstance(TwitterRestClient.class, context);
    }

    public MyDatabase getMyDatabase() {
        return myDatabase;
    }
}