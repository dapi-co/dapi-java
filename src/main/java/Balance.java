import com.google.gson.Gson;
import java.util.Map;

public class Balance
{
    private Map balance;
    private boolean sync;
    public Balance(String body, boolean sync)
    {
        balance = new Gson().fromJson(body, Map.class);
        this.sync = sync;
    }
    public String jobID()
    {
        return balance.get("jobID").toString();
    }
    public Double balance() throws Exception
    {
        if(sync)
            return Double.parseDouble(((Map)((Map)balance.get("result")).get("accountBalance")).get("balance").toString());
        throw new Exception("sync is false, no data available");
    }
    public String currency() throws Exception
    {
        if(sync)
            return ((Map)((Map)balance.get("result")).get("accountBalance")).get("currency").toString();
        throw new Exception("sync is false, no data available");
    }
    public String accountNumber() throws Exception
    {
        if(sync)
            return ((Map)((Map)balance.get("result")).get("accountBalance")).get("accountNumber").toString();
        throw new Exception("sync is false, no data available");
    }

}
