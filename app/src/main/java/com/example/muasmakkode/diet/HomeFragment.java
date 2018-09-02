package com.example.muasmakkode.diet;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.muasmakkode.diet.RadioButton.RadioButtonActivity;
import com.example.muasmakkode.diet.db.DataAdapter;
import com.example.muasmakkode.diet.db.DatabaseHandler;
import com.example.muasmakkode.diet.db.adapter.DataOlahragaAdapter;
import com.example.muasmakkode.diet.db.helper.DatabaseHandlerOlahraga;
import com.example.muasmakkode.diet.db.model.ModelMakanan;
import com.example.muasmakkode.diet.db.model.ModelOlahraga;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    /*@BindView(R.id.progresBar)
    ProgressBar progresBar;*/


    SharedPreferences pref;
    Unbinder unbinder;
    @BindView(R.id.textView_totalKebutuhanKalori)
    TextView textViewTotalKebutuhankalori;
    /*@BindView(R.id.recycle_view_sarapan)
    RecyclerView recycleViewSarapan;*/
    /*@BindView(R.id.txtNomorurut)
    TextView txtNomorurut;*/
    @BindView(R.id.textView_totalKarbo)
    TextView textViewTotalKarbo;
    @BindView(R.id.textView_totalProtein)
    TextView textViewTotalProtein;
    @BindView(R.id.textView_totalLemak)
    TextView textViewTotalLemak;
    @BindView(R.id.textView_totalKalori)
    TextView textViewTotalKalori;
    /*@BindView(R.id.button_tambah_makanan)
    Button buttonTambahMakanan;
    @BindView(R.id.recycle_view_olahraga)
    RecyclerView recycleViewOlahraga;
    @BindView(R.id.button_tambahOlahraga)
    Button buttonTambahOlahraga;*/
    @BindView(R.id.textView_judulhalamanFragment)
    TextView textViewJudulhalamanFragment;
    /*@BindView(R.id.imgOlahraga)
    ImageView imgOlahraga;
    @BindView(R.id.textView_PesanEmptyDataOlahraga)
    TextView textViewPesanEmptyDataOlahraga;
    @BindView(R.id.imgMakanan)
    ImageView imgMakanan;
    @BindView(R.id.textView_PesanEmptyDataMakanan)
    TextView textViewPesanEmptyDataMakanan;*/
    @BindView(R.id.textView_totalKaloriDibakar)
    TextView textViewTotalKaloriDibakar;
    @BindView(R.id.textView_pesanLebihKalori)
    TextView textViewPesanLebihKalori;
    @BindView(R.id.button_tambahOlahraga)
    Button buttonTambahOlahraga;
    @BindView(R.id.textView_pesanLebihKaloriDibakar)
    TextView textViewPesanLebihKaloriDibakar;
    @BindView(R.id.button_tambah_makanan)
    Button buttonTambahMakanan;
    @BindView(R.id.cardView_pesanLebihKaloriDikonsumsi)
    CardView cardViewPesanLebihKaloriDikonsumsi;
    @BindView(R.id.cardView_pesanLebihKaloriDibakar)
    CardView cardViewPesanLebihKaloriDibakar;
    @BindView(R.id.cardView_peringatanKosong)
    CardView cardViewPeringatanKosong;
    @BindView(R.id.cardView_peringatanKarbo)
    CardView cardViewPeringatanKarbo;
    @BindView(R.id.cardView_peringatanPro)
    CardView cardViewPeringatanPro;
    @BindView(R.id.pesan_nutrisi_protein)
    TextView textViewPesanNutrisiProtein;
    @BindView(R.id.pesan_nutrisi_karbo)
    TextView textViewPesanNutrisiKarbo;
    @BindView(R.id.pesan_nutrisi_lemak)
    TextView textViewPesanNutrisiLemak;
    @BindView(R.id.cardView_peringatanLemak)
    CardView cardViewPeringatanLemak;


    private List<ModelMakanan> modelMakananList = new ArrayList<>();
    ModelMakanan modelMakanan;
    DataAdapter dataAdapters;

    private List<ModelOlahraga> modelOlahragaList = new ArrayList<>();
    ModelOlahraga modelOlahraga;
    DataOlahragaAdapter dataOlahragaAdapter;

    DatabaseHandler db;
    DatabaseHandlerOlahraga databaseHandlerOlahraga;

    SharedPreferences sharedPreferences;

    double sumTotalKarbo, sumTotalProtein, sumTotalLemak, kebKalori;

    String nilaiBmr;

    private ArrayList<ModelMakanan> modelMakananArrayList;
    private ArrayList<ModelOlahraga> modelOlahragaArrayList;

    private Toolbar toolbar;

    double batasMinumumProtein;
    double batasMaksimumProtein;
    double batasmaksimumLemak;
    double batasminimumLemak;
    double batasmaksimumKarbo;
    double batasminimumKarbo;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle("Menu Monitoring");
        unbinder = ButterKnife.bind(this, view);

        sharedPreferences = getContext().getSharedPreferences("dataBmr", Context.MODE_PRIVATE);
        if (sharedPreferences != null) {


            nilaiBmr = String.valueOf(sharedPreferences.getString("my_eaf", ""));
            textViewJudulhalamanFragment.setText("Kebutuhan kalori " + nilaiBmr + " kal");
        }


        //Instantiate database handler
        db = new DatabaseHandler(getContext());
        databaseHandlerOlahraga = new DatabaseHandlerOlahraga(getContext());
        /*db.getProfilesCount();*/
        /*int profile_counts = db.getProfilesCount();
        txtNomorurut.setText(String.valueOf("No. urut : " + (profile_counts + 1)));*/


        int sumTotalKalori = db.getTotalKalori();
        textViewTotalKalori.setText(" " + sumTotalKalori + " kal");

        int sumTotalKaloriDibakar = databaseHandlerOlahraga.getTotalKaloriDibakar();
        textViewTotalKaloriDibakar.setText(" " + sumTotalKaloriDibakar + " kal");

        double kebKarbo = Double.parseDouble(nilaiBmr) * 0.75;
        double kebPro = Double.parseDouble(nilaiBmr) * 0.15;
        double kebLemak = Double.parseDouble(nilaiBmr) * 0.25;

        sumTotalKarbo = db.getTotalKarbo();
        textViewTotalKarbo.setText("Karbo : " + sumTotalKarbo + "/" + (int)kebKarbo + " kal");

        sumTotalProtein = db.getTotalProtein();
        textViewTotalProtein.setText("Protein : " + sumTotalProtein + "/" + (int) kebPro + " kal");

        sumTotalLemak = db.getTotalLemak();
        textViewTotalLemak.setText("Lemak : " + sumTotalLemak + "/" + (int) kebLemak + " kal");


        /*kode untuk set progres bar*/
        String nilaiBmr = String.valueOf(sharedPreferences.getString("my_eaf", ""));
        double kaloriDikonsumsi, hasilProgres, kaloriDibakar, net, setProgresBar;
        kebKalori = Double.parseDouble(nilaiBmr);
        kaloriDikonsumsi = sumTotalKalori;
        kaloriDibakar = sumTotalKaloriDibakar;
        net = kaloriDikonsumsi - kaloriDibakar;
        hasilProgres = kebKalori - net;

        textViewTotalKebutuhankalori.setText("sisa " + hasilProgres + " kal" + " dari " + kebKalori + " kal");

        minimumProtein();
        maksimumProtein();
        minimumLemak();
        minimumKarbo();
        maksimumKarbo();
        maksimumLemak();
        /*maks = 1000;
        setProgresBar = maks - hasilProgres;*/
        /*progresBar.setMax(kebKalori);
        progresBar.setProgress(kaloriDikonsumsi);*/
        /*if (kaloriDikonsumsi > kebKalori) {
            *//*Toast.makeText(getContext(), " lebih ", Toast.LENGTH_SHORT).show();*//*
            *//*int color = 0xFF00FF00;
            progresBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
            progresBar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);*//*
            cardViewPeringatanKosong.setVisibility(View.GONE);
            cardViewPesanLebihKaloriDikonsumsi.setVisibility(View.VISIBLE);
        }
        if (kaloriDibakar > kaloriDikonsumsi) {
            cardViewPeringatanKosong.setVisibility(View.GONE);
            cardViewPesanLebihKaloriDibakar.setVisibility(View.VISIBLE);
        }*/
        if (sumTotalKarbo > batasmaksimumKarbo) {
            cardViewPeringatanKosong.setVisibility(View.GONE);
            textViewPesanNutrisiKarbo.setText(getString(R.string.pesan_peringatan_lebih_karbo));
            cardViewPeringatanKarbo.setVisibility(View.VISIBLE);
        }
        if (sumTotalKarbo < batasmaksimumKarbo) {
            cardViewPeringatanKosong.setVisibility(View.GONE);
            textViewPesanNutrisiKarbo.setText(getString(R.string.pesan_peringatan_kurang_karbo));
            cardViewPeringatanKarbo.setVisibility(View.VISIBLE);
        }
        if (sumTotalProtein < batasMinumumProtein) {
            cardViewPeringatanKosong.setVisibility(View.GONE);
            textViewPesanNutrisiProtein.setText(getString(R.string.pesan_peringatan_kurang_pro));
            cardViewPeringatanPro.setVisibility(View.VISIBLE);
        }
        if (sumTotalProtein > batasMaksimumProtein) {
            cardViewPeringatanKosong.setVisibility(View.GONE);
            textViewPesanNutrisiProtein.setText(getString(R.string.pesan_peringatan_lebih_pro));
            cardViewPeringatanPro.setVisibility(View.VISIBLE);
        }
        if (sumTotalLemak > batasmaksimumLemak) {
            cardViewPeringatanKosong.setVisibility(View.GONE);
            textViewPesanNutrisiLemak.setText(getString(R.string.pesan_peringatan_lebih_lemak));
            cardViewPeringatanLemak.setVisibility(View.VISIBLE);
        }
        if (sumTotalLemak < batasminimumLemak) {
            cardViewPeringatanKosong.setVisibility(View.GONE);
            textViewPesanNutrisiLemak.setText(getString(R.string.pesan_peringatan_kurang_lemak));
            cardViewPeringatanLemak.setVisibility(View.VISIBLE);
        }

        return view;
    }

    private void minimumProtein(){
        batasMinumumProtein = kebKalori * 10/100;
        Log.d("batasMinumumProtein", "minimumProtein: adalah " + batasMinumumProtein);
    }

    private void maksimumProtein(){
        batasMaksimumProtein = kebKalori * 15/100;
        Log.d("batasMaksimumProtein", "maksimumProtein: adalah " + batasMaksimumProtein);
    }

    private void maksimumLemak(){
        batasmaksimumLemak = kebKalori * 25/100;
        Log.d("batasmaksimumLemak", "maksimumLemak: adalah " + batasmaksimumLemak);
    }

    private void minimumLemak(){
        batasminimumLemak = kebKalori * 10/100;
        Log.d("batasminimumLemak", "minimumLemak: adalah " + batasminimumLemak);
    }

    private void maksimumKarbo(){
        batasmaksimumKarbo = kebKalori * 75/100;
        Log.d("batasmaksimumKarbo", "maksimumKarbo: adalah " + batasmaksimumKarbo);
    }

    private void minimumKarbo(){
        batasminimumKarbo = kebKalori * 60/100;
        Log.d("batasminimumKarob", "minimumKarob: adalah " + batasminimumKarbo);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.button_tambahOlahraga, R.id.button_tambah_makanan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_tambahOlahraga:
                Intent intent = new Intent(getContext(), RadioButtonActivity.class);
                startActivity(intent);
                break;
            case R.id.button_tambah_makanan:
                MakananFragment makananFragment = new MakananFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, makananFragment);
                fragmentTransaction.commit();
                break;
        }
    }

}