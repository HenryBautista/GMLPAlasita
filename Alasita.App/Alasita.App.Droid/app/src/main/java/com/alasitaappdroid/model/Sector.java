package com.alasitaappdroid.model;

import java.util.ArrayList;

/**
 * Created by Arun on 01/13/2015.
 */
public class Sector {

    private String mSectorName;
    private String mSectorKey;
    private String mSectorDescription;
    private String mSectorMapImage;                         //If local this becomes an integer, moreover a color
    private ArrayList<Association> mSectorAssociations;
    private ArrayList<String> mSectorTags;

    public Sector() {
        mSectorAssociations = new ArrayList<>();
        mSectorTags = new ArrayList<>();
    }

    public String getSectorName() {
        return mSectorName;
    }

    public void setSectorName(String sectorName) {
        mSectorName = sectorName;
    }

    public String getSectorKey() {
        return mSectorKey;
    }

    public void setSectorKey(String sectorKey) {
        mSectorKey = sectorKey;
    }

    public String getSectorDescription() {
        return mSectorDescription;
    }

    public void setSectorDescription(String sectorDescription) {
        mSectorDescription = sectorDescription;
    }

    public String getSectorMapImage() {
        return mSectorMapImage;
    }

    public void setSectorMapImage(String sectorMapImage) {
        mSectorMapImage = sectorMapImage;
    }

    public ArrayList<Association> getSectorAssociations() {
        return mSectorAssociations;
    }

    public void setSectorAssociations(ArrayList<Association> sectorAssociations) {
        mSectorAssociations = sectorAssociations;
    }

    public ArrayList<String> getSectorTags() {
        return mSectorTags;
    }

    public void setSectorTags(ArrayList<String> sectorTags) {
        mSectorTags = sectorTags;
    }
}
