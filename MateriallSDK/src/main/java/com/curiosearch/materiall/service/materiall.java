package com.curiosearch.materiall.service;

import com.curiosearch.materiall.model.EventModel;
import com.curiosearch.materiall.model.products;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface materiall {
//        @Headers("Content-Type: application/json"  )
        @GET("api/products/recommendation?")
        Call<Object> materiallInterface(@Query("clientId") String clientId,
                                               @Query("userId") String userId,
                                               @Query("sessionId") String sessionId,
                                               @Query("categoryId") String categoryId,
                                               @Query("count") Integer count,
                                               @Query("page") Integer page,
                                               @Query("materiall") boolean materiall,
                                               @Query("template") String template,
                                               @Query("sortBy") String sortBy,
                                               @Query("filter") String filter);


    @GET("api/products/recommendation?")
    Call<Object> SearchqInterface(@Query("clientId") String clientId,
                                  @Query("userId") String userId,
                                    @Query("searchq") String searchq,
                                    @Query("count") Integer count,
                                    @Query("page") int page,
                                    @Query("sortBy") String sortBy);


    @GET("api/product/{productId}/similar/recommendations?")
    Call<Object> GetSimilarProductsInterface( @Path("productId") String productId,
                                  @Query("clientId") String clientId,
                                  @Query("categoryId") String categoryId,
                                  @Query("userId") String userId,
                                  @Query("count") Integer count,
                                  @Query("page") Integer page,
                                  @Query("template") String template);

    @Headers("Content-Type: application/json")
    @POST("api/user/{userId}/event?")
    Call<Object> EventInterface(@Path("userId") String userId,
                                @Query("clientId") String clientId,
                                @Query("sessionId") String sessionId,
                                @Query("categoryId") String categoryId,
                                @Query("type") String type,
                                @Body EventModel productsList);


  @GET("api/cache/refresh?")
    Call<Object> RefreshCacheInterface(@Query("clientId") String clientId);

   @GET("api/configuration/refresh?")
    Call<Object> RefreshInterface(@Query("clientId") String clientId);


   @GET("api/compute/diverse?")
    Call<String> DiverseInterface(@Query("clientId") String clientId);

   @GET("/api/compute/trends?")
    Call<String> TrendsInterface(@Query("clientId") String clientId);

}
