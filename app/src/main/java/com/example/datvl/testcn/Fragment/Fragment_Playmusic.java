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

import com.example.datvl.testcn.Activity.PlaymusicActivity;
import com.example.datvl.testcn.Adapter.PlaymusicAdapter;
import com.example.datvl.testcn.R;

public class Fragment_Playmusic  extends Fragment{

    View view;
    RecyclerView recyclerViewplaymusic;
    PlaymusicAdapter playmusicAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playmusic,container,false);
        recyclerViewplaymusic = view.findViewById(R.id.rvplaymusic);
        if(PlaymusicActivity.mangbaihat.size() > 0){
            playmusicAdapter = new PlaymusicAdapter(getActivity(),PlaymusicActivity.mangbaihat);
            recyclerViewplaymusic.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewplaymusic.setAdapter(playmusicAdapter);
        }
        return view;
    }
}
