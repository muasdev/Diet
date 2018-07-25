package com.example.muasmakkode.diet;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.muasmakkode.diet.UI.KaloriDibakarFragment;
import com.example.muasmakkode.diet.UI.MakananDikonsumsiFragment;
import com.example.muasmakkode.diet.UI.TentangAplikasi;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView tv_name, tv_umur;

    SharedPreferences sharedPreferences;
    public static final String PREFERENCE = "dataBmr";

    private boolean isUtama = false;
    private boolean isMakanan = false;
    private boolean isOlahraga = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        if (sharedPreferences.contains("my_eaf")) {
            //jadikan home fragment halaman utama
            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout, homeFragment);
            fragmentTransaction.commit();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Anda belum melakukan Diagnosa, Diagnosa sekarang untuk melanjutkan aplikasi");
            builder.setPositiveButton("Mulai Diagnosa",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), SettingFragment.class);
                            startActivity(intent);
                            finish();
                        }
                    });
            builder.show();
        }

        /*else {
            Intent intent = new Intent(this, SettingFragment.class);
            startActivity(intent);
            finish();

        }*/






        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                MakananFragment makananFragment = new MakananFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, makananFragment);
                fragmentTransaction.commit();
            }
        });
*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //code tambahan
        //digunakan untuk set Text ke header navigation drawer dari setting dan sharedPreferences
        /*View header = navigationView.getHeaderView(0);

        tv_name = (TextView) header.findViewById(R.id.namaUser);
        tv_umur = (TextView) header.findViewById(R.id.umurUser);

        pref = getApplicationContext().getSharedPreferences("userInfo", MODE_PRIVATE);

        String nama = pref.getString("username", "");
        String umur = pref.getString("userumur", "");

        tv_name.setText(nama);
        tv_umur.setText(umur);*/

        //sampai sini codenya
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finishAffinity();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();*/

        /*//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(this, SettingFragment.class);
            startActivity(intent);
            return true;
        } else*/
        /*if (id == R.id.action_refresh) {

            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout, homeFragment);
            fragmentTransaction.commit();
            return true;
        }*/

        /*return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_utama) {

            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout, homeFragment);
            isUtama = true;
            isMakanan = false;
            isOlahraga = false;

            fragmentTransaction.commit();

        } else if (id == R.id.nav_makanan) {

            MakananDikonsumsiFragment makananDikonsumsiFragment = new MakananDikonsumsiFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout, makananDikonsumsiFragment);
            isUtama = false;
            isMakanan = true;
            isOlahraga = false;

            fragmentTransaction.commit();


        } else if (id == R.id.nav_olahraga) {
            KaloriDibakarFragment kaloriDibakarFragment = new KaloriDibakarFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout, kaloriDibakarFragment);
            fragmentTransaction.commit();
            isUtama = false;
            isMakanan = false;
            isOlahraga = true;


        } else if (id == R.id.nav_setting) {

            /*SetDataFragment setDataFragment = new SetDataFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout, setDataFragment);
            fragmentTransaction.commit();*/

            Intent intent = new Intent(this, SettingFragment.class);
            startActivity(intent);

        } else if (id == R.id.nav_send) {

            Intent intent = new Intent(this, TentangAplikasi.class);
            startActivity(intent);


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cekHalaman(){
        if (isUtama) {
            getSupportActionBar().setSubtitle(getResources().getString(R.string.monitoring));
        } else if (isMakanan){
            getSupportActionBar().setSubtitle(getResources().getString(R.string.makanan));
        } else {
            getSupportActionBar().setSubtitle(getResources().getString(R.string.olahraga));
        }
    }
}
