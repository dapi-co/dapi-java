package com.dapi;

import com.dapi.response.CreateTransferResponse;
import com.dapi.response.GetBeneficiariesResponse;
import com.dapi.types.Beneficiary;
import com.dapi.types.BeneficiaryAddress;
import com.dapi.types.UserInput;

import java.io.IOException;

public class Payment {
    private final Config config;

    public Payment(Config config) {
        this.config = config;
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

    public CreateTransferResponse createTransfer(String tokenID, String userID, String userSecret, Transfer transfer, String hlAPIStep, String operationID, UserInput[] userInputs) throws IOException {

        // Create the request body of this call
        var bodyObj = new CreateTransferRequest(this.config.getBundleID(), this.config.getAppKey(),
                this.config.getAppSecret(), tokenID, userID, userSecret, transfer.senderID,
                transfer.amount, transfer.beneficiary, hlAPIStep, operationID, userInputs);

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

    public static class Transfer {
        private final String senderID;
        private final float amount;
        private final BeneficiaryInfo beneficiary;

        public Transfer(String senderID, float amount, BeneficiaryInfo beneficiary) {
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
        private final String swiftCode;
        private final String iban;
        private final String accountNumber;
        private final Beneficiary.BeneficiaryType type;
        private final BeneficiaryAddress address;
        private final String country;
        private final String branchAddress;
        private final String branchName;
        private final String bankName;
        private final String phoneNumber;
        private final String sortCode;

        public BeneficiaryInfo(String name, String swiftCode, String iban, String accountNumber, Beneficiary.BeneficiaryType type, BeneficiaryAddress address, String country, String branchAddress, String branchName, String bankName, String phoneNumber, String sortCode) {
            this.name = name;
            this.swiftCode = swiftCode;
            this.iban = iban;
            this.accountNumber = accountNumber;
            this.type = type;
            this.address = address;
            this.country = country;
            this.branchAddress = branchAddress;
            this.branchName = branchName;
            this.bankName = bankName;
            this.phoneNumber = phoneNumber;
            this.sortCode = sortCode;
        }

        public BeneficiaryInfo(String name, String swiftCode, String iban, String accountNumber, Beneficiary.BeneficiaryType type, BeneficiaryAddress address, String country, String branchAddress, String branchName) {
            this.name = name;
            this.swiftCode = swiftCode;
            this.iban = iban;
            this.accountNumber = accountNumber;
            this.type = type;
            this.address = address;
            this.country = country;
            this.branchAddress = branchAddress;
            this.branchName = branchName;
            this.bankName = null;
            this.phoneNumber = null;
            this.sortCode = null;
        }

        public String getName() {
            return name;
        }

        public String getSwiftCode() {
            return swiftCode;
        }

        public String getIban() {
            return iban;
        }

        public String getAccountNumber() {
            return accountNumber;
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

        public String getSortCode() {
            return sortCode;
        }

        public String getBranchAddress() {
            return branchAddress;
        }

        public String getBankName() {
            return bankName;
        }

        public String getBranchName() {
            return branchName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }
    }

    private static class GetBenefsRequest extends DapiRequest.BaseRequest {
        private final String action = "/payment/beneficiaries/get";

        public GetBenefsRequest(String appKey, String appSecret, String tokenID, String userID, String userSecret, String operationID, UserInput[] userInputs) {
            super(appKey, appSecret, tokenID, userID, userSecret, operationID, userInputs);
        }
    }

    private static class CreateTransferRequest extends DapiRequest.BaseRequest {
        private final String action = "/payment/transfer/autoflow";
        private final String bundleID;
        private final String senderID;
        private final float amount;
        private final BeneficiaryInfo beneficiary;
        private final String hlAPIStep;

        public CreateTransferRequest(String bundleID, String appKey, String appSecret, String tokenID, String userID, String userSecret, String senderID, float amount, BeneficiaryInfo beneficiary, String hlAPIStep, String operationID, UserInput[] userInputs) {
            super(appKey, appSecret, tokenID, userID, userSecret, operationID, userInputs);
            this.bundleID = bundleID;
            this.senderID = senderID;
            this.amount = amount;
            this.beneficiary = beneficiary;
            this.hlAPIStep = hlAPIStep;
        }
    }
}
