package com.androidtutorialshub.tamobilelanjutan.remote;

import com.androidtutorialshub.tamobilelanjutan.model.Transaksi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TransaksiService {

    @FormUrlEncoded
    @POST("mutasi")
    Call<List<Transaksi>> cekMutasi(
            @Field("norekening") String norekening,
            @Field("bulan") String bulan,
            @Field("pin") String pin);

}
