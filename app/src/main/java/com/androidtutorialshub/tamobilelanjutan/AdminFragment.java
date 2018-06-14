package com.androidtutorialshub.tamobilelanjutan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminFragment extends Fragment {

    String[] adminSubmenuList = new String[] {"Ganti PIN", "Blokir ATM"};
    ListView lvAdmin;
    EditText pin;

    public AdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View oncreateView  = inflater.inflate(R.layout.fragment_admin, container, false);
        adminCustomView pcv = new adminCustomView(getContext(), adminSubmenuList);

        lvAdmin = oncreateView.findViewById(R.id.adminlistview);
        lvAdmin.setAdapter(pcv);
        lvAdmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //Toast.makeText(InfoActivity.this, "Memilih : "+infoitem[position], Toast.LENGTH_LONG).show();
            if(position==0) {
                startActivity(new Intent(getContext(), GantiPINActivity.class));
            }
            //finish();

            }
        });

        return oncreateView;

    }

    public class adminCustomView extends ArrayAdapter{

        private final Context context;
        private final String[] values;

        public adminCustomView(Context context, String[] values){
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