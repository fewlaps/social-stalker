package com.fewlaps.stalker.domain.twitter;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

public class TwitterApiClient {

    public static final int TWEET_ALREADY_FAVORITE = 139;
    private final String apiKey;
    private final String apiSecret;
    private final String accessToken;
    private final String accessTokenSecret;

    public TwitterApiClient(String apiKey, String apiSecret, String accessToken, String accessTokenSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
    }

    public List<Tweet> getTweets(String keyword) {
        List<Tweet> tweets = new ArrayList<>();
        try {
            Twitter twitter = getTwitterInstance();
            Query query = new Query(keyword);
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                tweets.add(new Tweet(status.getId(), status.getUser().getScreenName(), status.getText(), status.getCreatedAt()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Something bad happened: " + e.getMessage());
        }
        return tweets;
    }

    public void favorite(Tweet tweet) {
        Twitter twitter = getTwitterInstance();
        try {
            twitter.createFavorite(tweet.getId());
        } catch (TwitterException e) {
            if (e.getErrorCode() != TWEET_ALREADY_FAVORITE) {
                throw new RuntimeException("Something bad happened");
            }
        }
    }

    private Twitter getTwitterInstance() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(apiKey)
                .setOAuthConsumerSecret(apiSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        return new TwitterFactory(cb.build()).getInstance();
    }
}
