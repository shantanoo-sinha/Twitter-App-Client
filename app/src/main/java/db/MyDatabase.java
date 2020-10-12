package db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.shantanoo.twitter.appclient.models.SampleModel;
import com.shantanoo.twitter.appclient.models.Tweet;
import com.shantanoo.twitter.appclient.models.User;

@Database(entities={SampleModel.class, Tweet.class, User.class}, version=2)
public abstract class MyDatabase extends RoomDatabase {
    public abstract SampleModelDao sampleModelDao();
    public abstract TweetDao tweetDao();

    // Database name to be used
    public static final String NAME = "MyDataBase";
}
