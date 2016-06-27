package com.fewlaps.stalker.cli;

import com.fewlaps.stalker.domain.twitter.TwitterRequest;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;

public class CliParamsParserTest {

    CliParamsParser parser;

    @Test(expected = InvalidParameterException.class)
    public void shouldLaunchException_whenInputIsNull() {
        String[] input = null;
        parser = new CliParamsParser(input);

        parser.getRequests();
    }

    @Test
    public void shouldReturnGoodTwitterRequest_whenGettingGoodTwitterRequestValues() {
        String[] input = {"-t", "roc", "apiKey", "apiKeySecret", "clientToken", "clientTokenSecret"};
        parser = new CliParamsParser(input);

        TwitterRequest twitterRequest = (TwitterRequest) parser.getRequests().get(0);

        assertThat(twitterRequest.getKeyword()).isEqualTo("roc");
        assertThat(twitterRequest.getApiKey()).isEqualTo("apiKey");
        assertThat(twitterRequest.getApiKeySecret()).isEqualTo("apiKeySecret");
        assertThat(twitterRequest.getClientToken()).isEqualTo("clientToken");
        assertThat(twitterRequest.getClientTokenSecret()).isEqualTo("clientTokenSecret");
    }

    @Test
    public void hasMoreItems_returnsTrue_whenInputHasTwoElements_andIndexIsZero() {
        String[] input = {"1", "2"};
        parser = new CliParamsParser(input);

        boolean result = parser.hasMoreItems();

        assertThat(result).isTrue();
    }

    @Test
    public void hasMoreItems_returnsTrue_whenInputHasTwoElements_andIndexIsOne() {
        String[] input = {"1", "2"};
        parser = new CliParamsParser(input);
        parser.getNextItem();

        boolean result = parser.hasMoreItems();

        assertThat(result).isTrue();
    }

    @Test
    public void hasMoreItems_returnsFalse_whenInputHasTwoElements_andIndexIsTwo() {
        String[] input = {"1", "2"};
        parser = new CliParamsParser(input);
        parser.getNextItem();
        parser.getNextItem();

        boolean result = parser.hasMoreItems();

        assertThat(result).isFalse();
    }
}