package com.muasdiet.muasmakkode.diet.db.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.muasdiet.muasmakkode.diet.db.model.ModelOlahraga;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muas on 16/03/2018.
 */

public class DatabaseHandlerOlahraga extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "dataOlahraga";

    // Contacts table name
    public static final String TABLE_OLAHRAGA = "tblolahraga";

    // Contacts Table Columns names
    private static final String KEY_ID = "no";
    private static final String KEY_NM_OLAHRAGA = "NamaOlahraga";
    private static final String KEY_KALORI_OLAHRAGA = "KaloriOlahraga";


    public DatabaseHandlerOlahraga(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_OLAHRAGA = "CREATE TABLE " + TABLE_OLAHRAGA + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NM_OLAHRAGA + " TEXT,"
                + KEY_KALORI_OLAHRAGA + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_OLAHRAGA);
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OLAHRAGA);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */


    //Insert values to the table contacts
    public void addDataOlahraga(ModelOlahraga modelOlahraga) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NM_OLAHRAGA, modelOlahraga.getNama_olahraga_model());
        values.put(KEY_KALORI_OLAHRAGA, modelOlahraga.getKalori_olahraga_model());


        db.insert(TABLE_OLAHRAGA, null, values);
        db.close();
    }

    /**
     * Getting All Contacts
     **/

    public List<ModelOlahraga> getAllDataOlahraga() {
        List<ModelOlahraga> modelOlahragaList = new ArrayList<ModelOlahraga>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_OLAHRAGA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            do {
                ModelOlahraga modelOlahraga = new ModelOlahraga();
                modelOlahraga.set_id(Integer.parseInt(cursor.getString(0)));
                modelOlahraga.setNama_olahraga_model(cursor.getString(1));
                modelOlahraga.setKalori_olahraga_model(cursor.getString(2));
                // Adding modelOlahraga to list
                modelOlahragaList.add(modelOlahraga);
            } while (cursor.moveToNext());
        }


        // return contact list
        return modelOlahragaList;
    }


    /**
     * Updating single modelOlahraga
     **/

    public int updateDataOlahraga(ModelOlahraga modelOlahraga, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NM_OLAHRAGA, modelOlahraga.getNama_olahraga_model());
        values.put(KEY_KALORI_OLAHRAGA, modelOlahraga.getKalori_olahraga_model());

        // updating row
        return db.update(TABLE_OLAHRAGA, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }


    public int deleteFromDbOlahraga(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int status = db.delete(TABLE_OLAHRAGA,KEY_ID + "= ? ",new String[]{String.valueOf(id)});
        db.close();

        return status;
    }


    /*hitung total kalori di kolom kalori*/
    public int getTotalKaloriDibakar(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sumQuery=String.format("SELECT SUM(" + DatabaseHandlerOlahraga.KEY_KALORI_OLAHRAGA + ") as Total FROM " + DatabaseHandlerOlahraga.TABLE_OLAHRAGA);
        int sum=0;
        Cursor cursor = db.rawQuery(sumQuery, null);

        if (cursor.moveToFirst()) {

            sum = cursor.getInt(0);// get final total
        }
        return sum;
    }


    /*hitung jumlah baris*/
    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_OLAHRAGA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        return count;
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // db.delete(TABLE_NAME,null,null);
        //db.execSQL("delete * from"+ TABLE_NAME);
        db.execSQL("delete from "+ TABLE_OLAHRAGA);
        db.close();
    }







}
