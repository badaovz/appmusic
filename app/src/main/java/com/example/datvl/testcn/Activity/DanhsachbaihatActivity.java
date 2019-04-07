package com.example.datvl.testcn.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.datvl.testcn.Adapter.DanhsachbaihatAdapter;
import com.example.datvl.testcn.Model.Album;
import com.example.datvl.testcn.Model.Baihat;
import com.example.datvl.testcn.Model.Playlist;
import com.example.datvl.testcn.Model.Quangcao;
import com.example.datvl.testcn.Model.TheLoai;
import com.example.datvl.testcn.R;
import com.example.datvl.testcn.Service.APIClient;
import com.example.datvl.testcn.Service.RequestApi;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DanhsachbaihatActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ImageView imgdsbaihat;
    ArrayList<Baihat> mangbaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;

    Quangcao quangcao;
    Playlist playlist;
    TheLoai theLoai;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        anhxa();
        Dataintent();
        init();
        if(quangcao != null && !quangcao.getTenbaihat().equals("")){
            SetValueInView(quangcao.getTenbaihat(),quangcao.getHinhbaihat());
            GetDataQuangCao(quangcao.getIdquangcao());
        }

        if(playlist != null && !playlist.getTen().equals("")){
            SetValueInView(playlist.getTen(),playlist.getHinhPlaylist());
            GetDataPlaylist(playlist.getIdPlaylist());
        }

        if(theLoai != null && !theLoai.getTenTheLoai().equals("")){
            SetValueInView(theLoai.getTenTheLoai(),theLoai.getHinhTheLoai());
            GetDataTheloai(theLoai.getIdTheLoai());
        }

        if(album != null && !album.getTenAlbum().equals("")){
            SetValueInView(album.getTenAlbum(),album.getHinhanhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }

    }

    private void GetDataAlbum(String idalbum) {
        Retrofit retrofit = APIClient.getClient();
        RequestApi requestApi = retrofit.create(RequestApi.class);
        Call<List<Baihat>> callback = requestApi.Getdsbhtheoalbum(idalbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter);
                eventclick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void GetDataTheloai(String idtheloai) {
        Retrofit retrofit = APIClient.getClient();
        RequestApi requestApi = retrofit.create(RequestApi.class);
        Call<List<Baihat>> callback = requestApi.Getdsbhtheotheloai(idtheloai);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter);
                eventclick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlaylist(String idplaylist) {
        Retrofit retrofit = APIClient.getClient();
        RequestApi requestApi = retrofit.create(RequestApi.class);
        Call<List<Baihat>> callback = requestApi.Getdsbhtheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter);
                eventclick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void GetDataQuangCao(String idquangcao) {
        Retrofit retrofit = APIClient.getClient();
        RequestApi requestApi = retrofit.create(RequestApi.class);
        Call<List<Baihat>> callback = requestApi.Getdsbhtheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerView.setAdapter(danhsachbaihatAdapter);
                eventclick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {
                Log.d("SFF","khong hung dc du lieu");
            }
        });
    }

    private void SetValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgdsbaihat);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void anhxa() {
        coordinatorLayout = findViewById(R.id.clbaihat);
        collapsingToolbarLayout = findViewById(R.id.ctlbaihat);
        toolbar = findViewById(R.id.tbbaihat);
        recyclerView = findViewById(R.id.rcvbaihatdsqc);
        floatingActionButton = findViewById(R.id.fabbaihat);
        imgdsbaihat = findViewById(R.id.ivbaihat);
        floatingActionButton.setEnabled(false);


    }

    private void Dataintent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("banner")){
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
                Toast.makeText(this,quangcao.getTenbaihat(),Toast.LENGTH_LONG).show();
            }

            if(intent.hasExtra("itemplaylist")){
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
            }

            if(intent.hasExtra("idtheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }

            if(intent.hasExtra("idalbum")){
                album = (Album) intent.getSerializableExtra("idalbum");
            }

        }
    }

    private void eventclick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhsachbaihatActivity.this,PlaymusicActivity.class);
                intent.putExtra("allbaihat",mangbaihat);
                startActivity(intent);
            }
        });
    }
}
