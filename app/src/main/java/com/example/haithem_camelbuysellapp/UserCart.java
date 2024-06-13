package com.example.haithem_camelbuysellapp;

import com.google.firebase.database.Exclude;

public class UserCart {
    public String cartName;
    public String cartPrice;
    public String cartDetail;
    public String cartUrl;
    public String cartKey;
    public String cartUid;
    public String itemSName;
    public String itemSPhone;

    public String mKey;

    public UserCart() {
    }

    public UserCart(String cartName, String cartPrice, String cartDetail, String cartUrl, String cartKey, String cartUid, String itemSName, String itemSPhone) {
        this.cartName = cartName;
        this.cartPrice = cartPrice;
        this.cartDetail = cartDetail;
        this.cartUrl = cartUrl;
        this.cartKey = cartKey;
        this.cartUid = cartUid;
        this.itemSName = itemSName;
        this.itemSPhone = itemSPhone;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public String getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(String cartPrice) {
        this.cartPrice = cartPrice;
    }

    public String getCartDetail() {
        return cartDetail;
    }

    public void setCartDetail(String cartDetail) {
        this.cartDetail = cartDetail;
    }

    public String getCartUrl() {
        return cartUrl;
    }

    public void setCartUrl(String cartUrl) {
        this.cartUrl = cartUrl;
    }

    public String getCartKey() {
        return cartKey;
    }

    public void setCartKey(String cartKey) {
        this.cartKey = cartKey;
    }

    public String getCartUid() {
        return cartUid;
    }

    public void setCartUid(String cartUid) {
        this.cartUid = cartUid;
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
