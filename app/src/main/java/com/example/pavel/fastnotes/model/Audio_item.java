package com.example.pavel.fastnotes.model;


import android.media.MediaPlayer;

import com.example.pavel.fastnotes.adapter.AudioAdapter;

import org.joda.time.DateTime;

/**
 * class created for storing information of recorded audio files
 * object contain date of recording, output file name, duration of recording.
 */

public class Audio_item{
    //String title;
    private String outPutFile;
    private String date;
    private int length;
    private DateTime dateTime;

    public Audio_item(String outPutFile) {
        this.outPutFile = outPutFile;
        dateTime = new DateTime();
        this.date = Integer.toString(dateTime.getDayOfMonth())+"."+
                Integer.toString(dateTime.getMonthOfYear())+"."+
                Integer.toString(dateTime.getYear());
    }

    public Audio_item(){

    }

    public String getDate() {
        return date ;
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

    public String getTitle(){return outPutFile;}

    private void GenerateTitle(){
        dateTime = new DateTime();
        //return title;
    }
}
