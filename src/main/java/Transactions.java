import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
public class Transactions
{
    private Map transactions;
    private List<Map> transactionsList;
    private boolean sync;

    public Transactions(String body, boolean sync)
    {
        String temp = "{\n" +
                "  \"jobID\": \"25701700-ec8b-5d7e-8410-f260bb7a5139\",\n" +
                "  \"success\": true,\n" +
                "  \"msg\": \"Job Finished\",\n" +
                "  \"result\": {\n" +
                "    \"transactions\": [\n" +
                "      {\n" +
                "        \"date\": \"2019-10-31T00:00:00.000Z\",\n" +
                "        \"amount\": 197,\n" +
                "        \"currency\": \"AED\",\n" +
                "        \"type\": \"debit\",\n" +
                "        \"description\": \"POS-PURCHASE\",\n" +
                "        \"details\":\n" +
                "          \"SPORTS VILLAGE LEISURE DUBAI:AE-678767 29-10-2019  197.00,AED\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"date\": \"2019-10-30T00:00:00.000Z\",\n" +
                "        \"amount\": 255.8,\n" +
                "        \"currency\": \"AED\",\n" +
                "        \"type\": \"debit\",\n" +
                "        \"description\": \"POS-PURCHASE\",\n" +
                "        \"details\":\n" +
                "          \"JORDANA RESTAURANT LLC DUBAI:AE-56787656 28-10-2019  255.80,AED\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        transactions = new Gson().fromJson(body, Map.class);
        //transactions = new Gson().fromJson(temp, Map.class);
        this.sync = sync;
        if (sync)
            transactionsList = (List<Map>)((Map) transactions.get("result")).get("transactions");
    }
    public String jobID()
    {
        return transactions.get("jobID").toString();
    }
    public String date(int n) throws Exception
    {
        if (sync)
        {
            if (transactionsList.size()==0)
                throw new Exception("No Transactions Available");
            return transactionsList.get(n).get("date").toString();
        }
        throw new Exception("sync is false, no data available");
    }
    public Double amount(int n) throws Exception
    {
        if (sync)
        {
            if (transactionsList.size()==0)
                throw new Exception("No Transactions Available");
            return Double.parseDouble(transactionsList.get(n).get("amount").toString());
        }
        throw new Exception("sync is false, no data available");
    }
    public String currency(int n) throws Exception
    {
        if (sync)
        {
            if (transactionsList.size()==0)
                throw new Exception("No Transactions Available");
            return transactionsList.get(n).get("currency").toString();
        }
        throw new Exception("sync is false, no data available");
    }
    public String type(int n) throws Exception
    {
        if (sync)
        {
            if (transactionsList.size()==0)
                throw new Exception("No Transactions Available");
            return transactionsList.get(n).get("type").toString();
        }
        throw new Exception("sync is false, no data available");
    }
    public String description(int n) throws Exception
    {
        if (sync)
        {
            if (transactionsList.size()==0)
                throw new Exception("No Transactions Available");
            return transactionsList.get(n).get("description").toString();
        }
        throw new Exception("sync is false, no data available");
    }
    public String details(int n) throws Exception
    {
        if (sync)
        {
            if (transactionsList.size()==0)
                throw new Exception("No Transactions Available");
            return transactionsList.get(n).get("details").toString();
        }
        throw new Exception("sync is false, no data available");
    }
}
