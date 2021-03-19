package com.dapi.types;

import java.util.Optional;

public class Beneficiary {
    private final String name;
    private final String id;
    private final BeneficiaryType type;
    private final BeneficiaryStatus status;
    private final String iban;
    private final String accountNumber;

    public Beneficiary(String name, String id, BeneficiaryType type, BeneficiaryStatus status, String iban, String accountNumber) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.status = status;
        this.iban = iban;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public BeneficiaryType getType() {
        return type;
    }

    public Optional<BeneficiaryStatus> getStatus() {
        return Optional.ofNullable(status);
    }

    public Optional<String> getIban() {
        return Optional.ofNullable(iban);
    }

    public Optional<String> getAccountNumber() {
        return Optional.ofNullable(accountNumber);
    }

    public enum BeneficiaryType {
        own,
        same,
        local,
        intl
    }

    public enum BeneficiaryStatus {
        approved,
        rejected,
        cancelled,
        waiting_for_confirmation,
        modified_for_pending_approval
    }
}
