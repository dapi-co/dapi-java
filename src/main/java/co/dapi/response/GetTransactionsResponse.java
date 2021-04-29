package co.dapi.response;

import co.dapi.types.Transaction;

import java.util.Optional;

public class GetTransactionsResponse extends BaseResponse {
    private Transaction[] transactions;

    GetTransactionsResponse() {
        super();
    }

    public GetTransactionsResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }

    public Optional<Transaction[]> getTransactions() {
        return Optional.ofNullable(transactions);
    }
}
