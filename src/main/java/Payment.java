import com.google.gson.Gson;
import java.util.Map;
public class Payment
{
    private Map payment;
    private boolean sync;
    public Payment(String body, boolean sync)
    {
        payment = new Gson().fromJson(body, Map.class);
        this.sync = sync;
    }
    public String jobID() { return payment.get("jobID").toString(); }

    public String message() throws Exception
    {
        if(sync)
            return ((Map)payment.get("result")).get("msg").toString();
        throw new Exception("sync is false, no data available");
    }

}
