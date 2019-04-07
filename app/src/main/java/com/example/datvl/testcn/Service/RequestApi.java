package com.example.datvl.testcn.Service;

import com.example.datvl.testcn.Model.Album;
import com.example.datvl.testcn.Model.Baihat;
import com.example.datvl.testcn.Model.ChuDe;
import com.example.datvl.testcn.Model.Playlist;
import com.example.datvl.testcn.Model.Quangcao;
import com.example.datvl.testcn.Model.TheLoai;
import com.example.datvl.testcn.Model.Theloaitrongngay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestApi {

    @GET("songbanner.php")
    Call<List<Quangcao>> GetQuangcao();

    @GET("playlistforday.php")
    Call<List<Playlist>> GetPlaylist();

    @GET("chudevstheloaiforday.php")
    Call<Theloaitrongngay> GetTheloaicd();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbum();

    @GET("baihatyeuthich.php")
    Call<List<Baihat>> GetBaihatYeuthich();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> Getdsbhtheoquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> Getdsbhtheoplaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachallplaylist.php")
    Call<List<Playlist>> Getallplaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> Getdsbhtheotheloai(@Field("idtheloai") String idtheloai);

    @GET("allchude.php")
    Call<List<ChuDe>> Getallchude();

    @FormUrlEncoded
    @POST("theloaiforchude.php")
    Call<List<TheLoai>> Gettheloaiforchude(@Field("idchude") String idchude);

    @GET("allalbum.php")
    Call<List<Album>> Getallalbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> Getdsbhtheoalbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> Getupdateloutthich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
        Call<List<Baihat>> Getsearchbaihat(@Field("tukhoa") String tukhoa);

}