package com.alasitaappdroid.model;

import java.util.ArrayList;

/**
 * Created by Arun on 01/13/2015.
 */
public class Association {

    private String mAssociationName;
    private int mAssociationKey;
    private ArrayList<String> mAssociationInfo;
    private String mAssociationDescription;
    private ArrayList<Product> mAssociationProducts;
    private int mExpoNumber;
    private String mAssociationImage;

    public Association() {
        mAssociationInfo = new ArrayList<>();
        mAssociationProducts = new ArrayList<>();
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

    public ArrayList<String> getAssociationInfo() {
        return mAssociationInfo;
    }

    public void setAssociationInfo(ArrayList<String> associationInfo) {
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

    public int getExpoNumber() {
        return mExpoNumber;
    }

    public void setExpoNumber(int expoNumber) {
        mExpoNumber = expoNumber;
    }

    public String getAssociationImage() {
        return mAssociationImage;
    }

    public void setAssociationImage(String associationImage) {
        mAssociationImage = associationImage;
    }

    @Override
    public String toString() {
        return mAssociationName;
    }
}
