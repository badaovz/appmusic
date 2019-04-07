package com.example.datvl.testcn.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.datvl.testcn.Adapter.AllchudeAdapter;
import com.example.datvl.testcn.Adapter.AllplaylistAdapter;
import com.example.datvl.testcn.Model.ChuDe;
import com.example.datvl.testcn.Model.Playlist;
import com.example.datvl.testcn.R;
import com.example.datvl.testcn.Service.APIClient;
import com.example.datvl.testcn.Service.RequestApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DanhsachallchudeActivity extends AppCompatActivity {
    RecyclerView recyclerViewallchude;
    Toolbar toolbarallchude;
    ArrayList<ChuDe> arrayallchude;
    AllchudeAdapter allchudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachallchude);

        anhxa();
        init();
        GetData();

    }

    private void GetData() {
        Retrofit retrofit = APIClient.getClient();
        RequestApi requestApi = retrofit.create(RequestApi.class);
        Call<List<ChuDe>> callback = requestApi.Getallchude();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                arrayallchude = (ArrayList<ChuDe>) response.body();
                allchudeAdapter = new AllchudeAdapter(DanhsachallchudeActivity.this,arrayallchude);
                recyclerViewallchude.setLayoutManager(new GridLayoutManager(DanhsachallchudeActivity.this,1));
                recyclerViewallchude.setAdapter(allchudeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        recyclerViewallchude = findViewById(R.id.rvallchude);
        toolbarallchude      = findViewById(R.id.tballchude);
    }

    private void init() {
        setSupportActionBar(toolbarallchude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbarallchude.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbarallchude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
