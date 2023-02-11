package co.dapi.response;

import co.dapi.types.ACHPullTransferInfo;

import java.util.Optional;

public class GetACHPullResponse extends BaseResponse {
    private ACHPullTransferInfo transfer;

    GetACHPullResponse() {
        super();
    }

    public GetACHPullResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }

    public Optional<ACHPullTransferInfo> getTransfer() {
        return Optional.ofNullable(transfer);
    }
}
