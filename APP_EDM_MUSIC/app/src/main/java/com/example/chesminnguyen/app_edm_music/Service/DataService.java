package com.example.chesminnguyen.app_edm_music.Service;

import com.example.chesminnguyen.app_edm_music.Model.Album;
import com.example.chesminnguyen.app_edm_music.Model.BaiHat;
import com.example.chesminnguyen.app_edm_music.Model.ChuDe;
import com.example.chesminnguyen.app_edm_music.Model.Playlist;
import com.example.chesminnguyen.app_edm_music.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

@SuppressWarnings("deprecation")
public interface DataService {

    //tương tác lên service

    @GET("chude.php")
    Call<List<ChuDe>> GetChuDe();

    @GET("baihat.php")
    Call<List<BaiHat>> GetBaihat();

    @GET("album.php")
    Call<List<Album>> GetAlbum();

    @GET("playlist.php")
    Call<List<Playlist>> GetPlaylist();

    @FormUrlEncoded
    @POST("theloaivechude.php")
    Call<List<TheLoai>> GetDanhSachCacTheLoaiTheoChuDe(@Field("idchude") String idchude);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoPlaylist(@Field("idplaylist") String idplaylist);
}
