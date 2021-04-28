package com.dapi;

/**
 * {@link Config} holds the fields that's specific to a single app.
 */
public class Config {
    private final String appSecret;

    public Config(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppSecret() {
        return appSecret;
    }
}
