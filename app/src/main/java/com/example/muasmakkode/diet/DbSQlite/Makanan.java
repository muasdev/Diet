package com.example.muasmakkode.diet.DbSQlite;

/**
 * Created by muasmakkode on 01/11/2017.
 */

public class Makanan {

    //private variable
    int id;
    String nama_food;
    String total_kalori;

    //Empty constructor
    public Makanan() {

    }

    public Makanan(int id, String nama_food, String total_kalori) {
        this.id = id;
        this.nama_food = nama_food;
        this.total_kalori = total_kalori;
    }

    public Makanan(String nama_food, String total_kalori) {
        this.nama_food = nama_food;
        this.total_kalori = total_kalori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_food() {
        return nama_food;
    }

    public void setNama_food(String nama_food) {
        this.nama_food = nama_food;
    }

    public String getTotal_kalori() {
        return total_kalori;
    }

    public void setTotal_kalori(String total_kalori) {
        this.total_kalori = total_kalori;
    }
}
