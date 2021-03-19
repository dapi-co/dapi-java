package com.dapi;

import com.dapi.types.UserInput;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DapiRequest {
        public final static String BaseURL = "https://dd.dapi.co";
//    public final static String BaseURL = "http://127.0.0.1:8095";

    final static Gson jsonAgent = new Gson()
            .newBuilder()
            .disableHtmlEscaping()
            .create();

    private final static OkHttpClient httpClient = new OkHttpClient()
            .newBuilder()
            .readTimeout(2, TimeUnit.MINUTES)
            .build();


    public static String Do(String bodyJson) throws IOException {

        // Create the request
        var reqBody = RequestBody.create(bodyJson, MediaType.parse("application/json"));
        var req = new Request.Builder()
                .url(BaseURL)
                .method("POST", reqBody)
                .addHeader("Content-Type", "application/json")
                .build();

        // Execute the request and get the response
        var resp = httpClient.newCall(req).execute();

        // Return the response body if it's there, otherwise return an empty string
        var respBody = resp.body();
        if (respBody == null)
            return "";
        return respBody.string();
    }

    static class BaseRequest {
        private final String appKey;
        private final String appSecret;
        private final String tokenID;
        private final String userID;
        private final String userSecret;
        private final String operationID;
        private final UserInput[] userInputs;

        public BaseRequest(String appKey, String appSecret, String tokenID, String userID, String userSecret, String operationID, UserInput[] userInputs) {
            this.appKey = appKey;
            this.appSecret = appSecret;
            this.tokenID = tokenID;
            this.userID = userID;
            this.userSecret = userSecret;
            this.operationID = operationID;
            this.userInputs = userInputs;
        }
    }
}
