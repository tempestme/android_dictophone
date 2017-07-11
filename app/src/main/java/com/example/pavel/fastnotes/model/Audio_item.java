package com.example.pavel.fastnotes.model;


import android.media.MediaPlayer;

import org.joda.time.DateTime;

/**
 * Created by pavel on 05.07.17.
 */

public class Audio_item {
    //String title;
    private String outPutFile;
    private String date;
    private int length;
    private DateTime dateTime;

    public Audio_item(String outPutFile, MediaPlayer m) {
        this.outPutFile = outPutFile;
        dateTime = new DateTime();
        this.date = Integer.toString(dateTime.getDayOfMonth())+"."+
                Integer.toString(dateTime.getMonthOfYear())+"."+
                Integer.toString(dateTime.getYear());
                length = m.getDuration();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    private void GenerateTitle(){
        dateTime = new DateTime();
        //return title;
    }
}
