package com.dapi.types;

public class Account {
    private final String name;
    private final String iban;
    private final String number;
    private final AccountType type;
    private final String id;
    private final boolean isFavourite;
    private final Currency currency;

    public Account(String name, String iban, String number, AccountType type, String id, boolean isFavourite, Currency currency, Balance balance) {
        this.name = name;
        this.iban = iban;
        this.number = number;
        this.type = type;
        this.id = id;
        this.isFavourite = isFavourite;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public String getIban() {
        return iban;
    }

    public String getNumber() {
        return number;
    }

    public AccountType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public Currency getCurrency() {
        return currency;
    }

    public enum AccountType {
        current,
        savings,
        loan,
        credit,
        deposit,
        other
    }
}
