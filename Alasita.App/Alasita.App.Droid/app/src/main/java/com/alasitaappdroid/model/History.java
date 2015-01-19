package com.alasitaappdroid.model;

import java.util.ArrayList;

/**
 * Created by Arun on 01/19/2015.
 */
public class History {
    private String mHistoryTitle;
    private String mHistoryDescription;
    private ArrayList<String> mHistoryImages;

    public History() {
        mHistoryImages = new ArrayList<>();
    }

    public String getHistoryTitle() {
        return mHistoryTitle;
    }

    public void setHistoryTitle(String historyTitle) {
        mHistoryTitle = historyTitle;
    }

    public String getHistoryDescription() {
        return mHistoryDescription;
    }

    public void setHistoryDescription(String historyDescription) {
        mHistoryDescription = historyDescription;
    }

    public ArrayList<String> getHistoryImages() {
        return mHistoryImages;
    }

    public void setHistoryImages(ArrayList<String> historyImages) {
        mHistoryImages = historyImages;
    }
}
