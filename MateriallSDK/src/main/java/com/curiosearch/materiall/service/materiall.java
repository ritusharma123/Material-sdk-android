package com.curiosearch.materiall.service;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface materiall {
    @Headers( "Content-Type: application/json; charset=utf-8")
    @GET
    Call<Object> materiallInterface(@Header ("Authorization") String authorization, @Header ("X-Request-ID") String request_id, @Url String url);

    @Headers( "Content-Type: application/json; charset=utf-8")
    @POST
    Call<Object> productListingInterface(@Header ("Authorization") String authorization, @Header ("X-Request-ID") String request_id, @Url String url,@Body RequestBody productList);

}
