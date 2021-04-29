package co.dapi.response;

import co.dapi.types.AccountsMetadata;

public class GetAccountsMetadataResponse extends BaseResponse {
    private AccountsMetadata accountsMetadata;

    GetAccountsMetadataResponse() {
        super();
    }

    public GetAccountsMetadataResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }


}
