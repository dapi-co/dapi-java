package com.dapi;

import com.dapi.response.*;
import com.dapi.types.UserInput;

import java.io.IOException;
import java.time.LocalDate;

public class DapiApp {

    private final LoginData loginData;
    private final Auth a;
    private final Data d;
    private final Payment p;
    private final Metadata m;

    public DapiApp(Config config, LoginData loginData) {
        this.loginData = loginData;
        this.a = new Auth(config);
        this.d = new Data(config);
        this.p = new Payment(config);
        this.m = new Metadata(config);
    }

    public ExchangeTokenResponse exchangeToken() throws IOException {
        return this.a.exchangeToken(this.loginData.getTokenID(), this.loginData.getAccessCode(),
                this.loginData.getConnectionID());
    }

    public GetIdentityResponse getIdentity(String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getIdentity(this.loginData.getTokenID(), this.loginData.getUserID(),
                this.loginData.getUserSecret(), operationID, userInputs);
    }

    public GetAccountsResponse getAccounts(String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getAccounts(this.loginData.getTokenID(), this.loginData.getUserID(),
                this.loginData.getUserSecret(), operationID, userInputs);
    }

    public GetBalanceResponse getBalance(String accountID, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getBalance(this.loginData.getTokenID(), this.loginData.getUserID(),
                this.loginData.getUserSecret(), accountID, operationID, userInputs);
    }

    public GetTransactionsResponse getTransactions(String accountID, LocalDate fromDate, LocalDate toDate, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getTransactions(this.loginData.getTokenID(), this.loginData.getUserID(),
                this.loginData.getUserSecret(), accountID, fromDate, toDate, operationID, userInputs);
    }

    public GetBeneficiariesResponse getBeneficiaries(String operationID, UserInput[] userInputs) throws IOException {
        return this.p.getBeneficiaries(this.loginData.getTokenID(), this.loginData.getUserID(),
                this.loginData.getUserSecret(), operationID, userInputs);
    }

    public CreateTransferResponse createTransfer(Payment.Transfer transfer, String hlAPIStep, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.createTransfer(this.loginData.getTokenID(), this.loginData.getUserID(),
                this.loginData.getUserSecret(), transfer, hlAPIStep, operationID, userInputs);
    }

    public GetAccountsMetadataResponse getAccountsMetadata(String operationID, UserInput[] userInputs) throws IOException {
        return this.m.getAccountsMetadata(this.loginData.getTokenID(), this.loginData.getUserSecret(),
                operationID, userInputs);
    }
}