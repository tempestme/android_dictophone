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
import android.widget.TextView;

import com.example.pavel.fastnotes.adapter.AudioAdapter;
import com.example.pavel.fastnotes.view.RecordingActivity;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.List;


/**
 * Created by pavel on 27.06.17.
 */

public class Recording {
    private MediaRecorder mediaRecorder;
    private String outputFile = null;
    private Vibrator vibrator;
    private MediaPlayer player;
    private Context context;
    private List list;
    private MediaPlayer m;
    private AudioAdapter audioAdapter;




    public Recording(Button playBtn,
                     final FloatingActionButton fab,
                     final Context context,
                     final Activity activity,
                     final List list,
                     final AudioAdapter audioAdapter){
        this.context = context;
        this.list = list;
        //this.audioAdapter = audioAdapter;

        vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        player = new MediaPlayer();

        m = new MediaPlayer();



        fab.setOnTouchListener(  new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                if(motionEvent.getAction()==motionEvent.ACTION_DOWN){
                    //press button event
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                    startRecording();
                    return true;
                }
                if(motionEvent.getAction()==motionEvent.ACTION_UP){
                    //release button event
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                    stopRecording();
                    list.add(new Audio_item(outputFile));
                    //audioAdapter.notifyDataSetChanged();
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

    public void SetPlayButton(FloatingActionButton button, final String outputFile){
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try {
                    m.setDataSource(outputFile);
                    m.prepare();
                } catch (IOException e) {
                    e.printStackTrace();

                }
                m.start();
                return true;
            }
        });
    }


    public void startRecording(){
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


    public void stopRecording(){
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

    public void addDate(TextView date){
        DateTime dateTime = new DateTime();
        date.setText(
                Integer.toString(dateTime.getDayOfMonth())+"."+
                        Integer.toString(dateTime.getMonthOfYear())+"."+
                        Integer.toString(dateTime.getYear())
        );
    }

    private void setTime(TextView timer){
        String normalTime = new String();
        String Hour = new String();
        String Minute = new String();
        DateTime dateTime = new DateTime();
        Hour = Integer.toString(dateTime.getHourOfDay());
        Minute = Integer.toString(dateTime.getMinuteOfHour());

        if (Hour.length()==1){
            normalTime =  normalTime + "0" + Hour;
        }
        else{
            normalTime+=Hour;
        }
        normalTime+=":";
        if (Minute.length()==1){
            normalTime = normalTime + "0" + Minute;
        }
        else{
            normalTime+=Minute;
        }

        timer.setText(normalTime);
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
