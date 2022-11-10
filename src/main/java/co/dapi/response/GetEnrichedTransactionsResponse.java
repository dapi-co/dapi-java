package co.dapi.response;

import co.dapi.types.EnrichedTransaction;

import java.util.Optional;

public class GetEnrichedTransactionsResponse extends BaseResponse {
    private EnrichedTransaction[] transactions;

    GetEnrichedTransactionsResponse() {
        super();
    }

    public GetEnrichedTransactionsResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }

    public Optional<EnrichedTransaction[]> getEnrichedTransactions() {
        return Optional.ofNullable(transactions);
    }
}
