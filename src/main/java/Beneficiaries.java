import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
public class Beneficiaries
{
    private Map beneficiaries;
    private List<Map> beneficiariesList;
    private boolean sync;

    public Beneficiaries(String body, boolean sync)
    {
        beneficiaries = new Gson().fromJson(body, Map.class);
        this.sync = sync;
        if (sync)
            beneficiariesList = (List<Map>)((Map) beneficiaries.get("result")).get("beneficiaries");
    }
    public String jobID()
    {
        return beneficiaries.get("jobID").toString();
    }
    public String name(int n) throws Exception
    {
        if (sync)
        {
            if (beneficiariesList.size()==0)
                throw new Exception("No beneficiaries Available");
            return beneficiariesList.get(n).get("name").toString();
        }
        throw new Exception("sync is false, no data available");
    }
    public String iban(int n) throws Exception
    {
        if (sync)
        {
            if (beneficiariesList.size()==0)
                throw new Exception("No beneficiaries Available");
            return beneficiariesList.get(n).get("iban").toString();
        }
        throw new Exception("sync is false, no data available");
    }
    public String accountNumber(int n) throws Exception
    {
        if (sync)
        {
            if (beneficiariesList.size()==0)
                throw new Exception("No beneficiaries Available");
            return beneficiariesList.get(n).get("accountNumber").toString();
        }
        throw new Exception("sync is false, no data available");
    }
}
