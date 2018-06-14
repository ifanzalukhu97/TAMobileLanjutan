package com.androidtutorialshub.tamobilelanjutan;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {

    String[] paymentSubmenuList = new String[] {"Kartu Kredit", "Handphone", "Telepon",
            "Publik / Utilitas", "Asuransi", "Internet"};
    ListView lvPayment;


    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View oncreateView  = inflater.inflate(R.layout.fragment_payment, container, false);
        paymentCustomView pcv = new paymentCustomView(getContext(), paymentSubmenuList);

        lvPayment = oncreateView.findViewById(R.id.paymentlistview);
        lvPayment.setAdapter(pcv);


        return oncreateView;

    }

    public class paymentCustomView extends ArrayAdapter{

        private final Context context;
        private final String[] values;

        public paymentCustomView(Context context, String[] values){
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
