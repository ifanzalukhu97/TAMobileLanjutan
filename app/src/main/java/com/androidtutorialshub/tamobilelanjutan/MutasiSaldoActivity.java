package com.androidtutorialshub.tamobilelanjutan;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidtutorialshub.tamobilelanjutan.model.Transaksi;
import com.androidtutorialshub.tamobilelanjutan.remote.ApiClient;
import com.androidtutorialshub.tamobilelanjutan.remote.TransaksiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Integer.parseInt;

public class MutasiSaldoActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner spinner1;
    private ImageView back;

    private TextInputEditText pinMutasi;

    public String bulanPilih;
    public String norekening;

    private Button Btncekmutasi;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Transaksi> transaksis;
    private RecyclerAdapterMutasi recyclerAdapterMutasi;
    private TransaksiService transaksiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutasi_saldo);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.secondlayout_actionbar);
        back=(ImageView)findViewById(R.id.actionbar_back);
        back.setOnClickListener(this);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        recyclerView = findViewById(R.id.recylerViewMutasi);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        pinMutasi = findViewById(R.id.pinMutasi);

        Btncekmutasi = findViewById(R.id.Btncekmutasi);
        Btncekmutasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekMutasi();
            }
        });

    }
    public void onClick(View v){
        if(v.getId()==R.id.actionbar_back){
            finish();
        }
    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        String firstItem = String.valueOf(spinner1.getSelectedItem());


        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            bulanPilih = Integer.toString(pos);

            if (firstItem.equals(String.valueOf(spinner1.getSelectedItem()))) {
                // when first item is selected
            } else {
                Toast.makeText(parent.getContext(),
                        "Kamu memilih : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }

    }

    public void cekMutasi(){

        if (parseInt(bulanPilih) < 10 && bulanPilih.length() == 1) {
            bulanPilih = "0" + bulanPilih;
        }

        String pin = pinMutasi.getText().toString();

        norekening = CarouselViewActivity.userConfig.readNorekening();

        transaksiService = ApiClient.getApiClient().create(TransaksiService.class);

        Call<List<Transaksi>> call = transaksiService.cekMutasi(norekening, bulanPilih, pin);

        call.enqueue(new Callback<List<Transaksi>>() {
            @Override
            public void onResponse(Call<List<Transaksi>> call, Response<List<Transaksi>> response) {

                transaksis = response.body();
                recyclerAdapterMutasi = new RecyclerAdapterMutasi(transaksis, MutasiSaldoActivity.this);
                recyclerView.setAdapter(recyclerAdapterMutasi);

            }

            @Override
            public void onFailure(Call<List<Transaksi>> call, Throwable t) {

            }
        });



    }

}