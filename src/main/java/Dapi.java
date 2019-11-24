import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;




public class Dapi
{
    private String appKey, appSecret, connectionID, accessCode, userSecret, accessToken;
    private String params;
    private boolean authenticated =false;



    public Dapi(String appKey, String appSecret, String accessCode, String connectionID, String userSecret)
    {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.accessCode = accessCode;
        this.connectionID = connectionID;
        this.userSecret = userSecret;
    }


    public String getAccessToken() throws Exception
    {
        if (authenticated)
            return accessToken;
        throw new Exception("Authentication required");
    }

    String post(String service) throws Exception {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, params);
        Request request;
        if (service.equals("auth/ExchangeToken"))
        {
            request = new Request.Builder()
                    .url("http://localhost:443/v1/" + service)
                    .post(body)
                    .build();
        } else
        {
            request = new Request.Builder()
                    .url("http://localhost:443/v1/" + service)
                    .post(body)
                    .header("Authorization", accessToken)
                    .build();
        }
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            JsonObject responseJson = new Gson().fromJson(responseBody, JsonObject.class);

            if (response.code() == 200)
            {
                if (service.equals("auth/ExchangeToken"))
                {
                    return responseJson.get("accessToken").getAsString();
                }
                return responseBody;
            }
            else
            {
                throw new Exception(responseJson.get("msg").getAsString());
            }
        }
    }

    private String addAddresses(String paramsTemp, String[] addresses)
    {
        paramsTemp += "\"addresses\":[";
        for (int i=0;i<addresses.length-1;i++)
            paramsTemp+="\""+addresses[i]+"\",";
        paramsTemp+="\""+addresses[addresses.length-1]+"\"],";
        return paramsTemp;
    }

    private boolean isValidFormat(String format, String value, Locale locale) throws Exception
    {
        LocalDateTime ldt = null;
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);
        try {
            ldt = LocalDateTime.parse(value, fomatter);
            String result = ldt.format(fomatter);
            return result.equals(value);
        } catch (DateTimeParseException e) {
            try {
                LocalDate ld = LocalDate.parse(value, fomatter);
                String result = ld.format(fomatter);
                return result.equals(value);
            } catch (DateTimeParseException exp) {
                try {
                    LocalTime lt = LocalTime.parse(value, fomatter);
                    String result = lt.format(fomatter);
                    return result.equals(value);
                } catch (DateTimeParseException e2) {
                }
            }
        }
        return false;
    }
    //ExchangeToken-------------------------------------------------------------------------------------------------
    public String exchangeToken() throws Exception
    {
        params = "{\"connectionID\":\"" + connectionID + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\",\r\n" +
                "\"accessCode\":\"" + accessCode + "\"\n}";
        accessToken = post("auth/ExchangeToken");
        authenticated = true;
        return accessToken;
    }
    //Identity-----------------------------------------------------------------------------------------------------
    public Identity identity() throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Identity(post("data/Identity"), false);
    }
    public Identity identity(boolean sync) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"sync\":"+sync+","+
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Identity(post("data/Identity"), sync);
    }
    public Identity identity(String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n";
        params = addAddresses(params, addresses);
        params+= "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Identity(post("data/Identity"), false);
    }
    public Identity identity(boolean sync, String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n";
        params = addAddresses(params, addresses);
        params+="\"sync\":"+sync+","+
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Identity(post("data/Identity"), sync);
    }
    //userAccounts---------------------------------------------------------------------------------------------------
    public UserAccounts userAccounts() throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new UserAccounts(post("data/UserAccounts"),false);
    }
    public UserAccounts userAccounts(boolean sync) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n" +
                "\"sync\":"+sync+"," +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new UserAccounts(post("data/UserAccounts"), sync);
    }
    public UserAccounts userAccounts(String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n";
        params = addAddresses(params, addresses);
        params+= "\"appSecret\":\"" + appSecret + "\"\n}";
        return new UserAccounts(post("data/UserAccounts"),false);
    }
    public UserAccounts userAccounts(boolean sync, String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n";
        params = addAddresses(params, addresses);
        params+="\"sync\":"+sync+","+
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new UserAccounts(post("data/UserAccounts"), sync);
    }
    //AccountBalance-------------------------------------------------------------------------------------------------
    public Balance accountBalance(String IBAN) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n" +
                "\"iban\":\"" + IBAN + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Balance(post("data/AccountBalance"), false);
    }
    public Balance accountBalance(String IBAN, boolean sync) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n" +
                "\"iban\":\"" + IBAN + "\",\r\n" +
                "\"sync\":"+sync+"," +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Balance(post("data/AccountBalance"), sync);
    }
    public Balance accountBalance(String IBAN, String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n" +
                "\"iban\":\"" + IBAN + "\",\r\n";
        params=addAddresses(params, addresses);
        params+="\"appSecret\":\"" + appSecret + "\"\n}";
        return new Balance(post("data/AccountBalance"), false);
    }
    public Balance accountBalance(String IBAN,boolean sync, String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n" +
                "\"iban\":\"" + IBAN + "\",\r\n";
        params =addAddresses(params, addresses);
        params+="\"sync\":"+sync+","+
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Balance(post("data/AccountBalance"), sync);
    }
    //Transactions---------------------------------------------------------------------------------------------------
    public Transactions transactions(String IBAN) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n" +
                "\"iban\":\"" + IBAN + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Transactions(post("data/Transactions"), false);
    }
    public Transactions transactions(String IBAN, boolean sync) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n" +
                "\"iban\":\"" + IBAN + "\",\r\n" +
                "\"sync\":"+sync+"," +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Transactions(post("data/Transactions"), sync);
    }
    public Transactions transactions(String IBAN, String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n" +
                "\"iban\":\"" + IBAN + "\",\r\n";
        params=addAddresses(params, addresses);
        params+="\"appSecret\":\"" + appSecret + "\"\n}";
        return new Transactions(post("data/Transactions"), false);
    }
    public Transactions transactions(String IBAN,boolean sync, String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n" +
                "\"iban\":\"" + IBAN + "\",\r\n";
        params =addAddresses(params, addresses);
        params+="\"sync\":"+sync+","+
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Transactions(post("data/Transactions"), sync);
    }
    public Transactions transactions(String IBAN, String fromDate, String toDate) throws Exception
    {
        if (!isValidFormat("yyyy-MM-dd", fromDate, Locale.ENGLISH))
            throw new Exception("fromDate format incorrect");
        if (!isValidFormat("yyyy-MM-dd", toDate, Locale.ENGLISH))
            throw new Exception("toDate format incorrect");
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n" +
                "\"iban\":\"" + IBAN + "\",\r\n" +
                "\"fromDate\":\"" + fromDate + "\",\r\n" +
                "\"toDate\":\"" + toDate + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Transactions(post("data/Transactions"), false);
    }
    public Transactions transactions(String IBAN, boolean sync, String fromDate, String toDate) throws Exception
    {
        if (!isValidFormat("yyyy-MM-dd", fromDate, Locale.ENGLISH))
            throw new Exception("fromDate format incorrect");
        if (!isValidFormat("yyyy-MM-dd", toDate, Locale.ENGLISH))
            throw new Exception("toDate format incorrect");
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n" +
                "\"iban\":\"" + IBAN + "\",\r\n" +
                "\"fromDate\":\"" + fromDate + "\",\r\n" +
                "\"toDate\":\"" + toDate + "\",\r\n" +
                "\"sync\":"+sync+"," +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Transactions(post("data/Transactions"), sync);
    }
    public Transactions transactions(String IBAN, String[] addresses, String fromDate, String toDate) throws Exception
    {
        if (!isValidFormat("yyyy-MM-dd", fromDate, Locale.ENGLISH))
            throw new Exception("fromDate format incorrect");
        if (!isValidFormat("yyyy-MM-dd", toDate, Locale.ENGLISH))
            throw new Exception("toDate format incorrect");
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n" +
                "\"fromDate\":\"" + fromDate + "\",\r\n" +
                "\"toDate\":\"" + toDate + "\",\r\n" +
                "\"iban\":\"" + IBAN + "\",\r\n";
        params=addAddresses(params, addresses);
        params+="\"appSecret\":\"" + appSecret + "\"\n}";
        return new Transactions(post("data/Transactions"), false);
    }
    public Transactions transactions(String IBAN, boolean sync, String[] addresses, String fromDate, String toDate) throws Exception
    {
        if (!isValidFormat("yyyy-MM-dd", fromDate, Locale.ENGLISH))
            throw new Exception("fromDate format incorrect");
        if (!isValidFormat("yyyy-MM-dd", toDate, Locale.ENGLISH))
            throw new Exception("toDate format incorrect");
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n" +
                "\"sync\":"+sync+"," +
                "\"fromDate\":\"" + fromDate + "\",\r\n" +
                "\"toDate\":\"" + toDate + "\",\r\n" +
                "\"iban\":\"" + IBAN + "\",\r\n";
        params=addAddresses(params, addresses);
        params+="\"appSecret\":\"" + appSecret + "\"\n}";
        return new Transactions(post("data/Transactions"), sync);
    }
    //GetBeneficiaries--------------------------------------------------------------------------------------------------
    public Beneficiaries getBeneficiaries(String type) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"type\":\"" + type + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Beneficiaries(post("payment/Beneficiaries"), false);
    }
    public Beneficiaries getBeneficiaries(String type, boolean sync) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"sync\":"+sync+"," +
                "\"type\":\"" + type + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Beneficiaries(post("payment/Beneficiaries"), sync);
    }
    public Beneficiaries getBeneficiaries(String type, String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"type\":\"" + type + "\",\r\n";
        params = addAddresses(params, addresses);
        params+= "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Beneficiaries(post("payment/Beneficiaries"), false);
    }
    public Beneficiaries getBeneficiaries(String type, boolean sync, String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"sync\":"+sync+"," +
                "\"type\":\"" + type + "\",\r\n";
        params = addAddresses(params, addresses);
        params+= "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Beneficiaries(post("payment/Beneficiaries"), sync);
    }
    //AddBeneficiary--------------------------------------------------------------------------------------------------
    public AddBeneficiary addBeneficiary(String name, String beneficiaryAccountNumber, String type) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"type\":\"" + type + "\",\r\n" +
                "\"name\":\"" + name + "\",\r\n" +
                "\"beneficiaryAccountNumber\":\"" + beneficiaryAccountNumber + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new AddBeneficiary(post("payment/AddBeneficiary"), false);
    }
    public AddBeneficiary addBeneficiary(String name, String beneficiaryAccountNumber, String type, boolean sync) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"sync\":"+sync+"," +
                "\"type\":\"" + type + "\",\r\n" +
                "\"name\":\"" + name + "\",\r\n" +
                "\"beneficiaryAccountNumber\":\"" + beneficiaryAccountNumber + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new AddBeneficiary(post("payment/AddBeneficiary"), sync);
    }
    public AddBeneficiary addBeneficiary(String name, String beneficiaryAccountNumber, String type, String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"type\":\"" + type + "\",\r\n" +
                "\"name\":\"" + name + "\",\r\n" +
                "\"beneficiaryAccountNumber\":\"" + beneficiaryAccountNumber + "\",\r\n";
        params = addAddresses(params, addresses);
        params+= "\"appSecret\":\"" + appSecret + "\"\n}";
        return new AddBeneficiary(post("payment/AddBeneficiary"), false);
    }
    public AddBeneficiary addBeneficiary(String name, String beneficiaryAccountNumber, String type, boolean sync, String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"sync\":"+sync+"," +
                "\"type\":\"" + type + "\",\r\n" +
                "\"name\":\"" + name + "\",\r\n" +
                "\"beneficiaryAccountNumber\":\"" + beneficiaryAccountNumber + "\",\r\n";
        params = addAddresses(params, addresses);
        params+= "\"appSecret\":\"" + appSecret + "\"\n}";
        return new AddBeneficiary(post("payment/AddBeneficiary"), sync);
    }
    public AddBeneficiary addBeneficiary(String name, String beneficiaryAccountNumber, String type, String swiftCode, String bankName) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"type\":\"" + type + "\",\r\n" +
                "\"name\":\"" + name + "\",\r\n" +
                "\"swiftCode\":\"" + swiftCode + "\",\r\n" +
                "\"bankName\":\"" + bankName + "\",\r\n" +
                "\"beneficiaryAccountNumber\":\"" + beneficiaryAccountNumber + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new AddBeneficiary(post("payment/AddBeneficiary"), false);
    }
    public AddBeneficiary addBeneficiary(String name, String beneficiaryAccountNumber, String type, boolean sync, String swiftCode, String bankName) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"sync\":"+sync+"," +
                "\"type\":\"" + type + "\",\r\n" +
                "\"name\":\"" + name + "\",\r\n" +
                "\"swiftCode\":\"" + swiftCode + "\",\r\n" +
                "\"bankName\":\"" + bankName + "\",\r\n" +
                "\"beneficiaryAccountNumber\":\"" + beneficiaryAccountNumber + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new AddBeneficiary(post("payment/AddBeneficiary"), sync);
    }
    public AddBeneficiary addBeneficiary(String name, String beneficiaryAccountNumber, String type, String[] addresses, String swiftCode, String bankName) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"type\":\"" + type + "\",\r\n" +
                "\"name\":\"" + name + "\",\r\n" +
                "\"swiftCode\":\"" + swiftCode + "\",\r\n" +
                "\"bankName\":\"" + bankName + "\",\r\n" +
                "\"beneficiaryAccountNumber\":\"" + beneficiaryAccountNumber + "\",\r\n";
        params = addAddresses(params, addresses);
        params+= "\"appSecret\":\"" + appSecret + "\"\n}";
        return new AddBeneficiary(post("payment/AddBeneficiary"), false);
    }
    public AddBeneficiary addBeneficiary(String name, String beneficiaryAccountNumber, String type, boolean sync, String[] addresses, String swiftCode, String bankName) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"sync\":"+sync+"," +
                "\"type\":\"" + type + "\",\r\n" +
                "\"name\":\"" + name + "\",\r\n" +
                "\"swiftCode\":\"" + swiftCode + "\",\r\n" +
                "\"bankName\":\"" + bankName + "\",\r\n" +
                "\"beneficiaryAccountNumber\":\"" + beneficiaryAccountNumber + "\",\r\n";
        params = addAddresses(params, addresses);
        params+= "\"appSecret\":\"" + appSecret + "\"\n}";
        return new AddBeneficiary(post("payment/AddBeneficiary"), sync);
    }
    //AddBeneficiaryResumption------------------------------------------------------------------------------------------
    public AddBeneficiary resumeAddBeneficiary(String otp, String jobID) throws Exception
    {
        params ="{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"otp\":\"" + otp + "\",\r\n" +
                "\"jobID\":\"" + jobID + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        //return post("payment/AddBeneficiary");
        return new AddBeneficiary(post("payment/AddBeneficiary"), false);
    }
    public AddBeneficiary resumeAddBeneficiary(String otp, String jobID, boolean sync) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"otp\":\"" + otp + "\",\r\n" +
                "\"sync\":"+sync+"," +
                "\"jobID\":\"" + jobID + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new AddBeneficiary(post("payment/AddBeneficiary"), sync);
    }
    public AddBeneficiary resumeAddBeneficiary(String otp, String jobID, String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"otp\":\"" + otp + "\",\r\n" +
                "\"jobID\":\"" + jobID + "\",\r\n";
        params = addAddresses(params, addresses);
        params+= "\"appSecret\":\"" + appSecret + "\"\n}";
        return new AddBeneficiary(post("payment/AddBeneficiary"), false);
    }
    public AddBeneficiary resumeAddBeneficiary(String otp, String jobID, boolean sync, String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"otp\":\"" + otp + "\",\r\n" +
                "\"sync\":"+sync+"," +
                "\"jobID\":\"" + jobID + "\",\r\n";
        params = addAddresses(params, addresses);
        params+= "\"appSecret\":\"" + appSecret + "\"\n}";
        return new AddBeneficiary(post("payment/AddBeneficiary"), sync);
    }
    //PaymentInitiation------------------------------------------------------------------------------------------------
    public Payment paymentInitiation (String type, String beneficiaryAccountNumber, double amount, String source) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"type\":\"" + type + "\",\r\n" +
                "\"beneficiaryAccountNumber\":\"" + beneficiaryAccountNumber + "\",\r\n" +
                "\"amount\":\"" + amount + "\",\r\n" +
                "\"source\":\"" + source + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Payment(post("payment/Payment"), false);
    }
    public Payment paymentInitiation (String type, String beneficiaryAccountNumber, double amount, String source, boolean sync) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"sync\":"+sync+"," +
                "\"type\":\"" + type + "\",\r\n" +
                "\"beneficiaryAccountNumber\":\"" + beneficiaryAccountNumber + "\",\r\n" +
                "\"amount\":\"" + amount + "\",\r\n" +
                "\"source\":\"" + source + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Payment(post("payment/Payment"), sync);
    }
    public Payment paymentInitiation (String type, String beneficiaryAccountNumber, double amount, String source, String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"type\":\"" + type + "\",\r\n" +
                "\"beneficiaryAccountNumber\":\"" + beneficiaryAccountNumber + "\",\r\n" +
                "\"amount\":\"" + amount + "\",\r\n" +
                "\"source\":\"" + source + "\",\r\n" ;
        params = addAddresses(params, addresses);
        params+= "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Payment(post("payment/Payment"), false);
    }
    public Payment paymentInitiation (String type, String beneficiaryAccountNumber, double amount, String source, boolean sync, String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"sync\":"+sync+"," +
                "\"type\":\"" + type + "\",\r\n" +
                "\"beneficiaryAccountNumber\":\"" + beneficiaryAccountNumber + "\",\r\n" +
                "\"amount\":\"" + amount + "\",\r\n" +
                "\"source\":\"" + source + "\",\r\n" ;
        params = addAddresses(params, addresses);
        params+= "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Payment(post("payment/Payment"), sync);
    }
    //PaymentResumption------------------------------------------------------------------------------------------------
    public Payment resumePayment(String otp, String jobID) throws Exception
    {
        params ="{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"otp\":\"" + otp + "\",\r\n" +
                "\"jobID\":\"" + jobID + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Payment(post("payment/Payment"), false);
    }
    public Payment resumePayment(String otp, String jobID, boolean sync) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"otp\":\"" + otp + "\",\r\n" +
                "\"sync\":"+sync+"," +
                "\"jobID\":\"" + jobID + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Payment(post("payment/Payment"), sync);
    }
    public Payment resumePayment(String otp, String jobID, String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"otp\":\"" + otp + "\",\r\n" +
                "\"jobID\":\"" + jobID + "\",\r\n";
        params = addAddresses(params, addresses);
        params+= "\"appSecret\":\"" + appSecret + "\"\n}";
        return new Payment(post("payment/Payment"), false);
    }
    public Payment resumePayment(String otp, String jobID, boolean sync, String[] addresses) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"otp\":\"" + otp + "\",\r\n" +
                "\"sync\":"+sync+"," +
                "\"jobID\":\"" + jobID + "\",\r\n";
        params = addAddresses(params, addresses);
        params+= "\"appSecret\":\"" + appSecret + "\"\n}";
        //return post("payment/Payment");
        return new Payment(post("payment/Payment"), sync);
    }
    //Status-----------------------------------------------------------------------------------------------------------
    public String getStatus(String jobID) throws Exception
    {
        params = "{\"userSecret\":\"" + userSecret + "\",\r\n"+
                "\"jobID\":\"" + jobID + "\",\r\n" +
                "\"appKey\":\"" + appKey + "\",\r\n" +
                "\"appSecret\":\"" + appSecret + "\"\n}";
        return post("status");
    }
}
