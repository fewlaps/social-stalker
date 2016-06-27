package com.fewlaps.stalker.domain.twitter;

import java.util.stream.Stream;

public class TwitterLover {

    private final TwitterApiClient client;

    public TwitterLover(TwitterApiClient client) {
        this.client = client;
    }

    public void loveTweets(String keyword) {
        Stream<Tweet> tweets = client.getTweets(keyword).stream();
        tweets.forEach(tweet -> client.favorite(tweet));
    }
}
