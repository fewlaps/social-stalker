package com.fewlaps.stalker.twitter;

import com.fewlaps.stalker.domain.twitter.Tweet;
import com.fewlaps.stalker.domain.twitter.TwitterApiClient;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TwitterApiClientTest {

    String FAKE_API_KEY = "Mk4Ee4C4eo7ByxiPkBaEHwNBR";
    String FAKE_API_SECRET = "T3LbZAC1yvnyFqDytZDXSUNCL54oIQ3LC3iBKGyMb5D3UVKVde";
    String FAKE_ACCESS_TOKEN = "747349257775157248-Q4zxZ5LGW6Uy07ZIQTrbF1XMxqXDm3h";
    String FAKE_ACCESS_TOKEN_SECRET = "oMA6JyV8vYL8b4ipETwUPNwToe4LKIQiDmSUPxypuS9Wl";

    TwitterApiClient client;

    @Before
    public void setUp() {
        client = new TwitterApiClient(
                FAKE_API_KEY,
                FAKE_API_SECRET,
                FAKE_ACCESS_TOKEN,
                FAKE_ACCESS_TOKEN_SECRET
        );
    }

    @Test
    public void shouldPrintQuitNowTweets_whenSearchingViaQuitNowApp() throws Exception {
        String keyword = "via @quitnowapp";
        List<Tweet> tweets = client.getTweets(keyword);
        assertThat(tweets).isNotEmpty();
    }

    @Test
    public void shouldNotLaunchException_whenFavoritingLastQuitNowAppTweet() {
        String keyword = "via @quitnowapp";
        List<Tweet> tweets = client.getTweets(keyword);

        client.favorite(tweets.get(0));
    }
}