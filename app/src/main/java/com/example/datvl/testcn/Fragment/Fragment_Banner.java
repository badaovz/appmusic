package com.example.datvl.testcn.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datvl.testcn.Adapter.BannerAdapter;
import com.example.datvl.testcn.Model.Quangcao;
import com.example.datvl.testcn.R;
import com.example.datvl.testcn.Service.APIClient;
import com.example.datvl.testcn.Service.RequestApi;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Fragment_Banner extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int curentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner,container,false);
        anhxa();
        GetData();
        return view;
    }

    private void anhxa() {
        viewPager       = view.findViewById(R.id.myViewPager);
        circleIndicator = view.findViewById(R.id.myIndicator);
    }

    private void GetData() {
        Retrofit retrofit = APIClient.getClient();
        RequestApi requestApi = retrofit.create(RequestApi.class);
        Call<List<Quangcao>> callback = requestApi.GetQuangcao();


        callback.enqueue(new Callback<List<Quangcao>>() {
            @Override
            public void onResponse(Call<List<Quangcao>> call, Response<List<Quangcao>> response) {
                ArrayList<Quangcao> banners = (ArrayList<Quangcao>) response.body();

                Log.d("AAA","da vao day");

                bannerAdapter = new BannerAdapter(getActivity(),banners);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        curentItem = viewPager.getCurrentItem();
                        curentItem++;
                        if(curentItem >= viewPager.getAdapter().getCount()){
                            curentItem = 0;
                        }
                        viewPager.setCurrentItem(curentItem,true);
                        handler.postDelayed(runnable,4500);
                    }
                };

                handler.postDelayed(runnable,4500);
            }

            @Override
            public void onFailure(Call<List<Quangcao>> call, Throwable t) {
                Log.d("BBB","loir");
            }
        });
    }
}
