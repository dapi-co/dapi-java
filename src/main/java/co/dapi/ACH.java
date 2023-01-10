package co.dapi;

import co.dapi.response.CreatePullResponse;
import co.dapi.types.UserInput;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;


public class ACH {
    private final Config config;

    public ACH(Config config) {
        this.config = config;
    }

    public CreatePullResponse createPull(CreatePull transfer, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        CreatePullRequest bodyObj = new CreatePullRequest(transfer, this.config.getAppSecret(), userSecret,
                operationID, userInputs);

        // Convert the request body to a JSON string
        String bodyJson = DapiRequest.jsonAgent.toJson(bodyObj, CreatePullRequest.class);

        // Construct the headers needed for this request
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        String respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + bodyObj.action, headers);


        // Convert the got response to the wanted response type
        CreatePullResponse resp = null;
        try {
            resp = DapiRequest.jsonAgent.fromJson(respJson, CreatePullResponse.class);
        } catch (JsonSyntaxException e) {
            // Empty catch, cause the handling code is below
        }

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || (resp.getStatus() == null && !resp.getType().isPresent())) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new CreatePullResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }


    public static class CreatePull {
        private final String senderID;
        private final float amount;
        private final String description;

        /**
         * Create an object that holds the info for an ACH create pull
         *
         * @param senderID    the id of the account which the money should be pulled from.
         *                    retrieved from one of the accounts array returned from the getAccounts method.
         * @param amount      the amount of money which should be pulled.
         * @param description description for the ACH pull.
         */
        public CreatePull(String senderID, float amount, String description) {
            this.senderID = senderID;
            this.amount = amount;
            this.description = description;
        }

        public String getSenderID() {
            return senderID;
        }

        public float getAmount() {
            return amount;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }
    }

    private static class CreatePullRequest extends DapiRequest.BaseRequest {
        private final String action = "/ach/pull/create";
        private final String senderID;
        private final float amount;
        private final String description;

        public CreatePullRequest(CreatePull transfer,
                                     String appSecret,
                                     String userSecret,
                                     String operationID,
                                     UserInput[] userInputs) {
            super(appSecret, userSecret, operationID, userInputs);
            this.senderID = transfer.senderID;
            this.amount = transfer.amount;
            this.description = transfer.description;
        }
    }

}
