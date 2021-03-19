package com.dapi;

import com.dapi.response.GetAccountsResponse;
import com.dapi.response.GetBalanceResponse;
import com.dapi.response.GetIdentityResponse;
import com.dapi.response.GetTransactionsResponse;
import com.dapi.types.UserInput;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Data {
    private final Config config;

    public Data(Config config) {
        this.config = config;
    }

    GetIdentityResponse getIdentity(String tokenID, String userID, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var bodyObj = new GetIdentityRequest(this.config.getAppKey(), this.config.getAppSecret(),
                tokenID, userID, userSecret, operationID, userInputs);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(bodyObj, GetIdentityRequest.class);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson);

        // Convert the got response to the wanted response type
        var resp = DapiRequest.jsonAgent.fromJson(respJson, GetIdentityResponse.class);

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || resp.getStatus() == null) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetIdentityResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    GetAccountsResponse getAccounts(String tokenID, String userID, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var bodyObj = new GetAccountsRequest(this.config.getAppKey(), this.config.getAppSecret(),
                tokenID, userID, userSecret, operationID, userInputs);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(bodyObj, GetAccountsRequest.class);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson);

        // Convert the got response to the wanted response type
        var resp = DapiRequest.jsonAgent.fromJson(respJson, GetAccountsResponse.class);

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || resp.getStatus() == null) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetAccountsResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    GetBalanceResponse getBalance(String tokenID, String userID, String userSecret, String accountID, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var bodyObj = new GetBalanceRequest(this.config.getAppKey(), this.config.getAppSecret(),
                tokenID, userID, userSecret, accountID, operationID, userInputs);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(bodyObj, GetBalanceRequest.class);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson);

        // Convert the got response to the wanted response type
        var resp = DapiRequest.jsonAgent.fromJson(respJson, GetBalanceResponse.class);

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || resp.getStatus() == null) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetBalanceResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    GetTransactionsResponse getTransactions(String tokenID, String userID, String userSecret, String accountID, LocalDate fromDate, LocalDate toDate, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var bodyObj = new GetTransactionsRequest(this.config.getAppKey(), this.config.getAppSecret(),
                tokenID, userID, userSecret, accountID, fromDate, toDate, operationID, userInputs);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(bodyObj, GetTransactionsRequest.class);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson);

        // Convert the got response to the wanted response type
        var resp = DapiRequest.jsonAgent.fromJson(respJson, GetTransactionsResponse.class);

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || resp.getStatus() == null) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetTransactionsResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    private static class GetIdentityRequest extends DapiRequest.BaseRequest {
        final private String action = "/data/identity/get";

        public GetIdentityRequest(String appKey, String appSecret, String tokenID, String userID, String userSecret, String operationID, UserInput[] userInputs) {
            super(appKey, appSecret, tokenID, userID, userSecret, operationID, userInputs);
        }
    }

    private static class GetAccountsRequest extends DapiRequest.BaseRequest {
        final private String action = "/data/accounts/get";

        public GetAccountsRequest(String appKey, String appSecret, String tokenID, String userID, String userSecret, String operationID, UserInput[] userInputs) {
            super(appKey, appSecret, tokenID, userID, userSecret, operationID, userInputs);
        }
    }

    private static class GetBalanceRequest extends DapiRequest.BaseRequest {
        private final String action = "/data/balance/get";
        private final String accountID;

        public GetBalanceRequest(String appKey, String appSecret, String tokenID, String userID, String userSecret, String accountID, String operationID, UserInput[] userInputs) {
            super(appKey, appSecret, tokenID, userID, userSecret, operationID, userInputs);
            this.accountID = accountID;
        }
    }

    private static class GetTransactionsRequest extends DapiRequest.BaseRequest {
        private final String action = "/data/transactions/get";
        private final String accountID;
        private final String fromDate;
        private final String toDate;

        private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        public GetTransactionsRequest(String appKey, String appSecret, String tokenID, String userID, String userSecret, String accountID, LocalDate fromDate, LocalDate toDate, String operationID, UserInput[] userInputs) {
            super(appKey, appSecret, tokenID, userID, userSecret, operationID, userInputs);
            this.accountID = accountID;
            this.fromDate = dateFormatter.format(fromDate);
            this.toDate = dateFormatter.format(toDate);
        }
    }
}
