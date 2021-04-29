package co.dapi.response;

import co.dapi.types.Range;

import java.util.Optional;

public class TransferAutoflowResponse extends BaseResponse {
    private String reference;
    private Range coolDownPeriod;

    TransferAutoflowResponse() {
        super();
    }

    public TransferAutoflowResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }

    public Optional<String> getReference() {
        return Optional.ofNullable(reference);
    }

    public Optional<Range> getCoolDownPeriod() {
        return Optional.ofNullable(coolDownPeriod);
    }
}
