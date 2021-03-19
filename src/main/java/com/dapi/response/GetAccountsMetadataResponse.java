package com.dapi.response;

import com.dapi.types.AccountsMetadata;

public class GetAccountsMetadataResponse extends BaseResponse {
    private AccountsMetadata accountsMetadata;

    GetAccountsMetadataResponse() {
        super();
    }

    public GetAccountsMetadataResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }


}
