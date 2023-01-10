package co.dapi.response;

public class CreatePullResponse extends BaseResponse {
    CreatePullResponse() {
        super();
    }

    public CreatePullResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }
}
