package dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.shantanoo.twitter.appclient.models.SampleModel;
import com.shantanoo.twitter.appclient.models.Tweet;
import com.shantanoo.twitter.appclient.models.TweetWithUser;
import com.shantanoo.twitter.appclient.models.User;

import java.util.List;

/**
 * Created by Shantanoo on 10/11/2020.
 */
@Dao
public interface TweetDao {
    @Query("SELECT Tweet.text as tweet_text, Tweet.createdAt as tweet_createdAt, Tweet.id as tweet_id, User.* " +
            "FROM Tweet INNER JOIN USER ON Tweet.userId = User.id ORDER BY createdAt DESC LIMIT 5")
    List<TweetWithUser> recentItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(Tweet... tweets);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(User... users);
}
