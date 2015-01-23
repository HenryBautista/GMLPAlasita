package com.alasitaappdroid.model;

/**
 * Created by Arun on 01/19/2015.
 */
public class Event {

    private String mEventDate;
    private String mEventActivity;
    private String mEventPlace;
    private String mEventTime;
    private String mEventManager;

    public Event() {
    }

    public String getEventDate() {
        return mEventDate;
    }

    public void setEventDate(String eventDate) {
        mEventDate = eventDate;
    }

    public String getEventActivity() {
        return mEventActivity;
    }

    public void setEventActivity(String eventActivity) {
        mEventActivity = eventActivity;
    }

    public String getEventPlace() {
        return mEventPlace;
    }

    public void setEventPlace(String eventPlace) {
        mEventPlace = eventPlace;
    }

    public String getEventTime() {
        return mEventTime;
    }

    public void setEventTime(String eventTime) {
        mEventTime = eventTime;
    }

    public String getEventManager() {
        return mEventManager;
    }

    public void setEventManager(String eventManager) {
        mEventManager = eventManager;
    }
}
