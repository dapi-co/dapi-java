package com.dapi;

import com.dapi.response.*;
import com.dapi.types.UserInput;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

public class DapiApp {

    private final LoginData loginData;
    private final Config config;
    private final Auth a;
    private final Data d;
    private final Payment p;
    private final Metadata m;

    public DapiApp(Config config, LoginData loginData) {
        this.loginData = loginData;
        this.config = config;
        this.a = new Auth(config);
        this.d = new Data(config);
        this.p = new Payment(config);
        this.m = new Metadata(config);
    }

    public ExchangeTokenResponse exchangeToken() throws IOException {
        return this.a.exchangeToken(this.loginData.getAccessCode(), this.loginData.getConnectionID());
    }

    public GetIdentityResponse getIdentity(String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getIdentity(accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }

    public GetAccountsResponse getAccounts(String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getAccounts(accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }

    public GetBalanceResponse getBalance(String accountID, String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getBalance(accountID, accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }

    public GetTransactionsResponse getTransactions(String accountID, LocalDate fromDate, LocalDate toDate, String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getTransactions(accountID, fromDate, toDate, accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }

    public CreateBeneficiaryResponse createBeneficiary(Payment.BeneficiaryInfo beneficiary, String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.createBeneficiary(beneficiary, accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }

    public GetBeneficiariesResponse getBeneficiaries(String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.getBeneficiaries(accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }

    public CreateTransferResponse createTransfer(Payment.Transfer transfer, String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.createTransfer(transfer, accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }

    public TransferAutoflowResponse transferAutoflow(Payment.TransferAutoflow transferAutoflow, String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.transferAutoflow(transferAutoflow, accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }

    public GetAccountsMetadataResponse getAccountsMetadata(String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.m.getAccountsMetadata(accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }

    public Response handleSDKRequest(String bodyJson, HashMap<String, String> headersMap) throws IOException {
        var bodyMap = DapiRequest.jsonAgent.fromJson(bodyJson, HashMap.class);
        bodyMap.put("appSecret", this.config.getAppSecret());
        bodyJson = DapiRequest.jsonAgent.toJson(bodyMap);
        return DapiRequest.HandleSDK(bodyJson, headersMap);
    }

    public Response handleSDKRequest(String bodyJson) throws IOException {
        return this.handleSDKRequest(bodyJson, new HashMap<>());
    }
}