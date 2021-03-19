package com.dapi.types;

public class PhoneNumber {
    private final String value;
    private final PhoneNumberType type;

    public PhoneNumber(String value, PhoneNumberType type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public PhoneNumberType getType() {
        return type;
    }

    public enum PhoneNumberType {
        mobile,
        home,
        office,
        fax
    }
}
