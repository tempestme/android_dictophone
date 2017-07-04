package com.example.pavel.fastnotes;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
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
        Activity activity = this;

        play = (Button)findViewById(R.id.btnPlay);
        recBtn = (FloatingActionButton)findViewById(R.id.btnRecord);

        recording = new Recording(play, recBtn, context, activity);
        //recording.playRecording(play);
    }
}
