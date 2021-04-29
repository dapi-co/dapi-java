package co.dapi.response;

import co.dapi.types.Beneficiary;

import java.util.Optional;

public class GetBeneficiariesResponse extends BaseResponse {
    private Beneficiary[] beneficiaries;

    GetBeneficiariesResponse() {
        super();
    }

    public GetBeneficiariesResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }

    public Optional<Beneficiary[]> getBeneficiaries() {
        return Optional.ofNullable(beneficiaries);
    }
}
