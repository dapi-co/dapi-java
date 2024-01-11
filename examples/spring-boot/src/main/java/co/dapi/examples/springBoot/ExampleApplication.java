package co.dapi.examples.springBoot;

import co.dapi.Config;
import co.dapi.DapiApp;
import com.google.gson.Gson;
import okhttp3.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;

@SpringBootApplication
@RestController
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    // Build the JSON decoder/encoder to be used
    private static final Gson jsonAgent = (new Gson()).newBuilder().disableHtmlEscaping().create();

    // The DapiApp instance that will be used by your server
    private DapiApp dapiApp;

    @PostConstruct
    public void init() {
        // Replace YOUR_APP_SECRET with the appSecret of your Dapi app
        dapiApp = new DapiApp(new Config("YOUR_APPSECRET"));
    }

    @RequestMapping(
            value = "/handleSDKRequest",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String handleSDKRequest(@RequestBody String reqBodyJSON, @RequestHeader HashMap<String, String> reqHeadersMap) throws IOException {
        // Modify the request body before passing it to DapiApp
        try {
            // decode the JSON body into a HashMap, to be able to modify it
            HashMap reqBodyMap = jsonAgent.fromJson(reqBodyJSON, HashMap.class);

            // handle having an empty string as the request body(if it's allowed)
            if (reqBodyMap == null) {
                // create a new HasMap to use it as the request body
                reqBodyMap = new HashMap<>();
            }

            // modify the request body, or add new fields to it
            reqBodyMap.put("myCustomID", "CUSTOM_ID");

            // convert the updated request body back to a JSON string
            reqBodyJSON = jsonAgent.toJson(reqBodyMap);
        } catch (Exception e) {
            // add proper exception handling
            e.printStackTrace();
        }

        // Possibly, modify the header before passing it
        reqHeadersMap.put("X-My-Custom-Header", "CUSTOM_HEADER");

        // Forward the request body and headers to Dapi
        Response resp = dapiApp.handleSDKRequest(reqBodyJSON, reqHeadersMap);

        // Handle the response and return its body...
        // this will be returned only if the response body is null
        String respBodyJSON = "{\"error\": \"response.body is null\"}";

        // access the response body only if it's not null
        okhttp3.ResponseBody respBody = resp.body();
        if (respBody != null) respBodyJSON = respBody.string();

        return respBodyJSON;
    }
}
