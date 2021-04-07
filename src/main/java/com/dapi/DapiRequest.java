package com.dapi;

import com.dapi.types.UserInput;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Headers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DapiRequest {
    public final static String Dapi_URL = "https://api.dapi.co";
    public final static String DD_URL = "https://dd.dapi.co";
//    public final static String Dapi_URL = "http://127.0.0.1:8090";
//    public final static String DD_URL = "http://127.0.0.1:8095";

    final static Gson jsonAgent = new Gson().newBuilder()
            .disableHtmlEscaping()
            .create();

    private final static OkHttpClient httpClient = new OkHttpClient().newBuilder()
            .readTimeout(2, TimeUnit.MINUTES)
            .build();

    public static String Do(String bodyJson, String url) throws IOException {
        return Do(bodyJson, url, new HashMap<>());
    }

    public static String Do(String bodyJson, String url, HashMap<String, String> headersMap) throws IOException {


        // Build the headers from the default values, plus the passed ones
        var headers = new Headers.Builder().
                add("Content-Type", "application/json");

        for (Map.Entry<String, String> header : headersMap.entrySet()) {
            headers.add(header.getKey(), header.getValue());
        }

        // Create the request
        var reqBody = RequestBody.create(bodyJson, MediaType.parse("application/json"));
        var req = new Request.Builder()
                .url(url)
                .method("POST", reqBody)
                .headers(headers.build())
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
        private final String appSecret;
        private final String userSecret;
        private final String operationID;
        private final UserInput[] userInputs;

        public BaseRequest(String appSecret, String userSecret, String operationID, UserInput[] userInputs) {
            this.appSecret = appSecret;
            this.userSecret = userSecret;
            this.operationID = operationID;
            this.userInputs = userInputs;
        }
    }
}
