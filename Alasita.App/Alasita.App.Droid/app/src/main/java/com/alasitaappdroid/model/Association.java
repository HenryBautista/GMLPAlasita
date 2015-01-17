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

    public Association() {
    }

    public Association(String associationName, int associationKey, String associationInfo, String associationDescription) {
        mAssociationName = associationName;
        mAssociationKey = associationKey;
        mAssociationInfo = associationInfo;
        mAssociationDescription = associationDescription;
    }

    public String getAssociationName() {
        return mAssociationName;
    }

    public void setAssociationName(String associationName) {
        mAssociationName = associationName;
    }

    public int getAssociationKey() {
        return mAssociationKey;
    }

    public void setAssociationKey(int associationKey) {
        mAssociationKey = associationKey;
    }

    public String getAssociationInfo() {
        return mAssociationInfo;
    }

    public void setAssociationInfo(String associationInfo) {
        mAssociationInfo = associationInfo;
    }

    public String getAssociationDescription() {
        return mAssociationDescription;
    }

    public void setAssociationDescription(String associationDescription) {
        mAssociationDescription = associationDescription;
    }

    public ArrayList<Product> getAssociationProducts() {
        return mAssociationProducts;
    }

    public void setAssociationProducts(ArrayList<Product> associationProducts) {
        mAssociationProducts = associationProducts;
    }

    @Override
    public String toString() {
        return mAssociationName;
    }
}
