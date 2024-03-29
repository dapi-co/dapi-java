package co.dapi;

import co.dapi.types.UserInput;
import co.dapi.response.GetAccountsMetadataResponse;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.HashMap;


public class Metadata {
    private final Config config;

    public Metadata(Config config) {
        this.config = config;
    }

    public GetAccountsMetadataResponse getAccountsMetadata(String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        GetAccountsMetadataRequest body = new GetAccountsMetadataRequest(this.config.getAppSecret(), userSecret, operationID, userInputs);

        // Convert the body map to a JSON string
        String bodyJson = DapiRequest.jsonAgent.toJson(body, GetAccountsMetadataRequest.class);

        // Construct the headers needed for this request
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        String respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + body.action, headers);


        // Convert the got response to the wanted response type
        GetAccountsMetadataResponse resp = null;
        try {
            resp = DapiRequest.jsonAgent.fromJson(respJson, GetAccountsMetadataResponse.class);
        } catch (JsonSyntaxException e) {
            // Empty catch, cause the handling code is below
        }

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || (resp.getStatus() == null && !resp.getType().isPresent())) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetAccountsMetadataResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    private static class GetAccountsMetadataRequest extends DapiRequest.BaseRequest {
        private final String action = "/metadata/accounts/get";

        public GetAccountsMetadataRequest(String appSecret, String userSecret, String operationID, UserInput[] userInputs) {
            super(appSecret, userSecret, operationID, userInputs);
        }
    }
}
