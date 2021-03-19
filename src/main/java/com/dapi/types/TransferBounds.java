package com.dapi.types;

public class TransferBounds {
    private final int minimum;
    private final Beneficiary.BeneficiaryType type;
    private final Currency currency;

    public TransferBounds(int minimum, Beneficiary.BeneficiaryType type, Currency currency) {
        this.minimum = minimum;
        this.type = type;
        this.currency = currency;
    }

    public int getMinimum() {
        return minimum;
    }

    public Beneficiary.BeneficiaryType getType() {
        return type;
    }

    public Currency getCurrency() {
        return currency;
    }
}
