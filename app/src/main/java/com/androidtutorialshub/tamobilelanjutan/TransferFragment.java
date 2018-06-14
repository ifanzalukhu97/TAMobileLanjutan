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
public class TransferFragment extends Fragment {

    String[] transferSubmenuList = new String[] {"Antar Rekening", "Antar Bank", "Virtual Account"};
    ListView lvTransfer;


    public TransferFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View oncreateView  = inflater.inflate(R.layout.fragment_transfer, container, false);
        transferCustomView pcv = new transferCustomView(getContext(), transferSubmenuList);

        lvTransfer = oncreateView.findViewById(R.id.transferlistview);
        lvTransfer.setAdapter(pcv);


        return oncreateView;

    }

    public class transferCustomView extends ArrayAdapter{

        private final Context context;
        private final String[] values;

        public transferCustomView(Context context, String[] values){
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
