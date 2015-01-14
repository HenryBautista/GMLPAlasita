package com.alasitaappdroid.model;

/**
 * Created by Arun on 01/13/2015.
 */
public class Product {

    private String mProductName;
    private int mProductKey;
    private String mProductImage;               //If local then this becomes an integer
    private String mProductDescription;         //Can be deleted if no info is provided

    public String getmProductName() {
        return mProductName;
    }

    public void setmProductName(String mProductName) {
        this.mProductName = mProductName;
    }

    public int getmProductKey() {
        return mProductKey;
    }

    public void setmProductKey(int mProductKey) {
        this.mProductKey = mProductKey;
    }

    public String getmProductImage() {
        return mProductImage;
    }

    public void setmProductImage(String mProductImage) {
        this.mProductImage = mProductImage;
    }

    public String getmProductDescription() {
        return mProductDescription;
    }

    public void setmProductDescription(String mProductDescription) {
        this.mProductDescription = mProductDescription;
    }
}
