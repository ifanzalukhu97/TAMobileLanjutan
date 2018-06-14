package com.androidtutorialshub.tamobilelanjutan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidtutorialshub.tamobilelanjutan.model.Transaksi;

import java.util.List;

public class RecyclerAdapterMutasi extends RecyclerView.Adapter <RecyclerAdapterMutasi.MyViewHolder> {

    private List<Transaksi> transaksis;
    private Context context;

    public RecyclerAdapterMutasi(List<Transaksi> transaksis, Context context){

        this.transaksis = transaksis;
        Log.e("HASILLLLLLLL", Integer.toString(transaksis.size()));
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mutasi_rv_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tanggal.setText(transaksis.get(position).getTanggal());
        holder.nominal.setText(Integer.toString(transaksis.get(position).getNominal()));
        holder.jenisTransaksi.setText(transaksis.get(position).getJenis_transaksi());

    }

    @Override
    public int getItemCount() {
        return transaksis.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tanggal, nominal, jenisTransaksi;

        public MyViewHolder(View itemView) {
            super(itemView);

            tanggal = itemView.findViewById(R.id.tanggalMutasi);
            nominal = itemView.findViewById(R.id.nominalMutasi);
            jenisTransaksi = itemView.findViewById(R.id.jenisTransaksiMutasi);

        }
    }
}
