import com.google.gson.Gson;
import java.util.Map;
public class AddBeneficiary
{
    private Map addBeneficiary;
    private boolean sync;
    public AddBeneficiary(String body, boolean sync)
    {
        addBeneficiary = new Gson().fromJson(body, Map.class);
        this.sync = sync;
    }
    public String jobID()
    {
        return addBeneficiary.get("jobID").toString();
    }

    public String message() throws Exception
    {
        if(sync)
            return ((Map)addBeneficiary.get("result")).get("msg").toString();
        throw new Exception("sync is false, no data available");
    }
}
