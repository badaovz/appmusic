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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat>  mangbaihat;

    public DanhsachbaihatAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachbaihat,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Baihat baihat = mangbaihat.get(position);
        holder.tenbhdsbhqc.setText(baihat.getTenbaihat());
        holder.tencasidsbhqc.setText(baihat.getCasi());
        holder.thutudsbhqc.setText(position + 1 + "");

    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView thutudsbhqc, tenbhdsbhqc, tencasidsbhqc;
        ImageView iconlove;

        public ViewHolder(View itemView) {
            super(itemView);

            thutudsbhqc   = itemView.findViewById(R.id.tvthutubhqc);
            tenbhdsbhqc   = itemView.findViewById(R.id.tvtenbhdsbhqc);
            tencasidsbhqc = itemView.findViewById(R.id.tvtencasibhdsbhqc);
            iconlove      = itemView.findViewById(R.id.iviconlovedsbhqc);

            iconlove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iconlove.setImageResource(R.drawable.iconloved);
                    Retrofit retrofit = APIClient.getClient();
                    RequestApi requestApi = retrofit.create(RequestApi.class);
                    Call<String> callback = requestApi.Getupdateloutthich("1", mangbaihat.get(getPosition()).getIdbaihat());
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
                    intent.putExtra("cakhuc", mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }

}
