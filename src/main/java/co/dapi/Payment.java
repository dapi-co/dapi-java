package co.dapi;

import co.dapi.response.CreateBeneficiaryResponse;
import co.dapi.response.CreateTransferResponse;
import co.dapi.response.GetBeneficiariesResponse;
import co.dapi.response.TransferAutoflowResponse;
import co.dapi.types.Beneficiary;
import co.dapi.types.UserInput;
import co.dapi.types.BeneficiaryAddress;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class Payment {
    private final Config config;

    public Payment(Config config) {
        this.config = config;
    }

    public CreateBeneficiaryResponse createBeneficiary(BeneficiaryInfo beneficiary, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        CreateBenefRequest bodyObj = new CreateBenefRequest(beneficiary, this.config.getAppSecret(), userSecret,
                operationID, userInputs);

        // Convert the request body to a JSON string
        String bodyJson = DapiRequest.jsonAgent.toJson(bodyObj, CreateBenefRequest.class);

        // Construct the headers needed for this request
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        String respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + bodyObj.action, headers);


        // Convert the got response to the wanted response type
        CreateBeneficiaryResponse resp = null;
        try {
            resp = DapiRequest.jsonAgent.fromJson(respJson, CreateBeneficiaryResponse.class);
        } catch (JsonSyntaxException e) {
            // Empty catch, cause the handling code is below
        }

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || (resp.getStatus() == null && !resp.getType().isPresent())) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new CreateBeneficiaryResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    public GetBeneficiariesResponse getBeneficiaries(String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        GetBenefsRequest bodyObj = new GetBenefsRequest(this.config.getAppSecret(), userSecret, operationID, userInputs);

        // Convert the request body to a JSON string
        String bodyJson = DapiRequest.jsonAgent.toJson(bodyObj, GetBenefsRequest.class);

        // Construct the headers needed for this request
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        String respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + bodyObj.action, headers);

        // Convert the got response to the wanted response type
        GetBeneficiariesResponse resp = null;
        try {
            resp = DapiRequest.jsonAgent.fromJson(respJson, GetBeneficiariesResponse.class);
        } catch (JsonSyntaxException e) {
            // Empty catch, cause the handling code is below
        }

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || (resp.getStatus() == null && !resp.getType().isPresent())) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetBeneficiariesResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    public CreateTransferResponse createTransfer(Transfer transfer, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        CreateTransferRequest body = new CreateTransferRequest(transfer, this.config.getAppSecret(), userSecret,
                operationID, userInputs);

        // Convert the request body to a JSON string
        String bodyJson = DapiRequest.jsonAgent.toJson(body, CreateTransferRequest.class);

        // Construct the headers needed for this request
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        String respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + body.action, headers);

        // Convert the got response to the wanted response type
        CreateTransferResponse resp = null;
        try {
            resp = DapiRequest.jsonAgent.fromJson(respJson, CreateTransferResponse.class);
        } catch (JsonSyntaxException e) {
            // Empty catch, cause the handling code is below
        }

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || (resp.getStatus() == null && !resp.getType().isPresent())) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new CreateTransferResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    public TransferAutoflowResponse transferAutoflow(TransferAutoflow transferAutoflow, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        TransferAutoflowRequest body = new TransferAutoflowRequest(transferAutoflow, this.config.getAppSecret(), userSecret,
                operationID, userInputs);

        // Convert the request body to a JSON string
        String bodyJson = DapiRequest.jsonAgent.toJson(body, TransferAutoflowRequest.class);

        // Construct the headers needed for this request
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        String respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + body.action, headers);

        // Convert the got response to the wanted response type
        TransferAutoflowResponse resp = null;
        try {
            resp = DapiRequest.jsonAgent.fromJson(respJson, TransferAutoflowResponse.class);
        } catch (JsonSyntaxException e) {
            // Empty catch, cause the handling code is below
        }

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || (resp.getStatus() == null && !resp.getType().isPresent())) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new TransferAutoflowResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    public static class Transfer {
        private final String senderID;
        private final float amount;
        private final String receiverID;
        private final String name;
        private final String iban;
        private final String accountNumber;
        private final String remark;
        private final String nickname;

        /**
         * Create an object that holds the info for a transfer from a bank that requires the receiver to be already
         * registered as a beneficiary to perform a transaction.
         *
         * @param senderID   the id of the account which the money should be sent from.
         *                   retrieved from one of the accounts array returned from the getAccounts method.
         * @param amount     the amount of money which should be sent.
         * @param receiverID the id of the beneficiary which the money should be sent to.
         *                   retrieved from one of the beneficiaries array returned from the getBeneficiaries method.
         */
        public Transfer(String senderID, float amount, String receiverID) {
            this.senderID = senderID;
            this.amount = amount;
            this.receiverID = receiverID;
            this.name = null;
            this.iban = null;
            this.accountNumber = null;
            this.remark = null;
            this.nickname = null;
        }

        /**
         * Create an object that holds the info for a transfer from a bank that requires the receiver to be already
         * registered as a beneficiary to perform a transaction.
         *
         * @param senderID   the id of the account which the money should be sent from.
         *                   retrieved from one of the accounts array returned from the getAccounts method.
         * @param amount     the amount of money which should be sent.
         * @param receiverID the id of the beneficiary which the money should be sent to.
         *                   retrieved from one of the beneficiaries array returned from the getBeneficiaries method.
         * @param remark     the remark string that will be sent with this transfer.
         */
        public Transfer(String senderID, float amount, String receiverID, String remark) {
            this.senderID = senderID;
            this.amount = amount;
            this.receiverID = receiverID;
            this.remark = remark;
            this.name = null;
            this.iban = null;
            this.accountNumber = null;
            this.nickname = null;
        }

        /**
         * Create an object that holds the info for a transfer from a bank that handles the creation of beneficiaries
         * on its own, internally, and doesn't require the receiver to be already registered as a beneficiary to perform
         * a transaction.
         *
         * @param senderID      the id of the account which the money should be sent from.
         *                      retrieved from one of the accounts array returned from the getAccounts method.
         * @param amount        the amount of money which should be sent.
         * @param name          the name of receiver.
         * @param iban          the IBAN of the receiver's account.
         * @param accountNumber the Account Number of the receiver's account.
         */
        public Transfer(String senderID, float amount, String name, String iban, String accountNumber) {
            this.senderID = senderID;
            this.amount = amount;
            this.name = name;
            this.iban = iban;
            this.accountNumber = accountNumber;
            this.receiverID = null;
            this.remark = null;
            this.nickname = null;
        }

        /**
         * Create an object that holds the info for a transfer from a bank that handles the creation of beneficiaries
         * on its own, internally, and doesn't require the receiver to be already registered as a beneficiary to perform
         * a transaction.
         *
         * @param senderID      the id of the account which the money should be sent from.
         *                      retrieved from one of the accounts array returned from the getAccounts method.
         * @param amount        the amount of money which should be sent.
         * @param name          the name of receiver.
         * @param iban          the IBAN of the receiver's account.
         * @param accountNumber the Account Number of the receiver's account.
         * @param remark        the remark string that will be sent with this transfer.
         * @param nickname      the nickname of the receiver.
         */
        public Transfer(String senderID, float amount, String name, String iban, String accountNumber, String remark, String nickname) {
            this.senderID = senderID;
            this.amount = amount;
            this.name = name;
            this.iban = iban;
            this.accountNumber = accountNumber;
            this.remark = remark;
            this.nickname = nickname;
            this.receiverID = null;
        }

        public String getSenderID() {
            return senderID;
        }

        public float getAmount() {
            return amount;
        }

        public Optional<String> getReceiverID() {
            return Optional.ofNullable(receiverID);
        }

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }

        public Optional<String> getIban() {
            return Optional.ofNullable(iban);
        }

        public Optional<String> getAccountNumber() {
            return Optional.ofNullable(accountNumber);
        }

        public Optional<String> getRemark() {
            return Optional.ofNullable(remark);
        }

        public Optional<String> getNickname() {
            return Optional.ofNullable(nickname);
        }
    }

    public static class TransferAutoflow {
        private final String bundleID;
        private final String appKey;
        private final String userID;
        private final String bankID;
        private final String senderID;
        private final float amount;
        private final String remark;
        private final BeneficiaryInfo beneficiary;

        /**
         * Create an object that holds the info needed for the transferAutoflow method.
         *
         * @param bundleID    one of the bundleIDs set for this app.
         * @param appKey      the appKey of this app.
         * @param userID      the userID of the user which is initiating this transfer.
         * @param bankID      the bankID of the user which is initiating this transfer.
         * @param senderID    the id of the account which the money should be sent from.
         *                    retrieved from one of the accounts array returned from the getAccounts method.
         * @param amount      the amount of money which should be sent.
         * @param beneficiary the required info about the beneficiary.
         */
        public TransferAutoflow(String bundleID, String appKey, String userID, String bankID, String senderID, float amount, BeneficiaryInfo beneficiary) {
            this.bundleID = bundleID;
            this.appKey = appKey;
            this.userID = userID;
            this.bankID = bankID;
            this.senderID = senderID;
            this.amount = amount;
            this.beneficiary = beneficiary;
            this.remark = null;
        }

        /**
         * Create an object that holds the info needed for the transferAutoflow method.
         *
         * @param bundleID    one of the bundleIDs set for this app.
         * @param appKey      the appKey of this app.
         * @param userID      the userID of the user which is initiating this transfer.
         * @param bankID      the bankID of the user which is initiating this transfer.
         * @param senderID    the id of the account which the money should be sent from.
         *                    retrieved from one of the accounts array returned from the getAccounts method.
         * @param amount      the amount of money which should be sent.
         * @param beneficiary the required info about the beneficiary.
         * @param remark      the remark string that will be sent with this transfer.
         */
        public TransferAutoflow(String bundleID, String appKey, String userID, String bankID, String senderID, float amount, BeneficiaryInfo beneficiary, String remark) {
            this.bundleID = bundleID;
            this.appKey = appKey;
            this.userID = userID;
            this.bankID = bankID;
            this.senderID = senderID;
            this.amount = amount;
            this.beneficiary = beneficiary;
            this.remark = remark;
        }

        public String getBundleID() {
            return bundleID;
        }

        public String getAppKey() {
            return appKey;
        }

        public String getUserID() {
            return userID;
        }

        public String getSenderID() {
            return senderID;
        }

        public float getAmount() {
            return amount;
        }

        public BeneficiaryInfo getBeneficiary() {
            return beneficiary;
        }

        public Optional<String> getRemark() {
            return Optional.ofNullable(remark);
        }
    }

    public static class BeneficiaryInfo {
        private final String name;
        private final String accountNumber;
        private final String iban;
        private final String swiftCode;
        private final Beneficiary.BeneficiaryType type;
        private final BeneficiaryAddress address;
        private final String country;
        private final String branchAddress;
        private final String branchName;
        private final String bankName;
        private final String phoneNumber;
        private final String sortCode;
        private final String nickname;
        private final String routingNumber;

        /**
         * Creates an object that holds all the info about some beneficiary.
         */
        public BeneficiaryInfo(String name,
                               String accountNumber,
                               String iban,
                               String swiftCode,
                               Beneficiary.BeneficiaryType type,
                               BeneficiaryAddress address,
                               String country,
                               String branchAddress,
                               String branchName,
                               String bankName,
                               String phoneNumber,
                               String sortCode,
                               String nickname,
                               String routingNumber) {
            this.name = name;
            this.accountNumber = accountNumber;
            this.iban = iban;
            this.swiftCode = swiftCode;
            this.type = type;
            this.address = address;
            this.country = country;
            this.branchAddress = branchAddress;
            this.branchName = branchName;
            this.bankName = bankName;
            this.phoneNumber = phoneNumber;
            this.sortCode = sortCode;
            this.nickname = nickname;
            this.routingNumber = routingNumber;
        }

        /**
         * Creates an object that holds only the required info about some beneficiary.
         */
        public BeneficiaryInfo(String name,
                               String accountNumber,
                               String iban,
                               String swiftCode,
                               Beneficiary.BeneficiaryType type,
                               BeneficiaryAddress address,
                               String country,
                               String branchAddress,
                               String branchName) {
            this.name = name;
            this.accountNumber = accountNumber;
            this.iban = iban;
            this.swiftCode = swiftCode;
            this.type = type;
            this.address = address;
            this.country = country;
            this.branchAddress = branchAddress;
            this.branchName = branchName;
            this.bankName = null;
            this.phoneNumber = null;
            this.sortCode = null;
            this.nickname = null;
            this.routingNumber = null;
        }

        public String getName() {
            return name;
        }

        public String getIban() {
            return iban;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getSwiftCode() {
            return swiftCode;
        }

        public Beneficiary.BeneficiaryType getType() {
            return type;
        }

        public BeneficiaryAddress getAddress() {
            return address;
        }

        public String getCountry() {
            return country;
        }

        public String getBranchAddress() {
            return branchAddress;
        }

        public String getBranchName() {
            return branchName;
        }

        public Optional<String> getBankName() {
            return Optional.ofNullable(bankName);
        }

        public Optional<String> getPhoneNumber() {
            return Optional.ofNullable(phoneNumber);
        }

        public Optional<String> getSortCode() {
            return Optional.ofNullable(sortCode);
        }

        public Optional<String> getNickname() {
            return Optional.ofNullable(nickname);
        }

        public Optional<String> getRoutingNumber() {
            return Optional.ofNullable(routingNumber);
        }
    }

    private static class CreateBenefRequest extends DapiRequest.BaseRequest {
        private final String action = "/payment/beneficiaries/create";
        private final String name;
        private final String accountNumber;
        private final String iban;
        private final String swiftCode;
        private final Beneficiary.BeneficiaryType type;
        private final BeneficiaryAddress address;
        private final String country;
        private final String branchAddress;
        private final String branchName;
        private final String bankName;
        private final String phoneNumber;
        private final String sortCode;
        private final String nickname;
        private final String routingNumber;

        public CreateBenefRequest(BeneficiaryInfo beneficiary,
                                  String appSecret,
                                  String userSecret,
                                  String operationID,
                                  UserInput[] userInputs) {
            super(appSecret, userSecret, operationID, userInputs);
            this.name = beneficiary.name;
            this.accountNumber = beneficiary.accountNumber;
            this.iban = beneficiary.iban;
            this.swiftCode = beneficiary.swiftCode;
            this.type = beneficiary.type;
            this.address = beneficiary.address;
            this.country = beneficiary.country;
            this.branchAddress = beneficiary.branchAddress;
            this.branchName = beneficiary.branchName;
            this.bankName = beneficiary.bankName;
            this.phoneNumber = beneficiary.phoneNumber;
            this.sortCode = beneficiary.sortCode;
            this.nickname = beneficiary.nickname;
            this.routingNumber = beneficiary.routingNumber;
        }
    }

    private static class GetBenefsRequest extends DapiRequest.BaseRequest {
        private final String action = "/payment/beneficiaries/get";

        public GetBenefsRequest(String appSecret,
                                String userSecret,
                                String operationID,
                                UserInput[] userInputs) {
            super(appSecret, userSecret, operationID, userInputs);
        }
    }

    private static class CreateTransferRequest extends DapiRequest.BaseRequest {
        private final String action = "/payment/transfer/create";
        private final String senderID;
        private final float amount;
        private final String receiverID;
        private final String name;
        private final String iban;
        private final String accountNumber;
        private final String remark;
        private final String nickname;

        public CreateTransferRequest(Transfer transfer,
                                     String appSecret,
                                     String userSecret,
                                     String operationID,
                                     UserInput[] userInputs) {
            super(appSecret, userSecret, operationID, userInputs);
            this.senderID = transfer.senderID;
            this.amount = transfer.amount;
            this.receiverID = transfer.receiverID;
            this.name = transfer.name;
            this.iban = transfer.iban;
            this.accountNumber = transfer.accountNumber;
            this.remark = transfer.remark;
            this.nickname = transfer.nickname;
        }
    }

    private static class TransferAutoflowRequest extends DapiRequest.BaseRequest {
        private final String action = "/payment/transfer/autoflow";
        private final String bundleID;
        private final String appKey;
        private final String userID;
        private final String bankID;
        private final String senderID;
        private final float amount;
        private final BeneficiaryInfo beneficiary;
        private final String remark;

        public TransferAutoflowRequest(TransferAutoflow transferAutoflow,
                                       String appSecret,
                                       String userSecret,
                                       String operationID,
                                       UserInput[] userInputs) {
            super(appSecret, userSecret, operationID, userInputs);
            this.bundleID = transferAutoflow.bundleID;
            this.appKey = transferAutoflow.appKey;
            this.userID = transferAutoflow.userID;
            this.bankID = transferAutoflow.bankID;
            this.senderID = transferAutoflow.senderID;
            this.amount = transferAutoflow.amount;
            this.beneficiary = transferAutoflow.beneficiary;
            this.remark = transferAutoflow.remark;
        }
    }
}
