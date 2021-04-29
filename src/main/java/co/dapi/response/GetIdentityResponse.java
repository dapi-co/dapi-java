package co.dapi.response;

import co.dapi.types.Identity;

import java.util.Optional;

public class GetIdentityResponse extends BaseResponse {
    private Identity identity;

    GetIdentityResponse() {
        super();
    }

    public GetIdentityResponse(String errType, String errMsg) {
        super(errType, errMsg);
    }

    public Optional<Identity> getIdentity() {
        return Optional.ofNullable(identity);
    }
}
