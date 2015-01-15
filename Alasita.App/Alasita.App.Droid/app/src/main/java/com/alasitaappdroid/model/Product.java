package com.alasitaappdroid.model;

/**
 * Created by Arun on 01/13/2015.
 */
public class Product {

    private String mProductName;
    private int mProductKey;
    private String mProductImage;               //If local then this becomes an integer
    private String mProductDescription;         //Can be deleted if no info is provided

    public Product() {
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String productName) {
        mProductName = productName;
    }

    public int getProductKey() {
        return mProductKey;
    }

    public void setProductKey(int productKey) {
        mProductKey = productKey;
    }

    public String getProductImage() {
        return mProductImage;
    }

    public void setProductImage(String productImage) {
        mProductImage = productImage;
    }

    public String getProductDescription() {
        return mProductDescription;
    }

    public void setProductDescription(String productDescription) {
        mProductDescription = productDescription;
    }
}
