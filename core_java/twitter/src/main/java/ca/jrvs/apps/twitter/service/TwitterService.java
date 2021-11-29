package ca.jrvs.apps.twitter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;

public class TwitterService implements Service {
    private CrdDao<Tweet, String> dao;
    private static final int TWEET_LIMIT = 280;
    private static final HashSet<String> validFields = new HashSet<String>(Arrays.asList(
        "created_at",
        "id",
        "id_str",
        "text",
        "entities",
        "coordinates",
        "retweet_count",
        "favorite_count",
        "favorited",
        "retweeted"
    ));

    public void validatePostTweet(Tweet tweet) {
        float[] coordinates = tweet.getCoordinates().getCoordinates();
        
        if (tweet.getText().length() >= TWEET_LIMIT) {
            throw new IllegalArgumentException("Tweet must be 280 characters or less.");
        } else if (coordinates[0] > Long.MAX_VALUE || coordinates[0] < Long.MIN_VALUE ||
                   coordinates[1] > Long.MAX_VALUE || coordinates[1] < Long.MIN_VALUE) {
            throw new IllegalArgumentException("Coordinates cannot be proccessed:");         
        }
    }

    public void validateShowTweet(String id, String[] fields) {
        validateId(id);

        for (String field : fields) {
            if (!validFields.contains(field)) {
                throw new IllegalArgumentException("Invalid field: " + field);
            }
        }
    }

    @Autowired
    public TwitterService(CrdDao<Tweet, String> dao) { this.dao = dao; }

    @Override
    public Tweet postTweet(Tweet tweet) {
        validatePostTweet(tweet);
        return (Tweet) dao.create(tweet);
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {
        validateShowTweet(id, fields);
        return dao.findById(id);
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        ArrayList<Tweet> deleted = new ArrayList<Tweet>();
        for (String id : ids) {
            validateId(id);
            deleted.add(dao.deleteById(id));
        }
        return deleted;
    }

    public void validateId(String id) {
        if (!id.matches("[0-9]+")) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
    }    
}
