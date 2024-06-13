package com.example.haithem_camelbuysellapp;

import com.google.firebase.database.Exclude;

public class UserInformation {
    public String userName;
    public String userPhone;
    public String userAddress;

    public String mKey;

    public UserInformation() {
    }

    public UserInformation(String userName, String userPhone, String userAddress) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String Key) {
        mKey = Key;
    }
}
