package com.example.muasmakkode.diet.Data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by muasmakkode on 21/09/2017.
 */

public class MakananModel implements Parcelable{
    private String nama_makanan;
    private String jenis_makanan;
    private String kalori_makanan;

    public String getKarbo_makanan() {
        return karbo_makanan;
    }

    public void setKarbo_makanan(String karbo_makanan) {
        this.karbo_makanan = karbo_makanan;
    }

    public String getProtein_makanan() {
        return protein_makanan;
    }

    public void setProtein_makanan(String protein_makanan) {
        this.protein_makanan = protein_makanan;
    }

    public String getLemak_makanan() {
        return lemak_makanan;
    }

    public void setLemak_makanan(String lemak_makanan) {
        this.lemak_makanan = lemak_makanan;
    }

    private String karbo_makanan, protein_makanan, lemak_makanan;


    public String getNama_makanan() {
        return nama_makanan;
    }

    public void setNama_makanan(String nama_makanan) {
        this.nama_makanan = nama_makanan;
    }

    public String getJenis_makanan() {
        return jenis_makanan;
    }

    public void setJenis_makanan(String jenis_makanan) {
        this.jenis_makanan = jenis_makanan;
    }

    public String getKalori_makanan() {
        return kalori_makanan;
    }

    public void setKalori_makanan(String kalori_makanan) {
        this.kalori_makanan = kalori_makanan;
    }


    public MakananModel() {
        super();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama_makanan);
        dest.writeString(this.jenis_makanan);
        dest.writeString(this.kalori_makanan);
        dest.writeString(this.karbo_makanan);
        dest.writeString(this.protein_makanan);
        dest.writeString(this.lemak_makanan);
    }

    protected MakananModel(Parcel in) {
        this.nama_makanan = in.readString();
        this.jenis_makanan = in.readString();
        this.kalori_makanan = in.readString();
        this.karbo_makanan = in.readString();
        this.protein_makanan = in.readString();
        this.lemak_makanan = in.readString();
    }

    public static final Creator<MakananModel> CREATOR = new Creator<MakananModel>() {
        @Override
        public MakananModel createFromParcel(Parcel source) {
            return new MakananModel(source);
        }

        @Override
        public MakananModel[] newArray(int size) {
            return new MakananModel[size];
        }
    };
}
