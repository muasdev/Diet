package com.example.muasmakkode.diet;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.muasmakkode.diet.RadioButton.RadioButtonActivity;
import com.example.muasmakkode.diet.UI.DetailKonsumsi;
import com.example.muasmakkode.diet.db.DataAdapter;
import com.example.muasmakkode.diet.db.DatabaseHandler;
import com.example.muasmakkode.diet.db.adapter.DataOlahragaAdapter;
import com.example.muasmakkode.diet.db.helper.DatabaseHandlerOlahraga;
import com.example.muasmakkode.diet.db.model.ModelMakanan;
import com.example.muasmakkode.diet.db.model.ModelOlahraga;
import com.intrusoft.scatter.ChartData;
import com.intrusoft.scatter.PieChart;

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
    @BindView(R.id.simple_chart)
    PieChart simpleChart;
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

    int sumTotalKarbo, sumTotalProtein, sumTotalLemak, kebKalori;

    String nilaiBmr;

    private ArrayList<ModelMakanan> modelMakananArrayList;
    private ArrayList<ModelOlahraga> modelOlahragaArrayList;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        sharedPreferences = getContext().getSharedPreferences("dataBmr", Context.MODE_PRIVATE);
        if (sharedPreferences != null) {


            nilaiBmr = String.valueOf(sharedPreferences.getString("my_eaf", ""));
            textViewJudulhalamanFragment.setText("Kebutuhan kalori " + nilaiBmr);
        }


        //Instantiate database handler
        db = new DatabaseHandler(getContext());
        databaseHandlerOlahraga = new DatabaseHandlerOlahraga(getContext());
        /*db.getProfilesCount();*/
        /*int profile_counts = db.getProfilesCount();
        txtNomorurut.setText(String.valueOf("No. urut : " + (profile_counts + 1)));*/


        int sumTotalKalori = db.getTotalKalori();
        textViewTotalKalori.setText(" " + sumTotalKalori + " kkal");

        int sumTotalKaloriDibakar = databaseHandlerOlahraga.getTotalKaloriDibakar();
        textViewTotalKaloriDibakar.setText(" " + sumTotalKaloriDibakar + " kkal");

        double kebKarbo = Integer.parseInt(nilaiBmr) * 0.15;
        double kebPro = Integer.parseInt(nilaiBmr) * 0.25;
        double kebLemak = Integer.parseInt(nilaiBmr) * 0.15;

        sumTotalKarbo = db.getTotalKarbo();
        textViewTotalKarbo.setText("Karbo : " + sumTotalKarbo + "/" + (int) kebKarbo);

        sumTotalProtein = db.getTotalProtein();
        textViewTotalProtein.setText("Protein : " + sumTotalProtein + "/" + (int) kebPro);

        sumTotalLemak = db.getTotalLemak();
        textViewTotalLemak.setText("Lemak : " + sumTotalLemak + "/" + (int) kebLemak);


        /*kode untuk set progres bar*/
        String nilaiBmr = String.valueOf(sharedPreferences.getString("my_eaf", ""));
        int kaloriDikonsumsi, hasilProgres, kaloriDibakar, net, setProgresBar;
        kebKalori = Integer.parseInt(nilaiBmr);
        kaloriDikonsumsi = sumTotalKalori;
        kaloriDibakar = sumTotalKaloriDibakar;
        net = kaloriDikonsumsi - kaloriDibakar;
        hasilProgres = kebKalori - net;

        textViewTotalKebutuhankalori.setText("sisa " + hasilProgres + " dari " + kebKalori);
        /*maks = 1000;
        setProgresBar = maks - hasilProgres;*/
        /*progresBar.setMax(kebKalori);
        progresBar.setProgress(kaloriDikonsumsi);*/
        if (kaloriDikonsumsi > kebKalori) {
            /*Toast.makeText(getContext(), " lebih ", Toast.LENGTH_SHORT).show();*/
            /*int color = 0xFF00FF00;
            progresBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
            progresBar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);*/
            cardViewPeringatanKosong.setVisibility(View.GONE);
            cardViewPesanLebihKaloriDikonsumsi.setVisibility(View.VISIBLE);
        }
        if (kaloriDibakar > kaloriDikonsumsi) {
            cardViewPeringatanKosong.setVisibility(View.GONE);
            cardViewPesanLebihKaloriDibakar.setVisibility(View.VISIBLE);
        }
        if (sumTotalKarbo > kebKarbo) {
            cardViewPeringatanKosong.setVisibility(View.GONE);
            cardViewPeringatanKarbo.setVisibility(View.VISIBLE);
        }
        if (sumTotalProtein > kebPro) {
            cardViewPeringatanKosong.setVisibility(View.GONE);
            cardViewPeringatanPro.setVisibility(View.VISIBLE);
        }
        if (sumTotalLemak > kebLemak) {
            cardViewPeringatanKosong.setVisibility(View.GONE);
            cardViewPeringatanLemak.setVisibility(View.VISIBLE);
        }


        charpie();


        /*kode untuk menampilkan pesan jika kelebihan konsumsi kalori*/


        /*String nilaiBmr = String.valueOf(sharedPreferences.getString("my_eaf", ""));
        Integer maks, sisa_energi, total_energi;
        maks = 2000;
        total_energi = maks - 100;
        sisa_energi = maks - total_energi;
        progresBar.setProgress(sisa_energi);
        textViewTotalKebutuhankalori.setText(sisa_energi.toString() + " kkal tersisa dari " + total_energi.toString());*/

        /*modelMakananArrayList = new ArrayList<>(db.getAllContacts());*/

        /*recycleViewSarapan = (RecyclerView) view.findViewById(R.id.recycle_view_sarapan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycleViewSarapan.setLayoutManager(layoutManager);
        *//*dataAdapters = new DataAdapter(modelMakananArrayList);*//*
        dataAdapters = new DataAdapter((ArrayList<ModelMakanan>) db.getAllContacts(), getContext());
        recycleViewSarapan.setAdapter(dataAdapters);
        if (dataAdapters.getItemCount() == 0) {
            imgMakanan.setVisibility(View.VISIBLE);
            textViewPesanEmptyDataMakanan.setVisibility(View.VISIBLE);
        }
        *//*ItemClickSupport.addTo(recycleViewSarapan).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedMakanan(modelMakananArrayList.get(position));
            }
        });*//*
        dataAdapters.notifyDataSetChanged();*/

        /*kode untuk set adapter
                ke dataolahraga*//*
        recycleViewOlahraga = (RecyclerView) view.findViewById(R.id.recycle_view_olahraga);
        RecyclerView.LayoutManager layoutManagerOlahraga = new LinearLayoutManager(getContext());
        recycleViewOlahraga.setLayoutManager(layoutManagerOlahraga);
        dataOlahragaAdapter = new DataOlahragaAdapter((ArrayList<ModelOlahraga>) databaseHandlerOlahraga.getAllDataOlahraga(), getContext());

        recycleViewOlahraga.setAdapter(dataOlahragaAdapter);
        if (dataOlahragaAdapter.getItemCount() == 0) {
            imgOlahraga.setVisibility(View.VISIBLE);
            textViewPesanEmptyDataOlahraga.setVisibility(View.VISIBLE);
        }
        dataOlahragaAdapter.notifyDataSetChanged();
*/
        /*pref = getContext().getSharedPreferences("userInfo", MODE_PRIVATE);

        try {
            Integer umur = Integer.parseInt(pref.getString("userumur", ""));
            Integer tinggiBadan = Integer.parseInt(pref.getString("usertinggi", ""));
            Integer total_energi, sisa_energi, maks;
            maks = 100;
            total_energi = umur + tinggiBadan;
            sisa_energi = maks - total_energi;
            progresBar.setProgress(total_energi);
            textViewTotalKebutuhankalori.setText(sisa_energi.toString() + " kkal tersisa dari " + total_energi.toString());
        } catch (Exception ex) {

        }*/


        return view;
    }

    public void charpie() {
        /*List<ChartData> data = new ArrayList<>();
        data.add(new ChartData("First", 35));     //ARGS-> (display text, percentage)
        data.add(new ChartData("Second", 25));
        data.add(new ChartData("Third", 30));
        data.add(new ChartData("Fourth", 10));
        pieChart.setChartData(data);*/

//chart data with specified colors
        List<ChartData> data = new ArrayList<>();
        data = new ArrayList<>();

//ARGS-> (displayText, percentage, textColor, backgroundColor)
        int hasilKarb = (int) (0.60 * kebKalori);
        data.add(new ChartData(String.valueOf(sumTotalKarbo) + " / " + hasilKarb, 60, Color.WHITE, Color.parseColor("#0091EA")));    //ARGS-> (display text, percentage)
        data.add(new ChartData(String.valueOf(sumTotalProtein) + " / " + (int) (0.25 * kebKalori), 25, Color.WHITE, Color.parseColor("#FF4081")));    //ARGS-> (display text, percentage)
        data.add(new ChartData(String.valueOf(sumTotalLemak) + " / " + (int) (0.15 * kebKalori), 15, Color.WHITE, Color.parseColor("#FFFFE600")));    //ARGS-> (display text, percentage)
        /*data.add(new ChartData("Second", 25));*/

        /*data.add(new ChartData("First", 35, Color.WHITE, Color.parseColor("#0091EA")));
        data.add(new ChartData("Second", 25, Color.WHITE, Color.parseColor("#33691E")));
        data.add(new ChartData("Third", 30, Color.DKGRAY, Color.parseColor("#F57F17")));
        data.add(new ChartData("Fourth", 10, Color.DKGRAY, Color.parseColor("#FFD600")));*/
        simpleChart.setChartData(data);
    }

    private void showSelectedMakanan(ModelMakanan modelMakanan) {
        Intent i = new Intent(getContext(), DetailKonsumsi.class);
        modelMakanan.get_id();
        modelMakanan.getNama_model();
        modelMakanan.getUrt_model();
        modelMakanan.getKalori_model();
        modelMakanan.getKarbo_model();
        modelMakanan.getProtein_model();
        modelMakanan.getLemak_model();

        i.putExtra("modelMakanan", modelMakanan);
        startActivity(i);
    }
    /*void addData(){
        modelMakananArrayList = new ArrayList<>();
        modelMakananArrayList.add(new ModelMakanan(1, "123456789", "dada", "ddd", "Dimas Maulan", "dddss", "ssss", "ssss"));
        modelMakananArrayList.add(new ModelMakanan(2, "123456789", "dada", "ddd", "Dimas Maulan", "dddss", "ssss", "ssss"));
    }*/


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



    /*@OnClick({R.id.button_tambah_makanan, R.id.button_tambahOlahraga})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_tambah_makanan:
                MakananFragment makananFragment = new MakananFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, makananFragment);
                fragmentTransaction.commit();
                break;
            case R.id.button_tambahOlahraga:
                Intent intent = new Intent(getContext(), RadioButtonActivity.class);
                startActivity(intent);
                break;
        }
    }*/
}