package com.androidtutorialshub.tamobilelanjutan.remote;

import com.androidtutorialshub.tamobilelanjutan.model.User;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {

    // TODO 4 User Service

    @FormUrlEncoded
    @POST("login")
    Call<User> userLogin(
            @Field("username") String UserName,
            @Field("noHp") String noHp);

    @FormUrlEncoded
    @POST("ceksaldo")
    Call<User> cekSaldo(
            @Field("pin") String pin,
            @Field("norekening") String norekening
    );
}
