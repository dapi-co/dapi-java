package com.dapi.types;

public class CreateBeneficiaryValidator {
    private final CreateBeneficiaryValidatorProps local;
    private final CreateBeneficiaryValidatorProps same;

    public CreateBeneficiaryValidator(CreateBeneficiaryValidatorProps local, CreateBeneficiaryValidatorProps same) {
        this.local = local;
        this.same = same;
    }

    public CreateBeneficiaryValidatorProps getLocal() {
        return local;
    }

    public CreateBeneficiaryValidatorProps getSame() {
        return same;
    }
}
