package com.example.user.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class detail2 extends AppCompatActivity {
    private ImageView im,im1;
    private TextView tv;
    private ArrayList<String> satuanList;
    private SqlHelper s=new SqlHelper(detail2.this);
    Boolean fav=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        final modelmovie m = (modelmovie) getIntent().getParcelableExtra("parcel_data");

        setTitle((m.getNama()+" ("+m.getRilis()+")"));
        tv=(TextView)findViewById(R.id.textView);
        tv.setText("\t\t\t\t\t\t"+m.getDetail());

        im=(ImageView)findViewById(R.id.image);
        im1=(ImageView)findViewById(R.id.image1);

        if (s.tampildatafavorit().isEmpty()){

        }else {
            satuanList=s.tampildatafavorit();
            for (int i=0;i<satuanList.size();i++){
                if (m.getId().equals(satuanList.get(i))){
                    fav=true;
                    im1.setImageResource(R.drawable.ic_favorite_black_24dp);
                }
            }

        }
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fav){
                    s.delete_favorit(m.getId());
                    fav=false;
                    im1.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }else {
                    s.tambah_favorit(m.getId(),m.getNama(),m.getDetail(),m.getUrlgambar());
                    fav=true;
                    im1.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

            }
        });
        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w185"+m.getUrlgambar())
                //.override(500, 500)
                .crossFade()
                .into(im);


    }
}
