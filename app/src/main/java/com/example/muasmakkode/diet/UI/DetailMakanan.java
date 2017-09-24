package com.example.muasmakkode.diet.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.muasmakkode.diet.Data.MakananData;
import com.example.muasmakkode.diet.Data.MakananModel;
import com.example.muasmakkode.diet.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMakanan extends AppCompatActivity {


    @BindView(R.id.textView_detail_nama_makanan)
    TextView textViewDetailNamaMakanan;
    @BindView(R.id.textView_detail_jenis_makanan)
    TextView textViewDetailJenisMakanan;
    @BindView(R.id.textView_detail_kalori_makanan)
    TextView textViewDetailKaloriMakanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_makanan);
        ButterKnife.bind(this);

        MakananModel makananModel = (MakananModel) getIntent().getParcelableExtra("Makanan");
        textViewDetailNamaMakanan.setText(makananModel.getNama_makanan());

    }
}
