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

import com.example.datvl.testcn.Activity.DanhsachbaihatActivity;
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

public class SearchbaihatAdapter extends RecyclerView.Adapter<SearchbaihatAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> albaihat;

    public SearchbaihatAdapter(Context context, ArrayList<Baihat> albaihat) {
        this.context = context;
        this.albaihat = albaihat;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_searchbaihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Baihat  baihat = albaihat.get(position);
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.hinhbh);
        holder.tenbh.setText(baihat.getTenbaihat());
        holder.tencasi.setText(baihat.getCasi());
    }

    @Override
    public int getItemCount() {
        return albaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView hinhbh, iconlove;
        TextView tenbh, tencasi;
        public ViewHolder(View itemView) {
            super(itemView);

            hinhbh = itemView.findViewById(R.id.ivbaihatsearch);
            iconlove = itemView.findViewById(R.id.ivlovesearch);
            tenbh   =  itemView.findViewById(R.id.tvtenbhsearch);
            tencasi  = itemView.findViewById(R.id.tvtencasisearch);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlaymusicActivity.class);
                    intent.putExtra("cakhuc",albaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });

            iconlove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iconlove.setImageResource(R.drawable.iconloved);
                    Retrofit retrofit = APIClient.getClient();
                    RequestApi requestApi = retrofit.create(RequestApi.class);
                    Call<String> callback = requestApi.Getupdateloutthich("1", albaihat.get(getPosition()).getIdbaihat());
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
        }
    }
}
