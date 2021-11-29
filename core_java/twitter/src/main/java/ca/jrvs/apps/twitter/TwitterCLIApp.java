package ca.jrvs.apps.twitter;

import org.springframework.beans.factory.annotation.Autowired;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.service.TwitterService;

public class TwitterCLIApp {
    private Controller controller;

    @Autowired
    public TwitterCLIApp(Controller controller) { this.controller = controller; }

    public static void main(String[] args) {
        String consumerKey = System.getProperty("consumerKey");
        String consumerSecret = System.getProperty("consumerSecret");
        String accessToken = System.getProperty("accessToken");
        String tokenSecret = System.getProperty("tokenSecret");

        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);

        TwitterCLIApp app = new TwitterCLIApp(
            new TwitterController(
                new TwitterService(
                    new TwitterDao(httpHelper)
                )
            )
        );

        app.run(args);
    }

    public void run(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                "USAGE: twitterApp post|show|delete [options]"
            );
        }

        String input = args[0].toLowerCase();

        switch(input) {
            case "post":
                controller.postTweet(args);
                break;
            case "show":
                controller.showTweet(args);
                break;
            case "delete":
                controller.deleteTweet(args);
                break;
        }
    }
}
