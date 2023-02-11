package co.dapi.response;

public class CreateACHPullResponse extends BaseResponse {
    CreateACHPullResponse() {
        super();
    }

    public CreateACHPullResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }
}
