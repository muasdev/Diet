package com.muasdiet.muasmakkode.diet.RadioButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.muasdiet.muasmakkode.diet.MainActivity;
import com.muasdiet.muasmakkode.diet.R;
import com.muasdiet.muasmakkode.diet.db.helper.DatabaseHandlerOlahraga;
import com.muasdiet.muasmakkode.diet.db.model.ModelOlahraga;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadioButtonActivity extends AppCompatActivity {

    @BindView(R.id.balapSepeda1)
    RadioButton balapSepeda1;
    @BindView(R.id.balapSepeda2)
    RadioButton balapSepeda2;
    @BindView(R.id.balapSepeda3)
    RadioButton balapSepeda3;
    @BindView(R.id.balapSepeda4)
    RadioButton balapSepeda4;
    @BindView(R.id.bulutangkis)
    RadioButton bulutangkis;
    @BindView(R.id.bolaBasket)
    RadioButton bolaBasket;
    @BindView(R.id.bolaVoli)
    RadioButton bolaVoli;
    @BindView(R.id.beladiri)
    RadioButton beladiri;
    @BindView(R.id.dayung)
    RadioButton dayung;
    @BindView(R.id.golf)
    RadioButton golf;
    @BindView(R.id.hoki)
    RadioButton hoki;
    @BindView(R.id.judo)
    RadioButton judo;
    @BindView(R.id.jalanKaki1)
    RadioButton jalanKaki1;
    @BindView(R.id.jalanKaki2)
    RadioButton jalanKaki2;
    @BindView(R.id.jalanKaki3)
    RadioButton jalanKaki3;
    @BindView(R.id.lari1)
    RadioButton lari1;
    @BindView(R.id.lari2)
    RadioButton lari2;
    @BindView(R.id.lari3)
    RadioButton lari3;
    @BindView(R.id.lari4)
    RadioButton lari4;
    @BindView(R.id.latihanbeban)
    RadioButton latihanbeban;
    @BindView(R.id.panahan)
    RadioButton panahan;
    @BindView(R.id.renangGbebas)
    RadioButton renangGbebas;
    @BindView(R.id.renangGpunggung)
    RadioButton renangGpunggung;
    @BindView(R.id.renangGdada)
    RadioButton renangGdada;
    @BindView(R.id.senam)
    RadioButton senam;
    @BindView(R.id.senamAeroPemula)
    RadioButton senamAeroPemula;
    @BindView(R.id.senamAeroTerampil)
    RadioButton senamAeroTerampil;
    @BindView(R.id.sepakBola)
    RadioButton sepakBola;
    @BindView(R.id.tenisLapRekreasi)
    RadioButton tenisLapRekreasi;
    @BindView(R.id.tenisLapBertanding)
    RadioButton tenisLapBertanding;
    @BindView(R.id.tenisMeja)
    RadioButton tenisMeja;
    @BindView(R.id.tinjuLatihan)
    RadioButton tinjuLatihan;
    @BindView(R.id.tinjuBertanding)
    RadioButton tinjuBertanding;
    @BindView(R.id.radio_grup_olahraga)
    RadioGroup radioGrupOlahraga;

    int jumlah_kebutuhan_energi_perminggu;
    int jumlah_kebutuhan_energi_perhari;
    String energi_perminggu;
    String energi_perhari;

    int beratBadan;
    int nilaiKalori = 0;
    int jumlah_latihan_seminggu;
    int lama_latihan;
    @BindView(R.id.button_simpan_olahraga)
    Button buttonSimpanOlahraga;
    @BindView(R.id.button_reset_olahraga)
    Button buttonResetOlahraga;

    private String namaOlahraga, kaloriOlahraga;

    private DatabaseHandlerOlahraga db;

    SharedPreferences sharedPreferences;
    @BindView(R.id.editText_lama_latihan)
    TextInputEditText editTextLamaLatihan;
    RadioButton radioButton;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);
        ButterKnife.bind(this);

        /*/Instantiate database handler*/
        db = new DatabaseHandlerOlahraga(getApplicationContext());
        sharedPreferences = getApplicationContext().getSharedPreferences("dataBmr", Context.MODE_PRIVATE);
        beratBadan = Integer.parseInt(sharedPreferences.getString("berat_badan", ""));

        buttonSimpanOlahraga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodeHitungData();
                checkRadio();
                addData();
            }
        });

        buttonResetOlahraga.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                editTextLamaLatihan.setText("");
            }
        });


    }

    private void metodeHitungData() {
        if (balapSepeda1.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 3;
            } else if (beratBadan >= 60 || beratBadan < 80) {
                nilaiKalori = 4;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 5;
            } else {
                nilaiKalori = 6;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (balapSepeda2.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 5;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 6;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 7;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 8;
            } else {
                nilaiKalori = 9;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (balapSepeda3.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 8;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 10;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 12;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 13;
            } else {
                nilaiKalori = 15;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (bulutangkis.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 5;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 6;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 7;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 7;
            } else {
                nilaiKalori = 9;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (bolaBasket.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 7;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 8;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 10;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 11;
            } else {
                nilaiKalori = 12;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (bolaVoli.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 2;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 3;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 4;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 4;
            } else {
                nilaiKalori = 5;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (beladiri.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 10;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 12;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 14;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 15;
            } else {
                nilaiKalori = 17;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (dayung.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 5;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 6;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 7;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 8;
            } else {
                nilaiKalori = 9;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (golf.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 4;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 5;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 6;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 7;
            } else {
                nilaiKalori = 8;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (hoki.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 4;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 5;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 6;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 7;
            } else {
                nilaiKalori = 8;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (judo.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 10;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 12;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 14;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 15;
            } else {
                nilaiKalori = 17;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (jalanKaki1.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 5;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 6;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 7;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 8;
            } else {
                nilaiKalori = 9;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (jalanKaki2.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 6;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 7;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 8;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 10;
            } else {
                nilaiKalori = 11;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (jalanKaki3.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 10;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 12;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 15;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 17;
            } else {
                nilaiKalori = 19;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (lari1.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 10;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 12;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 14;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 15;
            } else {
                nilaiKalori = 17;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (lari2.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 10;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 12;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 15;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 17;
            } else {
                nilaiKalori = 19;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (lari3.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 11;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 13;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 15;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 18;
            } else {
                nilaiKalori = 20;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (lari4.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 13;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 15;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 18;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 21;
            } else {
                nilaiKalori = 23;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (latihanbeban.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 7;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 8;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 10;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 11;
            } else {
                nilaiKalori = 12;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (panahan.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 3;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 4;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 4;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 5;
            } else {
                nilaiKalori = 6;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (renangGbebas.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 8;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 10;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 11;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 12;
            } else {
                nilaiKalori = 14;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (renangGpunggung.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 9;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 10;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 12;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 13;
            } else {
                nilaiKalori = 15;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (renangGdada.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 8;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 10;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 11;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 13;
            } else {
                nilaiKalori = 15;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();
        } else if (senam.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 3;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 4;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 5;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 5;
            } else {
                nilaiKalori = 6;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (senamAeroPemula.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 5;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 6;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 7;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 8;
            } else {
                nilaiKalori = 9;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (senamAeroTerampil.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 7;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 8;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 9;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 18;
            } else {
                nilaiKalori = 12;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (sepakBola.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 7;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 8;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 10;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 11;
            } else {
                nilaiKalori = 12;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (tenisLapRekreasi.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 4;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 4;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 5;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 5;
            } else {
                nilaiKalori = 6;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (tenisLapBertanding.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 9;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 10;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 12;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 14;
            } else {
                nilaiKalori = 15;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (tenisMeja.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 3;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 4;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 5;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 5;
            } else {
                nilaiKalori = 6;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (tinjuLatihan.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 11;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 13;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 15;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 18;
            } else {
                nilaiKalori = 20;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();

        } else if (tinjuBertanding.isChecked()) {
            if (beratBadan < 60) {
                nilaiKalori = 7;
            } else if (beratBadan >= 60 || beratBadan < 70) {
                nilaiKalori = 8;
            } else if (beratBadan >= 70 || beratBadan < 80) {
                nilaiKalori = 10;
            } else if (beratBadan >= 80 || beratBadan < 90) {
                nilaiKalori = 11;
            } else {
                nilaiKalori = 12;
            }
            metodeUntukHitungEnergiSemniggu();
            metodeUntukHitungEnergiPerHari();
        }
    }

    // function to get values from the Edittext and image
    private void getValues() {
        namaOlahraga = namaOlahraga;
        kaloriOlahraga = energi_perhari;

    }

    //Insert data to the database
    private void addData() {
        getValues();

        db.addDataOlahraga(new ModelOlahraga(namaOlahraga, kaloriOlahraga));
        Toast.makeText(this, "Berhasil menambahkan " + namaOlahraga + " ke dalam daftar", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void metodeUntukHitungEnergiSemniggu() {
        //hitung energi perminggu
        jumlah_latihan_seminggu = 1;
        lama_latihan = Integer.parseInt(editTextLamaLatihan.getText().toString());
        jumlah_kebutuhan_energi_perminggu = jumlah_latihan_seminggu * lama_latihan * nilaiKalori;
    }

    private void metodeUntukHitungEnergiPerHari() {
        //hitung energi perhari
        jumlah_kebutuhan_energi_perhari = jumlah_kebutuhan_energi_perminggu / 7;
        energi_perhari = String.valueOf(jumlah_kebutuhan_energi_perhari);
    }

    private void checkRadio() {
        // get selected radio button from radioGroup
        int selectedId = radioGrupOlahraga.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioButton = findViewById(selectedId);
        namaOlahraga = String.valueOf(radioButton.getText());
    }
}
