package com.muasdiet.muasmakkode.diet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Coba extends AppCompatActivity {

    @BindView(R.id.editText_a)
    EditText editTextA;

    Integer ans = 0;
    @BindView(R.id.textView4)
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba);
        ButterKnife.bind(this);


        editTextA.addTextChangedListener(new TextWatcher() {

            String tv_1;
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                tv_1 = s.toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = Integer.parseInt(s.toString());
                ans += number;
                textView4.setText("sum is " + ans + 3);
            }
        });


    }
}
