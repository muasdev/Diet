package com.example.muasmakkode.diet;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.muasmakkode.diet.RadioButton.RadioButtonActivity;
import com.example.muasmakkode.diet.UI.DetailKonsumsi;
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


    @BindView(R.id.progresBar)
    ProgressBar progresBar;


    SharedPreferences pref;
    Unbinder unbinder;
    @BindView(R.id.textView_totalKebutuhanKalori)
    TextView textViewTotalKebutuhankalori;
    @BindView(R.id.recycle_view_sarapan)
    RecyclerView recycleViewSarapan;
    @BindView(R.id.txtNomorurut)
    TextView txtNomorurut;
    @BindView(R.id.textView_totalKarbo)
    TextView textViewTotalKarbo;
    @BindView(R.id.textView_totalProtein)
    TextView textViewTotalProtein;
    @BindView(R.id.textView_totalLemak)
    TextView textViewTotalLemak;
    @BindView(R.id.textView_totalKalori)
    TextView textViewTotalKalori;
    @BindView(R.id.textView_totalKaloriKonsumsi)
    TextView textViewTotalKaloriKonsumsi;
    @BindView(R.id.textView_totalKaloriDibakar)
    TextView textViewTotalKaloriDibakar;
    @BindView(R.id.button_tambah_makanan)
    Button buttonTambahMakanan;
    @BindView(R.id.recycle_view_olahraga)
    RecyclerView recycleViewOlahraga;
    @BindView(R.id.button_tambahOlahraga)
    Button buttonTambahOlahraga;


    private List<ModelMakanan> modelMakananList = new ArrayList<>();

    private List<ModelOlahraga> modelOlahragaList = new ArrayList<>();

    ModelMakanan modelMakanan;
    ModelOlahraga modelOlahraga;

    DataAdapter dataAdapters;
    DataOlahragaAdapter dataOlahragaAdapter;

    DatabaseHandler db;
    DatabaseHandlerOlahraga databaseHandlerOlahraga;

    SharedPreferences sharedPreferences;

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


            String nilaiBmr = String.valueOf(sharedPreferences.getString("my_eaf", ""));
            textViewTotalKebutuhankalori.setText("" + nilaiBmr);
        }

        /*if (sharedPreferences != null) {
            String nilaiBmr = String.valueOf(sharedPreferences.getString("my_eaf", ""));
            textViewTotalKebutuhankalori.setText("" + nilaiBmr);
        }*/


        //Instantiate database handler
        db = new DatabaseHandler(getContext());
        databaseHandlerOlahraga = new DatabaseHandlerOlahraga(getContext());
        /*db.getProfilesCount();*/
        /*int profile_counts = db.getProfilesCount();
        txtNomorurut.setText(String.valueOf("No. urut : " + (profile_counts + 1)));*/


        int sumTotalKalori = db.getTotalKalori();
        textViewTotalKalori.setText("Kalori : " + sumTotalKalori);
        textViewTotalKaloriKonsumsi.setText("Kalori : " + sumTotalKalori);

        int sumTotalKarbo = db.getTotalKarbo();
        textViewTotalKarbo.setText("Karbo : " + sumTotalKarbo);

        int sumTotalProtein = db.getTotalProtein();
        textViewTotalProtein.setText("Protein : " + sumTotalProtein);

        int sumTotalLemak = db.getTotalLemak();
        textViewTotalLemak.setText("Lemak : " + sumTotalLemak);

        /*modelMakananArrayList = new ArrayList<>(db.getAllContacts());*/

        recycleViewSarapan = (RecyclerView) view.findViewById(R.id.recycle_view_sarapan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycleViewSarapan.setLayoutManager(layoutManager);
        /*dataAdapters = new DataAdapter(modelMakananArrayList);*/
        dataAdapters = new DataAdapter((ArrayList<ModelMakanan>) db.getAllContacts(), getContext());
        recycleViewSarapan.setAdapter(dataAdapters);
        /*ItemClickSupport.addTo(recycleViewSarapan).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedMakanan(modelMakananArrayList.get(position));
            }
        });*/
        dataAdapters.notifyDataSetChanged();

        /*kode untuk set adapter
                ke dataolahraga*/
        recycleViewOlahraga = (RecyclerView) view.findViewById(R.id.recycle_view_olahraga);
        RecyclerView.LayoutManager layoutManagerOlahraga = new LinearLayoutManager(getContext());
        recycleViewOlahraga.setLayoutManager(layoutManagerOlahraga);
        dataOlahragaAdapter = new DataOlahragaAdapter((ArrayList<ModelOlahraga>) databaseHandlerOlahraga.getAllDataOlahraga(), getContext());
        recycleViewOlahraga.setAdapter(dataOlahragaAdapter);
        dataOlahragaAdapter.notifyDataSetChanged();

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


    @OnClick({R.id.button_tambah_makanan, R.id.button_tambahOlahraga})
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
    }
}