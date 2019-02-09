package com.muasdiet.muasmakkode.diet.UI;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muasdiet.muasmakkode.diet.R;
import com.muasdiet.muasmakkode.diet.RadioButton.RadioButtonActivity;
import com.muasdiet.muasmakkode.diet.db.adapter.DataOlahragaAdapter;
import com.muasdiet.muasmakkode.diet.db.helper.DatabaseHandlerOlahraga;
import com.muasdiet.muasmakkode.diet.db.model.ModelOlahraga;
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
public class KaloriDibakarFragment extends Fragment {


    @BindView(R.id.recycle_view_olahraga)
    RecyclerView recycleViewOlahraga;

    DataOlahragaAdapter dataOlahragaAdapter;
    DatabaseHandlerOlahraga databaseHandlerOlahraga;
    @BindView(R.id.fab_tambahOlahraga)
    FloatingActionButton fabTambahOlahraga;
    @BindView(R.id.textView_noOlahraga)
    TextView textView_noOlahraga;

    private List<ModelOlahraga> modelOlahragaList = new ArrayList<>();
    ModelOlahraga modelOlahraga;
    Unbinder unbinder;

    public KaloriDibakarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kalori_dibakar, container, false);
        getActivity().setTitle("Menu Olahraga");
        unbinder = ButterKnife.bind(this, view);

        databaseHandlerOlahraga = new DatabaseHandlerOlahraga(getContext());

        /*kode untuk set adapter
                ke dataolahraga*/
        recycleViewOlahraga = view.findViewById(R.id.recycle_view_olahraga);
        RecyclerView.LayoutManager layoutManagerOlahraga = new LinearLayoutManager(getContext());
        recycleViewOlahraga.setLayoutManager(layoutManagerOlahraga);
        dataOlahragaAdapter = new DataOlahragaAdapter((ArrayList<ModelOlahraga>) databaseHandlerOlahraga.getAllDataOlahraga(), getContext());

        recycleViewOlahraga.setAdapter(dataOlahragaAdapter);
        if (dataOlahragaAdapter.getItemCount() == 0) {
            textView_noOlahraga.setVisibility(View.VISIBLE);
        }
        dataOlahragaAdapter.notifyDataSetChanged();

        fabTambahOlahraga.attachToRecyclerView(recycleViewOlahraga);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.fab_tambahOlahraga)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), RadioButtonActivity.class);
        startActivity(intent);
    }
}
