package com.dapi;

import com.dapi.response.GetAccountsMetadataResponse;
import com.dapi.types.UserInput;

import java.io.IOException;


public class Metadata {
    private final Config config;

    public Metadata(Config config) {
        this.config = config;
    }

    public GetAccountsMetadataResponse getAccountsMetadata(String tokenID, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var bodyObj = new GetAccountsMetadataRequest(this.config.getAppKey(), this.config.getAppSecret(),
                tokenID, userSecret, operationID, userInputs);

        // Convert the body map to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(bodyObj, GetAccountsMetadataRequest.class);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson);

        // Convert the got response to the wanted response type
        var resp = DapiRequest.jsonAgent.fromJson(respJson, GetAccountsMetadataResponse.class);

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || resp.getStatus() == null) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetAccountsMetadataResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    private static class GetAccountsMetadataRequest extends DapiRequest.BaseRequest {
        private final String action = "/metadata/accounts/get";

        public GetAccountsMetadataRequest(String appKey, String appSecret, String tokenID, String userSecret, String operationID, UserInput[] userInputs) {
            super(appKey, appSecret, tokenID, null, userSecret, operationID, userInputs);
        }
    }
}
