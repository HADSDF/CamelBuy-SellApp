package com.example.haithem_camelbuysellapp;

import com.google.firebase.database.Exclude;

public class UserCheckStatistics {
    public String statItemName;
    public String statSellerName;
    public String statDate;
    public String statQuantity;

    public String mKey;

    public UserCheckStatistics() {
    }

    public UserCheckStatistics(String statItemName, String statSellerName, String statDate, String statQuantity) {
        this.statItemName = statItemName;
        this.statSellerName = statSellerName;
        this.statDate = statDate;
        this.statQuantity = statQuantity;
    }

    public String getStatItemName() {
        return statItemName;
    }

    public void setStatItemName(String statItemName) {
        this.statItemName = statItemName;
    }

    public String getStatSellerName() {
        return statSellerName;
    }

    public void setStatSellerName(String statSellerName) {
        this.statSellerName = statSellerName;
    }

    public String getStatDate() {
        return statDate;
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate;
    }

    public String getStatQuantity() {
        return statQuantity;
    }

    public void setStatQuantity(String statQuantity) {
        this.statQuantity = statQuantity;
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


