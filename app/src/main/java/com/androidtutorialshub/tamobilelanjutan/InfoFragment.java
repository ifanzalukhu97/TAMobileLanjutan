package com.androidtutorialshub.tamobilelanjutan;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidtutorialshub.tamobilelanjutan.model.User;
import com.androidtutorialshub.tamobilelanjutan.remote.ApiClient;
import com.androidtutorialshub.tamobilelanjutan.remote.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    String[] infoSubmenuList = new String[]{"Info Saldo", "Mutasi Rekening"};
    ListView lvInfo;
    TextInputLayout textInputLayoutPIN;
    TextInputEditText textInputEditTextPIN;
    Button ok;
    TextView pesansaldo;

    public String pesanStatus;
    public int saldo;

    public static UserService userService;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View oncreateView = inflater.inflate(R.layout.fragment_info, container, false);
        infoCustomView pcv = new infoCustomView(getContext(), infoSubmenuList);

        userService = ApiClient.getApiClient().create(UserService.class);


        lvInfo = oncreateView.findViewById(R.id.infolistview);
        lvInfo.setAdapter(pcv);
        lvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //Toast.makeText(InfoActivity.this, "Memilih : "+infoitem[position], Toast.LENGTH_LONG).show();
                if (position == 0) {
                    final Dialog dialog = new Dialog(view.getContext());
                    dialog.setContentView(R.layout.login_pin);
                    //dialog.setTitle("Pilih Aksi");

                    dialog.show();

                    textInputLayoutPIN = (TextInputLayout) dialog.findViewById(R.id.textInputLayoutPIN);
                    textInputEditTextPIN = (TextInputEditText) dialog.findViewById(R.id.textInputEditTextPIN);
                    //final Integer nama=username.getText().toString().length();


                    final String noRekening = HomeActivity.userConfig.readNorekening();


                    textInputEditTextPIN.setOnKeyListener(new View.OnKeyListener() {
                        public boolean onKey(View v, int keyCode, KeyEvent keyevent) {
                            //If the keyevent is a key-down event on the "enter" button


                            if (textInputEditTextPIN.getText().toString().trim().length() == 6) {

                                String inputPIN = textInputEditTextPIN.getText().toString();

                                Call<User> call = userService.cekSaldo(inputPIN, noRekening);
                                call.enqueue(new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {

                                        pesanStatus = response.body().getMessage();
                                        saldo = response.body().getSaldo();

                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {

                                    }
                                });

                                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                                    if (pesanStatus.equals("ok")) {

                                        dialog.dismiss();
                                        if (position == 0) {
                                            final Dialog pesan = new Dialog(v.getContext());
                                            pesan.setContentView(R.layout.cek_saldo);
                                            pesan.show();
                                            pesansaldo = (TextView) pesan.findViewById(R.id.pesansaldo);
                                            pesansaldo.setText("Saldo: " + saldo);

                                            ok = (Button) pesan.findViewById(R.id.ok);
                                            ok.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v1) {
                                                    if (v1.getId() == R.id.ok) {
                                                        pesan.dismiss();
                                                    }
                                                }
                                            });
                                        }
                                        //finish();
                                        return true;
                                    } else if (pesanStatus.equals("failed")) {
                                        Toast.makeText(getContext(), "Pin salah", Toast.LENGTH_SHORT).show();
                                    }

                                    textInputEditTextPIN.setText("");
                                }

                            }
                            return false;
                        }

                    });

                    //memanggil set on Item ClickListener untuk Listview, jadi jika salah satu item list view diklik akan
                    //akan bereaksi menampilkan toast atau aksi lainya.
                    //Step 4
                } else if (position == 1) {
                    startActivity(new Intent(getContext(), MutasiSaldoActivity.class));
                }
            }
        });

        return oncreateView;

    }

    public class infoCustomView extends ArrayAdapter {

        private final Context context;
        private final String[] values;

        public infoCustomView(Context context, String[] values) {
            super(context, R.layout.item_listview, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.item_listview, parent, false);
            TextView submenuPayment = (TextView) rowView.findViewById(R.id.tv_layout);

            String item_value = values[position];
            submenuPayment.setText(item_value);

            return rowView;
        }
    }
}
