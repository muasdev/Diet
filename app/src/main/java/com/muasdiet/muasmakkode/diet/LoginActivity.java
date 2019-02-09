package com.muasdiet.muasmakkode.diet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.button_daftar)
    Button buttonDaftar;
    @BindView(R.id.button_masuk)
    Button buttonMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_daftar, R.id.button_masuk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_daftar:
                Intent daftar = new Intent(this, KonsulAwalActivity.class);
                startActivity(daftar);
                break;
            case R.id.button_masuk:
                Intent masuk = new Intent(this, KonsulAwalActivity.class);
                startActivity(masuk);
                break;
        }
    }
}
