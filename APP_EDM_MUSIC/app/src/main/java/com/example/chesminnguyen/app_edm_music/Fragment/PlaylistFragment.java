package com.example.chesminnguyen.app_edm_music.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chesminnguyen.app_edm_music.Adapter.PlaylistAdapter;
import com.example.chesminnguyen.app_edm_music.Model.Playlist;
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
public class PlaylistFragment extends Fragment {
    View view;
    RecyclerView recyclerViewPlayList;
    PlaylistAdapter playlistAdapter;
    LinearLayoutManager  linearLayoutManager;
    ArrayList<Playlist> playlists;
    public PlaylistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


         view=inflater.inflate(R.layout.playlist, container, false);
        recyclerViewPlayList=(RecyclerView)view.findViewById(R.id.recyclerviewdanhsachplaylist);
        GetData();
        return view;
    }
    private void  GetData()
    {
        DataService dataService= APIService.getService();
        Call<List<Playlist>> call= dataService.GetPlaylist();
        call.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                playlists= (ArrayList<Playlist>) response.body();
                playlistAdapter= new PlaylistAdapter(getContext(),playlists);
                linearLayoutManager= new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewPlayList.setLayoutManager(linearLayoutManager);
                recyclerViewPlayList.setAdapter(playlistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });

    }

}
