package co.dapi.response;

import co.dapi.types.Balance;

import java.util.Optional;

public class GetBalanceResponse extends BaseResponse {
    private Balance balance;

    GetBalanceResponse() {
        super();
    }

    public GetBalanceResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }

    public Optional<Balance> getBalance() {
        return Optional.ofNullable(balance);
    }
}
