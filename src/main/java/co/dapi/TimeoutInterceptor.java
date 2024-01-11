package co.dapi;

import com.google.gson.*;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okio.Buffer;

class TimeoutInterceptor implements Interceptor {
    Gson jsonAgent;

    public static final int MAX_TIMEOUT = 300;
    public static final int MIN_TIMEOUT = 135;

    public static final String CREATE_TRANSFER = "payment/transfer/autoflow";
    public static final String CREATE_TRANSFER_TO_EXISTING_BENEFICIARY = "payment/transfer/create";
    public static final String CREATE_WIRE_TRANSFER = "wire/transfer/autoflow";
    public static final String CREATE_WIRE_TRANSFER_TO_EXISTING_BENEFICIARY = "wire/transfer/create";
    public static final String CREATE_ACH_PULL_TRANSFER = "ach/pull/create";
    public static final String NYMCARD_LOAD_FUNDS = "nymcard/funds/load";

    private static final String[] maxTimeoutEndpoints = {
            CREATE_TRANSFER,
            CREATE_TRANSFER_TO_EXISTING_BENEFICIARY,
            CREATE_WIRE_TRANSFER,
            CREATE_WIRE_TRANSFER_TO_EXISTING_BENEFICIARY,
            CREATE_ACH_PULL_TRANSFER,
            NYMCARD_LOAD_FUNDS
    };

    public static boolean maxTimeoutEnabled(String endpoint) {
        for (String maxTimeoutEndpoint : maxTimeoutEndpoints) {
            if (endpoint.contains(maxTimeoutEndpoint)) {
                return true;
            }
        }
        return false;
    }

    private static String stringify(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return null;
        }
    }

    public TimeoutInterceptor(Gson jsonAgent) {
        this.jsonAgent = jsonAgent;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HashMap bodyMap = jsonAgent.fromJson(stringify(request), HashMap.class);
        int timeout;

        if (bodyMap.containsKey("action") && maxTimeoutEnabled(bodyMap.get("action").toString())) {
            timeout = MAX_TIMEOUT;
        } else {
            timeout = MIN_TIMEOUT;
        }

        return chain.withReadTimeout(timeout, TimeUnit.SECONDS)
                .withConnectTimeout(timeout, TimeUnit.SECONDS)
                .withWriteTimeout(timeout, TimeUnit.SECONDS)
                .proceed(request);
    }
}