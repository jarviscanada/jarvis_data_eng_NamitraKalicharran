package ca.jrvs.apps.twitter.dao;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.BasicConfigurator;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

public class TwitterHttpHelper implements HttpHelper {
    final static Logger logger = LoggerFactory.getLogger(TwitterHttpHelper.class);
    /**
     * Dependencies are specified as private member variables
     */
    private OAuthConsumer consumer;
    private HttpClient httpClient;

    /**
     * Constructor
     * Setup dependencies using secrets
     * 
     * @param consumerKey
     * @param consumerSecret
     * @param accessToken
     * @param tokenSecret
     */
    public TwitterHttpHelper(String consumerKey, String consumerSecret,
        String accessToken, String tokenSecret) {
        
        BasicConfigurator.configure();
        consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(accessToken, tokenSecret);

        /**
         * Default = single connection.
         */
        httpClient = HttpClientBuilder.create().build();
    }

    @Override
    public HttpResponse httpPost(URI uri) {
        HttpPost request = new HttpPost(uri);

        try {
            consumer.sign(request);
            return httpClient.execute(request);

        } catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException | IOException e) {
            logger.error("POST request failed:", e);
        }
        return null;
    }

    @Override
    public HttpResponse httpGet(URI uri) {
        HttpGet request = new HttpGet(uri);

        try {
            consumer.sign(request);
            return httpClient.execute(request);

        } catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException | IOException e) {
            logger.error("GET request failed:", e);
        }
        return null;
    }
}
