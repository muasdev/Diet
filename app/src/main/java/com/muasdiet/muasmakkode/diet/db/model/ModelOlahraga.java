package com.muasdiet.muasmakkode.diet.db.model;

/**
 * Created by muas on 16/03/2018.
 */

public class ModelOlahraga {
    int _id;
    String nama_olahraga_model;
    String kalori_olahraga_model;

    public ModelOlahraga(String nama_olahraga_model, String kalori_olahraga_model) {
        this.nama_olahraga_model = nama_olahraga_model;
        this.kalori_olahraga_model = kalori_olahraga_model;
    }

    public ModelOlahraga(int _id, String nama_olahraga_model, String kalori_olahraga_model) {
        this._id = _id;
        this.nama_olahraga_model = nama_olahraga_model;
        this.kalori_olahraga_model = kalori_olahraga_model;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNama_olahraga_model() {
        return nama_olahraga_model;
    }

    public void setNama_olahraga_model(String nama_olahraga_model) {
        this.nama_olahraga_model = nama_olahraga_model;
    }

    public String getKalori_olahraga_model() {
        return kalori_olahraga_model;
    }

    public void setKalori_olahraga_model(String kalori_olahraga_model) {
        this.kalori_olahraga_model = kalori_olahraga_model;
    }

    public ModelOlahraga() {
    }
}