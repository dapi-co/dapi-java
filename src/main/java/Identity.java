import com.google.gson.Gson;
import java.util.Map;

public class Identity
{
    private Map identity;
    private boolean sync;
    public Identity(String body, boolean sync)
    {
        identity = new Gson().fromJson(body, Map.class);
        this.sync = sync;
    }
    public String jobID()
    {
        return identity.get("jobID").toString();
    }
    public String firstName() throws Exception
    {
        if(sync)
            return ((Map)((Map)identity.get("result")).get("identity")).get("firstName").toString();
        throw new Exception("sync is false, no data available");
    }
    public String lastName() throws Exception
    {
        if(sync)
            return ((Map)((Map)identity.get("result")).get("identity")).get("lastName").toString();
        throw new Exception("sync is false, no data available");
    }
    public String emailAddress() throws Exception
    {
        if(sync)
            return ((Map)((Map)identity.get("result")).get("identity")).get("emailAddress").toString();
        throw new Exception("sync is false, no data available");
    }
    public String nationality() throws Exception
    {
        if(sync)
            return ((Map)((Map)identity.get("result")).get("identity")).get("nationality").toString();
        throw new Exception("sync is false, no data available");
    }
    public String passportNumber() throws Exception
    {
        if(sync)
            return ((Map)((Map)identity.get("result")).get("identity")).get("passportNumber").toString();
        throw new Exception("sync is false, no data available");
    }
    public String flat() throws Exception
    {
        if(sync)
            return ((Map)((Map)((Map)identity.get("result")).get("identity")).get("address")).get("flat").toString();
        throw new Exception("sync is false, no data available");
    }
    public String building() throws Exception
    {
        if(sync)
            return ((Map)((Map)((Map)identity.get("result")).get("identity")).get("address")).get("building").toString();
        throw new Exception("sync is false, no data available");
    }
    public String area() throws Exception
    {
        if(sync)
            return ((Map)((Map)((Map)identity.get("result")).get("identity")).get("address")).get("area").toString();
        throw new Exception("sync is false, no data available");
    }
    public String poBox() throws Exception
    {
        if(sync)
            return ((Map)((Map)((Map)identity.get("result")).get("identity")).get("address")).get("poBox").toString();
        throw new Exception("sync is false, no data available");
    }
    public String city() throws Exception
    {
        if(sync)
            return ((Map)((Map)((Map)identity.get("result")).get("identity")).get("address")).get("city").toString();
        throw new Exception("sync is false, no data available");
    }
    public String emirate() throws Exception
    {
        if(sync)
            return ((Map)((Map)((Map)identity.get("result")).get("identity")).get("address")).get("emirate").toString();
        throw new Exception("sync is false, no data available");
    }
    public String country() throws Exception
    {
        if(sync)
            return ((Map)((Map)((Map)identity.get("result")).get("identity")).get("address")).get("country").toString();
        throw new Exception("sync is false, no data available");
    }
}
