package com.dapi;

public class Config {
    private final String appSecret;

    public Config(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppSecret() {
        return appSecret;
    }
}
