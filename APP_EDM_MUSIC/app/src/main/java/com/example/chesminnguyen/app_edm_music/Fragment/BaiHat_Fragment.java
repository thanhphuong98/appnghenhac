package com.example.chesminnguyen.app_edm_music.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chesminnguyen.app_edm_music.Adapter.BaiHatAdapter;
import com.example.chesminnguyen.app_edm_music.Model.BaiHat;
import com.example.chesminnguyen.app_edm_music.R;
import com.example.chesminnguyen.app_edm_music.Service.APIService;
import com.example.chesminnguyen.app_edm_music.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("deprecation")
public class BaiHat_Fragment extends Fragment {
    View view;
    ArrayList<BaiHat> baiHatArrayList = new ArrayList<BaiHat>();
    BaiHatAdapter baiHatAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.baihat_fragment, container, false);

        //ánh xạ
        recyclerView=(RecyclerView)view.findViewById(R.id.danhsachbaihat);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        baiHatAdapter= new BaiHatAdapter(getContext(),baiHatArrayList);
        recyclerView.setAdapter(baiHatAdapter);

        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService= APIService.getService();
        Call<List<BaiHat>> call= dataService.GetBaihat();
        call.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList= (ArrayList<BaiHat>) response.body();
               // Log.d("DDD",baiHatArrayList.get(0).getTenBaiHat());
                baiHatAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

}
