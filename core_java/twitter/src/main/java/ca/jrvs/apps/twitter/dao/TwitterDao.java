package ca.jrvs.apps.twitter.dao;

import java.io.IOException;
import java.net.URI;

import com.google.gdata.util.common.base.PercentEscaper;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import oauth.signpost.exception.OAuthException;

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

    private Tweet parseResponse(HttpResponse response, Integer statusCode) {
        Tweet tweet;

        int status = response.getStatusLine().getStatusCode();
        if (status != statusCode) {
            try {
                System.out.println(EntityUtils.toString(response.getEntity()));
            } catch (IOException e) {
                System.out.println("Response has no entity");
            }
            throw new RuntimeException("Unexpected status code: " + status);
        }

        if (response.getEntity() != null) {
            throw new RuntimeException("Empty response body");
        }

        String jsonStr;
        try {
            jsonStr = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert Entity to String", e);
        }

        try {
            tweet = JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON to object", e);
        }

        return tweet;
    }

    public URI makeUri(HttpMethod method, String status) {
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        
        if (method == HttpMethod.POST) {
            return URI.create(
                API_BASE_URI +
                POST_PATH +
                QUERY_SYM +
                percentEscaper.escape("status=") +
                percentEscaper.escape(status)
            );
        } else if (method == HttpMethod.GET) {
            return URI.create(
                API_BASE_URI +
                SHOW_PATH +
                QUERY_SYM +
                percentEscaper.escape("id=") +
                percentEscaper.escape(status)
            );
        } else if (method == HttpMethod.DELETE) {
            return URI.create(
                API_BASE_URI +
                DELETE_PATH +
                "/" + percentEscaper.escape(status) +
                percentEscaper.escape(".json")
            );
        } else {
            throw new IllegalArgumentException("Invalid CRUD method");
        }
    }

    @Override
    public Tweet create(Tweet entity) {
        URI uri;
        uri = makeUri(HttpMethod.POST, entity.getText());

        try {
            HttpResponse response = httpHelper.httpPost(uri);
            return parseResponse(response, 200);
        } catch (OAuthException e) {
            throw new RuntimeException("Unable to fulfill POST request", e);
        }
    }

    @Override
    public Tweet findById(String id) {
        URI uri = makeUri(HttpMethod.GET, id);
        HttpResponse response = httpHelper.httpGet(uri);
        return parseResponse(response, 200);
    }

    @Override
    public Tweet deleteById(String id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
