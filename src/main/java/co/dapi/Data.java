package co.dapi;

import co.dapi.response.GetAccountsResponse;
import co.dapi.response.GetBalanceResponse;
import co.dapi.response.GetIdentityResponse;
import co.dapi.types.UserInput;
import co.dapi.response.GetTransactionsResponse;
import co.dapi.response.GetCategorizedTransactionsResponse;
import co.dapi.response.GetEnrichedTransactionsResponse;
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
        GetIdentityRequest body = new GetIdentityRequest(this.config.getAppSecret(), userSecret, operationID, userInputs);

        // Convert the request body to a JSON string
        String bodyJson = DapiRequest.jsonAgent.toJson(body, GetIdentityRequest.class);

        // Construct the headers needed for this request
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        String respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + body.action, headers);

        // Convert the got response to the wanted response type
        GetIdentityResponse resp = null;
        try {
            resp = DapiRequest.jsonAgent.fromJson(respJson, GetIdentityResponse.class);
        } catch (JsonSyntaxException e) {
            // Empty catch, cause the handling code is below
        }

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || (resp.getStatus() == null && !resp.getType().isPresent())) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetIdentityResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    GetAccountsResponse getAccounts(String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        GetAccountsRequest body = new GetAccountsRequest(this.config.getAppSecret(), userSecret, operationID, userInputs);

        // Convert the request body to a JSON string
        String bodyJson = DapiRequest.jsonAgent.toJson(body, GetAccountsRequest.class);

        // Construct the headers needed for this request
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        String respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + body.action, headers);

        // Convert the got response to the wanted response type
        GetAccountsResponse resp = null;
        try {
            resp = DapiRequest.jsonAgent.fromJson(respJson, GetAccountsResponse.class);
        } catch (JsonSyntaxException e) {
            // Empty catch, cause the handling code is below
        }

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || (resp.getStatus() == null && !resp.getType().isPresent())) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetAccountsResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    GetBalanceResponse getBalance(String accountID, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        GetBalanceRequest body = new GetBalanceRequest(accountID, this.config.getAppSecret(), userSecret,
                operationID, userInputs);

        // Convert the request body to a JSON string
        String bodyJson = DapiRequest.jsonAgent.toJson(body, GetBalanceRequest.class);

        // Construct the headers needed for this request
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        String respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + body.action, headers);

        // Convert the got response to the wanted response type
        GetBalanceResponse resp = null;
        try {
            resp = DapiRequest.jsonAgent.fromJson(respJson, GetBalanceResponse.class);
        } catch (JsonSyntaxException e) {
            // Empty catch, cause the handling code is below
        }

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || (resp.getStatus() == null && !resp.getType().isPresent())) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetBalanceResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    GetTransactionsResponse getTransactions(String accountID, LocalDate fromDate, LocalDate toDate, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        GetTransactionsRequest body = new GetTransactionsRequest(accountID, fromDate, toDate, this.config.getAppSecret(),
                userSecret, operationID, userInputs);

        // Convert the request body to a JSON string
        String bodyJson = DapiRequest.jsonAgent.toJson(body, GetTransactionsRequest.class);

        // Construct the headers needed for this request
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        String respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + body.action, headers);

        // Convert the got response to the wanted response type
        GetTransactionsResponse resp = null;
        try {
            resp = DapiRequest.jsonAgent.fromJson(respJson, GetTransactionsResponse.class);
        } catch (JsonSyntaxException e) {
            // Empty catch, cause the handling code is below
        }

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || (resp.getStatus() == null && !resp.getType().isPresent())) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetTransactionsResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    GetCategorizedTransactionsResponse getCategorizedTransactions(String accountID, LocalDate fromDate, LocalDate toDate, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        GetCategorizedTransactionsRequest body = new GetCategorizedTransactionsRequest(accountID, fromDate, toDate, this.config.getAppSecret(),
                userSecret, operationID, userInputs);

        // Convert the request body to a JSON string
        String bodyJson = DapiRequest.jsonAgent.toJson(body, GetCategorizedTransactionsRequest.class);

        // Construct the headers needed for this request
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        String respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + body.action, headers);

        // Convert the got response to the wanted response type
        GetCategorizedTransactionsResponse resp = null;
        try {
            resp = DapiRequest.jsonAgent.fromJson(respJson, GetCategorizedTransactionsResponse.class);
        } catch (JsonSyntaxException e) {
            // Empty catch, cause the handling code is below
        }

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || (resp.getStatus() == null && !resp.getType().isPresent())) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetCategorizedTransactionsResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    GetEnrichedTransactionsResponse getEnrichedTransactions(String accountID, LocalDate fromDate, LocalDate toDate, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        GetEnrichedTransactionsRequest body = new GetEnrichedTransactionsRequest(accountID, fromDate, toDate, this.config.getAppSecret(),
                userSecret, operationID, userInputs);

        // Convert the request body to a JSON string
        String bodyJson = DapiRequest.jsonAgent.toJson(body, GetEnrichedTransactionsRequest.class);

        // Construct the headers needed for this request
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        String respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + body.action, headers);

        // Convert the got response to the wanted response type
        GetEnrichedTransactionsResponse resp = null;
        try {
            resp = DapiRequest.jsonAgent.fromJson(respJson, GetEnrichedTransactionsResponse.class);
        } catch (JsonSyntaxException e) {
            // Empty catch, cause the handling code is below
        }

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || (resp.getStatus() == null && !resp.getType().isPresent())) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetEnrichedTransactionsResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
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

    private static class GetCategorizedTransactionsRequest extends DapiRequest.BaseRequest {
        private final String action = "/data/categorizedTransactions/get";
        private final String accountID;
        private final String fromDate;
        private final String toDate;

        private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        public GetCategorizedTransactionsRequest(String accountID, LocalDate fromDate, LocalDate toDate, String appSecret, String userSecret, String operationID, UserInput[] userInputs) {
            super(appSecret, userSecret, operationID, userInputs);
            this.accountID = accountID;
            this.fromDate = dateFormatter.format(fromDate);
            this.toDate = dateFormatter.format(toDate);
        }
    }

    private static class GetEnrichedTransactionsRequest extends DapiRequest.BaseRequest {
        private final String action = "/data/enrichedTransactions/get";
        private final String accountID;
        private final String fromDate;
        private final String toDate;

        private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        public GetEnrichedTransactionsRequest(String accountID, LocalDate fromDate, LocalDate toDate, String appSecret, String userSecret, String operationID, UserInput[] userInputs) {
            super(appSecret, userSecret, operationID, userInputs);
            this.accountID = accountID;
            this.fromDate = dateFormatter.format(fromDate);
            this.toDate = dateFormatter.format(toDate);
        }
    }
}
