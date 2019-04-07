package com.example.datvl.testcn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datvl.testcn.Activity.DanhsachTheloaitheoChudeActivity;
import com.example.datvl.testcn.Model.ChuDe;
import com.example.datvl.testcn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllchudeAdapter extends RecyclerView.Adapter<AllchudeAdapter.ViewHolder>{

    Context context;
    ArrayList<ChuDe> arrayListallchude;

    public AllchudeAdapter(Context context, ArrayList<ChuDe> arrayListallchude) {
        this.context = context;
        this.arrayListallchude = arrayListallchude;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_allchude,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChuDe chuDe = arrayListallchude.get(position);
        holder.tenchude.setText(chuDe.getTenChuDe());
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(holder.hinhchude);
    }

    @Override
    public int getItemCount() {
        return arrayListallchude.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView hinhchude;
        TextView tenchude;
        public ViewHolder(View itemView) {
            super(itemView);
            tenchude  = itemView.findViewById(R.id.tvtenchude);
            hinhchude = itemView.findViewById(R.id.ivxemthemallchude);
            hinhchude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachTheloaitheoChudeActivity.class);
                    intent.putExtra("chude",arrayListallchude.get(getPosition()));
                    context.startActivity(intent);

                }
            });
        }
    }
}
