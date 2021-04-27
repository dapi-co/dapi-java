package com.dapi;

import java.util.Optional;


public class LoginData {
    private final String userSecret;
    private final String accessCode;
    private final String connectionID;

    public LoginData(String userSecret, String accessCode, String connectionID) {
        this.userSecret = userSecret;
        this.accessCode = accessCode;
        this.connectionID = connectionID;
    }

    public LoginData(String userSecret) {
        this.userSecret = userSecret;
        this.accessCode = null;
        this.connectionID = null;
    }

    public String getUserSecret() {
        return userSecret;
    }

    public Optional<String> getAccessCode() {
        return Optional.ofNullable(accessCode);
    }

    public Optional<String> getConnectionID() {
        return Optional.ofNullable(connectionID);
    }
}
