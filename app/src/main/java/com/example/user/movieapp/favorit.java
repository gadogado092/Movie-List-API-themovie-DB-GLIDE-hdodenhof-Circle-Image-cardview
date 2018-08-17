package com.example.user.movieapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 2/8/2018.
 */

public class favorit extends Fragment {
    private RecyclerView rvmovie;
    private EditText editText;
    private ImageButton button;
    private ArrayList<modelmovie> list;
    private costumadapter movieadapter;
    private favorit c;
    public favorit(){


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentfav, container,false);
        //c=getContext();
        editText=(EditText)view.findViewById(R.id.editnama);

        rvmovie=(RecyclerView)view.findViewById(R.id.list);
        rvmovie.setHasFixedSize(false);

        button=(ImageButton)view.findViewById(R.id.button);
        list=new ArrayList<>();
        SqlHelper s= new SqlHelper(getActivity());


        rvmovie.setLayoutManager(new LinearLayoutManager(getContext()));
        costumadapterfav listPresidentAdapter = new costumadapterfav(getContext());
        listPresidentAdapter.setListmovie(s.tampildatafavorit());
        //list=data;
        rvmovie.setAdapter(listPresidentAdapter);

        return view;
    }



}