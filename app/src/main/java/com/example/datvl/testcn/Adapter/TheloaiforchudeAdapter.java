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
import com.example.datvl.testcn.Model.TheLoai;
import com.example.datvl.testcn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TheloaiforchudeAdapter extends RecyclerView.Adapter<TheloaiforchudeAdapter.ViewHolder>{
    Context context;
    ArrayList<TheLoai> arrayListtheloai;

    public TheloaiforchudeAdapter(Context context, ArrayList<TheLoai> arrayListtheloai) {
        this.context = context;
        this.arrayListtheloai = arrayListtheloai;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_theloaiforchude,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TheLoai theLoai = arrayListtheloai.get(position);
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.hinhtheloai);
        holder.tentheloai.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return arrayListtheloai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView hinhtheloai;
        TextView tentheloai;
        public ViewHolder(View itemView) {
            super(itemView);
            hinhtheloai = itemView.findViewById(R.id.ivtheloaiforchude);
            tentheloai  = itemView.findViewById(R.id.tvtheloaiforchude);
            hinhtheloai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("idtheloai",arrayListtheloai.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
