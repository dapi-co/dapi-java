package co.dapi.types;

public class ACHPullTransferInfo {
    private final Float amount;
    private final String status;
    private final Currency currency;

    public ACHPullTransferInfo(Float amount, String status, Currency currency) {
        this.amount = amount;
        this.status = status;
        this.currency = currency;
    }

    public Float getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public Currency getCurrency() {
        return currency;
    }

}
