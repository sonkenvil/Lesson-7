package com.framgia.nguyenson.lesson7;

public class Repos {
    private int mId;
    private String mName;
    private String mFullName;

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    private Owner mOwner;
    private License mLicense;

    public Repos() {
        mOwner = new Owner();
        mLicense = new License();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Owner getOwner() {
        return mOwner;
    }

    public void setOwner(Owner owner) {
        mOwner = owner;
    }

    public License getLicense() {
        return mLicense;
    }

    public void setLicense(License license) {
        mLicense = license;
    }
}
