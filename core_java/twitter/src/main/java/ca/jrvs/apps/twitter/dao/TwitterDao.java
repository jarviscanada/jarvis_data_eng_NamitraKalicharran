package ca.jrvs.apps.twitter.dao;

import org.springframework.beans.factory.annotation.Autowired;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.model.Tweet;

public class TwitterDao implements CrdDao<Tweet, String> {
    // URI constants
    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy";

    // URI symbols
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";

    // Response code
    private static final int HTTP_OK = 200;

    private HttpHelper httpHelper;

    @Autowired
    public TwitterDao(HttpHelper httpHelper) { this.httpHelper = httpHelper; }

    @Override
    public Tweet create(Tweet entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Tweet findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Tweet deleteById(String id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
