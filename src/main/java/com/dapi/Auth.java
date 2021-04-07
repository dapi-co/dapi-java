package com.dapi;

import com.dapi.response.ExchangeTokenResponse;

import java.io.IOException;


public class Auth {
    private final Config config;

    public Auth(Config config) {
        this.config = config;
    }

    public ExchangeTokenResponse exchangeToken(String accessCode, String connectionID) throws IOException {

        // Create the request body of this call
        var body = new ExchangeTokenRequest(this.config.getAppSecret(), accessCode, connectionID);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(body, ExchangeTokenRequest.class);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + body.action);

        // Convert the got response to the wanted response type
        var resp = DapiRequest.jsonAgent.fromJson(respJson, ExchangeTokenResponse.class);

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || resp.getStatus() == null) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new ExchangeTokenResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    private static class ExchangeTokenRequest {
        private final String action = "/auth/ExchangeToken";
        private final String appSecret;
        private final String accessCode;
        private final String connectionID;

        public ExchangeTokenRequest(String appSecret, String accessCode, String connectionID) {
            this.appSecret = appSecret;
            this.accessCode = accessCode;
            this.connectionID = connectionID;
        }
    }
}
