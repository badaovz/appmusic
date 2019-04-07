package com.example.datvl.testcn.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.datvl.testcn.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Dianhac extends Fragment{

    View view;
    CircleImageView circleImageViewdianhac;
    ObjectAnimator objectAnimatordianhac;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dianhac,container,false);
        circleImageViewdianhac = view.findViewById(R.id.civdianhac);
        objectAnimatordianhac  = objectAnimatordianhac.ofFloat(circleImageViewdianhac,"rotation",0f,360f);
        objectAnimatordianhac.setDuration(10000);
        objectAnimatordianhac.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimatordianhac.setRepeatMode(ValueAnimator.RESTART);
        objectAnimatordianhac.setInterpolator(new LinearInterpolator());
        objectAnimatordianhac.start();
        return view;
    }


    public void Playnhac(String hinh){
        Picasso.with(getActivity()).load(hinh).into(circleImageViewdianhac);
    }
}
