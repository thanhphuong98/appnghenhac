package com.example.chesminnguyen.app_edm_music.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chesminnguyen.app_edm_music.Activity.DanhSachCacTheLoaiTheoChuDeActivity;
import com.example.chesminnguyen.app_edm_music.Model.ChuDe;
import com.example.chesminnguyen.app_edm_music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class ChuDeAdapter extends RecyclerView.Adapter<ChuDeAdapter.ViewHodel> {

    ArrayList<ChuDe> chuDeArrayList;
    Context context;

    public ChuDeAdapter(ArrayList<ChuDe> chuDeArrayList, Context context) {
        this.chuDeArrayList = chuDeArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.dong_chude,parent,false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {
       // ChuDe chuDe;
        Picasso.with(context).load(chuDeArrayList.get(position).getHinhChuDe()).into(holder.imageViewChuDe);
        holder.txtTenChuDe.setText(chuDeArrayList.get(position).getTenChuDe());

    }

    @Override
    public int getItemCount() {
        return chuDeArrayList.size();
    }

    @SuppressWarnings("deprecation")
    public class ViewHodel extends RecyclerView.ViewHolder {
        //ánh xạ
        ImageView imageViewChuDe;
        TextView txtTenChuDe;
        CardView cardView;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            //Anh xa

            imageViewChuDe=(ImageView)itemView.findViewById(R.id.imagechude);
            txtTenChuDe=(TextView)itemView.findViewById(R.id.tvTenChuDe);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context,"ban đã click",Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(context, DanhSachCacTheLoaiTheoChuDeActivity.class);
                    intent.putExtra("idchude",chuDeArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}