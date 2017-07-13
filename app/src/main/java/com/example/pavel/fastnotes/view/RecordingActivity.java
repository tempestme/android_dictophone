package com.example.pavel.fastnotes.view;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pavel.fastnotes.R;
import com.example.pavel.fastnotes.model.Recording;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;

import java.util.Date;


public class RecordingActivity extends AppCompatActivity {
    private Recording recording;
    private FloatingActionButton recBtn;
    private Context context;
    private Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        //joda time library initialization
        JodaTimeAndroid.init(this); //temporary
        context = getBaseContext();
        Activity activity = this;

        play = (Button) findViewById(R.id.btnPlay);
        recBtn = (FloatingActionButton) findViewById(R.id.btnRecord);

        recording = new Recording(play, recBtn, context, activity);
        //recording.playRecording(play);


        //todo: this is test code for debug version, it will be refacrored in release version.
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

    /*
    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
        @Override
        public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public CustomViewHolder(View itemView) {
                super(itemView);
            }

            @Override
            public void onClick(View view) {

            }
        }
    }*/
}
