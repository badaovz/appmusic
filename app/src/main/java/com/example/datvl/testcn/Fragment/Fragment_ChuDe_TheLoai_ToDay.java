package com.example.datvl.testcn.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.datvl.testcn.Activity.DanhsachTheloaitheoChudeActivity;
import com.example.datvl.testcn.Activity.DanhsachallchudeActivity;
import com.example.datvl.testcn.Activity.DanhsachbaihatActivity;
import com.example.datvl.testcn.Model.ChuDe;
import com.example.datvl.testcn.Model.TheLoai;
import com.example.datvl.testcn.Model.Theloaitrongngay;
import com.example.datvl.testcn.R;
import com.example.datvl.testcn.Service.APIClient;
import com.example.datvl.testcn.Service.RequestApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Fragment_ChuDe_TheLoai_ToDay extends Fragment{
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView xemthemcdtl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today,container,false);
        horizontalScrollView = view.findViewById(R.id.hstheloaicd);
        xemthemcdtl          = view.findViewById(R.id.tvxemthem);

        xemthemcdtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DanhsachallchudeActivity.class));
            }
        });

        GetData();
        return view;
    }

    private void GetData() {
        Retrofit retrofit = APIClient.getClient();
        RequestApi requestApi = retrofit.create(RequestApi.class);
        Call<Theloaitrongngay> callback = requestApi.GetTheloaicd();

        callback.enqueue(new Callback<Theloaitrongngay>() {
            @Override
            public void onResponse(Call<Theloaitrongngay> call, Response<Theloaitrongngay> response) {
               Theloaitrongngay theloaitrongngay = response.body();

                final ArrayList<ChuDe> arrchude = new ArrayList<>();
                arrchude.addAll(theloaitrongngay.getChuDe());

                final ArrayList<TheLoai> arrtheloai = new ArrayList<>();
                arrtheloai.addAll(theloaitrongngay.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580,250);
                layout.setMargins(10,20,10,30);
                for(int i = 0;i < arrchude.size();i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(arrchude.get(i).getHinhChuDe() != null){
                        Picasso.with(getActivity()).load(arrchude.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhsachTheloaitheoChudeActivity.class);
                            intent.putExtra("chude",arrchude.get(finalI));
                            startActivity(intent);
                        }
                    });
                }

                for(int j = 0;j < arrtheloai.size();j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(arrtheloai.get(j).getHinhTheLoai() != null){
                        Picasso.with(getActivity()).load(arrtheloai.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai", arrtheloai.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }

                horizontalScrollView.addView(linearLayout);

            }

            @Override
            public void onFailure(Call<Theloaitrongngay> call, Throwable t) {

            }
        });
    }
}
