package co.dapi.response;

import co.dapi.types.APIStatus;
import co.dapi.types.UserInput;

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
     * This is a private constructor to this lib
     */
    BaseResponse(String errType, String errMsg) {
        this.status = APIStatus.failed;
        this.success = false;
        this.type = errType;
        this.msg = errMsg;
    }

    /**
     * returns the status of the operation.
     */
    public APIStatus getStatus() {
        return status;
    }

    /**
     * returns true if request is successful and false otherwise.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * returns a unique ID generated to identify a specific operation.
     */
    public Optional<String> getOperationID() {
        return Optional.ofNullable(operationID);
    }

    /**
     * returns the UserInputs required for this operation, which are the further information
     * required from the user before the job can be completed.
     */
    public Optional<UserInput[]> getUserInputs() {
        return Optional.ofNullable(userInputs);
    }

    /**
     * returns the type of error encountered.
     * only available if the operation was not successful.
     */
    public Optional<String> getType() {
        return Optional.ofNullable(type);
    }

    /**
     * returns the message of the error encountered.
     * only available if the operation was not successful.
     */
    public Optional<String> getMsg() {
        return Optional.ofNullable(msg);
    }
}
