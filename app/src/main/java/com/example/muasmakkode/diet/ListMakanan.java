package com.example.muasmakkode.diet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.muasmakkode.diet.Data.CardViewMakananAdapter;
import com.example.muasmakkode.diet.Data.ItemClickSupport;
import com.example.muasmakkode.diet.Data.MakananData;
import com.example.muasmakkode.diet.Data.MakananModel;
import com.example.muasmakkode.diet.UI.DetailMakanan;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMakanan extends AppCompatActivity {

    @BindView(R.id.recycle_view_makanan)
    RecyclerView recycleViewMakanan;
    private ArrayList<MakananModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_makanan);
        ButterKnife.bind(this);

        recycleViewMakanan.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(MakananData.getListData());

        showRecyclerCardView();
    }

    private void showRecyclerCardView(){
        recycleViewMakanan.setLayoutManager(new LinearLayoutManager(this));
        CardViewMakananAdapter cardViewMakananAdapter = new CardViewMakananAdapter();
        cardViewMakananAdapter.setListMakanan(list);
        recycleViewMakanan.setAdapter(cardViewMakananAdapter);

        ItemClickSupport.addTo(recycleViewMakanan).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedMakanan(list.get(position));
            }
        });
    }

    private void showSelectedMakanan (MakananModel makananModel){
        MakananData makananData;
        Intent intent = new Intent(this, DetailMakanan.class);
        intent.putExtra("makanan", makananModel);
        startActivity(intent);
    }
}
