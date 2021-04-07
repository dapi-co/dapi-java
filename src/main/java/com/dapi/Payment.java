package com.dapi;

import com.dapi.response.CreateBeneficiaryResponse;
import com.dapi.response.CreateTransferResponse;
import com.dapi.response.GetBeneficiariesResponse;
import com.dapi.response.TransferAutoflowResponse;
import com.dapi.types.Beneficiary;
import com.dapi.types.BeneficiaryAddress;
import com.dapi.types.UserInput;

import java.io.IOException;
import java.util.Optional;

public class Payment {
    private final Config config;

    public Payment(Config config) {
        this.config = config;
    }

    public CreateBeneficiaryResponse createBeneficiary(String tokenID, String userID, String userSecret, BeneficiaryInfo beneficiary, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var bodyObj = new CreateBenefRequest(this.config.getAppKey(), this.config.getAppSecret(),
                tokenID, userID, userSecret, beneficiary, operationID, userInputs);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(bodyObj, CreateBenefRequest.class);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson);

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

    public GetBeneficiariesResponse getBeneficiaries(String tokenID, String userID, String userSecret, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var bodyObj = new GetBenefsRequest(this.config.getAppKey(), this.config.getAppSecret(),
                tokenID, userID, userSecret, operationID, userInputs);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(bodyObj, GetBenefsRequest.class);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson);

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

    public CreateTransferResponse createTransfer(String tokenID, String userID, String userSecret, Transfer transfer, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var bodyObj = new CreateTransferRequest(this.config.getAppKey(), this.config.getAppSecret(),
                tokenID, userID, userSecret, transfer, operationID, userInputs);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(bodyObj, CreateTransferRequest.class);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson);

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

    public TransferAutoflowResponse transferAutoflow(String tokenID, String userID, String userSecret, TransferAutoflow transferAutoflow, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var bodyObj = new TransferAutoflowRequest(this.config.getBundleID(), this.config.getAppKey(),
                this.config.getAppSecret(), tokenID, userID, userSecret, transferAutoflow,
                operationID, userInputs);

        // Convert the request body to a JSON string
        var bodyJson = DapiRequest.jsonAgent.toJson(bodyObj, TransferAutoflowRequest.class);

        // Make the request and get the response
        var respJson = DapiRequest.Do(bodyJson);

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
        private final String senderID;
        private final float amount;
        private final BeneficiaryInfo beneficiary;

        public TransferAutoflow(String senderID, float amount, BeneficiaryInfo beneficiary) {
            this.senderID = senderID;
            this.amount = amount;
            this.beneficiary = beneficiary;
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

        public CreateBenefRequest(String appKey,
                                  String appSecret,
                                  String tokenID,
                                  String userID,
                                  String userSecret,
                                  BeneficiaryInfo beneficiary,
                                  String operationID,
                                  UserInput[] userInputs) {
            super(appKey, appSecret, tokenID, userID, userSecret, operationID, userInputs);
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

        public GetBenefsRequest(String appKey,
                                String appSecret,
                                String tokenID,
                                String userID,
                                String userSecret,
                                String operationID,
                                UserInput[] userInputs) {
            super(appKey, appSecret, tokenID, userID, userSecret, operationID, userInputs);
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

        public CreateTransferRequest(String appKey,
                                     String appSecret,
                                     String tokenID,
                                     String userID,
                                     String userSecret,
                                     Transfer transfer,
                                     String operationID,
                                     UserInput[] userInputs) {
            super(appKey, appSecret, tokenID, userID, userSecret, operationID, userInputs);
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
        private final String senderID;
        private final float amount;
        private final BeneficiaryInfo beneficiary;

        public TransferAutoflowRequest(String bundleID,
                                       String appKey,
                                       String appSecret,
                                       String tokenID,
                                       String userID,
                                       String userSecret,
                                       TransferAutoflow transferAutoflow,
                                       String operationID,
                                       UserInput[] userInputs) {
            super(appKey, appSecret, tokenID, userID, userSecret, operationID, userInputs);
            this.bundleID = bundleID;
            this.senderID = transferAutoflow.senderID;
            this.amount = transferAutoflow.amount;
            this.beneficiary = transferAutoflow.beneficiary;
        }
    }
}
