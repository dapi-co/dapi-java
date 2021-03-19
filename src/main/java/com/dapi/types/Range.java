package com.dapi.types;

public class Range {
    private final int value;
    private final String unit;

    public Range(int value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
