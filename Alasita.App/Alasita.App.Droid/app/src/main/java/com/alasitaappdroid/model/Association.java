package com.alasitaappdroid.model;

import java.util.ArrayList;

/**
 * Created by Arun on 01/13/2015.
 */
public class Association {

    private String mAssociationName;
    private int mAssociationKey;
    private String mAssociationDescription;
    private ArrayList<Product> mAssociationProducts;
    private int mAssociationExpoNumber;
    private String mAssociationImage;


    public Association() {
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

    public int getAssociationExpoNumber() {
        return mAssociationExpoNumber;
    }

    public void setAssociationExpoNumber(int associationExpoNumber) {
        mAssociationExpoNumber = associationExpoNumber;
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
