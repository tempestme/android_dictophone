package com.example.pavel.fastnotes.model;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import org.joda.time.DateTime;

import java.io.IOException;


/**
 * Created by pavel on 27.06.17.
 */

public class Recording {
    private MediaRecorder mediaRecorder;
    private String outputFile = null;
    private FloatingActionButton fab;
    private Vibrator vibrator;
    private MediaPlayer player;




    public Recording(Button playBtn, FloatingActionButton fab, final Context context, final Activity activity){
        vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        player = new MediaPlayer();
        //final Activity activity = (Activity)context;
        final MediaPlayer m = new MediaPlayer();
        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                if(motionEvent.getAction()==motionEvent.ACTION_DOWN){
                    //press button event
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                    startRecording();
                    return true;
                }
                if(motionEvent.getAction()==motionEvent.ACTION_UP){

                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                    stopRecording();
                    return true;
                }
                return false;
            }
        });


        playBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try{
                    m.setDataSource(outputFile);
                    m.prepare();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                m.start();
                m.getDuration();
                vibrator.vibrate(50);
                return true;
            }
        });

    }
    private void startRecording(){
        try {
            //outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/newrecording.3gp";
            outputFile = generateName();
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(outputFile);
            vibrator.vibrate(75);
            mediaRecorder.prepare();
            mediaRecorder.start();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }
    private void stopRecording(){
        try{
            vibrator.vibrate(75);
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            return;
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    private String generateName(){
        String name = new String();
        DateTime dateTime = new DateTime();
        name = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/" + Integer.toString(dateTime.getDayOfYear())+Integer.toString(dateTime.getSecondOfDay())+"3gp";
        return name;
    }

    /*
    private void playRecording(){
        final MediaPlayer m = new MediaPlayer();
        playBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try{
                    m.setDataSource(outputFile);
                    m.prepare();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                m.start();
                vibrator.vibrate(50);
                return true;
            }
        });

    }*/

}
