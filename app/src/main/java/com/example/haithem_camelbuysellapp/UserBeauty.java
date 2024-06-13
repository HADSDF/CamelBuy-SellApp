package com.example.haithem_camelbuysellapp;

import com.google.firebase.database.Exclude;

public class UserBeauty {
    public String itemName;
    public String itemPrice;
    public String itemSpecification;
    public String itemQuality;
    public String itemUrl;
    public String itemSName;
    public String itemSPhone;

    public String mKey;

    public UserBeauty() {
    }

    public UserBeauty(String itemName, String itemPrice, String itemSpecification, String itemQuality, String itemUrl, String itemSName, String itemSPhone) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemSpecification = itemSpecification;
        this.itemQuality = itemQuality;
        this.itemUrl = itemUrl;
        this.itemSName = itemSName;
        this.itemSPhone = itemSPhone;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemSpecification() {
        return itemSpecification;
    }

    public void setItemSpecification(String itemSpecification) {
        this.itemSpecification = itemSpecification;
    }

    public String getItemQuality() {
        return itemQuality;
    }

    public void setItemQuality(String itemQuality) {
        this.itemQuality = itemQuality;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getItemSName() {
        return itemSName;
    }

    public void setItemSName(String itemSName) {
        this.itemSName = itemSName;
    }

    public String getItemSPhone() {
        return itemSPhone;
    }

    public void setItemSPhone(String itemSPhone) {
        this.itemSPhone = itemSPhone;
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
