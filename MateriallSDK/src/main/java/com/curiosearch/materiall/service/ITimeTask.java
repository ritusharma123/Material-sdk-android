package com.curiosearch.materiall.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface ITimeTask {
    String TIME_SERVER_URL = "https://worldtimeapi.org/api/";
    @Headers( "Content-Type: application/json; charset=utf-8")
    @GET("ip")
    Call<Object> currentTimeStamp();
}
