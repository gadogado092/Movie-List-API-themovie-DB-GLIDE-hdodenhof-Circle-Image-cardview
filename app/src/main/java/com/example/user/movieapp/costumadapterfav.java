package com.example.user.movieapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by User on 2/7/2018.
 */

public class costumadapterfav extends RecyclerView.Adapter<costumadapterfav.ViewHolder> {
    private Context context;
    private ArrayList<String> listmovie=new ArrayList<String>();

    costumadapterfav(Context context) {
        this.context = context;
    }

    void setListmovie(ArrayList<String> listmovie) {
        this.listmovie = listmovie;
    }

    ArrayList <String> getlistmovie(){
        return listmovie;
    }




    @Override
    public costumadapterfav.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutitem, parent, false);
        return new ViewHolder(itemRow);

    }

    @Override
    public void onBindViewHolder(costumadapterfav.ViewHolder holder, int position) {
        holder.textViewnamamovie.setText(getlistmovie().get(position));
        holder.textViewrilis.setText(getlistmovie().get(position));
        holder.textViewdeskripsi.setText(getlistmovie().get(position));
        //System.out.println("TESS"+getlistmovie().get(position).getNama());
        if (holder.imageViewposter != null) {
            Glide.with(context)
                    .load("http://image.tmdb.org/t/p/w185"+getlistmovie().get(position))
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
