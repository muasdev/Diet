package com.example.muasmakkode.diet;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.muasmakkode.diet.db.DataAdapter;
import com.example.muasmakkode.diet.db.DatabaseHandler;
import com.example.muasmakkode.diet.db.model.ModelMakanan;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;


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


    private List<ModelMakanan> modelMakananList = new ArrayList<>();

    ModelMakanan modelMakanan;

    DataAdapter dataAdapters;

    DatabaseHandler db;

    private ArrayList<ModelMakanan> modelMakananArrayList;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        //Instantiate database handler
        db = new DatabaseHandler(getContext());
        db.getProfilesCount();
        /*int profile_counts = db.getProfilesCount();
        txtNomorurut.setText(String.valueOf("No. urut : " + (profile_counts + 1)));*/


        int sumTotalKalori = db.getTotalKalori();
        textViewTotalKalori.setText(" " + sumTotalKalori);

        int sumTotalKarbo = db.getTotalKarbo();
        textViewTotalKarbo.setText(" " + sumTotalKarbo);

        int sumTotalProtein = db.getTotalProtein();
        textViewTotalProtein.setText(" " + sumTotalProtein);

        int sumTotalLemak = db.getTotalLemak();
        textViewTotalLemak.setText(" " + sumTotalLemak);

        modelMakananArrayList = new ArrayList<>(db.getAllContacts());

        recycleViewSarapan = (RecyclerView) view.findViewById(R.id.recycle_view_sarapan);


        dataAdapters = new DataAdapter(modelMakananArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recycleViewSarapan.setLayoutManager(layoutManager);

        recycleViewSarapan.setAdapter(dataAdapters);

        pref = getContext().getSharedPreferences("userInfo", MODE_PRIVATE);

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

        }


        return view;
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

}