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
import com.example.chesminnguyen.app_edm_music.Model.TheLoai;

import com.example.chesminnguyen.app_edm_music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ViewHolder>{

    Context context;
    ArrayList<TheLoai> theLoaiArrayList;

    public TheLoaiAdapter(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_theloai,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai  theLoai=theLoaiArrayList.get(position);
        holder.textViewTenTheLoai.setText(theLoai.getTenTheLoai());
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imageViewHinhTheLoai);

    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    @SuppressWarnings("deprecation")
    class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewHinhTheLoai;
        TextView textViewTenTheLoai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewHinhTheLoai=(ImageView)itemView.findViewById(R.id.imageviewdongtheloaitheochude);
            textViewTenTheLoai=(TextView)itemView.findViewById(R.id.textviewdongtentheloaitheochude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("idtheloai",theLoaiArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

