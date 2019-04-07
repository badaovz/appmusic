package com.example.datvl.testcn.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.datvl.testcn.Adapter.AllplaylistAdapter;
import com.example.datvl.testcn.Model.Playlist;
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

public class DanhsachallplaylistActivity extends AppCompatActivity {

    Toolbar toolbarallplaylist;
    RecyclerView recyclerViewallplaylist;
    AllplaylistAdapter allplaylistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachallplaylist);
        anhxa();
        init();
        GetData();
    }

    private void GetData() {
        Retrofit retrofit = APIClient.getClient();
        RequestApi requestApi = retrofit.create(RequestApi.class);
        Call<List<Playlist>> callback = requestApi.Getallplaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> arallplaylist = (ArrayList<Playlist>) response.body();
                allplaylistAdapter = new AllplaylistAdapter(DanhsachallplaylistActivity.this,arallplaylist);
                recyclerViewallplaylist.setLayoutManager(new GridLayoutManager(DanhsachallplaylistActivity.this,2));
                recyclerViewallplaylist.setAdapter(allplaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbarallplaylist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play List");
        toolbarallplaylist.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbarallplaylist.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbarallplaylist = findViewById(R.id.tbdsalplaylist);
        recyclerViewallplaylist = findViewById(R.id.rvallplaylist);
    }
}
