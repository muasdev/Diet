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
            {
                    "Nasi Putih",
                    "1 gelas",
                    "394",
                    "40",
                    "4",
                    "0"
            },
            {
                    "bubur beras",
                    "2 gelas",
                    "175",
                    "40",
                    "4",
                    "0"
            },
            {
                    "Roti Putih",
                    "4 iris",
                    "175",
                    "40",
                    "4",
                    "0"
            },
            {
                    "mi kering",
                    "1 gelas",
                    "175",
                    "40",
                    "4",
                    "0"
            },
            {
                    "Jagung segar",
                    "3 bh sedang",
                    "175",
                    "40",
                    "4",
                    "0"
            },
            {
                    "Daging Sapi",
                    "1 potong sedang",
                    "95",
                    "0",
                    "10",
                    "6"
            },
            {
                    "Daging ayam",
                    "1 ptg sdg",
                    "95",
                    "0",
                    "10",
                    "6"
            },
            {
                    "Hati sapi",
                    "1 ptg sdg",
                    "95",
                    "0",
                    "10",
                    "6"
            },
            {
                    "Ikan segar",
                    "1 ptg sdg",
                    "95",
                    "0",
                    "10",
                    "6"
            },
            {
                    "Telur ayam",
                    "2 butir",
                    "95",
                    "0",
                    "10",
                    "6"
            },
            {
                    "Telur bebek",
                    "1 butir",
                    "95",
                    "0",
                    "10",
                    "6"
            },
            {
                    "Ikan asin",
                    "1 ptg sdg",
                    "95",
                    "0",
                    "10",
                    "6"
            },
            {
                    "Bakso daging",
                    "10 biji bsr/20 biji kcl",
                    "95",
                    "0",
                    "10",
                    "6"
            },
            {
                    "kuning telur ayam",
                    "4 butir",
                    "150",
                    "0",
                    "7",
                    "5"
            },
            {
                    "bebek",
                    "1 ptg sdg",
                    "150",
                    "0",
                    "7",
                    "5"
            },
            {
                    "Tahu",
                    "1 biji besar",
                    "75",
                    "7",
                    "5",
                    "3"
            },
            {
                    "Tempe",
                    "2 ptg sdg",
                    "75",
                    "7",
                    "5",
                    "3"
            },
            {
                    "Anggur",
                    "20 bh sdg",
                    "50",
                    "12",
                    "0",
                    "0"
            },
            {
                    "Apel",
                    "1 bh",
                    "50",
                    "12",
                    "0",
                    "0"
            },
            {
                    "Durian",
                    "2 bj bsr",
                    "50",
                    "12",
                    "0",
                    "0"
            },
            {
                    "Jeruk manis",
                    "2 bh",
                    "50",
                    "12",
                    "0",
                    "0"
            },
            {
                    "Kedondong",
                    "2 bh sdg",
                    "50",
                    "12",
                    "0",
                    "0"
            },
            {
                    "Kurma",
                    "3 bh",
                    "50",
                    "12",
                    "0",
                    "0"
            },
            {
                    "Madu",
                    "1 sdm",
                    "50",
                    "12",
                    "0",
                    "0"
            },
            {
                    "Pepaya",
                    "1 ptg bsr",
                    "50",
                    "12",
                    "0",
                    "0"
            },
            {
                    "Pisang",
                    "1 bh",
                    "50",
                    "12",
                    "0",
                    "0"
            },
            {
                    "Rambutan",
                    "8 bh",
                    "50",
                    "12",
                    "0",
                    "0"
            },
            {
                    "Semangka",
                    "20 bh sdg",
                    "50",
                    "12",
                    "0",
                    "0"
            },
            {
                    "Keju",
                    "1 ptg kcl",
                    "125",
                    "10",
                    "7",
                    "6"
            },
            {
                    "Susu sapi",
                    "1 gls",
                    "125",
                    "10",
                    "7",
                    "6"
            },
            {
                    "Minyak zaitun",
                    "1 sdt",
                    "50",
                    "0",
                    "0",
                    "5"
            },
            {
                    "Minyak kelapa",
                    "1 sdt",
                    "50",
                    "0",
                    "0",
                    "5"
            },
    };

    /**
     * buat kelas array menampung data
     */
    public static ArrayList<MakananModel> getListData() {
        MakananModel makanan = null;
        ArrayList<MakananModel> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            makanan = new MakananModel();
            makanan.setNama_makanan(data[i][0]);
            makanan.setUkuran_saji(data[i][1]);
            makanan.setKalori_makanan(data[i][2]);
            makanan.setKarbo_makanan(data[i][3]);
            makanan.setProtein_makanan(data[i][4]);
            makanan.setLemak_makanan(data[i][5]);

            list.add(makanan);
        }

        return list;
    }

}