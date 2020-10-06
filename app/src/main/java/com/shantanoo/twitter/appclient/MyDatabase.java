package com.shantanoo.twitter.appclient;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.shantanoo.twitter.appclient.models.SampleModel;
import com.shantanoo.twitter.appclient.models.SampleModelDao;

@Database(entities={SampleModel.class}, version=1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract SampleModelDao sampleModelDao();

    // Database name to be used
    public static final String NAME = "MyDataBase";
}
