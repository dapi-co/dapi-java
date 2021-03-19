package com.dapi.response;

import com.dapi.types.APIStatus;
import com.dapi.types.UserInput;

import java.util.Optional;

public class BaseResponse {
    private APIStatus status;
    private boolean success;
    private String operationID;
    private UserInput[] userInputs;
    private String type;
    private String msg;

    BaseResponse() {
    }

    /**
     * This is used to construct an error response from the reading of the got response.
     *
     * @param errType
     * @param errMsg
     */
    BaseResponse(String errType, String errMsg) {
        this.status = APIStatus.failed;
        this.success = false;
        this.type = errType;
        this.msg = errMsg;
    }

    public APIStatus getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return success;
    }

    public Optional<String> getOperationID() {
        return Optional.ofNullable(operationID);
    }

    public Optional<UserInput[]> getUserInputs() {
        return Optional.ofNullable(userInputs);
    }

    public Optional<String> getType() {
        return Optional.ofNullable(type);
    }

    public Optional<String> getMsg() {
        return Optional.ofNullable(msg);
    }
}
