package com.dapi;

import com.dapi.types.UserInput;
import com.google.gson.*;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DapiRequest {
    public final static String Dapi_URL = "https://api.dapi.co";
    public final static String DD_URL = "https://dd.dapi.co";

    final static Gson jsonAgent = new Gson().newBuilder()
            .disableHtmlEscaping()
            .registerTypeAdapter(HashMap.class, new SDKRequestSerializer())
            .create();

    private final static OkHttpClient httpClient = new OkHttpClient().newBuilder()
            .readTimeout(2, TimeUnit.MINUTES)
            .build();


    public static Response HandleSDK(String bodyJson, HashMap<String, String> headersMap) throws IOException {
        return doRequest(bodyJson, DD_URL, headersMap);
    }

    public static String Do(String bodyJson, String url) throws IOException {
        return Do(bodyJson, url, new HashMap<>());
    }

    public static String Do(String bodyJson, String url, HashMap<String, String> headersMap) throws IOException {
        // Execute the request and get the response
        var resp = doRequest(bodyJson, url, headersMap);

        // Return the response body if it's there, otherwise return an empty string
        var respBody = resp.body();
        if (respBody == null)
            return "";
        return respBody.string();
    }

    private static Response doRequest(String bodyJson, String url, HashMap<String, String> headersMap) throws IOException {
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

        // Execute the request and return the response
        return httpClient.newCall(req).execute();
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


    static class SDKRequestSerializer implements JsonSerializer<HashMap<String, Object>> {

        @Override
        public JsonElement serialize(HashMap<String, Object> src, Type typeOfSrc, JsonSerializationContext context) {
            var output = new JsonObject();

            // loop over each field in the request
            for (var kv : src.entrySet()) {
                var fieldName = kv.getKey();
                var fieldValue = kv.getValue();

                // if the current field is not the userInputs, or the type of userInputs is not
                // as expected, serialize it using the default serializer.
                if (!fieldName.equals("userInputs") || !(fieldValue instanceof List)) {
                    output.add(fieldName, context.serialize(fieldValue));
                    continue;
                }

                var userInputsOutput = new JsonArray();

                // loop over each element in the userInputs array
                for (var ui : (List<?>) fieldValue) {
                    if (!(ui instanceof Map)) {
                        userInputsOutput.add(context.serialize(ui));
                        continue;
                    }

                    var outputUiObj = new JsonObject();

                    // loop over each field of the current userInput object
                    for (var uiEntry : ((Map<?, ?>) ui).entrySet()) {
                        var uiFieldName = (String) uiEntry.getKey();
                        var uiFieldValue = uiEntry.getValue();

                        // handle only the index field, so use the default serializer otherwise
                        if (!uiFieldName.equals("index")) {
                            outputUiObj.add(uiFieldName, context.serialize(uiFieldValue));
                        } else {
                            var index = ((int) ((double) uiFieldValue));
                            outputUiObj.add(uiFieldName, new JsonPrimitive(index));
                        }
                    }

                    userInputsOutput.add(outputUiObj);
                }

                output.add(fieldName, userInputsOutput);
            }

            return output;
        }
    }
}
