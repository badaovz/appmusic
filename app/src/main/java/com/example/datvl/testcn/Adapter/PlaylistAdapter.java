package com.example.datvl.testcn.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.datvl.testcn.Model.Playlist;
import com.example.datvl.testcn.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        TextView tenplaylist;
        ImageView bgplaylist, imageplaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.dong_playlist,null);
            viewHolder = new ViewHolder();

            viewHolder.tenplaylist = convertView.findViewById(R.id.tvtenbhplaylist);
            viewHolder.bgplaylist  = convertView.findViewById(R.id.ivbgplaylists);
            viewHolder.imageplaylist = convertView.findViewById(R.id.ivhinhbhplaylist);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Playlist playlist = getItem(position);
        Picasso.with(getContext()).load(playlist.getHinhPlaylist()).into(viewHolder.bgplaylist);
        Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.imageplaylist);
        viewHolder.tenplaylist.setText(playlist.getTen());
        return convertView;
    }
}
