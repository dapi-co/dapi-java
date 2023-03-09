package co.dapi.response;

import co.dapi.types.CategorizedTransaction;

import java.util.Optional;

public class GetCategorizedTransactionsResponse extends BaseResponse {
    private CategorizedTransaction[] transactions;

    GetCategorizedTransactionsResponse() {
        super();
    }

    public GetCategorizedTransactionsResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }

    public Optional<CategorizedTransaction[]> getCategorizedTransactions() {
        return Optional.ofNullable(transactions);
    }
}
