package com.example.chesminnguyen.app_edm_music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chesminnguyen.app_edm_music.Adapter.DanhSachBaiHatAdapter;
import com.example.chesminnguyen.app_edm_music.Model.Album;
import com.example.chesminnguyen.app_edm_music.Model.BaiHat;
import com.example.chesminnguyen.app_edm_music.Model.Playlist;
import com.example.chesminnguyen.app_edm_music.Model.TheLoai;
import com.example.chesminnguyen.app_edm_music.R;
import com.example.chesminnguyen.app_edm_music.Service.APIService;
import com.example.chesminnguyen.app_edm_music.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("deprecation")
public class DanhSachBaiHatActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar  toolbar;
    RecyclerView recyclerViewdanhsachBaiHat;
    FloatingActionButton floatingActionButton;
    ImageView imageViewDanhSachCaKhuc;
    Album album;
    Playlist playlist;
    TheLoai  theloai;
    BaiHat  baiHat;
    ArrayList<BaiHat> baiHatArrayList;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);

        //viết function để nhân
        GetIntent();
        anhxa();
        Unit();
        if (album!=null && !album.getTenAlbum().equals(""))
        {
            setValueView(album.getTenAlbum(),album.getHinhAlbum());
            GetDaTaAlbum(album.getIdAlbum());
        }
        if (theloai!=null && !theloai.getTenTheLoai().equals(""))
        {
            setValueView(theloai.getTenTheLoai(),theloai.getHinhTheLoai());
            GetDaTaTheLoai(theloai.getIdTheLoai());
        }
        if (playlist!=null && !playlist.getTen().equals(""))
        {
            setValueView(playlist.getTen(),playlist.getIcon());
            GetDaTaPlaylist(playlist.getIdPlaylist());
        }



    }

    private void GetDaTaPlaylist(String idPlaylist) {
        DataService dataService= APIService.getService();
        Call<List<BaiHat>> call= dataService.GetDanhSachBaiHatTheoPlaylist(playlist.getIdPlaylist());
        call.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList= (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,baiHatArrayList);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(DanhSachBaiHatActivity.this,LinearLayoutManager.VERTICAL,false);
                recyclerViewdanhsachBaiHat.setLayoutManager(linearLayoutManager);
                recyclerViewdanhsachBaiHat.setAdapter(danhSachBaiHatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDaTaTheLoai(String idTheLoai) {
        DataService dataService= APIService.getService();
        Call<List<BaiHat>> call= dataService.GetDanhSachBaiHatTheoTheLoai(theloai.getIdTheLoai());
        call.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList= (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,baiHatArrayList);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(DanhSachBaiHatActivity.this,LinearLayoutManager.VERTICAL,false);
                recyclerViewdanhsachBaiHat.setLayoutManager(linearLayoutManager);
                recyclerViewdanhsachBaiHat.setAdapter(danhSachBaiHatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDaTaAlbum(String idalbum){
        DataService dataService= APIService.getService();
        Call<List<BaiHat>> call= dataService.GetDanhSachBaiHatTheoAlbum(album.getIdAlbum());
        call.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList= (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,baiHatArrayList);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(DanhSachBaiHatActivity.this,LinearLayoutManager.VERTICAL,false);
                recyclerViewdanhsachBaiHat.setLayoutManager(linearLayoutManager);
                recyclerViewdanhsachBaiHat.setAdapter(danhSachBaiHatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void setValueView( String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);//đổ tên
        Picasso.with(this).load(hinh).into(imageViewDanhSachCaKhuc);

//        try {
//            URL url= new URL(hinh);
//            Bitmap bitmap= BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            BitmapDrawable bitmapDrawable= new BitmapDrawable(getResources(),bitmap);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                collapsingToolbarLayout.setBackground(bitmapDrawable);
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Picasso.with(this).load(hinh).into((Target) collapsingToolbarLayout);

    }

    private void Unit() {
        //tạo nút back về Activity
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//tạo ra nút home
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setExpanded(false);

    }

    private  void anhxa()
    {
        coordinatorLayout =(CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout =(CollapsingToolbarLayout) findViewById(R.id.collapsingToobar);
        toolbar  = (Toolbar) findViewById(R.id.toolbardanhsach);
        recyclerViewdanhsachBaiHat=(RecyclerView)findViewById(R.id.recycleviewdanhsachbaihat);
        floatingActionButton=(FloatingActionButton) findViewById(R.id.floatingactionbotton);
        imageViewDanhSachCaKhuc=(ImageView)findViewById(R.id.imageviewdanhsachcakhuc);
    }

    private void GetIntent()
    {
        Intent intent= getIntent();

        if(intent!=null)
        {
            if(intent.hasExtra("idalbum"))
            {
                album= (Album) intent.getSerializableExtra("idalbum");
                Log.d("ThanhPhuong", album.getTenAlbum());

            }
            if(intent.hasExtra("idtheloai"))
            {
                theloai= (TheLoai) intent.getSerializableExtra("idtheloai");


            }
            if(intent.hasExtra("idplaylist"))
            {
                playlist= (Playlist) intent.getSerializableExtra("idplaylist");


            }

        }
    }
}
