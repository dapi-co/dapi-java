package com.dapi.types;

public class BeneficiaryAddress {
    private final String line1;
    private final String line2;
    private final String line3;

    public BeneficiaryAddress(String line1, String line2, String line3) {
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getLine3() {
        return line3;
    }
}
