package com.example.datvl.testcn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datvl.testcn.Activity.DanhsachbaihatActivity;
import com.example.datvl.testcn.Model.Playlist;
import com.example.datvl.testcn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllplaylistAdapter extends RecyclerView.Adapter<AllplaylistAdapter.ViewHolder>{

    Context context;
    ArrayList<Playlist> arrayplaylist;

    public AllplaylistAdapter(Context context, ArrayList<Playlist> arrayplaylist) {
        this.context = context;
        this.arrayplaylist = arrayplaylist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_allplaylist,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Playlist playlist = arrayplaylist.get(position);
        Picasso.with(context).load(playlist.getHinhPlaylist()).into(holder.hinplaylist);
        holder.tenplaylist.setText(playlist.getTen());
    }

    @Override
    public int getItemCount() {
        return arrayplaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView hinplaylist;
        TextView tenplaylist;
        public ViewHolder(View itemView) {
            super(itemView);

            hinplaylist = itemView.findViewById(R.id.ivallplaylist);
            tenplaylist = itemView.findViewById(R.id.tvtenallplaylist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist",arrayplaylist.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
