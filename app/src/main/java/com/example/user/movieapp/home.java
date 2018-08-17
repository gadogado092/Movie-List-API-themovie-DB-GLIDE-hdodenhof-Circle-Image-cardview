package com.example.user.movieapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class home extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<modelmovie>>{
    private RecyclerView rvmovie,rvmovie2;
    //private EditText editText;
    //private ImageButton button;
    private ArrayList<modelmovie> list;
    private costumadapter movieadapter;
    private home c;
    private SqlHelper s=new SqlHelper(getContext());
    public home(){


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentberanda, container,false);

        rvmovie=(RecyclerView)view.findViewById(R.id.list);
        rvmovie.setHasFixedSize(false);

        list=new ArrayList<>();

        Fragment up=new up();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_main2, up);
        fragmentTransaction.commit();


        Bundle bundle = new Bundle();
        String url="https://api.themoviedb.org/3/movie/now_playing?api_key=3e7890a478575f86bbe9284f4d1b37e8&language=en-US";
        bundle.putString("url",url);
      //  bundle.putString("carimovie",editText.getText().toString());
        c=this;
        //getLoaderManager().initLoader(0,bundle, (LoaderManager.LoaderCallbacks<Object>) getContext());
        getLoaderManager().initLoader(0,bundle, this);


        return view;
    }


    @Override
    public Loader<ArrayList<modelmovie>> onCreateLoader(int id, Bundle bundle) {
        String url = "";
        //if (bundle != null){
            url = bundle.getString("url");
        //}else {
        //    movieadapter.setListmovie(null);
            //Toast.makeText(this,"Masukkan Nama Movie",Toast.LENGTH_SHORT).show();
        //}
       // String url="https://api.themoviedb.org/3/search/movie?api_key=3e7890a478575f86bbe9284f4d1b37e8&language=en-US&query="+cari;

        return new LoaderAsynTask(getContext(),url);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<modelmovie>> loader, final ArrayList<modelmovie> data) {

        rvmovie.setLayoutManager(new LinearLayoutManager(getContext()));
        costumadapternow ca = new costumadapternow(getContext());
        ca.setListmovie(data);
        list=data;
        rvmovie.setAdapter(ca);

        ItemClickSupport.addTo(rvmovie).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedmovie(position);
            }
        });
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<modelmovie>> loader) {
        rvmovie.setLayoutManager(new LinearLayoutManager(getContext()));
        costumadapternow listPresidentAdapter = new costumadapternow(getContext());
        listPresidentAdapter.setListmovie(null);
        rvmovie.setAdapter(listPresidentAdapter);
    }

    private void showSelectedmovie(int pos){
        modelmovie movie = new modelmovie(list.get(pos).getId(),list.get(pos).getNama(),list.get(pos).getDetail(),list.get(pos).getRilis(),list.get(pos).getUrlgambar());
        Intent intent = new Intent(getActivity(), detail2.class);
        intent.putExtra("parcel_data", movie);
        startActivity(intent);

    }
}