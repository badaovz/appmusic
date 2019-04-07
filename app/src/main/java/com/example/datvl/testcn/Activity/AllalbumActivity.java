package com.example.datvl.testcn.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.datvl.testcn.Adapter.AllalbumAdapter;
import com.example.datvl.testcn.Adapter.TheloaiforchudeAdapter;
import com.example.datvl.testcn.Model.Album;
import com.example.datvl.testcn.R;
import com.example.datvl.testcn.Service.APIClient;
import com.example.datvl.testcn.Service.RequestApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AllalbumActivity extends AppCompatActivity {
    Toolbar toolbarallalbum;
    RecyclerView recyclerViewallalbum;
    ArrayList<Album> arrallalbum;
    AllalbumAdapter allalbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allalbum);
        anhxa();
        init();
        GetData();
    }

    private void GetData() {
        Retrofit retrofit = APIClient.getClient();
        RequestApi requestApi = retrofit.create(RequestApi.class);
        Call<List<Album>> callback = requestApi.Getallalbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                arrallalbum = (ArrayList<Album>) response.body();
                allalbumAdapter = new AllalbumAdapter(AllalbumActivity.this,arrallalbum);
                recyclerViewallalbum.setLayoutManager(new GridLayoutManager(AllalbumActivity.this,2));
                recyclerViewallalbum.setAdapter(allalbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbarallalbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All Album");
        toolbarallalbum.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbarallalbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbarallalbum      = findViewById(R.id.tballalbum);
        recyclerViewallalbum = findViewById(R.id.rvallalbum);
    }
}
