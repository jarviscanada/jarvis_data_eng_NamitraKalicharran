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

import io.github.cdimascio.dotenv.Dotenv;

public class twitterApiTest {

    // From package to read .env files
    private static Dotenv dotenv = Dotenv.configure()
                                         .directory("core_java\\twitter\\.env")
                                         .load();

    private static String CONSUMER_KEY = dotenv.get("consumerKey");
    private static String CONSUMER_SECRET = dotenv.get("consumerSecret");
    private static String ACCESS_TOKEN = dotenv.get("accessToken");
    private static String TOKEN_SECRET = dotenv.get("tokenSecret");

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
