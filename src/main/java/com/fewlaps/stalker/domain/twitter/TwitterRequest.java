package com.fewlaps.stalker.domain.twitter;

import com.fewlaps.stalker.domain.StalkerRequest;

public class TwitterRequest extends StalkerRequest {

    private final String keyword;
    private final String apiKey;
    private final String apiKeySecret;
    private final String clientToken;
    private final String clientTokenSecret;

    public TwitterRequest(String keyword, String apiKey, String apiKeySecret, String clientToken, String clientTokenSecret) {
        this.keyword = keyword;
        this.apiKey = apiKey;
        this.apiKeySecret = apiKeySecret;
        this.clientToken = clientToken;
        this.clientTokenSecret = clientTokenSecret;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiKeySecret() {
        return apiKeySecret;
    }

    public String getClientToken() {
        return clientToken;
    }

    public String getClientTokenSecret() {
        return clientTokenSecret;
    }
}
