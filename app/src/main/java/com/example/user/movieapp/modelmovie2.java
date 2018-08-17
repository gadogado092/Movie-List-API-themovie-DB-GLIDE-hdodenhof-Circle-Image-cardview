package com.example.user.movieapp;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 2/7/2018.
 */

public class modelmovie2 implements Serializable {
    private String id;
    private String nama;
    private String detail;
    private String rilis;
    private String urlgambar;
    public modelmovie2(String id, String nama, String detail, String rilis, String urlgambar){
        this.id=id;this.nama=nama;this.detail=detail;this.rilis=rilis;this.urlgambar=urlgambar;
    }

    public modelmovie2(JSONObject object){
        try {
            String id=object.getString("id");
            String nama=object.getString("original_title");
            String detail=object.getString("overview");
            String rilis=object.getString("release_date");
            String url=object.getString("poster_path");
            //SimpleDateFormat parseFormat = new SimpleDateFormat("E MMMM dd,yyyy hh:mm a");
            //Date date =new Date();
            //String s = parseFormat.format(date);
            //SimpleDateFormat format = new SimpleDateFormat("MMMM dd,yyyy");
            //String datee = format.format(Date.parse(rilis));

            //2018-01-17
            String strCurrentDate = rilis;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = format.parse(strCurrentDate);

            format = new SimpleDateFormat("E, dd MMMM yyyy");
            String date = format.format(newDate);
            this.id=id;
            this.nama=nama;
            this.detail=detail;
            this.rilis= date;
            this.urlgambar=url;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRilis() {
        return rilis;
    }

    public void setRilis(String rilis) {
        this.rilis = rilis;
    }

    public String getUrlgambar() {
        return urlgambar;
    }

    public void setUrlgambar(String urlgambar) {
        this.urlgambar = urlgambar;
    }


}
