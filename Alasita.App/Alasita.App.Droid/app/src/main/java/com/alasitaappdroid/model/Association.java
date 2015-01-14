package com.alasitaappdroid.model;

import java.util.ArrayList;

/**
 * Created by Arun on 01/13/2015.
 */
public class Association {

    private String mAssociationName;
    private int mAssociationKey;
    private String mAssociationInfo;
    private String mAssociationDescription;
    private ArrayList<Product> mAssociationProducts;

    public String getmAssociationName() {
        return mAssociationName;
    }

    public void setmAssociationName(String mAssociationName) {
        this.mAssociationName = mAssociationName;
    }

    public int getmAssociationKey() {
        return mAssociationKey;
    }

    public void setmAssociationKey(int mAssociationKey) {
        this.mAssociationKey = mAssociationKey;
    }

    public String getmAssociationInfo() {
        return mAssociationInfo;
    }

    public void setmAssociationInfo(String mAssociationInfo) {
        this.mAssociationInfo = mAssociationInfo;
    }

    public String getmAssociationDescription() {
        return mAssociationDescription;
    }

    public void setmAssociationDescription(String mAssociationDescription) {
        this.mAssociationDescription = mAssociationDescription;
    }

    public ArrayList<Product> getmAssociationProducts() {
        return mAssociationProducts;
    }

    public void setmAssociationProducts(ArrayList<Product> mAssociationProducts) {
        this.mAssociationProducts = mAssociationProducts;
    }
}
