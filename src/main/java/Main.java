public class Main {

    public static void main(String[] args)
    {
        String appKey = "fe7eac37bb1b23051ec3dde979c70a97dcf9c6f3cc9256d4c7f65b0b34cd64c9";
        String appSecret = "36443fb22dc0f5a5ac8bbb2b4a3d6c838952e061dfe93581b5d5166694fe3c9964929c59816d7961831017514ed290c1c99cb12fb452cab3c1fee507c30c6b3bc7a14496cf5faa6ef5164421e816a73698bdd8198a1fc3432e6d4e92979b219746762a185ebfb32bdb3c72d5cf8dbf99d607e760d7d1b971dfc422fc3fcae36f";
        String accessCode = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzYWx0IjoiUnU1VmMrZ2tHQ2VMQm9XUG5VSWhzR2JOWC9oMUN2UjhOOFNjVDlXeGJlYz0iLCJhcHBLZXkiOiJmOTg2NTVlZWQzYzg1NmY3MGRjOTYzNmNmZmZmMDEwNTIzY2EwMGMxNjYzMThkY2ZmNDgzNzI0NTdjN2U3YzhiIiwiaWF0IjoxNTc0NjA2NTczLCJleHAiOjE1NzQ2MDgzNzN9.CXhv1undnOwV0AWkeuyAQgSGY-JVlPccmVju79OusAg";
        String connectionID = "5fa3d1521397af5708f6053e2b1f242eb659c78d";
        String userSecret = "ZcZi4DfDbuPajrhNfTDVa2K+0XnWWtBxiY+LUjo+x/1Q/X2Ot300woeSuD2XmeI3J8OtWQXKTlTU6lrW9Q4IZ/juvZYHGz/Yz+UBmqXrvaZWVH/eA7KDN41fvxlzLYhH20XKwPbIkxTTyBOWD+r8QqmIGO3U8KMhuygj5sYoEg3irSVC0zrf9NC8IqOfd3/jmp1FhLe4kIjJYz++oG1CVG688AvIDxuAZoyLk0W3w/Q3rnz/v5mb90she0TlK9sYpOSoymYJqKbLaQkr3bzgNejhQMyASGyMJchcuTdOncalrQ1jPjqaQ52rT9/XHn4NtTlq+oPhXxWs2C+j2Y1QXubPlvM8ya1MtPaprLdmHwaZ52Hoi4Ww1S5Els/5mFiSD3NQsnUn2jlxIY90P6Crl1f4GAP4ZnqK20JPP193xLX22dRYLLq6hLmtm9UmDOtKUr6kde2OUYNqwHkHVSe03As7qgab+w1xI68x62jCQca+yZupt5IKM0lX0SOr732Kv7SIivgvQqAFaWflm+8Y+WzVWma0eDJW4+0n4bTPtvEWxVJVcTKn2UrEGewOygsRi0NDFcQH9Q+RJAQvTa15s/mKhU4A9QZ5K7M8uBqgGg+ck2QndgzukRzSZLcyl00Wj5m6/ykRLnC3/Ii5YFTK93dx2YazYOs2ovzVbTPW54A=";

        String iban = "GB33BAEDB20201555555893";
        Dapi dapi = new Dapi(appKey, appSecret, accessCode, connectionID, userSecret);
        String[] webhooks = new String[]{"https://testdapi.free.beeceptor.com","https://webhook.site/7fbe6180-e5e3-4f09-8024-299ca32e6cd8"};
        try
        {
            dapi.exchangeToken();
            Identity identity = dapi.identity(true);
            System.out.println(identity.firstName());
            //Payment payment = dapi.paymentInitiation("same", "FTQ72AEDB20201555555893", 20, "GB33BAEDB20201555555893", true);
            //System.out.println(payment.message());
            //Payment payment1 = dapi.resumePayment("123456", payment.jobID(), true);
            //System.out.println(payment1.message());
            //Beneficiaries beneficiaries = dapi.getBeneficiaries("same", true);
            //AddBeneficiary addBeneficiary = dapi.addBeneficiary("test6", "987654321", "same", true);
            //System.out.println(addBeneficiary.message());
            //AddBeneficiary addBeneficiary1 = dapi.resumeAddBeneficiary("123456", addBeneficiary.jobID(), true);
            //System.out.println(addBeneficiary1.message());
            //Transactions transactions = dapi.transactions(iban, true);
            //System.out.println(transactions.description(0));
            //System.out.println(dapi.transactions(userAccounts.ibanOfSavings(0), true));

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
