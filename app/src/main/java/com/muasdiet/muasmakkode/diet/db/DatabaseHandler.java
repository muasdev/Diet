package com.muasdiet.muasmakkode.diet.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.muasdiet.muasmakkode.diet.db.model.ModelMakanan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "dataDiet";

    // Contacts table name
    public static final String TABLE_MAKANAN = "makananTabel";

    // Contacts Table Columns names
    private static final String KEY_ID = "no";
    private static final String KEY_NAMA = "Nama";
    private static final String KEY_URT = "Instansi";
    private static final String KEY_KALORI = "nomorhp";
    private static final String KEY_KARBOHIDRAT = "tujuan";
    private static final String KEY_PROTEIN = "timein";
    private static final String KEY_LEMAK = "signature";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_MAKANAN = "CREATE TABLE " + TABLE_MAKANAN + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAMA + " TEXT,"
                + KEY_URT + " TEXT,"
                + KEY_KALORI + " TEXT,"
                + KEY_KARBOHIDRAT + " TEXT,"
                + KEY_PROTEIN + " TEXT,"
                + KEY_LEMAK + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_MAKANAN);
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAKANAN);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */


    //Insert values to the table contacts
    public void addContacts(ModelMakanan modelMakanan) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAMA, modelMakanan.getNama_model());
        values.put(KEY_URT, modelMakanan.getUrt_model());
        values.put(KEY_KALORI, modelMakanan.getKalori_model());
        values.put(KEY_KARBOHIDRAT, modelMakanan.getKarbo_model());
        values.put(KEY_PROTEIN, modelMakanan.getProtein_model());
        values.put(KEY_LEMAK, modelMakanan.getLemak_model());


        db.insert(TABLE_MAKANAN, null, values);
        db.close();
    }

    /**
     * Getting All Contacts
     **/

    public List<ModelMakanan> getAllContacts() {
        List<ModelMakanan> modelMakananList = new ArrayList<ModelMakanan>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MAKANAN;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            do {
                ModelMakanan modelMakanan = new ModelMakanan();
                modelMakanan.set_id(Integer.parseInt(cursor.getString(0)));
                modelMakanan.setNama_model(cursor.getString(1));
                modelMakanan.setUrt_model(cursor.getString(2));
                modelMakanan.setKalori_model(cursor.getString(3));
                modelMakanan.setKarbo_model(cursor.getString(4));
                modelMakanan.setProtein_model(cursor.getString(5));
                modelMakanan.setLemak_model(cursor.getString(6));

                // Adding modelMakanan to list
                modelMakananList.add(modelMakanan);
            } while (cursor.moveToNext());
        }


        // return contact list
        return modelMakananList;
    }


    /**
     * Updating single modelMakanan
     **/

    public int updateContact(ModelMakanan modelMakanan, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, modelMakanan.getNama_model());
        values.put(KEY_URT, modelMakanan.getUrt_model());
        values.put(KEY_KALORI, modelMakanan.getKalori_model());
        values.put(KEY_KARBOHIDRAT, modelMakanan.getKarbo_model());
        values.put(KEY_PROTEIN, modelMakanan.getProtein_model());
        values.put(KEY_LEMAK, modelMakanan.getLemak_model());


        // updating row
        return db.update(TABLE_MAKANAN, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }


    /*public void deleteContact(long Id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        *//*db.execSQL("delete from "+TABLE_MAKANAN+" where "+Id+" ="+Id);*//*
        db.delete(TABLE_MAKANAN, KEY_ID + " = ?",
                new String[]{String.valueOf(Id)});
    }*/

    public int deleteFromDatabse(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int status = db.delete(TABLE_MAKANAN,KEY_ID + "= ? ",new String[]{String.valueOf(id)});
        db.close();

        return status;
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // db.delete(TABLE_NAME,null,null);
        //db.execSQL("delete * from"+ TABLE_NAME);
        db.execSQL("delete from "+ TABLE_MAKANAN);
        db.close();
    }


    /*hitung jumlah baris*/
    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MAKANAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        return count;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    /*hitung total kalori di kolom kalori*/
    public int getTotalKalori(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(" + DatabaseHandler.KEY_KALORI + ") as Total FROM " + DatabaseHandler.TABLE_MAKANAN);
        int sum=0;
        Cursor cursor = db.rawQuery(sumQuery, null);

        if (cursor.moveToFirst()) {

           sum = cursor.getInt(0);// get final total
        }
        return sum;
    }

    /*hitung total kalori di kolom kalori*/
    public int getTotalKarbo(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(" + DatabaseHandler.KEY_KARBOHIDRAT + ") as Total FROM " + DatabaseHandler.TABLE_MAKANAN);
        int sum=0;
        Cursor cursor = db.rawQuery(sumQuery, null);

        if (cursor.moveToFirst()) {

            sum = cursor.getInt(0);// get final total
        }
        return sum;
    }

    /*hitung total kalori di kolom kalori*/
    public int getTotalProtein(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(" + DatabaseHandler.KEY_PROTEIN + ") as Total FROM " + DatabaseHandler.TABLE_MAKANAN);
        int sum=0;
        Cursor cursor = db.rawQuery(sumQuery, null);

        if (cursor.moveToFirst()) {

            sum = cursor.getInt(0);// get final total
        }
        return sum;
    }

    /*hitung total kalori di kolom kalori*/
    public int getTotalLemak(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(" + DatabaseHandler.KEY_LEMAK + ") as Total FROM " + DatabaseHandler.TABLE_MAKANAN);
        int sum=0;
        Cursor cursor = db.rawQuery(sumQuery, null);

        if (cursor.moveToFirst()) {

            sum = cursor.getInt(0);// get final total
        }
        return sum;
    }


}
