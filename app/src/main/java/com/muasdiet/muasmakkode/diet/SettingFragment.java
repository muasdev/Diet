package com.muasdiet.muasmakkode.diet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.muasdiet.muasmakkode.diet.db.DataAdapter;
import com.muasdiet.muasmakkode.diet.db.DatabaseHandler;
import com.muasdiet.muasmakkode.diet.db.helper.DatabaseHandlerOlahraga;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingFragment extends AppCompatActivity {

    @BindView(R.id.rb_pria)
    RadioButton rbPria;
    @BindView(R.id.rb_wanita)
    RadioButton rbWanita;
    @BindView(R.id.radio_grup_jenis_kelamin)
    RadioGroup radioGrupJenisKelamin;
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
    double BMR = 0;
    double SDA = 0;
    double EAF = 0;

    boolean isEmptyFields;

    SharedPreferences sharedPreferences;

    public static final String PREFERENCE = "dataBmr";

    /*tambahan code untuk menyimpan nilai dari radio button ke sharedpref*/
    String textRadioButton;

    String umur, beratBadan, tinggiBadan;
    double beratBadanIdeal;
    @BindView(R.id.radio_grup_aktifitas_harian)
    RadioGroup radioGrupAktifitasHarian;
    @BindView(R.id.editText_Umur)
    EditText editTextUmur;
    @BindView(R.id.editText_beratBadan)
    EditText editTextBeratBadan;
    @BindView(R.id.editText_Nama)
    EditText editTextNama;
    @BindView(R.id.editText_tinggiBadan)
    EditText editTextTinggiBadan;
    @BindView(R.id.daftar_nama_layout)
    TextInputLayout daftarNamaLayout;
    @BindView(R.id.layout_diagnosa)
    NestedScrollView nestedScrollView;

    DataAdapter dataAdapters;
    DatabaseHandler db;
    DatabaseHandlerOlahraga databaseHandlerOlahraga;

    DecimalFormat digit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_fragment);
        sharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);

        ButterKnife.bind(this);
    }

    public void getValue() {
        textRadioButton = radioButton.getText().toString();
        /*Toast.makeText(getApplicationContext(), "ini " + textRadioButton, Toast.LENGTH_SHORT).show();*/
        beratBadan = editTextBeratBadan.getText().toString();
        umur = editTextUmur.getText().toString();
        tinggiBadan = editTextTinggiBadan.getText().toString();
        beratBadanIdeal = (Integer.parseInt(tinggiBadan) - 100) - (0.1 * (Integer.parseInt(tinggiBadan) - 100));
        Log.d("bbi", "getValue: " + beratBadanIdeal);
    }

    public void rumusSda() {
        SDA = 0.1 * BMR;
        SDA = SDA + BMR;
    }

    public void cekJenisKelamin() {
        final int selectedId = radioGrupJenisKelamin.getCheckedRadioButtonId();
        final int checkId = radioGrupAktifitasHarian.getCheckedRadioButtonId();
        radioButton = findViewById(selectedId);

        String nama = editTextNama.getText().toString().trim();
        String UMUR = editTextUmur.getText().toString().trim();
        String beratbadan = editTextBeratBadan.getText().toString().trim();
        String tinggibadan = editTextTinggiBadan.getText().toString().trim();

        isEmptyFields = false;

        if (TextUtils.isEmpty(nama)) {
            isEmptyFields = true;
            editTextNama.setError("masukkan nama anda");
        } else if (radioGrupJenisKelamin.getCheckedRadioButtonId() == -1) {
//            Toast.makeText(this, "pilih jenis kelamin", Toast.LENGTH_SHORT).show();
            Snackbar snackbar = Snackbar
                    .make(nestedScrollView, "pilih jenis kelamin", Snackbar.LENGTH_LONG);

            // Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(nestedScrollView.getResources().getColor(R.color.primaryColor));
            snackbar.show();
        } else if (TextUtils.isEmpty(UMUR)) {
            isEmptyFields = true;
            editTextUmur.setError("masukkan umur anda");
        } else if (TextUtils.isEmpty(beratbadan)) {
            isEmptyFields = true;
            editTextBeratBadan.setError("masukkan berat badan anda");
        } else if (TextUtils.isEmpty(tinggibadan)) {
            isEmptyFields = true;
            editTextTinggiBadan.setError("masukkan tinggi anda");
        } else if (radioGrupAktifitasHarian.getCheckedRadioButtonId() == -1) {
            Snackbar snackbar = Snackbar
                    .make(nestedScrollView, "pilih jenis aktivitas", Snackbar.LENGTH_LONG);

            // Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(nestedScrollView.getResources().getColor(R.color.primaryColor));
            snackbar.show();
        } else if (istirahat.isChecked()) {
            if (rbPria.isChecked()) {
                getValue();
                BMR = (66 + 13.7 * beratBadanIdeal) + 5 * Integer.parseInt(tinggiBadan) - 6.8 * Integer.parseInt(umur);
                rumusSda();
                EAF = (1.2 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            } else {
                getValue();
                BMR = (655 + 9.6 * beratBadanIdeal) + 1.8 * Integer.parseInt(tinggiBadan) - 4.7 * Integer.parseInt(umur);
                rumusSda();
                EAF = (1.2 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            }
        } else if (ringanSekali.isChecked()) {
            if (rbPria.isChecked()) {
                getValue();
                BMR = (66 + 13.7 * beratBadanIdeal) + 5 * Integer.parseInt(tinggiBadan) - 6.8 * Integer.parseInt(umur);
                rumusSda();
                EAF = (1.4 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            } else {
                getValue();
                BMR = (655 + 9.6 * beratBadanIdeal) + 1.8 * Integer.parseInt(tinggiBadan) - 4.7 * Integer.parseInt(umur);
                rumusSda();
                EAF = (1.4 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            }
        } else if (ringan.isChecked()) {
            if (rbPria.isChecked()) {
                getValue();
                BMR = (66 + 13.7 * beratBadanIdeal) + 5 * Integer.parseInt(tinggiBadan) - 6.8 * Integer.parseInt(umur);
                rumusSda();
                EAF = (1.5 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            } else {
                getValue();
                BMR = (655 + 9.6 * beratBadanIdeal) + 1.8 * Integer.parseInt(tinggiBadan) - 4.7 * Integer.parseInt(umur);
                rumusSda();
                EAF = (1.5 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            }
        } else if (ringanSedang.isChecked()) {
            if (rbPria.isChecked()) {
                getValue();
                BMR = (66 + 13.7 * beratBadanIdeal) + 5 * Integer.parseInt(tinggiBadan) - 6.8 * Integer.parseInt(umur);
                rumusSda();
                EAF = (1.7 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            } else {
                getValue();
                BMR = (655 + 9.6 * beratBadanIdeal) + 1.8 * Integer.parseInt(tinggiBadan) - 4.7 * Integer.parseInt(umur);
                rumusSda();
                EAF = (1.6 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            }
        } else if (sedang.isChecked()) {
            if (rbPria.isChecked()) {
                getValue();
                BMR = (66 + 13.7 * beratBadanIdeal) + 5 * Integer.parseInt(tinggiBadan) - 6.8 * Integer.parseInt(umur);
                rumusSda();
                EAF = (1.8 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            } else {
                getValue();
                BMR = (655 + 9.6 * beratBadanIdeal) + 1.8 * Integer.parseInt(tinggiBadan) - 4.7 * Integer.parseInt(umur);
                rumusSda();
                EAF = (1.7 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            }
        } else if (berat.isChecked()) {
            if (rbPria.isChecked()) {
                getValue();
                BMR = (66 + 13.7 * beratBadanIdeal) + 5 * Integer.parseInt(tinggiBadan) - 6.8 * Integer.parseInt(umur);
                rumusSda();
                EAF = (2.1 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            } else {
                getValue();
                BMR = (655 + 9.6 * beratBadanIdeal) + 1.8 * Integer.parseInt(tinggiBadan) - 4.7 * Integer.parseInt(umur);
                rumusSda();
                EAF = (1.8 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            }
        } else if (beratSekali.isChecked()) {
            if (rbPria.isChecked()) {
                getValue();
                BMR = (66 + 13.7 * beratBadanIdeal) + 5 * Integer.parseInt(tinggiBadan) - 6.8 * Integer.parseInt(umur);
                rumusSda();
                EAF = (2.3 * SDA);
                simpanSharedPref();
                pesanBerhasil();
            } else {
                getValue();
                BMR = (655 + 9.6 * beratBadanIdeal) + 1.8 * Integer.parseInt(tinggiBadan) - 4.7 * Integer.parseInt(umur);
                rumusSda();
                EAF = (2.0 * SDA);
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

    private void metodeDesimal() {
        //  Objek untuk library DecimalFormat
        digit = new DecimalFormat("#.##");
//        DecimalFormat df = new DecimalFormat("#.##");


    }

    public void simpanSharedPref() {
        // Store values between instances here
        sharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        double totalKebutuhanKalori = EAF;

        int angkaSignifikan = 2;
        double temp = Math.pow(10, angkaSignifikan);
        double y = (double) Math.round(totalKebutuhanKalori * temp) / temp;

        /*metodeDesimal();
//        DecimalFormat digit = new DecimalFormat("0.00");
        digit.format(totalKebutuhanKalori);*/
        Log.d("totalKebutuhanKalori", "simpanSharedPref: kalori" + y);

        editor.putString("my_eaf", String.valueOf(y));
        editor.putString("berat_badan", beratBadan);
        /*editor.putString("darah", textRadioButton);*/

        editor.commit();

        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);


    }


    @OnClick(R.id.button_simpan)
    public void onViewClicked() {

        db = new DatabaseHandler(this);
        databaseHandlerOlahraga = new DatabaseHandlerOlahraga(this);

        db.deleteAll();
        databaseHandlerOlahraga.deleteAll();


        cekJenisKelamin();
        /*cekJenisAktifitas();*/



    }

}
