package com.dapi;

import com.dapi.response.*;
import com.dapi.types.UserInput;
import com.google.gson.JsonSyntaxException;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;


/**
 * {@link DapiApp} represents a client app that's using one or more of the Dapi products.
 */
public class DapiApp {

    private final Config config;
    private final Auth a;
    private final Data d;
    private final Payment p;
    private final Metadata m;

    public DapiApp(Config config) {
        this.config = config;
        this.a = new Auth(config);
        this.d = new Data(config);
        this.p = new Payment(config);
        this.m = new Metadata(config);
    }

    /**
     * exchangeToken talks to the ExchangeToken endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @param accessCode   retrieved from user login
     * @param connectionID retrieved from user login
     * @return an {@link ExchangeTokenResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public ExchangeTokenResponse exchangeToken(String accessCode, String connectionID) throws IOException {
        return this.a.exchangeToken(accessCode, connectionID);
    }


    /**
     * getIdentity talks to the GetIdentity endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @return a {@link GetIdentityResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetIdentityResponse getIdentity(String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getIdentity(accessToken, userSecret, operationID, userInputs);
    }


    /**
     * getAccounts talks to the GetAccounts endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @return an {@link GetAccountsResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetAccountsResponse getAccounts(String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getAccounts(accessToken, userSecret, operationID, userInputs);
    }


    /**
     * getBalance talks to the GetBalance endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @return an {@link GetBalanceResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetBalanceResponse getBalance(String accountID, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getBalance(accountID, accessToken, userSecret, operationID, userInputs);
    }


    /**
     * getTransactions talks to the GetTransactions endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @return an {@link GetTransactionsResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetTransactionsResponse getTransactions(String accountID, LocalDate fromDate, LocalDate toDate, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getTransactions(accountID, fromDate, toDate, accessToken, userSecret, operationID, userInputs);
    }


    /**
     * createBeneficiary talks to the CreateBeneficiary endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @return an {@link CreateBeneficiaryResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public CreateBeneficiaryResponse createBeneficiary(Payment.BeneficiaryInfo beneficiary, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.createBeneficiary(beneficiary, accessToken, userSecret, operationID, userInputs);
    }


    /**
     * getBeneficiaries talks to the GetBeneficiaries endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @return an {@link GetBeneficiariesResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetBeneficiariesResponse getBeneficiaries(String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.getBeneficiaries(accessToken, userSecret, operationID, userInputs);
    }


    /**
     * createTransfer talks to the CreateTransfer endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @return an {@link CreateTransferResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public CreateTransferResponse createTransfer(Payment.Transfer transfer, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.createTransfer(transfer, accessToken, userSecret, operationID, userInputs);
    }


    /**
     * transferAutoflow talks to the TransferAutoflow endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @return an {@link TransferAutoflowResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public TransferAutoflowResponse transferAutoflow(Payment.TransferAutoflow transferAutoflow, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.transferAutoflow(transferAutoflow, accessToken, userSecret, operationID, userInputs);
    }


    /**
     * getAccountsMetadata talks to the GetAccountsMetadata endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @return an {@link GetAccountsMetadataResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetAccountsMetadataResponse getAccountsMetadata(String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.m.getAccountsMetadata(accessToken, userSecret, operationID, userInputs);
    }

    /**
     * handleSDKRequest injects this {@link DapiApp}'s appSecret in the passed request body, bodyJson, and then
     * forwards the request to Dapi, with the passed headers, headersMap, and returns the RAW response got.
     *
     * @return an {@link Response} representing the HTTP response of this operation.
     * @throws IOException in case of trouble happened while executing the request.
     */
    public Response handleSDKRequest(String bodyJson, HashMap<String, String> headersMap) throws IOException, JsonSyntaxException {
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
    public Response handleSDKRequest(String bodyJson) throws IOException, JsonSyntaxException {
        return this.handleSDKRequest(bodyJson, new HashMap<>());
    }
}