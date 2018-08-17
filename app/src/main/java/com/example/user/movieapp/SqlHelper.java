package com.example.user.movieapp;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 3/1/2017.
 */

public class SqlHelper extends SQLiteOpenHelper{
    private static final String nama_database = "fav.db";
    private static final int versi_database = 1;
    public Context c;
    private static final String tabelfavorit="tabelfavorit";
    private static final String kolomfavorit="kolomfaforit";
    private static final String nama="nama";
    private static final String judul="judul";
    private static final String gambar="gambar";
    //private static final String query_buat_tabel_kios = "CREATE TABLE IF NOT EXISTS tabelbarang(id INTEGER PRIMARY KEY AUTOINCREMENT,idbarang INTEGER (13), namabarang TEXT, hargabarang int (7))";
    private static final String buattabelbarang="CREATE TABLE IF NOT EXISTS "+tabelfavorit+" (" +
            kolomfavorit+" VARCHAR, " +
            nama+" VARCHAR, " +
            judul+" VARCHAR, " +
            gambar+" VARCHAR " +
            ")";
    public SqlHelper(Context context)
    {
        super(context, nama_database, null, versi_database);
        c=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(buattabelbarang);
        //System.out.println("tabel_barang sudah dibuat");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void tambah_favorit(String id,String namaa,String judula,String gambara) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(kolomfavorit, id);
        values.put(nama,namaa);
        values.put(judul,judula);
        values.put(gambar,gambara);
        database.insert(tabelfavorit, null, values);
        database.close();
    }


    public void delete_favorit(String idkey) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM tabelfavorit WHERE kolomfaforit='"+idkey+"' ");
        database.close();
    }





    public ArrayList<String> tampildatafavorit(){
        ArrayList<String> arraylisdata =new ArrayList<String>();
        //arraylisdata.add("Satuan");
        SQLiteDatabase database=this.getReadableDatabase();
        String ad[]={kolomfavorit,nama,judul,gambar};
        Cursor cursor=database.query(tabelfavorit,ad,null,null,null,null,kolomfavorit+" ASC");
        if(cursor.getCount()>0) {
            while(cursor.moveToNext()){
                arraylisdata.add(cursor.getString(cursor.getColumnIndex(kolomfavorit)));
            }
        }
        else {
            // Toast.makeText(getActivity(),"Data Kosong... Silahkan Tambah Data",Toast.LENGTH_SHORT).show();
            //tambahdata();
        }
        database.close();
        return arraylisdata;
    }



}

