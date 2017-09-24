package com.example.muasmakkode.diet.Data;

import java.util.ArrayList;

/**
 * Created by muasmakkode on 21/09/2017.
 */

public class MakananData {

    /**
     * buat kelas data
     */
    public static String[][] data = new String[][]{
            {"Nasi Putih",
                    "karbo",
                    "175"},
            {"Daging Ayam",
                    "protein",
                    "50"},
            {"Telur Ayam",
                    "protein",
                    "75"},
            {"Nasi Putih",
                    "karbo",
                    "175"},
            {"Daging Ayam",
                    "protein",
                    "50"},
            {"Telur Ayam",
                    "protein",
                    "75"},
            {"Telur Ayam",
                    "protein",
                    "75"},
            {"Nasi Putih",
                    "karbo",
                    "175"},
            {"Daging Ayam",
                    "protein",
                    "50"},
    };

    /**
     *
     * buat kelas array menampung data
     */
    public static ArrayList<MakananModel> getListData(){
        MakananModel makanan = null;
        ArrayList<MakananModel> list = new ArrayList<>();
        for (int i = 0; i <data.length; i++) {
            makanan.setNama_makanan(data[i][0]);
            makanan.setJenis_makanan(data[i][1]);
            makanan.setKalori_makanan(data[i][2]);

            list.add(makanan);
        }

        return list;
    }

}