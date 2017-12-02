package com.example.muasmakkode.diet.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muasmakkode.diet.Data.MakananModel;
import com.example.muasmakkode.diet.DbSQlite.DatabaseHandler;
import com.example.muasmakkode.diet.DbSQlite.Makanan;
import com.example.muasmakkode.diet.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMakanan extends AppCompatActivity {


    @BindView(R.id.textView_detail_kalori_makanan)
    TextView textViewDetailKaloriMakanan;
    @BindView(R.id.textView_detail_karbohidrat)
    TextView textViewDetailKarbohidrat;
    @BindView(R.id.textView_detail_protein)
    TextView textViewDetailProtein;
    @BindView(R.id.textView_detail_lemak)
    TextView textViewDetailLemak;
    @BindView(R.id.editText_jumlah_takaran)
    EditText editTextJumlahTakaran;
    @BindView(R.id.button_tambah)
    Button buttonTambah;

    DatabaseHandler databaseHandler = new DatabaseHandler(this);
    @BindView(R.id.textView_jumlahurt)
    TextView textViewJumlahurt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_makanan);
        ButterKnife.bind(this);

        final MakananModel makananModel;
        makananModel = getIntent().getParcelableExtra("makananModel");

        String judulTitleBar = makananModel.getNama_makanan().toString();
        getSupportActionBar().setTitle(judulTitleBar);


//        textViewDetailNamaMakanan.setText(makananModel.getNama_makanan()); //kode set textview dari model

        textViewDetailKaloriMakanan.setText(makananModel.getKalori_makanan());
        textViewDetailKarbohidrat.setText(makananModel.getKarbo_makanan());
        textViewDetailProtein.setText(makananModel.getProtein_makanan());
        textViewDetailLemak.setText(makananModel.getLemak_makanan());
        textViewJumlahurt.setText(makananModel.getUkuran_saji());

        editTextJumlahTakaran.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                try {


                    int hasil_karbo = Integer.parseInt(editTextJumlahTakaran.getText().toString())
                            * Integer.parseInt(makananModel.getKarbo_makanan().toString());

                    double hasil_kalori = Integer.parseInt(editTextJumlahTakaran.getText().toString())
                            * Integer.parseInt(makananModel.getKalori_makanan().toString());

                    int hasil_protein = Integer.parseInt(editTextJumlahTakaran.getText().toString())
                            * Integer.parseInt(makananModel.getProtein_makanan().toString());

                    int hasil_lemak = Integer.parseInt(editTextJumlahTakaran.getText().toString())
                            * Integer.parseInt(makananModel.getLemak_makanan().toString());

                    textViewDetailKaloriMakanan.setText(String.valueOf(hasil_kalori));
                    textViewDetailKarbohidrat.setText(String.valueOf(hasil_karbo));
                    textViewDetailProtein.setText(String.valueOf(hasil_protein));
                    textViewDetailLemak.setText(String.valueOf(hasil_lemak));


                } catch (Exception ex) {

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void tambahData(View view) {

        /**
         * CRUD Operations
         * */

        String totalKalori = textViewDetailKaloriMakanan.getText().toString();

        // Inserting Contacts
//        Log.d("Insert: ", "Inserting ..");
//        databaseHandler.addMakanan(new Makanan(namaMakanan, totalKalori));

        Toast.makeText(this, "anda telah menambah data makanan yang anda konsumsi", Toast.LENGTH_SHORT).show();

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Makanan> makanan = databaseHandler.getAllMakanans();

        for (Makanan cn : makanan) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getNama_food() + " ,Phone: " + cn.getTotal_kalori();
            // Writing Makanans to log
            Log.d("Name: ", log);
        }
    }


}