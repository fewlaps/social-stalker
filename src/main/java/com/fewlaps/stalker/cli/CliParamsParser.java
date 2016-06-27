package com.fewlaps.stalker.cli;

import com.fewlaps.stalker.domain.StalkerRequest;
import com.fewlaps.stalker.domain.twitter.TwitterRequest;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class CliParamsParser {

    public static final String TWITTER_REQUEST_STARTER = "-t";

    private final String[] args;

    private int index = 0;

    public CliParamsParser(String[] args) {
        if (args == null) {
            throw new InvalidParameterException();
        }
        this.args = args;
    }

    List<StalkerRequest> getRequests() {
        List<StalkerRequest> requests = new ArrayList<>();
        while (hasMoreItems()) {
            String requestType = getNextItem();
            if (requestType.equals(TWITTER_REQUEST_STARTER)) {
                String keyword = getNextItem();
                String apiKey = getNextItem();
                String apiKeySecret = getNextItem();
                String clientToken = getNextItem();
                String clientTokenSecret = getNextItem();
                TwitterRequest twitterRequest = new TwitterRequest(keyword, apiKey, apiKeySecret, clientToken, clientTokenSecret);
                requests.add(twitterRequest);
            }
        }
        return requests;
    }

    String getNextItem() {
        return args[index++];
    }

    boolean hasMoreItems() {
        if (args.length > index) {
            return true;
        }
        return false;
    }
}
