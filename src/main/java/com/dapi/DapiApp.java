package com.dapi;

import com.dapi.response.*;
import com.dapi.types.UserInput;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;


/**
 * {@link DapiApp} represents a client app that's using one or more of the Dapi products.
 */
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

    /**
     * exchangeToken talks to the ExchangeToken endpoint of Dapi, with this {@link DapiApp}'s appSecret and
     * its user {@link LoginData}.
     * The {@link LoginData} passed to this {@link DapiApp} instance must contain the accessCode and connectionID,
     * otherwise this call will return an error.
     *
     * @return an {@link ExchangeTokenResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public ExchangeTokenResponse exchangeToken() throws IOException {
        return this.a.exchangeToken(this.loginData.getAccessCode().orElse(""), this.loginData.getConnectionID().orElse(""));
    }


    /**
     * getIdentity talks to the GetIdentity endpoint of Dapi, with this {@link DapiApp}'s appSecret and
     * its user {@link LoginData}.
     *
     * @return a {@link GetIdentityResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetIdentityResponse getIdentity(String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getIdentity(accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }


    /**
     * getAccounts talks to the GetAccounts endpoint of Dapi, with this {@link DapiApp}'s appSecret and
     * its user {@link LoginData}.
     *
     * @return an {@link GetAccountsResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetAccountsResponse getAccounts(String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getAccounts(accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }


    /**
     * getBalance talks to the GetBalance endpoint of Dapi, with this {@link DapiApp}'s appSecret and
     * its user {@link LoginData}.
     *
     * @return an {@link GetBalanceResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetBalanceResponse getBalance(String accountID, String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getBalance(accountID, accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }


    /**
     * getTransactions talks to the GetTransactions endpoint of Dapi, with this {@link DapiApp}'s appSecret and
     * its user {@link LoginData}.
     *
     * @return an {@link GetTransactionsResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetTransactionsResponse getTransactions(String accountID, LocalDate fromDate, LocalDate toDate, String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getTransactions(accountID, fromDate, toDate, accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }


    /**
     * createBeneficiary talks to the CreateBeneficiary endpoint of Dapi, with this {@link DapiApp}'s appSecret and
     * its user {@link LoginData}.
     *
     * @return an {@link CreateBeneficiaryResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public CreateBeneficiaryResponse createBeneficiary(Payment.BeneficiaryInfo beneficiary, String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.createBeneficiary(beneficiary, accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }


    /**
     * getBeneficiaries talks to the GetBeneficiaries endpoint of Dapi, with this {@link DapiApp}'s appSecret and
     * its user {@link LoginData}.
     *
     * @return an {@link GetBeneficiariesResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetBeneficiariesResponse getBeneficiaries(String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.getBeneficiaries(accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }


    /**
     * createTransfer talks to the CreateTransfer endpoint of Dapi, with this {@link DapiApp}'s appSecret and
     * its user {@link LoginData}.
     *
     * @return an {@link CreateTransferResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public CreateTransferResponse createTransfer(Payment.Transfer transfer, String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.createTransfer(transfer, accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }


    /**
     * transferAutoflow talks to the TransferAutoflow endpoint of Dapi, with this {@link DapiApp}'s appSecret and
     * its user {@link LoginData}.
     *
     * @return an {@link TransferAutoflowResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public TransferAutoflowResponse transferAutoflow(Payment.TransferAutoflow transferAutoflow, String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.transferAutoflow(transferAutoflow, accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }


    /**
     * getAccountsMetadata talks to the GetAccountsMetadata endpoint of Dapi, with this {@link DapiApp}'s appSecret and
     * its user {@link LoginData}.
     *
     * @return an {@link GetAccountsMetadataResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetAccountsMetadataResponse getAccountsMetadata(String accessToken, String operationID, UserInput[] userInputs) throws IOException {
        return this.m.getAccountsMetadata(accessToken, this.loginData.getUserSecret(), operationID, userInputs);
    }

    /**
     * handleSDKRequest injects this {@link DapiApp}'s appSecret in the passed request body, bodyJson, and then
     * forwards the request to Dapi, with the passed headers, headersMap, and returns the RAW response got.
     *
     * @return an {@link Response} representing the HTTP response of this operation.
     * @throws IOException in case of trouble happened while executing the request.
     */
    public Response handleSDKRequest(String bodyJson, HashMap<String, String> headersMap) throws IOException {
        var bodyMap = DapiRequest.jsonAgent.fromJson(bodyJson, HashMap.class);
        bodyMap.put("appSecret", this.config.getAppSecret());
        bodyJson = DapiRequest.jsonAgent.toJson(bodyMap);
        return DapiRequest.HandleSDK(bodyJson, headersMap);
    }

    /**
     * handleSDKRequest injects this {@link DapiApp}'s appSecret in the passed request body, bodyJson, and then
     * forwards the request to Dapi, and returns the RAW response got.
     *
     * @return an {@link Response} representing the HTTP response of this operation.
     * @throws IOException in case of trouble happened while executing the request.
     */
    public Response handleSDKRequest(String bodyJson) throws IOException {
        return this.handleSDKRequest(bodyJson, new HashMap<>());
    }
}