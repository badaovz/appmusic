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
import com.example.datvl.testcn.Model.Album;
import com.example.datvl.testcn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllalbumAdapter extends RecyclerView.Adapter<AllalbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Album> arrayListalbum;

    public AllalbumAdapter(Context context, ArrayList<Album> arrayListalbum) {
        this.context = context;
        this.arrayListalbum = arrayListalbum;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_theloaiforchude,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Album album = arrayListalbum.get(position);
        Picasso.with(context).load(album.getHinhanhAlbum()).into(holder.hinhalbum);
        holder.tenalbum.setText(album.getTenAlbum());
    }

    @Override
    public int getItemCount() {
        return arrayListalbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView hinhalbum;
        TextView tenalbum;
        public ViewHolder(View itemView) {
            super(itemView);
            hinhalbum = itemView.findViewById(R.id.ivtheloaiforchude);
            tenalbum  = itemView.findViewById(R.id.tvtheloaiforchude);
            hinhalbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("idalbum",arrayListalbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

}
