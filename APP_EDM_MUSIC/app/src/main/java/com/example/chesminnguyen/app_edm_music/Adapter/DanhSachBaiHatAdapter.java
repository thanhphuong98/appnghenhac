package com.example.chesminnguyen.app_edm_music.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chesminnguyen.app_edm_music.Activity.DiaNhacActivity;
import com.example.chesminnguyen.app_edm_music.Model.BaiHat;
import com.example.chesminnguyen.app_edm_music.R;
import com.example.chesminnguyen.app_edm_music.Service.APIService;
import com.example.chesminnguyen.app_edm_music.Service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder>  {

    Context context;
    ArrayList<BaiHat> baiHatArrayList;
    public DanhSachBaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_danh_sach_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat= baiHatArrayList.get(position);
        holder.txttencsi.setText(baiHat.getCaSi());
        holder.txttenbaihat.setText(baiHat.getTenBaiHat());
        holder.txtvitribaihat.setText(position+1+"");


    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    @SuppressWarnings("deprecation")
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtvitribaihat, txttenbaihat,txttencsi;
        ImageView imgluothtich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtvitribaihat=(TextView)itemView.findViewById(R.id.textviewdanhsachindex);
            txttencsi=(TextView)itemView.findViewById(R.id.textviewtencasi) ;
            txttenbaihat=(TextView)itemView.findViewById(R.id.textviewtenbaihat);
            imgluothtich=(ImageView)itemView.findViewById(R.id.imageviewluotthichdanhsachbaihat);

//            imgluothtich.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    imgluothtich.setImageResource(R.drawable.iconloved);
//
//                    DataService dataService= APIService.getService();
//                    Call<String> call= dataService.UpdateLuotThich("1",baiHatArrayList.get(getPosition()).getIdBaiHat());
//
//                    call.enqueue(new Callback<String>() {
//                        @Override
//                        public void onResponse(Call<String> call, Response<String> response) {
//                            String ketqua=response.body();
//
//                            if(ketqua.equals("ThanhCong"))
//                            {
//                                Toast.makeText(context,"Đã Thích",Toast.LENGTH_LONG).show();
//                            }else
//                            {
//                                Toast.makeText(context,"Lỗi!",Toast.LENGTH_LONG).show();
//                            }
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<String> call, Throwable t) {
//
//                        }
//                    });
//                    imgluothtich.setEnabled(false);
//
//                }
//
//
//            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, DiaNhacActivity.class);
                    intent.putExtra("idbaihatdianhac",baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

}

