package com.dapi;

public class LoginData {
    private final String tokenID;
    private final String userID;
    private final String userSecret;
    private final String accessCode;
    private final String connectionID;

    public LoginData(String tokenID, String userID, String userSecret, String accessCode, String connectionID) {
        this.tokenID = tokenID;
        this.userID = userID;
        this.userSecret = userSecret;
        this.accessCode = accessCode;
        this.connectionID = connectionID;
    }

    public LoginData(String tokenID, String userID, String userSecret) {
        this.tokenID = tokenID;
        this.userID = userID;
        this.userSecret = userSecret;
        this.accessCode = null;
        this.connectionID = null;
    }

    public String getTokenID() {
        return tokenID;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserSecret() {
        return userSecret;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public String getConnectionID() {
        return connectionID;
    }
}
