package com.example.muasmakkode.diet.Awal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.muasmakkode.diet.Data.SharedPref;
import com.example.muasmakkode.diet.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KonsulActivity extends AppCompatActivity {


    @BindView(R.id.editText_Nama)
    TextInputEditText editTextNama;
    @BindView(R.id.editText_Umur)
    TextInputEditText editTextUmur;
    @BindView(R.id.rb_pria)
    RadioButton rbPria;
    @BindView(R.id.rb_wanita)
    RadioButton rbWanita;
    @BindView(R.id.radio_grup_jenis_kelamin)
    RadioGroup radioGrupJenisKelamin;
    @BindView(R.id.editText_beratBadan)
    TextInputEditText editTextBeratBadan;
    @BindView(R.id.editText_tinggiBadan)
    TextInputEditText editTextTinggiBadan;
    @BindView(R.id.button_back)
    Button buttonBack;
    @BindView(R.id.button_next)
    Button buttonNext;

    double jumlah_bmr;
    int berat_badan, tinggi_badan, umur;
    String total_bmr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsul);
        ButterKnife.bind(this);

        SharedPref.init(getApplicationContext());

    }


    @OnClick({R.id.button_back, R.id.button_next})
    public void onViewClicked(View view) {

        //cek radio button yang tercek
        switch (view.getId()) {
            case R.id.button_back:
                break;
            case R.id.button_next:
                /*berat_badan = Integer.parseInt(editTextBeratBadan.getText().toString());
                tinggi_badan = Integer.parseInt(editTextTinggiBadan.getText().toString());
                umur = Integer.parseInt(editTextUmur.getText().toString());

                RadioGroup g = (RadioGroup) findViewById(R.id.radio_grup_jenis_kelamin);

                switch (g.getCheckedRadioButtonId()) {
                    case R.id.rb_pria:

                        //rumus hitung BMR untuk pria
                        jumlah_bmr = 66 + (13.7 * berat_badan) + (5 * tinggi_badan) - (6.8 * umur);
                        break;

                    case R.id.rb_wanita:
                        //rumus hitung BMR untuk wanita
                        jumlah_bmr = 655 + (9.6 * berat_badan) + (1.8 * tinggi_badan) - (4.7 * umur);
                        break;
                }

                SharedPref.write(SharedPref.JENIS_KELAMIN, radioGrupJenisKelamin.getCheckedRadioButtonId());

                total_bmr = String.valueOf(Math.round(jumlah_bmr));*/
                Intent intent = new Intent(this, KonsulKetigaActivity.class);
                //kode kirim data ke activity selanjutnya
//                intent.putExtra("jumlah_bmr", total_bmr);
                startActivity(intent);
        }
    }



                /*SharedPref.write(SharedPref.NAMA, editTextNama.getText().toString());
                SharedPref.write(SharedPref.UMUR, editTextUmur.getText().toString());
                SharedPref.write(SharedPref.JENIS_KELAMIN, radioGrupJenisKelamin.getCheckedRadioButtonId());
                SharedPref.write(SharedPref.BERAT_BADAN, editTextBeratBadan.getText().toString());
                SharedPref.write(SharedPref.TINGGI_BADAN, editTextTinggiBadan.getText().toString());*/

    //kode untuk ngecek apa isian sudah lengkap
                /*if (editTextNama.getText().toString().isEmpty() || editTextUmur.getText().toString().isEmpty() ||
                        radioGrupJenisKelamin.getCheckedRadioButtonId() == -1 ||
                        editTextBeratBadan.getText().toString().isEmpty() ||
                        editTextTinggiBadan.getText().toString().isEmpty()
                        ) {

                    Toast.makeText(this, "harap lengkapi isian yang ada di atas", Toast.LENGTH_SHORT).show();
                } else {*/


}

