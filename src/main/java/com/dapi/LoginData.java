package com.dapi;

import java.util.Optional;


/**
 * {@link LoginData} represents the data retrieved from a user login,
 * and needed by all products a {@link DapiApp} can use for a single user.
 */
public class LoginData {
    private final String userSecret;
    private final String accessCode;
    private final String connectionID;

    /**
     * This Constructs a {@link LoginData} that holds the data needed for all products.
     *
     * @param userSecret   retrieved from user login.
     * @param accessCode   retrieved from user login.
     * @param connectionID retrieved from user login.
     */
    public LoginData(String userSecret, String accessCode, String connectionID) {
        this.userSecret = userSecret;
        this.accessCode = accessCode;
        this.connectionID = connectionID;
    }


    /**
     * This Constructs a {@link LoginData} that holds the data needed for all products,
     * except {@link Auth}.
     *
     * @param userSecret retrieved from user login.
     */
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
