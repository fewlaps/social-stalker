package com.fewlaps.stalker.cli;

import com.fewlaps.stalker.domain.StalkerRequest;
import com.fewlaps.stalker.domain.twitter.TwitterApiClient;
import com.fewlaps.stalker.domain.twitter.TwitterLover;
import com.fewlaps.stalker.domain.twitter.TwitterRequest;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        CliParamsParser parser = new CliParamsParser(args);
        List<StalkerRequest> requests = parser.getRequests();
        for (StalkerRequest request : requests) {
            if (request instanceof TwitterRequest) {
                TwitterRequest twitterRequest = (TwitterRequest) request;
                TwitterApiClient client = new TwitterApiClient(
                        twitterRequest.getApiKey(),
                        twitterRequest.getApiKeySecret(),
                        twitterRequest.getClientToken(),
                        twitterRequest.getClientTokenSecret());
                TwitterLover twitterLover = new TwitterLover(client);
                twitterLover.loveTweets(twitterRequest.getKeyword());
            }
        }
    }
}
