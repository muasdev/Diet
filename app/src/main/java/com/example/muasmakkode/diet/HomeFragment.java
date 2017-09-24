package com.example.muasmakkode.diet;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

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

    TextView tv_umur;

    SharedPreferences pref;
    Unbinder unbinder;
    @BindView(R.id.textView_totalkalori)
    TextView textViewTotalkalori;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        pref = getContext().getSharedPreferences("userInfo", MODE_PRIVATE);

        try {
            Integer umur = Integer.parseInt(pref.getString("userumur", ""));
            Integer tinggiBadan = Integer.parseInt(pref.getString("usertinggi", ""));
            Integer total_energi, sisa_energi, maks;
            maks = 100;
            total_energi = umur + tinggiBadan;
            sisa_energi = maks - total_energi ;
            progresBar.setProgress(total_energi);
            textViewTotalkalori.setText(sisa_energi.toString() + " kkal tersisa dari " + total_energi.toString());
        } catch (Exception ex) {

        }


        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}