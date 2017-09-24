package com.example.muasmakkode.diet.Data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by muasmakkode on 20/09/2017.
 */

public class SharedPref {


    private static SharedPreferences mSharedPref;
    public static final String NAMA = "NAMA";
    public static final String UMUR = "UMUR";
    public static final String JENIS_KELAMIN = "JENIS_KELAMIN";
    public static final String BERAT_BADAN = "BERAT_BADAN";
    public static final String TINGGI_BADAN = "TINGGI_BADAN";
    public static final String JENIS_AKTIFITAS = "JENIS_AKTIFITAS";

    private SharedPref() {
    }

    public static void init(Context context) {
        if (mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public static boolean read(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public static Integer read(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static void write(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value).commit();
    }

}
