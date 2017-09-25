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
                    "175",
            "20",
            "50",
            "60"},
            {"Daging Ayam",
                    "protein",
                    "50",
                    "20",
                    "50",
                    "60"},
            {"Telur Ayam",
                    "protein",
                    "75",
                    "20",
                    "50",
                    "60"},
            {"Nasi Putih",
                    "karbo",
                    "175",
                    "20",
                    "50",
                    "60"},
    };

    /**
     *
     * buat kelas array menampung data
     */
    public static ArrayList<MakananModel> getListData(){
        MakananModel makanan = null;
        ArrayList<MakananModel> list = new ArrayList<>();
        for (int i = 0; i <data.length; i++) {
            makanan = new MakananModel();
            makanan.setNama_makanan(data[i][0]);
            makanan.setJenis_makanan(data[i][1]);
            makanan.setKalori_makanan(data[i][2]);
            makanan.setKarbo_makanan(data[i][3]);
            makanan.setProtein_makanan(data[i][4]);
            makanan.setLemak_makanan(data[i][5]);

            list.add(makanan);
        }

        return list;
    }

}