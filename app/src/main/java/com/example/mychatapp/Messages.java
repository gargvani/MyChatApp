package com.example.mychatapp;

public class Messages {

    String message;
    String senderID;
    long timesmap;
    String currenttime;

    public Messages() {
    }

    public Messages(String message, String senderID, long timesmap, String currenttime) {
        this.message = message;
        this.senderID = senderID;
        this.timesmap = timesmap;
        this.currenttime = currenttime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public long getTimesmap() {
        return timesmap;
    }

    public void setTimesmap(long timesmap) {
        this.timesmap = timesmap;
    }

    public String getCurrenttime() {
        return currenttime;
    }

    public void setCurrenttime(String currenttime) {
        this.currenttime = currenttime;
    }
}
