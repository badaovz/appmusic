package com.example.datvl.testcn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datvl.testcn.Activity.PlaymusicActivity;
import com.example.datvl.testcn.Model.Baihat;
import com.example.datvl.testcn.R;
import com.example.datvl.testcn.Service.APIClient;
import com.example.datvl.testcn.Service.RequestApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BaihatytAdapter extends RecyclerView.Adapter<BaihatytAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> arrbaihatyt;

    public BaihatytAdapter(Context context, ArrayList<Baihat> arrbaihatyt) {
        this.context = context;
        this.arrbaihatyt = arrbaihatyt;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_baihat_yeuthich,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Baihat baihat = arrbaihatyt.get(position);

        holder.tenbhyt.setText(baihat.getTenbaihat());
        holder.tencasibhyt.setText(baihat.getCasi());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.hinhbhyt);

    }

    @Override
    public int getItemCount() {
        return arrbaihatyt.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tenbhyt,tencasibhyt;
        ImageView hinhbhyt,iconlove;

        public ViewHolder(View itemView) {
            super(itemView);

            tenbhyt = itemView.findViewById(R.id.tvtenbhyt);
            tencasibhyt = itemView.findViewById(R.id.tvcasibhyt);
            hinhbhyt = itemView.findViewById(R.id.ivhinhbhyt);
            iconlove = itemView.findViewById(R.id.ivicyeuthich);

            iconlove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iconlove.setImageResource(R.drawable.iconloved);
                    Retrofit retrofit = APIClient.getClient();
                    RequestApi requestApi = retrofit.create(RequestApi.class);
                    Call<String> callback = requestApi.Getupdateloutthich("1", arrbaihatyt.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            String kq = response.body();
                            if(kq.equals("success!")){
                                Toast.makeText(context,"da thich",Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(context,"loi!!",Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    iconlove.setEnabled(false);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlaymusicActivity.class);
                    intent.putExtra("cakhuc", arrbaihatyt.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
