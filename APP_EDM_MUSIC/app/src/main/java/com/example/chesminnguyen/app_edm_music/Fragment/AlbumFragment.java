package com.example.chesminnguyen.app_edm_music.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chesminnguyen.app_edm_music.Adapter.AlbumAdapter;
import com.example.chesminnguyen.app_edm_music.Model.Album;
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
public class AlbumFragment extends Fragment {
    View view;
    AlbumAdapter albumAdapter;
    ArrayList<Album> albums;
    RecyclerView recyclerViewAlbum;
    public AlbumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.album_fragment, container, false);
        recyclerViewAlbum= (RecyclerView)view.findViewById(R.id.recyclerviewdanhsachalbum);
        getData();
        return  view;
    }
    private void getData()
    {
        DataService dataService= APIService.getService();
        Call<List<Album>> call= dataService.GetAlbum();
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                albums= (ArrayList<Album>) response.body();
                albumAdapter= new AlbumAdapter(getContext(),albums);
                recyclerViewAlbum.setLayoutManager( new GridLayoutManager(getContext(),2));

                 recyclerViewAlbum.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });



    }

}
