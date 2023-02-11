package co.dapi.response;

import co.dapi.types.PullTransfer;

import java.util.Optional;

public class GetPullResponse extends BaseResponse {
    private PullTransfer transfer;

    GetPullResponse() {
        super();
    }

    public GetPullResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }

    public Optional<PullTransfer> getTransfer() {
        return Optional.ofNullable(transfer);
    }
}