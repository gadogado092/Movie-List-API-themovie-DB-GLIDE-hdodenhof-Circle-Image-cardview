package com.example.user.movieapp;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;


import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by User on 2/7/2018.
 */

public class LoaderAsynTask extends AsyncTaskLoader<ArrayList<modelmovie>> {

    private ArrayList<modelmovie> mdata;
    private String murl;
    private boolean mHasResult = false;

    public LoaderAsynTask(Context context,String url){
        super(context);
        onContentChanged();
        this.murl=url;

    }

    @Override
    protected void onStartLoading() {

        super.onStartLoading();
        if (takeContentChanged()){
            forceLoad();
        }else if (mHasResult){
            deliverResult(mdata);
        }
    }

    @Override
    public void deliverResult(ArrayList<modelmovie> data) {
        mdata=data;
        mHasResult=true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult){
            onReleaseResources(mdata);
            mdata=null;
            mHasResult=false;
        }
    }

    //private static final String API_KEY = "3e7890a478575f86bbe9284f4d1b37e8";
    @Override
    public ArrayList<modelmovie> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<modelmovie> moviedata=new ArrayList<>();

       // Bundle b=new Bundle();
        //String key=b.getString("apikey");
        //String url="https://api.themoviedb.org/3/search/movie?api_key=3e7890a478575f86bbe9284f4d1b37e8&language=en-US&query=Maze%20Runner:%20The%20Death%20Cure";
        //String url="https://api.themoviedb.org/3/search/movie?api_key=3e7890a478575f86bbe9284f4d1b37e8&language=en-US&query="+mvaluecari;
        client.get(murl, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                //set AsyncHttpResponseHandler supaya berjalan secara asyncronous
                setUseSynchronousMode(true);


            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result=new String(responseBody);
                    JSONObject responseObject=new JSONObject(result);
                    JSONArray list=responseObject.getJSONArray("results");

                    for (int i=0;i< list.length();i++){
                        JSONObject movie=list.getJSONObject(i);
                        modelmovie modelmovie=new modelmovie(movie);
                        moviedata.add(modelmovie);

                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return moviedata;
    }

    protected void onReleaseResources(ArrayList<modelmovie> data) {
        //nothing to do.
    }

}
