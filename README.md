# dapi-java

A client library that talks to the [Dapi](https://dapi.co) [API](https://api.dapi.co).

## Quickstart

### Configure Project

1. First install the library from maven and add it to your project.

```xml
<dependency>
   <groupId>co.dapi</groupId>
   <artifactId>dapi-java</artifactId>
   <version>1.0.0</version>
</dependency>
```

2. Import Dapi's library in your code.

```java
import co.dapi.*;
import co.dapi.types.*;
import co.dapi.response.*;
```

### Configure Library

1. Create a Dapi app with your App Secret

```java
class MainTestClass {
    private DapiApp myApp;

    public MainTestClass() {
        var myAppConfig = new Config("YOUR_APP_SECRET");
        myApp = new DapiApp(myAppConfig);
    }
}
```

2. Now you can use any of the functions of the `DapiApp` instance, `myApp`, to call Dapi with your `appSecret`.

```java
class MainTestClass {
    public void TestFunc() {
        try {
            var resp = myApp.getAccounts("YOUR_ACCESS_TOKEN", "YOUR_USER_SECRET");
            // do something with the resp
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

3. Or, you can use it inside your endpoint. Our code will basically update the request to add your app's `appSecret`
   to it, and forward the request to Dapi, then return the result.

```java
class MainTestClass {
    public void HandlerFunc(String requestBodyJson, HashMap<String, String> requestHeaders) {
        Response resp = null;
        try {
            resp = myApp.handleSDKRequest(requestBodyJson, requestHeaders);
            //resp = myApp.handleSDKRequest(requestBodyJson); // or with no headers
        } catch (Exception e) {
            e.printStackTrace();
        }
        // do something with the resp
    }
}
```

### Complete Example

You need to replace the placeholders in this code snippet(`appSecret`, `accessToken`, `userSecret`) with your own
values, and to handle the response got.

```java
import co.dapi.*;
import co.dapi.types.*;
import co.dapi.response.*;
import okhttp3.Response;

import java.util.HashMap;

public class MainTestClass {
    private DapiApp myApp;

    public MainTestClass() {
        var myAppConfig = new Config("YOUR_APP_SECRET");
        myApp = new DapiApp(myAppConfig);
    }

    public void TestFunc() {
        try {
            var resp = myApp.getAccounts("YOUR_ACCESS_TOKEN", "YOUR_USER_SECRET");
            // do something with the resp
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void HandlerFunc(String requestBodyJson, HashMap<String, String> requestHeaders) {
        Response resp = null;
        try {
            resp = myApp.handleSDKRequest(requestBodyJson, requestHeaders);
            //resp = myApp.handleSDKRequest(requestBodyJson); // or with no headers
        } catch (Exception e) {
            e.printStackTrace();
        }
        // do something with the resp
    }

    public static void main(String[] args) {
        var test = new MainTestClass();
        test.TestFunc();
    }
}
```

## Reference


### BaseResponse

All the responses extend BaseResponse class. Meaning all the responses described below in the document will have
following fields besides the ones specific to each response

| Parameter | Type | Description |
|---|---|---|
| operationID | `String` | Unique ID generated to identify a specific operation. |
| success | `Boolean` | Returns true if request is successful and false otherwise." |
| status | `APIStatus` (Enum) | The status of the job. <br><br> `done` - Operation Completed. <br> `failed` - Operation Failed. <br> `user_input_required` - Pending User Input. <br> `initialized` - Operation In Progress. <br><br> For further explanation see [Operation Statuses](https://dapi-api.readme.io/docs/operation-statuses). |
| userInputs | `UserInput[]` | Specifies the type of further information required from the user before the job can be completed. <br><br> Note: It's only returned if operation status is `user_input_required` |
| type | `String` | Type of error encountered. <br><br> Note: It's only returned if operation status is `failed` |
| msg | `String` | Detailed description of the error. <br><br> Note: It's only returned if operation status is `failed` |

#### UserInput Object

| Parameter | Type | Description |
|---|---|---|
| id | `UserInputID` (Enum) | Type of input required. <br><br> You can read more about user input types on [User Input Types](https://dapi-api.readme.io/docs/user-input-types). |
| query | `String` | Textual description of what is required from the user side. |
| index | `int` | Is used in case more than one user input is requested. <br> Will always be 0 If only one input is requested. |
| answer | `String` | User input that must be submitted. In the response it will always be empty. |


### Methods


#### DapiApp.exchangeToken

Method is used to obtain user's permanent access token by exchanging it with access code received during the user
authentication (user login).

##### Note:

You can read more about how to obtain a permanent token
on [Obtain an Access Token](https://dapi-api.readme.io/docs/get-an-access-token).

##### Method Description

```java
public ExchangeTokenResponse exchangeToken(String accessCode, String connectionID) throws IOException
```

##### Input Parameters

| Parameter | Type | Description |
|---|---|---|
| **accessCode** <br> _REQUIRED_ | `String` | Unique code for a user’s successful login to **Connect**. Returned in the response of **UserLogin**. |
| **connectionID** <br> _REQUIRED_ | `String` | The `connectionID` from a user’s successful log in to **Connect**. |

##### Response

In addition to the fields described in the BaseResponse, it has the following fields, which will only be returned if the status is `done`:

| Parameter | Type | Description |
|---|---|---|
| **accessToken** | `String` | A unique permanent token linked to the user. |

---

#### DapiApp.getIdentity

Method is used to retrieve personal details about the user.

##### Method Description

```java
public GetIdentityResponse getIdentity(String accessToken, String userSecret) throws IOException

public GetIdentityResponse getIdentity(String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException
```

##### Input Parameters

| Parameter | Type | Description |
|---|---|---|
| **accessToken** <br> _REQUIRED_ | `String` | Access Token obtained using the `exchangeToken` method. |
| **userSecret** <br> _REQUIRED_ | `String` | The `userSecret` from a user’s successful log in to **Connect**. |
| **operationID** <br> _OPTIONAL_ | `String` | The `operationID` from a previous call's response. <br> Required only when resuming a previous call that responded with `user_input_required` status, to provided user inputs. |
| **userInputs** <br> _OPTIONAL_ | `UserInput[]` | Array of `UserInput` object, that are needed to complete this operation. <br> Required only if a previous call responded with `user_input_required` status. <br><br> You can read more about user inputs specification on [Specify User Input](https://dapi-api.readme.io/docs/specify-user-input) |


###### UserInput Object

| Parameter | Type | Description |
|---|---|---|
| id | `UserInputID` (Enum) | Type of input required. <br><br> You can read more about user input types on [User Input Types](https://dapi-api.readme.io/docs/user-input-types). |
| index | `int` | Is used in case more than one user input is requested. <br> Will always be 0 If only one input is requested. |
| answer | `String` | User input that must be submitted. |

##### Response

In addition to the fields described in the BaseResponse, it has the following fields, which will only be returned if the status is `done`:

| Parameter | Type | Description |
|---|---|---|
| identity | `Identity` | An object containing the identity data of the user. |

---

#### DapiApp.getAccounts

Method is used to retrieve list of all the bank accounts registered on the user. The list will contain all types of bank accounts.

##### Method Description

```java
public GetAccountsResponse getAccounts(String accessToken, String userSecret) throws IOException

public GetAccountsResponse getAccounts(String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException
```

##### Input Parameters

| Parameter | Type | Description |
|---|---|---|
| **accessToken** <br> _REQUIRED_ | `String` | Access Token obtained using the `exchangeToken` method. |
| **userSecret** <br> _REQUIRED_ | `String` | The `userSecret` from a user’s successful log in to **Connect**. |
| **operationID** <br> _OPTIONAL_ | `String` | The `operationID` from a previous call's response. <br> Required only when resuming a previous call that responded with `user_input_required` status, to provided user inputs. |
| **userInputs** <br> _OPTIONAL_ | `UserInput[]` | Array of `UserInput` object, that are needed to complete this operation. <br> Required only if a previous call responded with `user_input_required` status. <br><br> You can read more about user inputs specification on [Specify User Input](https://dapi-api.readme.io/docs/specify-user-input) |


###### UserInput Object

| Parameter | Type | Description |
|---|---|---|
| id | `UserInputID` (Enum) | Type of input required. <br><br> You can read more about user input types on [User Input Types](https://dapi-api.readme.io/docs/user-input-types). |
| index | `int` | Is used in case more than one user input is requested. <br> Will always be 0 If only one input is requested. |
| answer | `String` | User input that must be submitted. |

##### Response

In addition to the fields described in the BaseResponse, it has the following fields, which will only be returned if the status is `done`:

| Parameter | Type | Description |
|---|---|---|
| accounts | `Account[]` | An array containing the accounts data of the user. |

---

#### DapiApp.getBalance

Method is used to retrieve balance on specific bank account of the user.

##### Method Description

```java
public GetBalanceResponse getBalance(String accountID, String accessToken, String userSecret) throws IOException

public GetBalanceResponse getBalance(String accountID, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException
```

##### Input Parameters

| Parameter | Type | Description |
|---|---|---|
| **accountID** <br> _REQUIRED_ | `String` | The bank account ID which its balance is requested. <br> Retrieved from one of the accounts returned from the `getAccounts` method. |
| **accessToken** <br> _REQUIRED_ | `String` | Access Token obtained using the `exchangeToken` method. |
| **userSecret** <br> _REQUIRED_ | `String` | The `userSecret` from a user’s successful log in to **Connect**. |
| **operationID** <br> _OPTIONAL_ | `String` | The `operationID` from a previous call's response. <br> Required only when resuming a previous call that responded with `user_input_required` status, to provided user inputs. |
| **userInputs** <br> _OPTIONAL_ | `UserInput[]` | Array of `UserInput` object, that are needed to complete this operation. <br> Required only if a previous call responded with `user_input_required` status. <br><br> You can read more about user inputs specification on [Specify User Input](https://dapi-api.readme.io/docs/specify-user-input) |


###### UserInput Object

| Parameter | Type | Description |
|---|---|---|
| id | `UserInputID` (Enum) | Type of input required. <br><br> You can read more about user input types on [User Input Types](https://dapi-api.readme.io/docs/user-input-types). |
| index | `int` | Is used in case more than one user input is requested. <br> Will always be 0 If only one input is requested. |
| answer | `String` | User input that must be submitted. |

##### Response

In addition to the fields described in the BaseResponse, it has the following fields, which will only be valid if the status is `done`:

| Parameter | Type | Description |
|---|---|---|
| balance | `Balance` | An object containing the account's balance information. |

---

#### DapiApp.getTransactions

Method is used to retrieve transactions that user has performed over a specific period of time from their bank account. The transaction list is unfiltered, meaning the response will contain all the transactions performed by the user (not just the transactions performed using your app).

Date range of the transactions that can be retrieved varies for each bank. The range supported by the users bank is shown in the response parameter `transactionRange` of Get Accounts Metadata endpoint.

##### Method Description

```java
public GetTransactionsResponse getTransactions(String accountID, LocalDate fromDate, LocalDate toDate, String accessToken, String userSecret) throws IOException

public GetTransactionsResponse getTransactions(String accountID, LocalDate fromDate, LocalDate toDate, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException
```

##### Input Parameters

| Parameter | Type | Description |
|---|---|---|
| **accountID** <br> _REQUIRED_ | `String` | The bank account ID which its transactions are requested. <br> Retrieved from one of the accounts returned from the `getAccounts` method. |
| **fromDate** <br> _REQUIRED_ | `LocalDate` | The start date of the transactions wanted. |
| **toDate** <br> _REQUIRED_ | `LocalDate` | The end date of the transactions wanted. |
| **accessToken** <br> _REQUIRED_ | `String` | Access Token obtained using the `exchangeToken` method. |
| **userSecret** <br> _REQUIRED_ | `String` | The `userSecret` from a user’s successful log in to **Connect**. |
| **operationID** <br> _OPTIONAL_ | `String` | The `operationID` from a previous call's response. <br> Required only when resuming a previous call that responded with `user_input_required` status, to provided user inputs. |
| **userInputs** <br> _OPTIONAL_ | `UserInput[]` | Array of `UserInput` object, that are needed to complete this operation. <br> Required only if a previous call responded with `user_input_required` status. <br><br> You can read more about user inputs specification on [Specify User Input](https://dapi-api.readme.io/docs/specify-user-input) |


###### UserInput Object

| Parameter | Type | Description |
|---|---|---|
| id | `UserInputID` (Enum) | Type of input required. <br><br> You can read more about user input types on [User Input Types](https://dapi-api.readme.io/docs/user-input-types). |
| index | `int` | Is used in case more than one user input is requested. <br> Will always be 0 If only one input is requested. |
| answer | `String` | User input that must be submitted. |

##### Response

In addition to the fields described in the BaseResponse, it has the following fields, which will only be valid if the status is `done`:

| Parameter | Type | Description |
|---|---|---|
| transactions | `Transaction[]` | Array containing the transactional data for the specified account within the specified period. |

---

#### DapiApp.getBeneficiaries

Method is used to retrieve list of all the beneficiaries already added for a user within a financial institution.

##### Method Description

```java
public GetBeneficiariesResponse getBeneficiaries(String accessToken, String userSecret) throws IOException

public GetBeneficiariesResponse getBeneficiaries(String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException
```

##### Input Parameters

| Parameter | Type | Description |
|---|---|---|
| **accessToken** <br> _REQUIRED_ | `String` | Access Token obtained using the `exchangeToken` method. |
| **userSecret** <br> _REQUIRED_ | `String` | The `userSecret` from a user’s successful log in to **Connect**. |
| **operationID** <br> _OPTIONAL_ | `String` | The `operationID` from a previous call's response. <br> Required only when resuming a previous call that responded with `user_input_required` status, to provided user inputs. |
| **userInputs** <br> _OPTIONAL_ | `UserInput[]` | Array of `UserInput` object, that are needed to complete this operation. <br> Required only if a previous call responded with `user_input_required` status. <br><br> You can read more about user inputs specification on [Specify User Input](https://dapi-api.readme.io/docs/specify-user-input) |


###### UserInput Object

| Parameter | Type | Description |
|---|---|---|
| id | `UserInputID` (Enum) | Type of input required. <br><br> You can read more about user input types on [User Input Types](https://dapi-api.readme.io/docs/user-input-types). |
| index | `int` | Is used in case more than one user input is requested. <br> Will always be 0 If only one input is requested. |
| answer | `String` | User input that must be submitted. |

##### Response

In addition to the fields described in the BaseResponse, it has the following fields, which will only be returned if the status is `done`:

| Parameter | Type | Description |
|---|---|---|
| beneficiaries | `Beneficiary[]` | An array containing the beneficiary information. |

---

#### DapiApp.createBeneficiary

Method is used to retrieve list of all the beneficiaries already added for a user within a financial institution.

##### Method Description

```java
public CreateBeneficiaryResponse createBeneficiary(Payment.BeneficiaryInfo beneficiary, String accessToken, String userSecret) throws IOException

public CreateBeneficiaryResponse createBeneficiary(Payment.BeneficiaryInfo beneficiary, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException
```

##### Input Parameters

| Parameter | Type | Description |
|---|---|---|
| **beneficiary** <br> _REQUIRED_ | `Payment.BeneficiaryInfo` | An object that contains info about the beneficiary that should be added. |
| **accessToken** <br> _REQUIRED_ | `String` | Access Token obtained using the `exchangeToken` method. |
| **userSecret** <br> _REQUIRED_ | `String` | The `userSecret` from a user’s successful log in to **Connect**. |
| **operationID** <br> _OPTIONAL_ | `String` | The `operationID` from a previous call's response. <br> Required only when resuming a previous call that responded with `user_input_required` status, to provided user inputs. |
| **userInputs** <br> _OPTIONAL_ | `UserInput[]` | Array of `UserInput` object, that are needed to complete this operation. <br> Required only if a previous call responded with `user_input_required` status. <br><br> You can read more about user inputs specification on [Specify User Input](https://dapi-api.readme.io/docs/specify-user-input) |


###### UserInput Object

| Parameter | Type | Description |
|---|---|---|
| id | `UserInputID` (Enum) | Type of input required. <br><br> You can read more about user input types on [User Input Types](https://dapi-api.readme.io/docs/user-input-types). |
| index | `int` | Is used in case more than one user input is requested. <br> Will always be 0 If only one input is requested. |
| answer | `String` | User input that must be submitted. |

##### Response

Method returns only the fields defined in the BaseResponse.

---

#### DapiApp.createTransfer

Method is used to initiate a new payment from one account to another account.

##### Important

We suggest you use `transferAutoflow` method instead to initiate a payment. `transferAutoflow` abstracts all the validations and processing logic, required to initiate a transaction using `createTransfer` method.

You can read about `transferAutoFlow` further in the document.

##### Method Description

```java
public CreateTransferResponse createTransfer(Payment.Transfer transfer, String accessToken, String userSecret) throws IOException

public CreateTransferResponse createTransfer(Payment.Transfer transfer, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException
```

##### Input Parameters

| Parameter | Type | Description |
|---|---|---|
| **transfer** <br> _REQUIRED_ | `Payment.Transfer` | An object that contains info about the transfer that should be initiated. |
| **accessToken** <br> _REQUIRED_ | `String` | Access Token obtained using the `exchangeToken` method. |
| **userSecret** <br> _REQUIRED_ | `String` | The `userSecret` from a user’s successful log in to **Connect**. |
| **operationID** <br> _OPTIONAL_ | `String` | The `operationID` from a previous call's response. <br> Required only when resuming a previous call that responded with `user_input_required` status, to provided user inputs. |
| **userInputs** <br> _OPTIONAL_ | `UserInput[]` | Array of `UserInput` object, that are needed to complete this operation. <br> Required only if a previous call responded with `user_input_required` status. <br><br> You can read more about user inputs specification on [Specify User Input](https://dapi-api.readme.io/docs/specify-user-input) |


###### UserInput Object

| Parameter | Type | Description |
|---|---|---|
| id | `UserInputID` (Enum) | Type of input required. <br><br> You can read more about user input types on [User Input Types](https://dapi-api.readme.io/docs/user-input-types). |
| index | `int` | Is used in case more than one user input is requested. <br> Will always be 0 If only one input is requested. |
| answer | `String` | User input that must be submitted. |

##### Response

In addition to the fields described in the BaseResponse, it has the following fields, which will only be returned if the status is `done`:

| Parameter | Type | Description |
|---|---|---|
| reference | `String` | Transaction reference string returned by the bank. |

---

#### DapiApp.transferAutoflow

Method is used to initiate a new payment from one account to another account, without having to care nor handle any special cases or scenarios.

##### Method Description

```java
public TransferAutoflowResponse transferAutoflow(Payment.TransferAutoflow transferAutoflow, String accessToken, String userSecret) throws IOException

public TransferAutoflowResponse transferAutoflow(Payment.TransferAutoflow transferAutoflow, String accessToken, String userSecret, String operationID, UserInput[] userInputs) throws IOException
```

##### Input Parameters

| Parameter | Type | Description |
|---|---|---|
| **transfer** <br> _REQUIRED_ | `Payment.TransferAutoflow` | An object that contains info about the transfer that should be initiated, and any other details that's used to automate the operation. |
| **accessToken** <br> _REQUIRED_ | `String` | Access Token obtained using the `exchangeToken` method. |
| **userSecret** <br> _REQUIRED_ | `String` | The `userSecret` from a user’s successful log in to **Connect**. |
| **operationID** <br> _OPTIONAL_ | `String` | The `operationID` from a previous call's response. <br> Required only when resuming a previous call that responded with `user_input_required` status, to provided user inputs. |
| **userInputs** <br> _OPTIONAL_ | `UserInput[]` | Array of `UserInput` object, that are needed to complete this operation. <br> Required only if a previous call responded with `user_input_required` status. <br><br> You can read more about user inputs specification on [Specify User Input](https://dapi-api.readme.io/docs/specify-user-input) |


###### UserInput Object

| Parameter | Type | Description |
|---|---|---|
| id | `UserInputID` (Enum) | Type of input required. <br><br> You can read more about user input types on [User Input Types](https://dapi-api.readme.io/docs/user-input-types). |
| index | `int` | Is used in case more than one user input is requested. <br> Will always be 0 If only one input is requested. |
| answer | `String` | User input that must be submitted. |

##### Response

In addition to the fields described in the BaseResponse, it has the following fields, which will only be returned if the status is `done`:

| Parameter | Type | Description |
|---|---|---|
| reference | `String` | Transaction reference string returned by the bank. |

---
