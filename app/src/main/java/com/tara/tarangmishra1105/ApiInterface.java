package com.tara.tarangmishra1105;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("api.php?size=5")
    Call<Root> getData();

    @FormUrlEncoded
    @POST("api.php")
    Call<Root> getData(@Field("size") int size);
}
