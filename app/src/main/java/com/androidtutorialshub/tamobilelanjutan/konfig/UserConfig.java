package com.androidtutorialshub.tamobilelanjutan.konfig;

import android.content.Context;
import android.content.SharedPreferences;

import com.androidtutorialshub.tamobilelanjutan.R;

public class UserConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public UserConfig(Context context) {

        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.user_config), Context.MODE_PRIVATE);

    }

    public void writeNorekening(String norekening) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.user_norekening), norekening);
        editor.commit();

    }

    public String readNorekening(){
        return sharedPreferences.getString(context.getString(R.string.user_norekening), "norekening");
    }

    public void writeNama(String nama) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.user_nama), nama);
        editor.commit();
    }

    public String readNama(){
        return sharedPreferences.getString(context.getString(R.string.user_nama), "nama");
    }

}
