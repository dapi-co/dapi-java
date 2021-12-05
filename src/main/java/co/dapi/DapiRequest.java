package co.dapi;

import co.dapi.types.UserInput;
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
        headersMap.put("host", "dd.dapi.co");
        return doRequest(bodyJson, DD_URL, headersMap);
    }

    public static String Do(String bodyJson, String url) throws IOException {
        return Do(bodyJson, url, new HashMap<>());
    }

    public static String Do(String bodyJson, String url, HashMap<String, String> headersMap) throws IOException {
        // Execute the request and get the response
        Response resp = doRequest(bodyJson, url, headersMap);

        // Return the response body if it's there, otherwise return an empty string
        ResponseBody respBody = resp.body();
        if (respBody == null)
            return "";
        return respBody.string();
    }

    private static Response doRequest(String bodyJson, String url, HashMap<String, String> headersMap) throws IOException {
        // Build the headers from the default values, plus the passed ones
        Headers.Builder headersBuilder = new Headers.Builder();
        for (Map.Entry<String, String> header : headersMap.entrySet()) {
            headersBuilder.set(header.getKey(), header.getValue());
        }
        headersBuilder.set("content-type", "application/json");

        // Create the request
        RequestBody reqBody = RequestBody.create(bodyJson, MediaType.parse("application/json"));
        Request req = new Request.Builder()
                .url(url)
                .method("POST", reqBody)
                .headers(headersBuilder.build())
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
            JsonObject output = new JsonObject();

            // loop over each field in the request
            for (Map.Entry<String, Object> kv : src.entrySet()) {
                String fieldName = kv.getKey();
                Object fieldValue = kv.getValue();

                // if the current field is not the userInputs, or the type of userInputs is not
                // as expected, serialize it using the default serializer.
                if (!fieldName.equals("userInputs") || !(fieldValue instanceof List)) {
                    output.add(fieldName, context.serialize(fieldValue));
                    continue;
                }

                JsonArray userInputsOutput = new JsonArray();

                // loop over each element in the userInputs array
                for (Object ui : (List<?>) fieldValue) {
                    if (!(ui instanceof Map)) {
                        userInputsOutput.add(context.serialize(ui));
                        continue;
                    }

                    JsonObject outputUiObj = new JsonObject();

                    // loop over each field of the current userInput object
                    for (Map.Entry<?, ?> uiEntry : ((Map<?, ?>) ui).entrySet()) {
                        String uiFieldName = (String) uiEntry.getKey();
                        Object uiFieldValue = uiEntry.getValue();

                        // handle only the index field, so use the default serializer otherwise
                        if (!uiFieldName.equals("index")) {
                            outputUiObj.add(uiFieldName, context.serialize(uiFieldValue));
                        } else {
                            int index = ((int) ((double) uiFieldValue));
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
