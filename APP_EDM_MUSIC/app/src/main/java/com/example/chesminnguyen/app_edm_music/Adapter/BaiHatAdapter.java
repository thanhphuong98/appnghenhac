package com.example.chesminnguyen.app_edm_music.Adapter;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chesminnguyen.app_edm_music.Activity.DiaNhacActivity;
import com.example.chesminnguyen.app_edm_music.Model.BaiHat;
import com.example.chesminnguyen.app_edm_music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class BaiHatAdapter extends RecyclerView.Adapter<BaiHatAdapter.ViewHolder>{


    Context context;
    ArrayList<BaiHat> baiHatArrayList;
    public BaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        context=parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        view =inflater.inflate(R.layout.dong_baihat,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat= baiHatArrayList.get(position);
        holder.txtTenCaSiHat.setText(baiHat.getCaSi());
        holder.txtTenBaiHat.setText(baiHat.getTenBaiHat());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imageViewBaiHat);

    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //khai báo
        ImageView imageViewBaiHat, imageViewLuotThich;
        TextView txtTenBaiHat, txtTenCaSiHat;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewBaiHat=(ImageView)itemView.findViewById(R.id.imageviewbaihat);
            imageViewLuotThich=(ImageView)itemView.findViewById(R.id.imageviewluotthich);
            txtTenBaiHat=(TextView)itemView.findViewById(R.id.textviewtenbaihat);
            txtTenCaSiHat=(TextView)itemView.findViewById(R.id.textviewtencasibaihat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent= new Intent(context, DiaNhacActivity.class);
                    intent.putExtra("idbaihatdianhac",baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

