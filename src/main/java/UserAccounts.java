import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class UserAccounts
{
    private Map accounts;
    private List<Map> savings;
    private List<Map> current;
    private boolean sync;
    public UserAccounts(String body, boolean sync)
    {
        accounts = new Gson().fromJson(body, Map.class);
        this.sync = sync;
        if (sync)
        {
            savings = (List<Map>) ((Map) ((Map) ((Map) accounts.get("result")).get("accounts")).get("accounts")).get("savings");
            current = (List<Map>) ((Map) ((Map) ((Map) accounts.get("result")).get("accounts")).get("accounts")).get("current");
        }
    }
    public String jobID()
    {
        return accounts.get("jobID").toString();
    }
    public Double balanceOfSavings(int n) throws Exception
    {
        if (sync)
        {
            if (savings.size()==0)
                throw new Exception("No Saving Accounts Available");
            return Double.parseDouble(savings.get(n).get("balance").toString());
        }
        throw new Exception("sync is false, no data available");
    }
    public String ibanOfSavings(int n) throws Exception
    {
        if (sync)
        {
            if (savings.size()==0)
                throw new Exception("No Saving Accounts Available");
            return savings.get(n).get("iban").toString();
        }
        throw new Exception("sync is false, no data available");
    }
    public String accountNumberOfSavings(int n) throws Exception
    {
        if (sync)
        {
            if (savings.size()==0)
                throw new Exception("No Saving Accounts Available");
            return savings.get(n).get("accountNumber").toString();
        }
        throw new Exception("sync is false, no data available");
    }
    public String currencyOfSavings(int n) throws Exception
    {
        if (sync)
            return savings.get(n).get("currency").toString();
        throw new Exception("sync is false, no data available");
    }

    public Double balanceOfCurrent(int n) throws Exception
    {
        if (sync)
        {
            if (current.size()==0)
                throw new Exception("No Current Accounts Available");
            return Double.parseDouble(current.get(n).get("balance").toString());
        }
        throw new Exception("sync is false, no data available");
    }
    public String ibanOfCurrent(int n) throws Exception
    {
        if (sync)
        {
            if (current.size()==0)
                throw new Exception("No Current Accounts Available");
            return current.get(n).get("iban").toString();
        }
        throw new Exception("sync is false, no data available");
    }
    public String accountNumberOfCurrent(int n) throws Exception
    {
        if (sync)
        {
            if (current.size()==0)
                throw new Exception("No Current Accounts Available");
            return current.get(n).get("accountNumber").toString();
        }
        throw new Exception("sync is false, no data available");
    }
    public String currencyOfCurrent(int n) throws Exception
    {
        if (sync)
        {
            if (current.size()==0)
                throw new Exception("No Current Accounts Available");
            return current.get(n).get("currency").toString();
        }
        throw new Exception("sync is false, no data available");
    }


    public int numberOfSavingsAccounts() throws Exception
    {
        if (sync)
            return savings.size();
        throw new Exception("sync is false, no data available");
    }
    public int numberOfCurrentAccounts() throws Exception
    {
        if (sync)
            return current.size();
        throw new Exception("sync is false, no data available");
    }

}
