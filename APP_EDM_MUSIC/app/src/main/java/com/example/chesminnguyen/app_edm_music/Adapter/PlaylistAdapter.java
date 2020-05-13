package com.example.chesminnguyen.app_edm_music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chesminnguyen.app_edm_music.Activity.DanhSachBaiHatActivity;
import com.example.chesminnguyen.app_edm_music.Model.Playlist;
import com.example.chesminnguyen.app_edm_music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    Context  context;
    ArrayList<Playlist>  playlists;

    public PlaylistAdapter(Context context, ArrayList<Playlist> playlists) {
        this.context = context;
        this.playlists = playlists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View  view= inflater.inflate(R.layout.dong_playlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Playlist playlist= playlists.get(position);

        holder.txtTenPlayList.setText(playlist.getTen());
        //Picasso.with(context).load(playlist.getHinhPlaylist()).into(holder.imageViewHinhnen);
        Picasso.with(context).load(playlist.getIcon()).into(holder.imageViewHinhicon);

    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewHinhicon;
        TextView txtTenPlayList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           // imageViewHinhnen=(ImageView)itemView.findViewById(R.id.imageviewbackgroundplaylist);
            imageViewHinhicon=(ImageView)itemView.findViewById(R.id.imageviewplaylist);
            txtTenPlayList=(TextView)itemView.findViewById(R.id.textviewtenplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("idplaylist",playlists.get(getPosition()));
                    context.startActivity(intent);

                }
            });
        }
    }
}
