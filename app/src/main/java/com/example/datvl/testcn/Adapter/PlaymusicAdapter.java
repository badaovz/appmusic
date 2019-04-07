package com.example.datvl.testcn.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.datvl.testcn.Model.Baihat;
import com.example.datvl.testcn.R;

import java.util.ArrayList;

public class PlaymusicAdapter extends RecyclerView.Adapter<PlaymusicAdapter.ViewHolder>{

    Context context;
    ArrayList<Baihat> mangbaihat;

    public PlaymusicAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_playmusic,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Baihat baihat = mangbaihat.get(position);
        holder.indexsong.setText(position + 1 + "");
        holder.namesong.setText(baihat.getTenbaihat());
        holder.namesinger.setText(baihat.getCasi());
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView indexsong, namesinger, namesong;
        public ViewHolder(View itemView) {
            super(itemView);

            indexsong = itemView.findViewById(R.id.tvindexplaymusic);
            namesinger = itemView.findViewById(R.id.tvtencasiplaymusic);
            namesong  = itemView.findViewById(R.id.tvtenbhplaymusic);
        }
    }
}
