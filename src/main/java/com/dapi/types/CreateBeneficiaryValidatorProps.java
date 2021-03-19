package com.dapi.types;

import java.util.Optional;

public class CreateBeneficiaryValidatorProps {
    private final ValidatorProps name;
    private final ValidatorProps nickname;
    private final ValidatorProps swiftCode;
    private final ValidatorProps iban;
    private final ValidatorProps accountNumber;
    private final ValidatorProps address;
    private final ValidatorProps branchAddress;
    private final ValidatorProps branchName;
    private final ValidatorProps country;
    private final ValidatorProps phoneNumber;
    private final ValidatorProps sortCode;

    public CreateBeneficiaryValidatorProps(ValidatorProps name, ValidatorProps nickname, ValidatorProps swiftCode, ValidatorProps iban, ValidatorProps accountNumber, ValidatorProps address, ValidatorProps branchAddress, ValidatorProps branchName, ValidatorProps country, ValidatorProps phoneNumber, ValidatorProps sortCode) {
        this.name = name;
        this.nickname = nickname;
        this.swiftCode = swiftCode;
        this.iban = iban;
        this.accountNumber = accountNumber;
        this.address = address;
        this.branchAddress = branchAddress;
        this.branchName = branchName;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.sortCode = sortCode;
    }

    public ValidatorProps getName() {
        return name;
    }

    public ValidatorProps getNickname() {
        return nickname;
    }

    public ValidatorProps getSwiftCode() {
        return swiftCode;
    }

    public Optional<ValidatorProps> getIban() {
        return Optional.ofNullable(iban);
    }

    public Optional<ValidatorProps> getAccountNumber() {
        return Optional.ofNullable(accountNumber);
    }

    public ValidatorProps getAddress() {
        return address;
    }

    public ValidatorProps getBranchAddress() {
        return branchAddress;
    }

    public ValidatorProps getBranchName() {
        return branchName;
    }

    public ValidatorProps getCountry() {
        return country;
    }

    public ValidatorProps getPhoneNumber() {
        return phoneNumber;
    }

    public ValidatorProps getSortCode() {
        return sortCode;
    }
}
