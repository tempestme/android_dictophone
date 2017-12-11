package com.example.pavel.fastnotes.adapter;

import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pavel.fastnotes.R;
import com.example.pavel.fastnotes.model.Audio_item;
import com.example.pavel.fastnotes.model.Recording;

import java.util.List;

import static android.R.id.list;

/**
 * Created by pavel on 02.08.17.
 */

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.MyViewHolder>{

    private Audio_item AudioList[];
    private Recording recording;


    public AudioAdapter(Audio_item[] audioList, Recording recording){
        this.recording = recording;
        this.AudioList = audioList;


    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.audio_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Audio_item audio_item = AudioList[position];
        holder.date.setText(audio_item.getDate());
        holder.outputFile.setText(audio_item.getTitle());
        holder.duration.setText("01:30"); //todo:THIS IS TEST LINE, need to implement real Getduration() method in audio_item



        //recording.SetPlayButton(holder.playFab, AudioList.get(position).getTitle());

    }



    @Override
    public int getItemCount() {
        return AudioList.length;
    }







    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView outputFile, date, duration;
        FloatingActionButton playFab;


        public MyViewHolder(View view){
            super(view);
            outputFile = (TextView)view.findViewById(R.id.filenameTv);
            date = (TextView)view.findViewById(R.id.dateTv);
            duration = (TextView)view.findViewById(R.id.timeTv);
            playFab = (FloatingActionButton)view.findViewById(R.id.playFab);



        }
    }
}
