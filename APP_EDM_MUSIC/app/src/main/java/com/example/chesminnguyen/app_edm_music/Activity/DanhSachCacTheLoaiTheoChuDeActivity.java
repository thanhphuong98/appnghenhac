package com.example.chesminnguyen.app_edm_music.Activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chesminnguyen.app_edm_music.Adapter.TheLoaiAdapter;
import com.example.chesminnguyen.app_edm_music.Model.ChuDe;
import com.example.chesminnguyen.app_edm_music.Model.TheLoai;
import com.example.chesminnguyen.app_edm_music.R;
import com.example.chesminnguyen.app_edm_music.Service.APIService;
import com.example.chesminnguyen.app_edm_music.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("deprecation")
public class DanhSachCacTheLoaiTheoChuDeActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachTheLoai;
    ChuDe chuDe;
    ArrayList<TheLoai> theLoaiArrayList;
    TheLoaiAdapter  theLoaiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_cac_the_loai_theo_chu_de);
        GetIntent();
        AnhXa();
        GetData();

    }

    private void GetData() {
        DataService dataService= APIService.getService();
        Call<List<TheLoai>>  call= dataService.GetDanhSachCacTheLoaiTheoChuDe(chuDe.getIdChuDe());
        call.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                theLoaiArrayList= (ArrayList<TheLoai>) response.body();

                theLoaiAdapter= new TheLoaiAdapter(DanhSachCacTheLoaiTheoChuDeActivity.this,theLoaiArrayList);
                recyclerViewDanhSachTheLoai.setLayoutManager(new GridLayoutManager(DanhSachCacTheLoaiTheoChuDeActivity.this,2, LinearLayoutManager.VERTICAL,false));
                recyclerViewDanhSachTheLoai.setHasFixedSize(true);

                recyclerViewDanhSachTheLoai.setAdapter(theLoaiAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });

    }

    private void AnhXa() {

        recyclerViewDanhSachTheLoai=(RecyclerView)findViewById(R.id.recyclerviewtheloaitheotungchude);
        toolbar=(Toolbar)findViewById(R.id.toolbartheloaitheochude);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(""+chuDe.getTenChuDe());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void GetIntent() {
        Intent intent= getIntent();
        if(intent !=null)
        {
            if(intent.hasExtra("idchude"))
            {
                chuDe = (ChuDe) intent.getSerializableExtra("idchude");
                Toast.makeText(DanhSachCacTheLoaiTheoChuDeActivity.this, chuDe.getTenChuDe(),Toast.LENGTH_LONG);
            }

        }

    }
}
