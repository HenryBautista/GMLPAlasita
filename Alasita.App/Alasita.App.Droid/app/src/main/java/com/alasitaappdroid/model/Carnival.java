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

    public String getmCarnivalName() {
        return mCarnivalName;
    }

    public void setmCarnivalName(String mCarnivalName) {
        this.mCarnivalName = mCarnivalName;
    }

    public String getmCarnivalBannerImageUrl() {
        return mCarnivalBannerImageUrl;
    }

    public void setmCarnivalBannerImageUrl(String mCarnivalBannerImageUrl) {
        this.mCarnivalBannerImageUrl = mCarnivalBannerImageUrl;
    }

    public ArrayList<String> getmCarnivalAdditionalImages() {
        return mCarnivalAdditionalImages;
    }

    public void setmCarnivalAdditionalImages(ArrayList<String> mCarnivalAdditionalImages) {
        this.mCarnivalAdditionalImages = mCarnivalAdditionalImages;
    }

    public ArrayList<Sector> getmCarnivalSectors() {
        return mCarnivalSectors;
    }

    public void setmCarnivalSectors(ArrayList<Sector> mCarnivalSectors) {
        this.mCarnivalSectors = mCarnivalSectors;
    }

    public String getmCarnivalDescription() {
        return mCarnivalDescription;
    }

    public void setmCarnivalDescription(String mCarnivalDescription) {
        this.mCarnivalDescription = mCarnivalDescription;
    }
}
