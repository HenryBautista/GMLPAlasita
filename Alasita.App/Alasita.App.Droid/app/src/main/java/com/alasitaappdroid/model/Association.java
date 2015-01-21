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
    private int mAssociationImage;


    public Association() {
        mAssociationProducts = new ArrayList<>();
        mAssociationImage = setImage();
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

    public int getAssociationImage() {
        return mAssociationImage;
    }

    public void setAssociationImage(int associationImage) {
        mAssociationImage = associationImage;
    }

    @Override
    public String toString() {
        return mAssociationName;
    }

    public int setImage() {
        int path = 0;
        int[] images = {
                0x7f020033,
                0x7f020034,
                0x7f020035,
                0x7f020036,
                0x7f020037,
                0x7f020038,
                0x7f02003a,
                0x7f02003b,
                0x7f02003e,
                0x7f02003f,
                0x7f020040,
                0x7f020041,
                0x7f020042,
                0x7f020043,
                0x7f020044,
                0x7f020045,
                0x7f020046,
                0x7f020047,
                0x7f020049,
                0x7f02004a,
                0x7f02004b


        };
        path = images[(int) (Math.random() * 20)];
        return path;
    }
}
