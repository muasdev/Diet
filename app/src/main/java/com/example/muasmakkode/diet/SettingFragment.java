package com.example.muasmakkode.diet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingFragment extends AppCompatActivity {


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
    double SDA = 0;
    int EAF = 0;

    SharedPreferences sharedPreferences;

    String umur, beratBadan, tinggiBadan;
    @BindView(R.id.radio_grup_aktifitas_harian)
    RadioGroup radioGrupAktifitasHarian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_fragment);
        ButterKnife.bind(this);
    }

    public void getValue() {
        beratBadan = editTextBeratBadan.getText().toString();
        umur = editTextUmur.getText().toString();
        tinggiBadan = editTextTinggiBadan.getText().toString();
    }

    public void rumusSda() {
        SDA = 0.1 * BMR;
        SDA = SDA + BMR;
    }

    public void cekJenisKelamin() {
        final int selectedId = radioGrupJenisKelamin.getCheckedRadioButtonId();
        final int checkId = radioGrupAktifitasHarian.getCheckedRadioButtonId();


        // find the radio button by returned id
        radioButton = (RadioButton) findViewById(selectedId);
        // find the radio button by returned id
        radioButton = (RadioButton) findViewById(checkId);

        if (radioGrupJenisKelamin.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "pilih jenis kelamin", Toast.LENGTH_SHORT).show();
        } else if (radioGrupAktifitasHarian.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "pilih jenis aktifitas", Toast.LENGTH_SHORT).show();
        } else if (istirahat.isChecked()) {
            if (rbPria.isChecked()) {
                getValue();
                BMR = (int) (66 + 13.7 * Integer.parseInt(beratBadan) + 5 * Integer.parseInt(tinggiBadan) - 6.8 * Integer.parseInt(umur));
                rumusSda();
                EAF = (int) (1.2 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            } else {
                getValue();
                BMR = (int) (655 + 9.6 * Integer.parseInt(beratBadan) + 1.8 * Integer.parseInt(tinggiBadan) - 4.7 * Integer.parseInt(umur));
                rumusSda();
                EAF = (int) (1.2 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            }
        } else if (ringanSekali.isChecked()) {
            if (rbPria.isChecked()) {
                getValue();
                BMR = (int) (66 + 13.7 * Integer.parseInt(beratBadan) + 5 * Integer.parseInt(tinggiBadan) - 6.8 * Integer.parseInt(umur));
                rumusSda();
                EAF = (int) (1.4 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            } else {
                getValue();
                BMR = (int) (655 + 9.6 * Integer.parseInt(beratBadan) + 1.8 * Integer.parseInt(tinggiBadan) - 4.7 * Integer.parseInt(umur));
                rumusSda();
                EAF = (int) (1.4 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            }
        } else if (ringan.isChecked()) {
            if (rbPria.isChecked()) {
                getValue();
                BMR = (int) (66 + 13.7 * Integer.parseInt(beratBadan) + 5 * Integer.parseInt(tinggiBadan) - 6.8 * Integer.parseInt(umur));
                rumusSda();
                EAF = (int) (1.5 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            } else {
                getValue();
                BMR = (int) (655 + 9.6 * Integer.parseInt(beratBadan) + 1.8 * Integer.parseInt(tinggiBadan) - 4.7 * Integer.parseInt(umur));
                rumusSda();
                EAF = (int) (1.5 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            }
        } else if (ringanSedang.isChecked()) {
            if (rbPria.isChecked()) {
                getValue();
                BMR = (int) (66 + 13.7 * Integer.parseInt(beratBadan) + 5 * Integer.parseInt(tinggiBadan) - 6.8 * Integer.parseInt(umur));
                rumusSda();
                EAF = (int) (1.7 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            } else {
                getValue();
                BMR = (int) (655 + 9.6 * Integer.parseInt(beratBadan) + 1.8 * Integer.parseInt(tinggiBadan) - 4.7 * Integer.parseInt(umur));
                rumusSda();
                EAF = (int) (1.6 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            }
        } else if (sedang.isChecked()) {
            if (rbPria.isChecked()) {
                getValue();
                BMR = (int) (66 + 13.7 * Integer.parseInt(beratBadan) + 5 * Integer.parseInt(tinggiBadan) - 6.8 * Integer.parseInt(umur));
                rumusSda();
                EAF = (int) (1.8 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            } else {
                getValue();
                BMR = (int) (655 + 9.6 * Integer.parseInt(beratBadan) + 1.8 * Integer.parseInt(tinggiBadan) - 4.7 * Integer.parseInt(umur));
                rumusSda();
                EAF = (int) (1.7 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            }
        } else if (berat.isChecked()) {
            if (rbPria.isChecked()) {
                getValue();
                BMR = (int) (66 + 13.7 * Integer.parseInt(beratBadan) + 5 * Integer.parseInt(tinggiBadan) - 6.8 * Integer.parseInt(umur));
                rumusSda();
                EAF = (int) (2.1 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            } else {
                getValue();
                BMR = (int) (655 + 9.6 * Integer.parseInt(beratBadan) + 1.8 * Integer.parseInt(tinggiBadan) - 4.7 * Integer.parseInt(umur));
                rumusSda();
                EAF = (int) (1.8 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            }
        } else if (beratSekali.isChecked()) {
            if (rbPria.isChecked()) {
                getValue();
                BMR = (int) (66 + 13.7 * Integer.parseInt(beratBadan) + 5 * Integer.parseInt(tinggiBadan) - 6.8 * Integer.parseInt(umur));
                rumusSda();
                EAF = (int) (2.3 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            } else {
                getValue();
                BMR = (int) (655 + 9.6 * Integer.parseInt(beratBadan) + 1.8 * Integer.parseInt(tinggiBadan) - 4.7 * Integer.parseInt(umur));
                rumusSda();
                EAF = (int) (2.0 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            }
        }
    }

    public void pesanBerhasil() {
        Toast.makeText(this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void simpanSharedPref() {
        // Store values between instances here
        sharedPreferences = getSharedPreferences("dataBmr", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int totalKebutuhanKalori = EAF;

        editor.putString("my_eaf", String.valueOf(totalKebutuhanKalori));

        editor.commit();
    }

    @OnClick(R.id.button_simpan)
    public void onViewClicked() {

        cekJenisKelamin();
        /*cekJenisAktifitas();*/
    }

}
