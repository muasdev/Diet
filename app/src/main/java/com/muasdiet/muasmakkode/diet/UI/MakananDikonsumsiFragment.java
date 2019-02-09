package com.muasdiet.muasmakkode.diet.UI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muasdiet.muasmakkode.diet.MakananFragment;
import com.muasdiet.muasmakkode.diet.R;
import com.muasdiet.muasmakkode.diet.db.DataAdapter;
import com.muasdiet.muasmakkode.diet.db.DatabaseHandler;
import com.muasdiet.muasmakkode.diet.db.model.ModelMakanan;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakananDikonsumsiFragment extends Fragment {


    @BindView(R.id.recycle_view_sarapan)
    RecyclerView recycleViewSarapan;

    DataAdapter dataAdapters;
    DatabaseHandler db;
    @BindView(R.id.fab_tambahMakanan)
    FloatingActionButton fabTambahMakanan;

    @BindView(R.id.textView_nomakanan)
    TextView textViewNoMkn;

    private List<ModelMakanan> modelMakananList = new ArrayList<>();
    private ArrayList<ModelMakanan> modelMakananArrayList;
    ModelMakanan modelMakanan;

    Unbinder unbinder;

    public MakananDikonsumsiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_makanan_dikonsumsi, container, false);

        getActivity().setTitle("Menu Makanan");
        unbinder = ButterKnife.bind(this, view);

        //Instantiate database handler
        db = new DatabaseHandler(getContext());
        recycleViewSarapan = view.findViewById(R.id.recycle_view_sarapan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycleViewSarapan.setLayoutManager(layoutManager);
        dataAdapters = new DataAdapter((ArrayList<ModelMakanan>) db.getAllContacts(), getContext());
        recycleViewSarapan.setAdapter(dataAdapters);
        if (dataAdapters.getItemCount() == 0) {
            textViewNoMkn.setVisibility(View.VISIBLE);
        }
        dataAdapters.notifyDataSetChanged();
        fabTambahMakanan.attachToRecyclerView(recycleViewSarapan);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fab_tambahMakanan)
    public void onViewClicked() {
        MakananFragment makananFragment = new MakananFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, makananFragment);
        fragmentTransaction.commit();
    }
}
