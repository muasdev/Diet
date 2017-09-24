package com.example.muasmakkode.diet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingFragment extends AppCompatActivity {

    @BindView(R.id.editText_Nama)
    EditText editTextNama;
    @BindView(R.id.editText_Umur)
    EditText editTextUmur;
    @BindView(R.id.editText_Tb)
    EditText editTextTb;
    @BindView(R.id.buttonSave)
    Button btnSave;
    @BindView(R.id.buttonShow)
    Button btnShow;

    SharedPreferences pref;
    @BindView(R.id.textView_Nama)
    TextView textViewNama;
    @BindView(R.id.textView_Umur)
    TextView textViewUmur;
    @BindView(R.id.textView_Tb)
    TextView textViewTb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_fragment);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.buttonSave, R.id.buttonShow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonSave:
                pref = getApplicationContext().getSharedPreferences("userInfo", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putString("username", editTextNama.getText().toString());
                editor.putString("userumur", editTextUmur.getText().toString());
                editor.putString("usertinggi", editTextTb.getText().toString());
                editor.commit();

                Toast.makeText(this, "berhasil disimpan", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonShow:

                pref = getApplicationContext().getSharedPreferences("userInfo", MODE_PRIVATE);

                String nama = pref.getString("username", "");
                String umur = pref.getString("userumur", "");
                String tinggibadan = pref.getString("usertinggi", "");

                textViewNama.setText("Nama" + nama);
                textViewUmur.setText("Umur" + umur);
                textViewTb.setText("Tinggi Badan" + tinggibadan);

                break;
        }
    }
}
