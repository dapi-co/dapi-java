package co.dapi.response;

public class CreateBeneficiaryResponse extends BaseResponse {
    CreateBeneficiaryResponse() {
        super();
    }

    public CreateBeneficiaryResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }
}
