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

    public Sector() {
    }

    public Sector(String sectorName, String sectorKey, String sectorDescription, String sectorMapImage) {
        mSectorName = sectorName;
        mSectorKey = sectorKey;
        mSectorDescription = sectorDescription;
        mSectorMapImage = sectorMapImage;
        mSectorAssociations = new ArrayList<Association>();
        mSectorAssociations.add(new Association("dummy1", 1, "dummy1", "dummy1"));
        mSectorAssociations.add(new Association("dummy2", 2, "dummy2", "dummy2"));
        mSectorAssociations.add(new Association("dummy3", 3, "dummy3", "dummy3"));
        mSectorAssociations.add(new Association("dummy4", 4, "dummy4", "dummy4"));
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
}
