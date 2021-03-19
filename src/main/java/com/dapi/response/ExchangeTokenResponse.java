package com.dapi.response;

public class ExchangeTokenResponse extends BaseResponse {
    ExchangeTokenResponse() {
        super();
    }

    public ExchangeTokenResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }
}
