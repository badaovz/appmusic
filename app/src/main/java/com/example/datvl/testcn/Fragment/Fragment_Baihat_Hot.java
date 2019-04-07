package com.example.datvl.testcn.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.datvl.testcn.Adapter.BaihatytAdapter;
import com.example.datvl.testcn.Model.Baihat;
import com.example.datvl.testcn.Model.Quangcao;
import com.example.datvl.testcn.R;
import com.example.datvl.testcn.Service.APIClient;
import com.example.datvl.testcn.Service.RequestApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Fragment_Baihat_Hot extends Fragment{
    View view;
    RecyclerView rvahyeuthich;
    BaihatytAdapter baihatytAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baihat_yeu_thich,container,false);
        rvahyeuthich = view.findViewById(R.id.rcvbhyeuthich);
        GetData();
        return view;
    }

    private void GetData() {
        Retrofit retrofit = APIClient.getClient();
        RequestApi requestApi = retrofit.create(RequestApi.class);
        Call<List<Baihat>> callback = requestApi.GetBaihatYeuthich();

        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> arrbaihat = (ArrayList<Baihat>) response.body();
                baihatytAdapter = new BaihatytAdapter(getActivity(),arrbaihat);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                rvahyeuthich.setLayoutManager(linearLayoutManager);
                rvahyeuthich.setAdapter(baihatytAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
