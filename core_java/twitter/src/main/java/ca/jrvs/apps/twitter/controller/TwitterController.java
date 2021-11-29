package ca.jrvs.apps.twitter.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;

public class TwitterController implements Controller {

    private static final String COORD_SEP = ":";
    private static final String COMMA = ",";

    private Service service;

    @Autowired
    public TwitterController(Service service) { this.service = service; }

    @Override
    public Tweet postTweet(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException(
                "USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\" "
            );
        }
        
        String tweet_text = args[0];
        String coord = args[2];
        String[] coordArray = coord.split(COORD_SEP);
        if (coordArray.length != 2 || StringUtils.isEmpty(tweet_text)) {
            throw new IllegalArgumentException(
                "Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\""
            );
        }
        Double lat = null;
        Double lon = null;

        try {
            lat = Double.parseDouble(coordArray[0]);
            lon = Double.parseDouble(coordArray[1]);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                "Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\""
            );
        }

        Tweet postTweet = buildTweet(tweet_text, lon, lat);
        return service.postTweet(postTweet);
    }

    private Tweet buildTweet(String tweet_text, Double lon, Double lat) {
        Tweet tweet = new Tweet();
        Coordinates coord = new Coordinates();
        float[] coordArr = {lon.floatValue(), lat.floatValue()};
        coord.setCoordinates(coordArr);
        coord.setType("Point");

        tweet.setText(tweet_text);
        tweet.setCoordinates(coord);

        return tweet;
    }

    @Override
    public Tweet showTweet(String[] args) {
        if (args.length > 3) {
            throw new IllegalArgumentException("USAGE: TwitterCLIApp show \"tweet_id\" \"field1,field2,...\"");
        }

        String id = args[1];

        if (args.length == 2) {
            return service.showTweet(id, null);
        }
        
        List<String> fieldsList = Arrays.stream(args[2].split(COMMA))
            .map(s -> s.toLowerCase())
            .map(s -> s.replaceAll("\\s+", ""))
            .collect(Collectors.toList());
        String[] fields = fieldsList.toArray(new String[fieldsList.size()]);

        return service.showTweet(id, fields);
    }

    @Override
    public List<Tweet> deleteTweet(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                "USAGE: TwitterCLIApp delete \"tweet_id\""
            );
        }

        String[] ids = args[1].split(",");
        return service.deleteTweets(ids);
    }
    
}
