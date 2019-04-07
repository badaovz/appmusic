package com.example.datvl.testcn.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.datvl.testcn.Adapter.TheloaiforchudeAdapter;
import com.example.datvl.testcn.Model.ChuDe;
import com.example.datvl.testcn.Model.Quangcao;
import com.example.datvl.testcn.Model.TheLoai;
import com.example.datvl.testcn.R;
import com.example.datvl.testcn.Service.APIClient;
import com.example.datvl.testcn.Service.RequestApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DanhsachTheloaitheoChudeActivity extends AppCompatActivity {

    Toolbar toolbartheloaiforchude;
    RecyclerView recyclerViewtheloaiforchude;
    TheloaiforchudeAdapter theloaiforchudeAdapter;
    ArrayList<TheLoai> artheloai;

    ChuDe chuDe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_theloaitheo_chude);
        anhxa();
        Getinten();
        init();
        GetData();
    }

    private void GetData() {
        Retrofit retrofit = APIClient.getClient();
        RequestApi requestApi = retrofit.create(RequestApi.class);
        Call<List<TheLoai>> callback = requestApi.Gettheloaiforchude(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                artheloai = (ArrayList<TheLoai>) response.body();
                theloaiforchudeAdapter = new TheloaiforchudeAdapter(DanhsachTheloaitheoChudeActivity.this,artheloai);
                recyclerViewtheloaiforchude.setLayoutManager(new GridLayoutManager(DanhsachTheloaitheoChudeActivity.this,2));
                recyclerViewtheloaiforchude.setAdapter(theloaiforchudeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });


    }

    private void init() {
        setSupportActionBar(toolbartheloaiforchude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbartheloaiforchude.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbartheloaiforchude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                }
            });
    }

    private void Getinten() {
        Intent intent = getIntent();
        if(intent.hasExtra("chude")){
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
        }
    }

    private void anhxa() {
        toolbartheloaiforchude = findViewById(R.id.tbtheloaiforchude);
        recyclerViewtheloaiforchude = findViewById(R.id.rvtheoloaiforchude);
    }
}
