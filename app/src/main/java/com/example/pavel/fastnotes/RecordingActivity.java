package com.example.pavel.fastnotes;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class RecordingActivity extends AppCompatActivity {
    private Button play, stop, record;
    private MediaRecorder audioRecorder;
    private String outputFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        play = (Button) findViewById(R.id.playButton);
        stop = (Button) findViewById(R.id.stopButton);
        record = (Button) findViewById(R.id.recordButton);

        stop.setEnabled(false);
        play.setEnabled(false);
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";

        audioRecorder = new MediaRecorder();
        audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        audioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        audioRecorder.setOutputFile(outputFile);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    audioRecorder.prepare();
                    audioRecorder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                record.setEnabled(false);
                stop.setEnabled(true);

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioRecorder.stop();
                audioRecorder.release();
                audioRecorder = null;

                stop.setEnabled(false);
                play.setEnabled(true);

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer m = new MediaPlayer();

                try{
                    m.setDataSource(outputFile);
                    m.prepare();
                }
                catch (IOException e){
                    Toast.makeText(RecordingActivity.this, "LOH", Toast.LENGTH_SHORT).show();
                }
                m.start();
            }
        });


    }
}
