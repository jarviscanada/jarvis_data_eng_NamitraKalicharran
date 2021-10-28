package ca.jrvs.apps.twitter;

import java.util.Arrays;

import com.google.gdata.util.common.base.PercentEscaper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

public class twitterApiTest {
    private static String CONSUMER_KEY = System.getenv("consumerKey");
    private static String CONSUMER_SECRET = System.getenv("consumerSecret");
    private static String ACCESS_TOKEN = System.getenv("accessToken");
    private static String TOKEN_SECRET = System.getenv("tokenSecret");

    public static void main(String[] args) throws Exception {
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY,
            CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        String status = "Hello World, from Java!";
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        HttpPost request = new HttpPost(
            "https://api.twitter.com/1.1/statuses/update.json?status=" + percentEscaper.escape(status)
        );

        // sign the request (add headers)
        consumer.sign(request);

        System.out.println("Http Request Headers:");
        Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

        // send the Request
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
