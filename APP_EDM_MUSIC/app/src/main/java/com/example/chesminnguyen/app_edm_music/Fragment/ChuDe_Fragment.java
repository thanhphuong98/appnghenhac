package com.example.chesminnguyen.app_edm_music.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chesminnguyen.app_edm_music.Adapter.ChuDeAdapter;
import com.example.chesminnguyen.app_edm_music.Model.ChuDe;
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
public class ChuDe_Fragment extends Fragment {
    View view;
    ArrayList<ChuDe> chuDeArrayList= new ArrayList<ChuDe>();
    ChuDeAdapter chuDeAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    public ChuDe_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.chude_fragment, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.danhsachchude);
        recyclerView.setHasFixedSize(true);//,RecyclerView.VERTICAL,false
        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        chuDeAdapter= new ChuDeAdapter(chuDeArrayList,getContext());
        recyclerView.setAdapter(chuDeAdapter);
         GetData();
        return view;

    }

    private void GetData()
    {


        DataService dataService= APIService.getService();
        Call<List<ChuDe>> listCall= dataService.GetChuDe();

        listCall.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {

                chuDeArrayList=(ArrayList<ChuDe>) response.body();
                chuDeAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });


    }

}
