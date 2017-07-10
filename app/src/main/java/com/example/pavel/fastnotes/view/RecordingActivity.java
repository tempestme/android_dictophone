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
        JodaTimeAndroid.init(this);
        context = getBaseContext();
        Activity activity = this;
        TextView fileName = (TextView)findViewById(R.id.recordId); //test filename line

        play = (Button) findViewById(R.id.btnPlay);
        recBtn = (FloatingActionButton) findViewById(R.id.btnRecord);

        recording = new Recording(play, recBtn, context, activity);
        //recording.playRecording(play);

        addTime(fileName);


    }

    public void addTime(TextView fn){
        DateTime dateTime = new DateTime();
        fn.setText(fn.getText() + Integer.toString(dateTime.getDayOfYear()));
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
