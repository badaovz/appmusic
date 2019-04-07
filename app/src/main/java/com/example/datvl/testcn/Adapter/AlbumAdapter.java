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

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Album> arralbum;

    public AlbumAdapter(Context context, ArrayList<Album> arralbum) {
        this.context = context;
        this.arralbum = arralbum;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Album album = arralbum.get(position);
        holder.tencasialbum.setText(album.getTencasiAlbum());
        holder.tenalbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhanhAlbum()).into(holder.hinhalbum);
    }

    @Override
    public int getItemCount() {
        return arralbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView hinhalbum;
        TextView tenalbum,tencasialbum;
        public ViewHolder(View itemView) {
            super(itemView);
            hinhalbum = itemView.findViewById(R.id.ivalbum);
            tenalbum  = itemView.findViewById(R.id.tvtenalbum);
            tencasialbum = itemView.findViewById(R.id.tvtencsialbum);
            hinhalbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("idalbum",arralbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

}
