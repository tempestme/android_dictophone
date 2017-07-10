package com.example.pavel.fastnotes.model;


import org.joda.time.DateTime;

/**
 * Created by pavel on 05.07.17.
 */

public class Audio_item {
    String title;
    String outPutFile;
    String date;
    int length;
    DateTime dateTime;

    public Audio_item(String title, String date, int length, String outPutFile) {
        this.title = title;
        this.date = date;
        this.length = length;
        this.outPutFile = outPutFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
