package com.dapi;

import com.dapi.response.GetAccountsResponse;
import com.dapi.response.GetBalanceResponse;
import com.dapi.response.GetIdentityResponse;
import com.dapi.response.GetTransactionsResponse;
import com.dapi.types.UserInput;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


public class Data {
    private final Config config;

    public Data(Config config) {
        this.config = config;
    }

    GetIdentityResponse getIdentity(String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var body = new GetIdentityRequest(this.config.getAppSecret(), userSecret, operationID, userInputs);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(body, GetIdentityRequest.class);

        // Construct the headers needed for this request
        var headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + body.action, headers);

        // Convert the got response to the wanted response type
        GetIdentityResponse resp = null;
        try {
            resp = DapiRequest.jsonAgent.fromJson(respJson, GetIdentityResponse.class);
        } catch (JsonSyntaxException e) {
            // Empty catch, cause the handling code is below
        }

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || (resp.getStatus() == null && resp.getType().isEmpty())) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetIdentityResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    GetAccountsResponse getAccounts(String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var body = new GetAccountsRequest(this.config.getAppSecret(), userSecret, operationID, userInputs);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(body, GetAccountsRequest.class);

        // Construct the headers needed for this request
        var headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + body.action, headers);

        // Convert the got response to the wanted response type
        GetAccountsResponse resp = null;
        try {
            resp = DapiRequest.jsonAgent.fromJson(respJson, GetAccountsResponse.class);
        } catch (JsonSyntaxException e) {
            // Empty catch, cause the handling code is below
        }

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || (resp.getStatus() == null && resp.getType().isEmpty())) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetAccountsResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    GetBalanceResponse getBalance(String accountID, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var body = new GetBalanceRequest(accountID, this.config.getAppSecret(), userSecret,
                operationID, userInputs);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(body, GetBalanceRequest.class);

        // Construct the headers needed for this request
        var headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + body.action, headers);

        // Convert the got response to the wanted response type
        GetBalanceResponse resp = null;
        try {
            resp = DapiRequest.jsonAgent.fromJson(respJson, GetBalanceResponse.class);
        } catch (JsonSyntaxException e) {
            // Empty catch, cause the handling code is below
        }

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || (resp.getStatus() == null && resp.getType().isEmpty())) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetBalanceResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    GetTransactionsResponse getTransactions(String accountID, LocalDate fromDate, LocalDate toDate, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var body = new GetTransactionsRequest(accountID, fromDate, toDate, this.config.getAppSecret(),
                userSecret, operationID, userInputs);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(body, GetTransactionsRequest.class);

        // Construct the headers needed for this request
        var headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + body.action, headers);

        // Convert the got response to the wanted response type
        GetTransactionsResponse resp = null;
        try {
            resp = DapiRequest.jsonAgent.fromJson(respJson, GetTransactionsResponse.class);
        } catch (JsonSyntaxException e) {
            // Empty catch, cause the handling code is below
        }

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || (resp.getStatus() == null && resp.getType().isEmpty())) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetTransactionsResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    private static class GetIdentityRequest extends DapiRequest.BaseRequest {
        final private String action = "/data/identity/get";

        public GetIdentityRequest(String appSecret, String userSecret, String operationID, UserInput[] userInputs) {
            super(appSecret, userSecret, operationID, userInputs);
        }
    }

    private static class GetAccountsRequest extends DapiRequest.BaseRequest {
        final private String action = "/data/accounts/get";

        public GetAccountsRequest(String appSecret, String userSecret, String operationID, UserInput[] userInputs) {
            super(appSecret, userSecret, operationID, userInputs);
        }
    }

    private static class GetBalanceRequest extends DapiRequest.BaseRequest {
        private final String action = "/data/balance/get";
        private final String accountID;

        public GetBalanceRequest(String accountID, String appSecret, String userSecret, String operationID, UserInput[] userInputs) {
            super(appSecret, userSecret, operationID, userInputs);
            this.accountID = accountID;
        }
    }

    private static class GetTransactionsRequest extends DapiRequest.BaseRequest {
        private final String action = "/data/transactions/get";
        private final String accountID;
        private final String fromDate;
        private final String toDate;

        private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        public GetTransactionsRequest(String accountID, LocalDate fromDate, LocalDate toDate, String appSecret, String userSecret, String operationID, UserInput[] userInputs) {
            super(appSecret, userSecret, operationID, userInputs);
            this.accountID = accountID;
            this.fromDate = dateFormatter.format(fromDate);
            this.toDate = dateFormatter.format(toDate);
        }
    }
}
