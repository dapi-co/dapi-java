package com.dapi.types;

public class CreateTransferValidatorProps {
    private final ValidatorProps remarks;

    public CreateTransferValidatorProps(ValidatorProps remarks) {
        this.remarks = remarks;
    }

    public ValidatorProps getRemarks() {
        return remarks;
    }
}
