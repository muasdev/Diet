package com.muasdiet.muasmakkode.diet.db.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelMakanan implements Parcelable {

    int _id;
    String nama_model;
    String urt_model;
    String kalori_model;
    String karbo_model;
    String protein_model;
    String lemak_model;

    public ModelMakanan(String nama_model, String urt_model, String kalori_model, String karbo_model, String protein_model, String lemak_model) {
        this.nama_model = nama_model;
        this.urt_model = urt_model;
        this.kalori_model = kalori_model;
        this.karbo_model = karbo_model;
        this.protein_model = protein_model;
        this.lemak_model = lemak_model;
    }

    public ModelMakanan(int _id, String nama_model, String urt_model, String kalori_model, String karbo_model, String protein_model, String lemak_model) {
        this._id = _id;
        this.nama_model = nama_model;
        this.urt_model = urt_model;
        this.kalori_model = kalori_model;
        this.karbo_model = karbo_model;
        this.protein_model = protein_model;
        this.lemak_model = lemak_model;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNama_model() {
        return nama_model;
    }

    public void setNama_model(String nama_model) {
        this.nama_model = nama_model;
    }

    public String getUrt_model() {
        return urt_model;
    }

    public void setUrt_model(String urt_model) {
        this.urt_model = urt_model;
    }

    public String getKalori_model() {
        return kalori_model;
    }

    public void setKalori_model(String kalori_model) {
        this.kalori_model = kalori_model;
    }

    public String getKarbo_model() {
        return karbo_model;
    }

    public void setKarbo_model(String karbo_model) {
        this.karbo_model = karbo_model;
    }

    public String getProtein_model() {
        return protein_model;
    }

    public void setProtein_model(String protein_model) {
        this.protein_model = protein_model;
    }

    public String getLemak_model() {
        return lemak_model;
    }

    public void setLemak_model(String lemak_model) {
        this.lemak_model = lemak_model;
    }

    public ModelMakanan() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.nama_model);
        parcel.writeString(this.urt_model);
        parcel.writeString(this.kalori_model);
        parcel.writeString(this.karbo_model);
        parcel.writeString(this.protein_model);
        parcel.writeString(this.lemak_model);
    }

    protected ModelMakanan(Parcel in) {
        this.nama_model = in.readString();
        this.urt_model = in.readString();
        this.kalori_model = in.readString();
        this.karbo_model = in.readString();
        this.protein_model = in.readString();
        this.lemak_model = in.readString();
    }

    public static final Creator<ModelMakanan> CREATOR = new Creator<ModelMakanan>() {
        @Override
        public ModelMakanan createFromParcel(Parcel source) {
            return new ModelMakanan(source);
        }

        @Override
        public ModelMakanan[] newArray(int size) {
            return new ModelMakanan[size];
        }
    };
}