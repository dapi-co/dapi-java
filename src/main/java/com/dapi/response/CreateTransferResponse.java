package com.dapi.response;

import com.dapi.types.Range;

import java.util.Optional;

public class CreateTransferResponse extends BaseResponse {
    private String reference;
    private String hlAPIStep;
    private Range coolDownPeriod;

    CreateTransferResponse() {
        super();
    }

    public CreateTransferResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }

    public Optional<String> getReference() {
        return Optional.ofNullable(reference);
    }

    public Optional<String> getHlAPIStep() {
        return Optional.ofNullable(hlAPIStep);
    }

    public Optional<Range> getCoolDownPeriod() {
        return Optional.ofNullable(coolDownPeriod);
    }
}
