package com.dapi.types;

import java.util.Optional;

public class AccountsMetadata {
    private final boolean isCreateBeneficiaryEndpointRequired;
    private final boolean willNewlyAddedBeneficiaryExistBeforeCoolDownPeriod;
    private final String swiftCode;
    private final String sortCode;
    private final String bankName;
    private final String branchName;
    private final String branchAddress;
    private final BeneficiaryAddress address;
    private final TransferBounds[] transferBounds;
    private final Range beneficiaryCoolDownPeriod;
    private final Range transactionRange;
    private final Country country;
    private final Validators validators;

    public AccountsMetadata(boolean isCreateBeneficiaryEndpointRequired, boolean willNewlyAddedBeneficiaryExistBeforeCoolDownPeriod, String swiftCode, String sortCode, String bankName, String branchName, String branchAddress, BeneficiaryAddress address, TransferBounds[] transferBounds, Range beneficiaryCoolDownPeriod, Range transactionRange, Country country, Validators validators) {
        this.isCreateBeneficiaryEndpointRequired = isCreateBeneficiaryEndpointRequired;
        this.willNewlyAddedBeneficiaryExistBeforeCoolDownPeriod = willNewlyAddedBeneficiaryExistBeforeCoolDownPeriod;
        this.swiftCode = swiftCode;
        this.sortCode = sortCode;
        this.bankName = bankName;
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.address = address;
        this.transferBounds = transferBounds;
        this.beneficiaryCoolDownPeriod = beneficiaryCoolDownPeriod;
        this.transactionRange = transactionRange;
        this.country = country;
        this.validators = validators;
    }

    public boolean isCreateBeneficiaryEndpointRequired() {
        return isCreateBeneficiaryEndpointRequired;
    }

    public boolean isWillNewlyAddedBeneficiaryExistBeforeCoolDownPeriod() {
        return willNewlyAddedBeneficiaryExistBeforeCoolDownPeriod;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public Optional<String> getSortCode() {
        return Optional.ofNullable(sortCode);
    }

    public String getBankName() {
        return bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public BeneficiaryAddress getAddress() {
        return address;
    }

    public TransferBounds[] getTransferBounds() {
        return transferBounds;
    }

    public Range getBeneficiaryCoolDownPeriod() {
        return beneficiaryCoolDownPeriod;
    }

    public Range getTransactionRange() {
        return transactionRange;
    }

    public Country getCountry() {
        return country;
    }

    public Validators getValidators() {
        return validators;
    }
}
