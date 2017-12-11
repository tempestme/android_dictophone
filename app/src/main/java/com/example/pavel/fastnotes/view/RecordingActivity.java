package com.example.pavel.fastnotes.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pavel.fastnotes.R;
import com.example.pavel.fastnotes.adapter.AudioAdapter;
import com.example.pavel.fastnotes.model.Audio_item;
import com.example.pavel.fastnotes.model.Recording;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;


public class RecordingActivity extends AppCompatActivity {
    private Recording recording;
    private FloatingActionButton recBtn;
    private Context context;
    private Button play;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Audio_item[] audioList;
    private AudioAdapter audioAdapter;
    private Activity activity;

    private final int WRITE_EXTERNAL_STORAGE = 201;
    private final int RECORD_AUDIO = 202;
    private final int VIBRATE = 203;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        //joda time library initialization
        JodaTimeAndroid.init(this); //temporary
        context = getBaseContext();
        activity = this;


        /** the list below is the our list of audio recordings.
         *
         */

        //audioList = new ArrayList<Audio_item>(); //this is it
        //prepareAudioData();

        /**
         * recyclerview
         */
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        audioAdapter = new AudioAdapter(audioList, recording);
        recyclerView.setAdapter(audioAdapter);


        /**
         * listners
         */
        recBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==motionEvent.ACTION_DOWN){
                    //press button event
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                    recording.startRecording(audioList);
                    return true;
                }
                if(motionEvent.getAction()==motionEvent.ACTION_UP){
                    //release button event
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                    recording.stopRecording();
                    audioAdapter.notifyDataSetChanged();
                    return true;
                }
                return false;            }
        });




        play = (Button) findViewById(R.id.btnPlay);
        recBtn = (FloatingActionButton) findViewById(R.id.btnRecord);

        recording = new Recording(context);

        //recording.playRecording(play);
        //audioList.add(new Audio_item("loh"));


         //todo: this is test code for debug version, it will be refacrored in release version.
        /**
         * all next code is legit only in debug version and added only for tests.
         * it will be moved to the recording class or deleted;
         */
        //TextView fileName = (TextView)findViewById(R.id.recordId); //test filename line
        //TextView timer = (TextView)findViewById(R.id.timer);
        //TextView date = (TextView)findViewById(R.id.date);
        //addTime(fileName);
        //setTime(timer);
        //addDate(date);

    }



    public void addTime(TextView fn){
        DateTime dateTime = new DateTime();
        fn.setText(fn.getText() + Integer.toString(dateTime.getDayOfYear()));
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


    /**
     *  This is permission handler section
     *  in this project will be requested 3 permissions
     *  WRITE_EXTERNAL_STORAGE
     *  RECORD_AUDIO
     *  VIBRATE
     *
     *
     *              if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
     * ***** this code used for compatibility with android 5 and older versions
     */


    private void askPermissions(String permission, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            }
            else {
                Log.e("permission", "already have permission " + Integer.toString(requestCode));
            }
        }
    }


    /**
     *  Overriding request permission callback.
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case WRITE_EXTERNAL_STORAGE:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Log.e("permission", "Permission WRITE_EXTERNAL_STORAGE granted" );
                }
            case RECORD_AUDIO:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Log.e("permission", "Permission RECORD_AUDIO granted" );
                }
            case VIBRATE:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Log.e("permission", "Permission VIBRATE granted" );
                }
        }
    }
}
