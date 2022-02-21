package co.dapi;

import co.dapi.response.*;
import co.dapi.types.UserInput;
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
     * @param accessCode   retrieved from user login.
     * @param connectionID retrieved from user login.
     * @return an {@link ExchangeTokenResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public ExchangeTokenResponse exchangeToken(String accessCode, String connectionID) throws IOException {
        return this.a.exchangeToken(accessCode, connectionID);
    }

    /**
     * getIdentity talks to the GetIdentity endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @param accessToken retrieved from the ExchangeToken process.
     * @param userSecret  retrieved from the user login.
     * @return a {@link GetIdentityResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetIdentityResponse getIdentity(String accessToken, String userSecret) throws IOException {
        return this.d.getIdentity(accessToken, userSecret, "", null);
    }

    /**
     * getIdentity talks to the GetIdentity endpoint of Dapi, with this {@link DapiApp}'s appSecret,
     * to continue a previous operation that required to provide some userInputs.
     *
     * @param accessToken retrieved from the ExchangeToken process.
     * @param userSecret  retrieved from the user login.
     * @param operationID retrieved from the previous call's response.
     * @param userInputs  built from the previous call's response, and the required user input.
     * @return a {@link GetIdentityResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetIdentityResponse getIdentity(String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getIdentity(accessToken, userSecret, operationID, userInputs);
    }

    /**
     * getAccounts talks to the GetAccounts endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @param accessToken retrieved from the ExchangeToken process.
     * @param userSecret  retrieved from the user login.
     * @return an {@link GetAccountsResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetAccountsResponse getAccounts(String accessToken, String userSecret) throws IOException {
        return this.d.getAccounts(accessToken, userSecret, "", null);
    }

    /**
     * getAccounts talks to the GetAccounts endpoint of Dapi, with this {@link DapiApp}'s appSecret,
     * to continue a previous operation that required to provide some userInputs.
     *
     * @param accessToken retrieved from the ExchangeToken process.
     * @param userSecret  retrieved from the user login.
     * @param operationID retrieved from the previous call's response.
     * @param userInputs  built from the previous call's response, and the required user input.
     * @return an {@link GetAccountsResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetAccountsResponse getAccounts(String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getAccounts(accessToken, userSecret, operationID, userInputs);
    }

    /**
     * getBalance talks to the GetBalance endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @param accountID   the id of the account which this operation is about.
     * @param accessToken retrieved from the ExchangeToken process.
     * @param userSecret  retrieved from the user login.
     * @return an {@link GetBalanceResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetBalanceResponse getBalance(String accountID, String accessToken, String userSecret) throws IOException {
        return this.d.getBalance(accountID, accessToken, userSecret, "", null);
    }

    /**
     * getBalance talks to the GetBalance endpoint of Dapi, with this {@link DapiApp}'s appSecret,
     * to continue a previous operation that required to provide some userInputs.
     *
     * @param accountID   the id of the account which this operation is about.
     * @param accessToken retrieved from the ExchangeToken process.
     * @param userSecret  retrieved from the user login.
     * @param operationID retrieved from the previous call's response.
     * @param userInputs  built from the previous call's response, and the required user input.
     * @return an {@link GetBalanceResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetBalanceResponse getBalance(String accountID, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getBalance(accountID, accessToken, userSecret, operationID, userInputs);
    }

    /**
     * getTransactions talks to the GetTransactions endpoint of Dapi, with this {@link DapiApp}'s appSecret,
     * to continue a previous operation that required to provide some userInputs.
     *
     * @param accountID   the id of the account which this operation is about.
     * @param fromDate    the start date of the transactions we want.
     * @param toDate      the end date of the transactions we want.
     * @param accessToken retrieved from the ExchangeToken process.
     * @param userSecret  retrieved from the user login.
     * @return an {@link GetTransactionsResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetTransactionsResponse getTransactions(String accountID, LocalDate fromDate, LocalDate toDate, String accessToken, String userSecret) throws IOException {
        return this.d.getTransactions(accountID, fromDate, toDate, accessToken, userSecret, "", null);
    }

    /**
     * getTransactions talks to the GetTransactions endpoint of Dapi, with this {@link DapiApp}'s appSecret,
     * to continue a previous operation that required to provide some userInputs.
     *
     * @param accountID   the id of the account which this operation is about.
     * @param fromDate    the start date of the transactions we want.
     * @param toDate      the end date of the transactions we want.
     * @param accessToken retrieved from the ExchangeToken process.
     * @param userSecret  retrieved from the user login.
     * @param operationID retrieved from the previous call's response.
     * @param userInputs  built from the previous call's response, and the required user input.
     * @return an {@link GetTransactionsResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetTransactionsResponse getTransactions(String accountID, LocalDate fromDate, LocalDate toDate, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.d.getTransactions(accountID, fromDate, toDate, accessToken, userSecret, operationID, userInputs);
    }

    /**
     * createBeneficiary talks to the CreateBeneficiary endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @param accessToken retrieved from the ExchangeToken process.
     * @param userSecret  retrieved from the user login.
     * @return an {@link CreateBeneficiaryResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public CreateBeneficiaryResponse createBeneficiary(Payment.BeneficiaryInfo beneficiary, String accessToken, String userSecret) throws IOException {
        return this.p.createBeneficiary(beneficiary, accessToken, userSecret, "", null);
    }

    /**
     * createBeneficiary talks to the CreateBeneficiary endpoint of Dapi, with this {@link DapiApp}'s appSecret,
     * to continue a previous operation that required to provide some userInputs.
     *
     * @param accessToken retrieved from the ExchangeToken process.
     * @param userSecret  retrieved from the user login.
     * @param operationID retrieved from the previous call's response.
     * @param userInputs  built from the previous call's response, and the required user input.
     * @return an {@link CreateBeneficiaryResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public CreateBeneficiaryResponse createBeneficiary(Payment.BeneficiaryInfo beneficiary, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.createBeneficiary(beneficiary, accessToken, userSecret, operationID, userInputs);
    }

    /**
     * getBeneficiaries talks to the GetBeneficiaries endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @param accessToken retrieved from the ExchangeToken process.
     * @param userSecret  retrieved from the user login.
     * @return an {@link GetBeneficiariesResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetBeneficiariesResponse getBeneficiaries(String accessToken, String userSecret) throws IOException {
        return this.p.getBeneficiaries(accessToken, userSecret, "", null);
    }

    /**
     * getBeneficiaries talks to the GetBeneficiaries endpoint of Dapi, with this {@link DapiApp}'s appSecret,
     * to continue a previous operation that required to provide some userInputs.
     *
     * @param accessToken retrieved from the ExchangeToken process.
     * @param userSecret  retrieved from the user login.
     * @param operationID retrieved from the previous call's response.
     * @param userInputs  built from the previous call's response, and the required user input.
     * @return an {@link GetBeneficiariesResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetBeneficiariesResponse getBeneficiaries(String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.getBeneficiaries(accessToken, userSecret, operationID, userInputs);
    }

    /**
     * createTransfer talks to the CreateTransfer endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @param transfer    the transfer details that we want to initiate.
     * @param accessToken retrieved from the ExchangeToken process.
     * @param userSecret  retrieved from the user login.
     * @return an {@link CreateTransferResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public CreateTransferResponse createTransfer(Payment.Transfer transfer, String accessToken, String userSecret) throws IOException {
        return this.p.createTransfer(transfer, accessToken, userSecret, "", null);
    }

    /**
     * createTransfer talks to the CreateTransfer endpoint of Dapi, with this {@link DapiApp}'s appSecret,
     * to continue a previous operation that required to provide some userInputs.
     *
     * @param transfer    the transfer details that we want to initiate.
     * @param accessToken retrieved from the ExchangeToken process.
     * @param userSecret  retrieved from the user login.
     * @param operationID retrieved from the previous call's response.
     * @param userInputs  built from the previous call's response, and the required user input.
     * @return an {@link CreateTransferResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public CreateTransferResponse createTransfer(Payment.Transfer transfer, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.createTransfer(transfer, accessToken, userSecret, operationID, userInputs);
    }

    /**
     * transferAutoflow talks to the TransferAutoflow endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @param transferAutoflow the details required to create a TransferAutoflow operation.
     * @param accessToken      retrieved from the ExchangeToken process.
     * @param userSecret       retrieved from the user login.
     * @return an {@link TransferAutoflowResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public TransferAutoflowResponse transferAutoflow(Payment.TransferAutoflow transferAutoflow, String accessToken, String userSecret) throws IOException {
        return this.p.transferAutoflow(transferAutoflow, accessToken, userSecret, "", null);
    }

    /**
     * transferAutoflow talks to the TransferAutoflow endpoint of Dapi, with this {@link DapiApp}'s appSecret,
     * to continue a previous operation that required to provide some userInputs.
     *
     * @param transferAutoflow the details required to create a TransferAutoflow operation.
     * @param accessToken      retrieved from the ExchangeToken process.
     * @param userSecret       retrieved from the user login.
     * @param operationID      retrieved from the previous call's response.
     * @param userInputs       built from the previous call's response, and the required user input.
     * @return an {@link TransferAutoflowResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public TransferAutoflowResponse transferAutoflow(Payment.TransferAutoflow transferAutoflow, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException {
        return this.p.transferAutoflow(transferAutoflow, accessToken, userSecret, operationID, userInputs);
    }

    /**
     * getAccountsMetadata talks to the GetAccountsMetadata endpoint of Dapi, with this {@link DapiApp}'s appSecret.
     *
     * @param accessToken retrieved from the ExchangeToken process.
     * @param userSecret  retrieved from the user login.
     * @return an {@link GetAccountsMetadataResponse}.
     * @throws IOException in case of trouble happened while executing the request or reading the response.
     */
    public GetAccountsMetadataResponse getAccountsMetadata(String accessToken, String userSecret) throws IOException {
        return this.m.getAccountsMetadata(accessToken, userSecret, "", null);
    }

    /**
     * getAccountsMetadata talks to the GetAccountsMetadata endpoint of Dapi, with this {@link DapiApp}'s appSecret,
     * to continue a previous operation that required to provide some userInputs.
     *
     * @param accessToken retrieved from the ExchangeToken process.
     * @param userSecret  retrieved from the user login.
     * @param operationID retrieved from the previous call's response.
     * @param userInputs  built from the previous call's response, and the required user input.
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
     * @param bodyJson   the body of the request, in JSON format.
     * @param headersMap any headers that needs to be passed with the request.
     * @return an {@link Response} representing the HTTP response of this operation.
     * @throws IOException         in case of trouble happened while executing the request.
     * @throws JsonSyntaxException in case of trouble happened while reading the request body.
     */
    public Response handleSDKRequest(String bodyJson, HashMap<String, String> headersMap) throws IOException, JsonSyntaxException {
        HashMap bodyMap = DapiRequest.jsonAgent.fromJson(bodyJson, HashMap.class);

        // handling passing empty body string
        if (bodyMap == null) bodyMap = new HashMap();

        bodyMap.put("appSecret", this.config.getAppSecret());
        bodyJson = DapiRequest.jsonAgent.toJson(bodyMap);
        return DapiRequest.HandleSDK(bodyJson, headersMap);
    }

    /**
     * handleSDKRequest injects this {@link DapiApp}'s appSecret in the passed request body, bodyJson, and then
     * forwards the request to Dapi, and returns the RAW response got.
     *
     * @param bodyJson the body of the request, in JSON format.
     * @return an {@link Response} representing the HTTP response of this operation.
     * @throws IOException         in case of trouble happened while executing the request.
     * @throws JsonSyntaxException in case of trouble happened while reading the request body.
     */
    public Response handleSDKRequest(String bodyJson) throws IOException, JsonSyntaxException {
        return this.handleSDKRequest(bodyJson, new HashMap<>());
    }
}