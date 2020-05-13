package com.example.chesminnguyen.app_edm_music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chesminnguyen.app_edm_music.Activity.DanhSachBaiHatActivity;
import com.example.chesminnguyen.app_edm_music.Model.Album;
import com.example.chesminnguyen.app_edm_music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>
{
    Context context;//truyeenf vaof manf hinhf
    ArrayList<Album> albums;

    public AlbumAdapter(Context context, ArrayList<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_album,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album=albums.get(position);
        holder.textViewTenAlbum.setText(album.getTenAlbum());
        holder.textViewTenCaSiAlbum.setText(album.getTenCaSiAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgHinhAnhAlbum);
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    @SuppressWarnings("deprecation")
    public class ViewHolder  extends RecyclerView.ViewHolder
    {
        ImageView imgHinhAnhAlbum;
        TextView textViewTenAlbum,textViewTenCaSiAlbum;
        CardView cardView;

        public  ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHinhAnhAlbum = (ImageView) itemView.findViewById(R.id.imageviewAlbum);
            textViewTenAlbum=(TextView) itemView.findViewById(R.id.textviewTenAlbum);
            textViewTenCaSiAlbum=(TextView)itemView.findViewById(R.id.textviewTenCasiAlbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("idalbum",albums.get(getPosition()));
                    context.startActivity(intent);

                }
            });

        }



    }

}
