package com.dapi.types;

import java.util.Optional;

public class Identity {
    private final String name;
    private final String nationality;
    private final String dateOfBirth;
    private final String emailAddress;
    private final PhoneNumber[] numbers;
    private final IdentityAddress address;
    private final Identification[] identification;

    public Identity(String name, String nationality, String dateOfBirth, String emailAddress, PhoneNumber[] numbers, IdentityAddress address, Identification[] identification) {
        this.name = name;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.numbers = numbers;
        this.address = address;
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getNationality() {
        return Optional.ofNullable(nationality);
    }

    public Optional<String> getDateOfBirth() {
        return Optional.ofNullable(dateOfBirth);
    }

    public Optional<String> getEmailAddress() {
        return Optional.ofNullable(emailAddress);
    }

    public Optional<PhoneNumber[]> getNumbers() {
        return Optional.ofNullable(numbers);
    }

    public Optional<IdentityAddress> getAddress() {
        return Optional.ofNullable(address);
    }

    public Optional<Identification[]> getIdentification() {
        return Optional.ofNullable(identification);
    }
}
