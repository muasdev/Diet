package com.example.muasmakkode.diet.InfoDev;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.muasmakkode.diet.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoDevActivity extends AppCompatActivity {

   /* @BindView(R.id.nomor_hp)
    TextView nomorHp;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_dev);
        ButterKnife.bind(this);

    }

    /*public void telponNomor(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + nomorHp.getText().toString()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }*/

    //kode untuk pencarian di browser
//    public void tombolSearch(View view) {
//        Intent intent = new Intent(Intent.ACTION_SEARCH);
//        intent.putExtra(SearchManager.QUERY, nomorHp.getText().toString());
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }

    //kode untuk buka file storage
//    static final int REQUEST_IMAGE_GET = 1;
//
//    public void selectImage(View view) {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("application/*");
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(intent, REQUEST_IMAGE_GET);
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
//            Uri fullPhotoUri = data.getData();
//            // Do work with photo saved at fullPhotoUri
//        }
//    }
}
