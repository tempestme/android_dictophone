package com.example.pavel.fastnotes;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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




    public Recording(FloatingActionButton fab, Context context){
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/newrecording.3gp";
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(outputFile);
        vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        player = new MediaPlayer();


        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==motionEvent.ACTION_DOWN){
                    startRecording();
                    return true;
                }
                if(motionEvent.getAction()==motionEvent.ACTION_UP){
                    stopRecording();
                    return true;
                }
                return false;
            }
        });

    }
    private void startRecording(){
        try {
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

    public void playRecording(Button play){
        final MediaPlayer m = new MediaPlayer();

        play.setOnTouchListener(new View.OnTouchListener() {
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

    }

}
