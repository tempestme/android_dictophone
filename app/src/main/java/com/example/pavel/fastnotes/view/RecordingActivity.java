package com.example.pavel.fastnotes.view;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private List<Audio_item> audioList;
    private AudioAdapter audioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        //joda time library initialization
        JodaTimeAndroid.init(this); //temporary
        context = getBaseContext();
        Activity activity = this;
        ActivityInfo activityInfo = new ActivityInfo();


        /** the list below is the our list of audio recordings.
         *  it passed to Recording object, and then after recording is done - recording object add new object to this list.
         */
        audioList = new ArrayList<Audio_item>(); //this is it
        prepareAudioData();

        /**
         * Then initialized recyclerview will be, behold!
         */
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        audioAdapter = new AudioAdapter(audioList);
        recyclerView.setAdapter(audioAdapter);





        play = (Button) findViewById(R.id.btnPlay);
        recBtn = (FloatingActionButton) findViewById(R.id.btnRecord);

        recording = new Recording(play, recBtn, context, activity, audioList);
        //todo:: create innerclass, and pass only object to Recording constructor, not a fucking bunch of it.
        //recording.playRecording(play);

        //audioList.add(new Audio_item("loh"));


         //todo: this is test code for debug version, it will be refacrored in release version.
        /**
         * all next code is legit only in debug version and added only for tests.
         * it will be moved to the recording class or deleted;
         */
        TextView fileName = (TextView)findViewById(R.id.recordId); //test filename line
        TextView timer = (TextView)findViewById(R.id.timer);
        TextView date = (TextView)findViewById(R.id.date);
        addTime(fileName);
        setTime(timer);
        addDate(date);



    }

    public void addDate(TextView date){
        DateTime dateTime = new DateTime();
        date.setText(
                        Integer.toString(dateTime.getDayOfMonth())+"."+
                        Integer.toString(dateTime.getMonthOfYear())+"."+
                        Integer.toString(dateTime.getYear())
        );
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

    private void prepareAudioData(){
        audioList.add(new Audio_item("ne"));
        audioList.add(new Audio_item("slujil"));
        audioList.add(new Audio_item("ne"));
        audioList.add(new Audio_item("mujik"));
        audioList.add(new Audio_item("!!!!"));
    }

    private class ActivityInfo{
        Activity activity;
        Context context;
        FloatingActionButton fab;
        Button playBtn;

        public Activity getActivity() {
            return activity;
        }

        public Context getContext() {
            return context;
        }

        public FloatingActionButton getFab() {
            return fab;
        }

        public Button getPlayBtn() {
            return playBtn;
        }

        public ActivityInfo(){
            activity = RecordingActivity.this;
            context = activity.getBaseContext();
            fab = (FloatingActionButton)findViewById(R.id.btnRecord);
            playBtn = (Button)findViewById(R.id.btnPlay);
        }
    }


}
