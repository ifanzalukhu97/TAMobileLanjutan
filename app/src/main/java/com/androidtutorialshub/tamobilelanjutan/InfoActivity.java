package com.androidtutorialshub.tamobilelanjutan;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.androidtutorialshub.tamobilelanjutan.konfig.UserConfig;

public class InfoActivity extends AppCompatActivity{

    private EditText pin;
    private ListView lvItem;
    private String[] infoitem = new String[]{
            "Info Saldo","Mutasi Rekening" };

    public static UserConfig userConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        lvItem = (ListView) findViewById(R.id.infolistview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(InfoActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, infoitem);
        lvItem.setAdapter(adapter);
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(InfoActivity.this, "Memilih : "+infoitem[position], Toast.LENGTH_LONG).show();
                final Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.login_pin);
                //dialog.setTitle("Pilih Aksi");
                dialog.show();

                pin = (EditText) dialog.findViewById(R.id.pin);

                //final Integer nama=username.getText().toString().length();
                pin.setOnKeyListener(new View.OnKeyListener() {
                    public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                        //If the keyevent is a key-down event on the "enter" button
                        if(pin.getText().toString().trim().length()==6) {
                            if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                dialog.dismiss();
                                //startActivity(new Intent(InfoActivity.this, HomeActivity.class));
                                //finish();
                                return true;
                            }
                        }
                        return false;
                    }

                });
                //memanggil set on Item ClickListener untuk Listview, jadi jika salah satu item list view diklik akan
                //akan bereaksi menampilkan toast atau aksi lainya.
                //Step 4
            }
        });


        final TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.colorWhite)));
        tabLayout.setTabMode(TabLayout.LAYOUT_MODE_CLIP_BOUNDS);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorWhite));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_home));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_info).setText("Info"),true);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_transfer));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_payment));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_commerce));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_admin));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {


            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tabLayout.getSelectedTabPosition() == 0) {
                    //tab.setText(R.string.menu_home);
                    //tab.setIcon(R.drawable.ic_home_active);
                    startActivity(new Intent(InfoActivity.this, HomeActivity.class));
                } else if(tabLayout.getSelectedTabPosition() == 2) {
                    tab.setText((R.string.menu_transfer));
                    tab.setIcon(R.drawable.ic_transfer_active);
                } else if(tabLayout.getSelectedTabPosition() == 3) {
                    tab.setText((R.string.menu_payment));
                    tab.setIcon(R.drawable.ic_payment_active);
                } else if(tabLayout.getSelectedTabPosition() == 4) {
                    tab.setText((R.string.menu_commerce));
                    tab.setIcon(R.drawable.ic_commerce_active);
                } else if(tabLayout.getSelectedTabPosition() == 5) {
                    //tab.setText((R.string.menu_admin));
                    //tab.setIcon(R.drawable.ic_admin_active);
                    startActivity(new Intent(InfoActivity.this, AdminActivity.class));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setText("");
                default_icon(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void default_icon(TabLayout.Tab tab){
        if(tab.getPosition()==0){
            tab.setIcon(R.drawable.ic_home);
        }
        else if(tab.getPosition()==1){
            tab.setIcon(R.drawable.ic_info);
        }
        else if(tab.getPosition()==2) {
            tab.setIcon(R.drawable.ic_transfer);
        }
        else if(tab.getPosition()==3) {
            tab.setIcon(R.drawable.ic_payment);
        }
        else if(tab.getPosition()==4) {
            tab.setIcon(R.drawable.ic_commerce);
        }
        else{
            tab.setIcon(R.drawable.ic_admin);
        }
    }


}
