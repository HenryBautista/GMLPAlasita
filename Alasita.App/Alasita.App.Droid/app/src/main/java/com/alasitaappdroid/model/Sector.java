package com.alasitaappdroid.model;

import java.util.ArrayList;

/**
 * Created by Arun on 01/13/2015.
 */
public class Sector {

    private String mSectorName;
    private String mSectorKey;
    private String mSectorDescription;
    private String mSectorMapImage;             //If local this becomes an integer, moreover a color
    private ArrayList<Association> mSectorAssociations;

    public String getmSectorName() {
        return mSectorName;
    }

    public void setmSectorName(String mSectorName) {
        this.mSectorName = mSectorName;
    }

    public String getmSectorKey() {
        return mSectorKey;
    }

    public void setmSectorKey(String mSectorKey) {
        this.mSectorKey = mSectorKey;
    }

    public String getmSectorDescription() {
        return mSectorDescription;
    }

    public void setmSectorDescription(String mSectorDescription) {
        this.mSectorDescription = mSectorDescription;
    }

    public String getmSectorMapImage() {
        return mSectorMapImage;
    }

    public void setmSectorMapImage(String mSectorMapImage) {
        this.mSectorMapImage = mSectorMapImage;
    }

    public ArrayList<Association> getmSectorAssociations() {
        return mSectorAssociations;
    }

    public void setmSectorAssociations(ArrayList<Association> mSectorAssociations) {
        this.mSectorAssociations = mSectorAssociations;
    }
}
