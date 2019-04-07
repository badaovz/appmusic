package com.example.datvl.testcn.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.datvl.testcn.Adapter.SearchbaihatAdapter;
import com.example.datvl.testcn.Model.Baihat;
import com.example.datvl.testcn.R;
import com.example.datvl.testcn.Service.APIClient;
import com.example.datvl.testcn.Service.RequestApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Fragment_Tim_Kiem extends Fragment {
    View view;
    Toolbar toolbartimkiem;
    RecyclerView recyclerViewtimkiem;
    TextView thongbao;
    SearchbaihatAdapter searchbaihatAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem,container,false);
        anhxa();
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbartimkiem);
        toolbartimkiem.setTitle("Search");
        setHasOptionsMenu(true);
        return view;


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu);
        MenuItem item = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchbaihat(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void anhxa() {
        toolbartimkiem = view.findViewById(R.id.tbtimkiem);
        recyclerViewtimkiem = view.findViewById(R.id.rvtimkiem);
        thongbao = view.findViewById(R.id.tvtimkiemtrong);
    }

    private void searchbaihat(String query){
        Retrofit retrofit = APIClient.getClient();
        RequestApi requestApi = retrofit.create(RequestApi.class);
        Call<List<Baihat>> callback = requestApi.Getsearchbaihat(query);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> arbaihat = (ArrayList<Baihat>) response.body();
                if(arbaihat.size() > 0){
                    searchbaihatAdapter = new SearchbaihatAdapter(getActivity(),arbaihat);
                    recyclerViewtimkiem.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerViewtimkiem.setAdapter(searchbaihatAdapter);
                    thongbao.setVisibility(View.GONE);
                    recyclerViewtimkiem.setVisibility(View.VISIBLE);
                }else {
                    recyclerViewtimkiem.setVisibility(View.GONE);
                    thongbao.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
