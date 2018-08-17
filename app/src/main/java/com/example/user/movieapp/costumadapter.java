package com.example.user.movieapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by User on 2/7/2018.
 */

public class costumadapter extends RecyclerView.Adapter<costumadapter.ViewHolder> {
    private Context context;
    private ArrayList<modelmovie> listmovie=new ArrayList<>();

    costumadapter(Context context) {
        this.context = context;
    }

    void setListmovie(ArrayList<modelmovie> listmovie) {
        this.listmovie = listmovie;
    }

    ArrayList <modelmovie> getlistmovie(){
        return listmovie;
    }




    @Override
    public costumadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutitem, parent, false);
        return new ViewHolder(itemRow);

    }

    @Override
    public void onBindViewHolder(costumadapter.ViewHolder holder, int position) {
        holder.textViewnamamovie.setText(getlistmovie().get(position).getNama());
        holder.textViewrilis.setText(getlistmovie().get(position).getRilis());
        holder.textViewdeskripsi.setText(getlistmovie().get(position).getDetail());
        //System.out.println("TESS"+getlistmovie().get(position).getNama());
        if (holder.imageViewposter != null) {
            Glide.with(context)
                    .load("http://image.tmdb.org/t/p/w185"+getlistmovie().get(position).getUrlgambar())
                    .override(300, 300)
                    .crossFade()
                    .into(holder.imageViewposter);
        }
    }

    @Override
    public int getItemCount() {
        if (listmovie==null) return 0;
        return listmovie.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewnamamovie;
        TextView textViewdeskripsi;
        TextView textViewrilis;
        ImageView imageViewposter;

        ViewHolder(View view){
            super(view);
            textViewnamamovie=(TextView)view.findViewById(R.id.nama);
            textViewdeskripsi=(TextView)view.findViewById(R.id.deskripsi);
            textViewrilis=(TextView)view.findViewById(R.id.rilis);
            imageViewposter=(ImageView)view.findViewById(R.id.poster);

        }
    }
}
