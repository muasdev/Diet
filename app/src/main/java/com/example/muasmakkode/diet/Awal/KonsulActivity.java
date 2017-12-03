package com.example.muasmakkode.diet.Awal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muasmakkode.diet.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KonsulActivity extends AppCompatActivity {

    @BindView(R.id.button_back)
    Button buttonBack;
    @BindView(R.id.button_next)
    Button buttonNext;
    @BindView(R.id.textView_jenis_kelamin)
    TextView textViewJenisKelamin;
    @BindView(R.id.radio_button_pria)
    RadioButton radioButtonPria;
    @BindView(R.id.radio_button_wanita)
    RadioButton radioButtonWanita;
    @BindView(R.id.radio_grup_jenis_kelamin)
    RadioGroup radioGrupJenisKelamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsul);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_back, R.id.button_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                break;
            case R.id.button_next:
                Intent intent = new Intent(this, KonsulKeduaActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void periksaJeniskelamin(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_button_pria:
                if (checked)
                    textViewJenisKelamin.setText("pria");
                break;
            case R.id.radio_button_wanita:
                if (checked)
                    textViewJenisKelamin.setText("wanita");
                break;
        }
    }
}
