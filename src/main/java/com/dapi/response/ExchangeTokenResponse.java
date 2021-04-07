package com.dapi.response;

import java.util.Optional;

public class ExchangeTokenResponse extends BaseResponse {
    private String accessToken;
    private String tokenID;

    ExchangeTokenResponse() {
        super();
    }

    public ExchangeTokenResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }

    public Optional<String> getAccessToken() {
        return Optional.ofNullable(accessToken);
    }

    public Optional<String> getTokenID() {
        return Optional.ofNullable(tokenID);
    }
}
