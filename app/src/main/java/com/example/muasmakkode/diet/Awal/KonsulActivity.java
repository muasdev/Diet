package com.example.muasmakkode.diet.Awal;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.muasmakkode.diet.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KonsulActivity extends AppCompatActivity {


    @BindView(R.id.editText_Nama)
    TextInputEditText editTextNama;
    @BindView(R.id.rb_pria)
    RadioButton rbPria;
    @BindView(R.id.rb_wanita)
    RadioButton rbWanita;
    @BindView(R.id.radio_grup_jenis_kelamin)
    RadioGroup radioGrupJenisKelamin;
    @BindView(R.id.editText_Umur)
    TextInputEditText editTextUmur;
    @BindView(R.id.editText_beratBadan)
    TextInputEditText editTextBeratBadan;
    @BindView(R.id.editText_tinggiBadan)
    TextInputEditText editTextTinggiBadan;
    @BindView(R.id.istirahat)
    RadioButton istirahat;
    @BindView(R.id.ringanSekali)
    RadioButton ringanSekali;
    @BindView(R.id.ringan)
    RadioButton ringan;
    @BindView(R.id.ringanSedang)
    RadioButton ringanSedang;
    @BindView(R.id.sedang)
    RadioButton sedang;
    @BindView(R.id.berat)
    RadioButton berat;
    @BindView(R.id.beratSekali)
    RadioButton beratSekali;
    @BindView(R.id.button_simpan)
    Button buttonSimpan;

    RadioButton radioButton;
    int BMR = 0;

    String umur, beratBadan, tinggiBadan;
    @BindView(R.id.radio_grup_aktifitas_harian)
    RadioGroup radioGrupAktifitasHarian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsul);
        ButterKnife.bind(this);


    }

    public void cekJenisKelamin() {
        final int selectedId = radioGrupJenisKelamin.getCheckedRadioButtonId();


        // find the radio button by returned id
        radioButton = (RadioButton) findViewById(selectedId);

        if (radioGrupJenisKelamin.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "pilih jenis kelamin", Toast.LENGTH_SHORT).show();
        } else {
            /*Toast.makeText(KonsulActivity.this,
                    radioButton.getText(), Toast.LENGTH_SHORT).show();*/
            if (rbPria.isChecked()) {
                beratBadan = editTextBeratBadan.getText().toString();
                umur = editTextUmur.getText().toString();
                tinggiBadan = editTextTinggiBadan.getText().toString();
                BMR = (int) (66 + 13.7 * Integer.parseInt(beratBadan) + 5 * Integer.parseInt(tinggiBadan) - 6.8 * Integer.parseInt(umur));
                /*Toast.makeText(this, "" + BMR, Toast.LENGTH_SHORT).show();*/
            } else {
                beratBadan = editTextBeratBadan.getText().toString();
                BMR = (int) (655 + 9.6 * Integer.parseInt(beratBadan) + 1.8 * Integer.parseInt(tinggiBadan) - 4.7 * Integer.parseInt(umur));
                /*Toast.makeText(this, "" + BMR, Toast.LENGTH_SHORT).show();*/
            }
        }

    }

    public void cekJenisAktifitas() {
        final int checkId = radioGrupAktifitasHarian.getCheckedRadioButtonId();


        // find the radio button by returned id
        radioButton = (RadioButton) findViewById(checkId);

        if (radioGrupAktifitasHarian.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "pilih jenis aktifitas", Toast.LENGTH_SHORT).show();
        } else {

            if (istirahat.isChecked()) {
                if (rbPria.isChecked()) {
                    int EAF = (int) 1.2 * BMR;
                    Toast.makeText(this, "" + EAF, Toast.LENGTH_SHORT).show();
                } else {
                    int EAF = (int) 1.2 * BMR;
                    Toast.makeText(this, "" + EAF, Toast.LENGTH_SHORT).show();
                }
            }
            if (ringanSekali.isChecked()) {

            }
            if (ringan.isChecked()) {

            }
            if (ringanSedang.isChecked()) {

            }
            if (sedang.isChecked()) {

            }
            if (berat.isChecked()) {

            }
            if (beratSekali.isChecked()) {

            }

            /*if (rbPria.isChecked()) {
                beratBadan = editTextBeratBadan.getText().toString();
                umur = editTextUmur.getText().toString();
                tinggiBadan = editTextTinggiBadan.getText().toString();
                int BMR = (int) (66 + 13.7 * Integer.parseInt(beratBadan) + 5 * Integer.parseInt(tinggiBadan) - 6.8 * Integer.parseInt(umur));
                Toast.makeText(this, "" + BMR, Toast.LENGTH_SHORT).show();
            } else {
                beratBadan = editTextBeratBadan.getText().toString();
                int BMR = (int) (655 + 9.6 * Integer.parseInt(beratBadan) + 1.8 * Integer.parseInt(tinggiBadan) - 4.7 * Integer.parseInt(umur));
                Toast.makeText(this, "" + BMR, Toast.LENGTH_SHORT).show();
            }*/
        }
    }


    @OnClick(R.id.button_simpan)
    public void onViewClicked() {

        cekJenisKelamin();
        cekJenisAktifitas();
    }
}


