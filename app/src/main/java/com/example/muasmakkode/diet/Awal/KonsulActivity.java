package com.example.muasmakkode.diet.Awal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.muasmakkode.diet.Data.SharedPref;
import com.example.muasmakkode.diet.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KonsulActivity extends AppCompatActivity {

    @BindView(R.id.button_back)
    Button buttonBack;
    @BindView(R.id.button_next)
    Button buttonNext;
    @BindView(R.id.radio_grup_jenis_kelamin)
    RadioGroup radioGrupJenisKelamin;
    @BindView(R.id.editText_Nama)
    TextInputEditText editTextNama;
    @BindView(R.id.editText_beratBadan)
    TextInputEditText editTextBeratBadan;
    @BindView(R.id.editText_tinggiBadan)
    TextInputEditText editTextTinggiBadan;
    @BindView(R.id.editText_Umur)
    TextInputEditText editTextUmur;

    RadioButton radioButton;

    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsul);
        ButterKnife.bind(this);

        SharedPref.init(getApplicationContext());
    }

    public void periksaJeniskelamin(View view) {

        int selectedId = radioGrupJenisKelamin.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        Toast.makeText(this, radioButton.getText(), Toast.LENGTH_SHORT).show();


        /*boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_button_pria:
                if (checked)
                    SharedPref.write(SharedPref.JENIS_KELAMIN, radioButtonPria.isChecked());
                break;
            case R.id.radio_button_wanita:
                if (checked)
                    SharedPref.write(SharedPref.JENIS_KELAMIN, radioButtonWanita.isChecked());
                break;
        }*/
    }

    @OnClick({R.id.button_back, R.id.button_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_back:

                break;
            case R.id.button_next:

                SharedPref.write(SharedPref.NAMA, editTextNama.getText().toString());
                SharedPref.write(SharedPref.UMUR, editTextUmur.getText().toString());
                SharedPref.write(SharedPref.JENIS_KELAMIN, radioGrupJenisKelamin.getCheckedRadioButtonId());
                SharedPref.write(SharedPref.BERAT_BADAN, editTextBeratBadan.getText().toString());
                SharedPref.write(SharedPref.TINGGI_BADAN, editTextTinggiBadan.getText().toString());

                //kode untuk ngecek apa isian sudah lengkap
                if (editTextNama.getText().toString().isEmpty() || editTextUmur.getText().toString().isEmpty() ||
                        radioGrupJenisKelamin.getCheckedRadioButtonId() == -1 ||
                        editTextBeratBadan.getText().toString().isEmpty() ||
                        editTextTinggiBadan.getText().toString().isEmpty()
                        ) {

                    Toast.makeText(this, "harap lengkapi isian yang ada di atas", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(this, "lanjut isi yang lain", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, KonsulKeduaActivity.class);
                    startActivity(intent);
                    break;
                }
        }

    }

}
