package com.curiosearch.materiall.service;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface materiall {
    @GET
    Call<Object> materiallInterface(@Url String url);

    @Headers( "Content-Type: application/json; charset=utf-8")
    @POST
    Call<Object> productListingInterface(@Url String url,@Body RequestBody productList);

}
