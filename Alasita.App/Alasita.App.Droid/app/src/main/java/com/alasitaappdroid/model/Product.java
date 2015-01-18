package com.alasitaappdroid.model;

/**
 * Created by Arun on 01/13/2015.
 */
public class Product {

    private String mProductName;
    private String mProductImage;               //If local then this becomes an integer

    public Product() {
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String productName) {
        mProductName = productName;
    }

    public String getProductImage() {
        return mProductImage;
    }

    public void setProductImage(String productImage) {
        mProductImage = productImage;
    }
}
