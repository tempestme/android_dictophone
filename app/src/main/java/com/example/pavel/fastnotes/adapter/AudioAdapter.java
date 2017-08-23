package com.example.pavel.fastnotes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pavel.fastnotes.R;
import com.example.pavel.fastnotes.model.Audio_item;

import java.util.List;

/**
 * Created by pavel on 02.08.17.
 */

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.MyViewHolder>{

    private List<Audio_item> AudioList;


    public AudioAdapter(List<Audio_item> audioList){
        this.AudioList = audioList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.audio_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Audio_item audio_item = AudioList.get(position);
        holder.date.setText(audio_item.getDate());
        holder.outputFile.setText(audio_item.getTitle());
        holder.duration.setText("01:30"); //todo:THIS IS TEST LINE, need to implement real Getduration() method in audio_item class
    }

    @Override
    public int getItemCount() {
        return AudioList.size();
    }





    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView outputFile, date, duration;


        public MyViewHolder(View view){
            super(view);
            outputFile = (TextView)view.findViewById(R.id.filenameTv);
            date = (TextView)view.findViewById(R.id.dateTv);
            duration = (TextView)view.findViewById(R.id.timeTv);


        }
    }
}
