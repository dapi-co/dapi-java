package com.dapi.response;

import com.dapi.types.Account;

import java.util.Optional;

public class GetAccountsResponse extends BaseResponse {
    private Account[] accounts;

    GetAccountsResponse() {
        super();
    }

    public GetAccountsResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }

    public Optional<Account[]> getAccounts() {
        return Optional.ofNullable(accounts);
    }
}
