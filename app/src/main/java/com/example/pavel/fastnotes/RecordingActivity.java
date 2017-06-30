package com.example.pavel.fastnotes;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class RecordingActivity extends AppCompatActivity {
    private Recording recording;
    private FloatingActionButton recBtn;
    private Context context;
    private Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        context = getBaseContext();
        recBtn = (FloatingActionButton)findViewById(R.id.btnRecord);
        recording = new Recording(recBtn, context);
        play = (Button)findViewById(R.id.btnPlay);

        recording.playRecording(play);
    }
}
