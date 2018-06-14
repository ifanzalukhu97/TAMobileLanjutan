package com.androidtutorialshub.tamobilelanjutan;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class ContainerActivity extends AppCompatActivity {
    public static String POSISI = "posisi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.layout_actionbar);
        //String posisi = getIntent().getStringExtra(PAKET);
        //Integer posisi=Integer.parseInt(position);
        int posisi = getIntent().getIntExtra(POSISI, 0);
        final TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.colorWhite)));
        tabLayout.setTabMode(TabLayout.LAYOUT_MODE_CLIP_BOUNDS);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorWhite));



        boolean home=false, info=false, transfer=false, payment=false, commerce=false, admin=false;
        String hometeks=null, infoteks=null, transferteks=null, paymentteks=null, commerceteks=null, adminteks=null;
        if(posisi==0){
            home=true;
            hometeks="Home";
        }
        else if(posisi==1){
            info=true;
            infoteks="Info";
            changeFragment(new InfoFragment());
        }
        else if(posisi==2){
            transfer=true;
            transferteks="Transfer";
            changeFragment(new TransferFragment());
        }
        else if(posisi==3){
            payment=true;
            paymentteks="Payment";
            changeFragment(new PaymentFragment());
        }
        else if(posisi==4){
            commerce=true;
            commerceteks="Commerce";
            changeFragment(new CommerceFragment());
        }
        else if(posisi==5){
            admin=true;
            adminteks="Admin";
            changeFragment(new AdminFragment());
        }
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_home).setText(hometeks),home);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_info).setText(infoteks),info);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_transfer).setText(transferteks),transfer);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_payment).setText(paymentteks),payment);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_commerce).setText(commerceteks),commerce);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_admin).setText(adminteks),admin);
        /*
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_home).setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_info));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_transfer));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_payment));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_commerce));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_admin));
        */
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tabLayout.getSelectedTabPosition() == 0) {
                    //tab.setText(R.string.menu_home);
                    startActivity(new Intent(ContainerActivity.this, HomeActivity.class));
                } else if(tabLayout.getSelectedTabPosition() == 1) {
                    tab.setText((R.string.menu_info));
                    tab.setIcon(R.drawable.ic_info_active);
                    changeFragment(new InfoFragment());
                } else if(tabLayout.getSelectedTabPosition() == 2) {
                    tab.setText((R.string.menu_transfer));
                    changeFragment(new TransferFragment());
                } else if(tabLayout.getSelectedTabPosition() == 3) {
                    tab.setText((R.string.menu_payment));
                    tab.setIcon(R.drawable.ic_payment_active);
                    changeFragment(new PaymentFragment());
                } else if(tabLayout.getSelectedTabPosition() == 4) {
                    tab.setText((R.string.menu_commerce));
                    changeFragment(new CommerceFragment());
                } else if(tabLayout.getSelectedTabPosition() == 5) {
                    tab.setText((R.string.menu_admin));
                    tab.setIcon(R.drawable.ic_admin_active);
                    changeFragment(new AdminFragment());
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

    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, fragment).commit();
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

