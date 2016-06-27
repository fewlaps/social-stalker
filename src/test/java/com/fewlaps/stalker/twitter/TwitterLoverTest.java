package com.fewlaps.stalker.twitter;

import com.fewlaps.stalker.domain.twitter.Tweet;
import com.fewlaps.stalker.domain.twitter.TwitterApiClient;
import com.fewlaps.stalker.domain.twitter.TwitterLover;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TwitterLoverTest {

    @Mock
    TwitterApiClient client;

    TwitterLover twitterLover;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        twitterLover = new TwitterLover(client);
    }

    @Test
    public void loveTweetsOfHashtag() throws Exception {
        String keyword = "test";
        when(client.getTweets(keyword)).thenReturn(threeTweets());

        twitterLover.loveTweets(keyword);

        verify(client, times(3)).favorite(any());
    }

    private List<Tweet> threeTweets() {
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(new Tweet(0, "roc", "hello", new Date()));
        tweets.add(new Tweet(1, "esteve", "hello", new Date()));
        tweets.add(new Tweet(2, "yeradis", "hello", new Date()));
        return tweets;
    }
}