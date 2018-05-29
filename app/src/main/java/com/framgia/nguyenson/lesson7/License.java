package com.framgia.nguyenson.lesson7;

public class License {
    private String mKey;
    private String mName;
    private String mSpdxId;
    private String mUrl;

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSpdxId() {
        return mSpdxId;
    }

    public void setSpdxId(String spdxId) {
        mSpdxId = spdxId;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
