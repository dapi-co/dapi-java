package com.dapi;

import com.dapi.response.CreateBeneficiaryResponse;
import com.dapi.response.CreateTransferResponse;
import com.dapi.response.GetBeneficiariesResponse;
import com.dapi.response.TransferAutoflowResponse;
import com.dapi.types.Beneficiary;
import com.dapi.types.BeneficiaryAddress;
import com.dapi.types.UserInput;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class Payment {
    private final Config config;

    public Payment(Config config) {
        this.config = config;
    }

    public CreateBeneficiaryResponse createBeneficiary(String accessToken, String userSecret, BeneficiaryInfo beneficiary, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var bodyObj = new CreateBenefRequest(this.config.getAppSecret(), userSecret, beneficiary,
                operationID, userInputs);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(bodyObj, CreateBenefRequest.class);

        // Construct the headers needed for this request
        var headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + bodyObj.action, headers);


        // Convert the got response to the wanted response type
        var resp = DapiRequest.jsonAgent.fromJson(respJson, CreateBeneficiaryResponse.class);

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || resp.getStatus() == null) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new CreateBeneficiaryResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    public GetBeneficiariesResponse getBeneficiaries(String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var bodyObj = new GetBenefsRequest(this.config.getAppSecret(), userSecret, operationID, userInputs);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(bodyObj, GetBenefsRequest.class);

        // Construct the headers needed for this request
        var headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + bodyObj.action, headers);

        // Convert the got response to the wanted response type
        var resp = DapiRequest.jsonAgent.fromJson(respJson, GetBeneficiariesResponse.class);

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || resp.getStatus() == null) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new GetBeneficiariesResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    public CreateTransferResponse createTransfer(String accessToken, String userSecret, Transfer transfer, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var body = new CreateTransferRequest(this.config.getAppSecret(), userSecret, transfer,
                operationID, userInputs);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(body, CreateTransferRequest.class);

        // Construct the headers needed for this request
        var headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + body.action, headers);

        // Convert the got response to the wanted response type
        var resp = DapiRequest.jsonAgent.fromJson(respJson, CreateTransferResponse.class);

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || resp.getStatus() == null) {
            // If the got response wasn't a JSON string, resp will be null, and if
            // it didn't have the 'status' field, getStatus() will return null.
            return new CreateTransferResponse("UNEXPECTED_RESPONSE", "Unexpected response body");
        }

        return resp;
    }

    public TransferAutoflowResponse transferAutoflow(String accessToken, String userSecret, TransferAutoflow transferAutoflow, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var body = new TransferAutoflowRequest(this.config.getAppSecret(), userSecret, transferAutoflow,
                operationID, userInputs);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(body, TransferAutoflowRequest.class);

        // Construct the headers needed for this request
        var headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer " + accessToken);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson, DapiRequest.Dapi_URL + "/v2" + body.action, headers);

        // Convert the got response to the wanted response type
        var resp = DapiRequest.jsonAgent.fromJson(respJson, TransferAutoflowResponse.class);

        // Check if the got response was of unexpected format, and return a suitable response
        if (resp == null || resp.getStatus() == null) {
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

        public Transfer(String senderID, float amount, String receiverID) {
            this.senderID = senderID;
            this.amount = amount;
            this.receiverID = receiverID;
            this.name = null;
            this.iban = null;
            this.accountNumber = null;
        }

        public Transfer(String senderID, float amount, String name, String iban, String accountNumber) {
            this.senderID = senderID;
            this.amount = amount;
            this.receiverID = null;
            this.name = name;
            this.iban = iban;
            this.accountNumber = accountNumber;
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
    }

    public static class TransferAutoflow {
        private final String bundleID;
        private final String appKey;
        private final String userID;
        private final String senderID;
        private final float amount;
        private final BeneficiaryInfo beneficiary;

        public TransferAutoflow(String bundleID, String appKey, String userID, String senderID, float amount, BeneficiaryInfo beneficiary) {
            this.bundleID = bundleID;
            this.appKey = appKey;
            this.userID = userID;
            this.senderID = senderID;
            this.amount = amount;
            this.beneficiary = beneficiary;
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

        public CreateBenefRequest(String appSecret,
                                  String userSecret,
                                  BeneficiaryInfo beneficiary,
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

        public CreateTransferRequest(String appSecret,
                                     String userSecret,
                                     Transfer transfer,
                                     String operationID,
                                     UserInput[] userInputs) {
            super(appSecret, userSecret, operationID, userInputs);
            this.senderID = transfer.senderID;
            this.amount = transfer.amount;
            this.receiverID = transfer.receiverID;
            this.name = transfer.name;
            this.iban = transfer.iban;
            this.accountNumber = transfer.accountNumber;
        }
    }

    private static class TransferAutoflowRequest extends DapiRequest.BaseRequest {
        private final String action = "/payment/transfer/autoflow";
        private final String bundleID;
        private final String appKey;
        private final String senderID;
        private final float amount;
        private final BeneficiaryInfo beneficiary;

        public TransferAutoflowRequest(String appSecret,
                                       String userSecret,
                                       TransferAutoflow transferAutoflow,
                                       String operationID,
                                       UserInput[] userInputs) {
            super(appSecret, userSecret, operationID, userInputs);
            this.bundleID = transferAutoflow.bundleID;
            this.appKey = transferAutoflow.appKey;
            this.senderID = transferAutoflow.senderID;
            this.amount = transferAutoflow.amount;
            this.beneficiary = transferAutoflow.beneficiary;
        }
    }
}
