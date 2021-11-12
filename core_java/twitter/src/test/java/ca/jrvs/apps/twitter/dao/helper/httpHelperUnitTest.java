package ca.jrvs.apps.twitter.dao.helper;

import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

public class httpHelperTest {
    HttpHelper httpHelper; 

    @Before
    public void setUp() {
        final Dotenv dotenv = Dotenv.configure()
                                         .directory("C:\\Users\\MitPC\\Documents\\Jarvis\\jarvis_data_eng_NamitraKalicharran\\core_java\\twitter")
                                         .load();

        String consumerKey = dotenv.get("consumerKey");
        String consumerSecret = dotenv.get("consumerSecret");
        String accessToken = dotenv.get("accessToken");
        String tokenSecret = dotenv.get("tokenSecret");

        httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    }
    
    @Test
    public void httpPostTest() throws Exception {
        HttpResponse response = httpHelper.httpPost(
            new URI(
                "https://api.twitter.com/1.1/statuses/update.json?status=Hello"
            )
        );

        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
