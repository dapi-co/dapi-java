package com.dapi;

public class Config {
    private String bundleID;
    private String appKey;
    private String appSecret;

    public Config(String bundleID, String appKey, String appSecret) {
        this.bundleID = bundleID;
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public Config(String bundleID, String appKey) {
        this.bundleID = bundleID;
        this.appKey = appKey;
    }

    public Config(String appKey) {
        this.appKey = appKey;
    }

    public String getBundleID() {
        return bundleID;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }
}
