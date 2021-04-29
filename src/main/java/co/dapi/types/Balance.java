package co.dapi.types;

public class Balance {
    private final String amount;
    private final String accountNumber;
    private final Currency currency;

    public Balance(String amount, String accountNumber, Currency currency) {
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Currency getCurrency() {
        return currency;
    }
}
