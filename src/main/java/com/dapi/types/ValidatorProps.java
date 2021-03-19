package com.dapi.types;

import java.util.Optional;

public class ValidatorProps {
    private final boolean required;
    private final int length;
    private final String allowedCharacters;
    private final Object[] attributes;

    public ValidatorProps(boolean required, int length, String allowedCharacters, Object[] attributes) {
        this.required = required;
        this.length = length;
        this.allowedCharacters = allowedCharacters;
        this.attributes = attributes;
    }

    public boolean isRequired() {
        return required;
    }

    public int getLength() {
        return length;
    }

    public Optional<String> getAllowedCharacters() {
        return Optional.ofNullable(allowedCharacters);
    }

    public Optional<Object[]> getAttributes() {
        return Optional.ofNullable(attributes);
    }
}
