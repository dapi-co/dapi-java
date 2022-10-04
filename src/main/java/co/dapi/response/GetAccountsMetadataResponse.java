package co.dapi.response;

import co.dapi.types.AccountsMetadata;

import java.util.Optional;

public class GetAccountsMetadataResponse extends BaseResponse {
    private AccountsMetadata accountsMetadata;

    GetAccountsMetadataResponse() {
        super();
    }

    public GetAccountsMetadataResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }

    public Optional<AccountsMetadata> getAccountsMetadata() {
        return Optional.ofNullable(accountsMetadata);
    }
}
