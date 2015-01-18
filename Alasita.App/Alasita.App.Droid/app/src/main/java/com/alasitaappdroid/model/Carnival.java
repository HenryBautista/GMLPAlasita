package com.alasitaappdroid.model;

import java.util.ArrayList;

/**
 * Created by Arun on 01/13/2015.
 */
public class Carnival {

    private String mCarnivalName;
    private String mCarnivalBannerImageUrl;
    private ArrayList<String> mCarnivalAdditionalImages;
    private ArrayList<Sector> mCarnivalSectors;
    private String mCarnivalDescription;

    public Carnival() {
        mCarnivalAdditionalImages = new ArrayList<>();
        mCarnivalSectors = new ArrayList<>();
    }

    public String getCarnivalName() {
        return mCarnivalName;
    }

    public void setCarnivalName(String carnivalName) {
        mCarnivalName = carnivalName;
    }

    public String getCarnivalBannerImageUrl() {
        return mCarnivalBannerImageUrl;
    }

    public void setCarnivalBannerImageUrl(String carnivalBannerImageUrl) {
        mCarnivalBannerImageUrl = carnivalBannerImageUrl;
    }

    public ArrayList<String> getCarnivalAdditionalImages() {
        return mCarnivalAdditionalImages;
    }

    public void setCarnivalAdditionalImages(ArrayList<String> carnivalAdditionalImages) {
        mCarnivalAdditionalImages = carnivalAdditionalImages;
    }

    public ArrayList<Sector> getCarnivalSectors() {
        return mCarnivalSectors;
    }

    public void setCarnivalSectors(ArrayList<Sector> carnivalSectors) {
        mCarnivalSectors = carnivalSectors;
    }

    public String getCarnivalDescription() {
        return mCarnivalDescription;
    }

    public void setCarnivalDescription(String carnivalDescription) {
        mCarnivalDescription = carnivalDescription;
    }
}
