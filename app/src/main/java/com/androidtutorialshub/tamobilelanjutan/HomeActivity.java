package com.androidtutorialshub.tamobilelanjutan;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.androidtutorialshub.tamobilelanjutan.konfig.UserConfig;

public class HomeActivity extends AppCompatActivity {

    public static UserConfig userConfig;

    private Button yes, cancel;
    private static final String[] nm_bn = { "M-Info", "M-Transfer", "M-Payment",
            "M-Commerce", "Tarik Tunai", "M-Admin" };

    private int[] gbr_bn = { R.drawable.ic_info_active, R.drawable.ic_transfer_active,
            R.drawable.ic_payment_active, R.drawable.ic_commerce_active,R.drawable.ic_monetization_on_24dp,
            R.drawable.ic_admin_active};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.layout_actionbar);
        GridView grid = (GridView) findViewById(R.id.gridView);

        userConfig = new UserConfig(this);

        grid.setAdapter(new Adapter());
    }

    public class Adapter extends ArrayAdapter<String> {

        public Adapter() {
            super(HomeActivity.this, R.layout.item_grid, nm_bn);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            // instansiasi row dari convertView
            TextView txt = (TextView) findViewById(R.id.text_view);

            txt.setText("Selamat Datang " + userConfig.readNama());


            View row = convertView;

            if (row == null) {
                // inflate layout
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.item_grid, parent, false);
            }
            final ImageButton imagebutton = (ImageButton) row.findViewById(R.id.imagebutton);
            TextView nama = (TextView) row.findViewById(R.id.textView1);
            // set skala gambar
            imagebutton.setScaleType(ScaleType.FIT_CENTER);

            imagebutton.setBackgroundResource(R.drawable.rounded);

            // set keterangan dan gambar berdasarkan position
            nama.setText(nm_bn[position]);
            imagebutton.setImageResource(gbr_bn[position]);

            imagebutton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent _intent = new Intent(HomeActivity.this, ContainerActivity.class);
                    if(position==0){
                        _intent.putExtra(ContainerActivity.POSISI, 1);
                    }
                    else if(position==1){
                        _intent.putExtra(ContainerActivity.POSISI, 2);
                    }
                    else if(position==2){
                        _intent.putExtra(ContainerActivity.POSISI, 3);
                    }
                    else if(position==3){
                        _intent.putExtra(ContainerActivity.POSISI, 4);
                    }
                    else if(position==5){
                        _intent.putExtra(ContainerActivity.POSISI, 5);
                    }
                    else{
                        _intent.putExtra(ContainerActivity.POSISI, 0);
                    }

                    startActivity(_intent);
                }
            });
            // kembalikan objek view
            return row;
        }
    }
    public void onBackPressed() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_logout);
        //dialog.setTitle("Pilih Aksi");
        dialog.show();

        yes = (Button) dialog.findViewById(R.id.yes);
        cancel = (Button) dialog.findViewById(R.id.cancel);
        dialog.setCancelable(false);
        //final Integer nama=username.getText().toString().length();
        yes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,CarouselViewActivity.class));
            }

        });
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }

        });
    }
}
