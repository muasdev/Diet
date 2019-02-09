package com.muasdiet.muasmakkode.diet;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.muasdiet.muasmakkode.diet.RadioButton.RadioButtonActivity;
import com.muasdiet.muasmakkode.diet.db.DataAdapter;
import com.muasdiet.muasmakkode.diet.db.DatabaseHandler;
import com.muasdiet.muasmakkode.diet.db.adapter.DataOlahragaAdapter;
import com.muasdiet.muasmakkode.diet.db.helper.DatabaseHandlerOlahraga;
import com.muasdiet.muasmakkode.diet.db.model.ModelMakanan;
import com.muasdiet.muasmakkode.diet.db.model.ModelOlahraga;

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

    @BindView(R.id.textView_totalKarbodikonsumsi)
    TextView textViewtotalKarbodikonsumsi;
    @BindView(R.id.textView_totalProteindikonsumsi)
    TextView textViewtotalProteindikonsumsi;
    @BindView(R.id.textView_totalLemakdikonsumsi)
    TextView textViewtotalLemakdikonsumsi;
    @BindView(R.id.button_lihat_hasil)
    Button btnLihatHasil;


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

    double kebKarbobatasbawah ;
    double kebKarbobatasatas ;

    double kebProbatasbawah ;
    double kebProbatasatas ;

    double kebLemakbatasbawah ;
    double kebLemakbatasatas ;

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

        kebKarbobatasbawah = Double.parseDouble(nilaiBmr) * 0.60;
        kebKarbobatasatas = Double.parseDouble(nilaiBmr) * 0.75;

        kebProbatasbawah = Double.parseDouble(nilaiBmr) * 0.10;
        kebProbatasatas = Double.parseDouble(nilaiBmr) * 0.15;

        kebLemakbatasbawah = Double.parseDouble(nilaiBmr) * 0.10;
        kebLemakbatasatas = Double.parseDouble(nilaiBmr) * 0.25;

        sumTotalKarbo = db.getTotalKarbo();
        textViewTotalKarbo.setText("Karbo : " + (int)kebKarbobatasbawah + " - " + (int)kebKarbobatasatas + " kal");
        textViewtotalKarbodikonsumsi.setText(sumTotalKarbo + " kal telah dipenuhi");

        sumTotalProtein = db.getTotalProtein();
        textViewTotalProtein.setText("Protein : " + (int)kebProbatasbawah + " - " + (int)kebProbatasatas + " kal");
        textViewtotalProteindikonsumsi.setText(sumTotalProtein + " kal telah dipenuhi");

        sumTotalLemak = db.getTotalLemak();
        textViewTotalLemak.setText("Lemak : " + (int)kebLemakbatasbawah + " - " + (int)kebLemakbatasatas + " kal");
        textViewtotalLemakdikonsumsi.setText(sumTotalLemak + " kal telah dipenuhi");

        btnLihatHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sumTotalKarbo < kebKarbobatasbawah){
                    cardViewPeringatanKosong.setVisibility(View.GONE);
                    textViewPesanNutrisiKarbo.setText(getString(R.string.pesan_peringatan_kurang_karbo));
                    cardViewPeringatanKarbo.setVisibility(View.VISIBLE);
                }
                if (sumTotalKarbo > kebKarbobatasatas){
                    cardViewPeringatanKosong.setVisibility(View.GONE);
                    textViewPesanNutrisiKarbo.setText(getString(R.string.pesan_peringatan_lebih_karbo));
                    cardViewPeringatanKarbo.setVisibility(View.VISIBLE);
                }
                if (sumTotalKarbo > kebKarbobatasbawah && sumTotalKarbo < kebKarbobatasatas){
                    cardViewPeringatanKosong.setVisibility(View.GONE);
                    textViewPesanNutrisiKarbo.setText(getString(R.string.pesan_peringatan_normal_karbo));
                    cardViewPeringatanKarbo.setVisibility(View.VISIBLE);
                }
                if (sumTotalProtein < kebProbatasbawah) {
                    cardViewPeringatanKosong.setVisibility(View.GONE);
                    textViewPesanNutrisiProtein.setText(getString(R.string.pesan_peringatan_kurang_pro));
                    cardViewPeringatanPro.setVisibility(View.VISIBLE);
                }
                if (sumTotalProtein > kebProbatasatas) {
                    cardViewPeringatanKosong.setVisibility(View.GONE);
                    textViewPesanNutrisiProtein.setText(getString(R.string.pesan_peringatan_lebih_pro));
                    cardViewPeringatanPro.setVisibility(View.VISIBLE);
                }
                if (sumTotalProtein > kebProbatasbawah && sumTotalProtein < kebProbatasatas){
                    cardViewPeringatanKosong.setVisibility(View.GONE);
                    textViewPesanNutrisiKarbo.setText(getString(R.string.pesan_peringatan_normal_karbo));
                    cardViewPeringatanKarbo.setVisibility(View.VISIBLE);
                }
                if (sumTotalLemak > kebLemakbatasatas) {
                    cardViewPeringatanKosong.setVisibility(View.GONE);
                    textViewPesanNutrisiLemak.setText(getString(R.string.pesan_peringatan_lebih_lemak));
                    cardViewPeringatanLemak.setVisibility(View.VISIBLE);
                }
                if (sumTotalLemak < kebLemakbatasbawah) {
                    cardViewPeringatanKosong.setVisibility(View.GONE);
                    textViewPesanNutrisiLemak.setText(getString(R.string.pesan_peringatan_kurang_lemak));
                    cardViewPeringatanLemak.setVisibility(View.VISIBLE);
                }
                if (sumTotalLemak > kebLemakbatasbawah && sumTotalLemak < kebLemakbatasatas){
                    cardViewPeringatanKosong.setVisibility(View.GONE);
                    textViewPesanNutrisiKarbo.setText(getString(R.string.pesan_peringatan_normal_karbo));
                    cardViewPeringatanKarbo.setVisibility(View.VISIBLE);
                }
            }
        });



        /*kode untuk set progres bar*/
        String nilaiBmr = String.valueOf(sharedPreferences.getString("my_eaf", ""));
        double kaloriDikonsumsi, hasilProgres, kaloriDibakar, net, setProgresBar;
        kebKalori = Double.parseDouble(nilaiBmr);
        kaloriDikonsumsi = sumTotalKalori;
        kaloriDibakar = sumTotalKaloriDibakar;
        net = kaloriDikonsumsi - kaloriDibakar;
        hasilProgres = kebKalori - net;
        int angkaSignifikan = 2;
        double temp = Math.pow(10, angkaSignifikan);
        double y = (double) Math.round(hasilProgres*temp)/temp;


        textViewTotalKebutuhankalori.setText("sisa " + y + " kal" + " dari " + kebKalori + " kal");


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


        return view;
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