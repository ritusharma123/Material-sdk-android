package com.materialsearch.networkconnection;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.curiosearch.materiall.ProductRecommendationSearch;
import com.curiosearch.materiall.model.materialresponse.MaterialResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import retrofit2.Response;

public class MainActivity extends Activity {
    Button confirm_button;
    String responseStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        confirm_button = findViewById(R.id.confirm_button);
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProductRecommendationSearch productRecommendationSearch = new ProductRecommendationSearch();
                //Optional parameters for the request
                productRecommendationSearch.setCount(10);
                productRecommendationSearch.setSessionId("s-745gh41");
                productRecommendationSearch.setMateriall(true);
                productRecommendationSearch.setTemplate("default");
                productRecommendationSearch.setSortBy("Relevance");
               // productRecommendationSearch.setFilter("(brand eq ‘Nike’ or brand eq ‘Reebok’) and price lt 50");

                try {
                    Response response = new ProductRecommendationSearch.RetrofitAyncProduct("apparel-demo", "u500", "Western Wear").execute().get();
                    if (response != null) {
                        System.out.println("search_Response_ClientSide :   " + response);
                        responseStr = new Gson().toJson(response.body());
                        try {
                            JSONObject result_json_Obj = new JSONObject(responseStr);
                            System.out.println("search_Response_ClientSide :  " + result_json_Obj);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    //pass the request parameters clientId, userId, searchq, page
                    Response response = new ProductRecommendationSearch.RetrofitAyncSearchq("apparel-demo", "48042", "V-Back Ruffled Gown", 2).execute().get();
                    if (response != null) {
                        System.out.println("search_Response_ClientSide_2ndUrl :   " + response);
                        String responseStr = new Gson().toJson(response.body());
                        try {
                            JSONObject result_json_Obj = new JSONObject(responseStr);
                            System.out.println("search_Response_ClientSide_2ndUrl :  " + result_json_Obj);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                } catch (ExecutionException e) {
                    e.printStackTrace();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    //pass the request parameters productId, clientId, categoryId, userId
                    Response response = new ProductRecommendationSearch.RetrofitAyncGetSimilarProducts("1602", "apparel-demo", "Western Wear", "48042").execute().get();
                    if (response != null) {
                        System.out.println("search_Response_ClientSide_3rdUrl :   " + response);
                        String responseStr = new Gson().toJson(response.body());
                        try {
                            JSONObject result_json_Obj = new JSONObject(responseStr);
                            System.out.println("search_Response_ClientSide_3rdUrl :  " + result_json_Obj);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    //pass the request parameters userId, clientId, sessionId, categoryId, type, id as body
                    Response response = new ProductRecommendationSearch.EventInterface("48042", "apparel-demo", "s1", "Western Wear", "seen", "1663").execute().get();
                    if (response != null) {
                        System.out.println("search_Response_ClientSide_4thUrl :   " + response);
                        String responseStr = new Gson().toJson(response.body());
                        try {
                            JSONObject result_json_Obj = new JSONObject(responseStr);
                            System.out.println("search_Response_ClientSide_4thUrl :  " + result_json_Obj);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}


//    private void RetrofitHitUrl() {
//        Retroclient retroclient = new Retroclient();
//        OtpRequestt service = retroclient.getClient().create(OtpRequestt.class);
//        Call<com.networkconnection.networkconnection.model.otp.otpresponse.OtpResponse> call = service.getStringScalar(new com.networkconnection.networkconnection.model.otp.otprequest.OtpRequest(new com.networkconnection.networkconnection.model.otp.otprequest.Json(new com.networkconnection.networkconnection.model.otp.otprequest.Request("1", "10", new Data("91", "7013316155")))));
//
//        call.enqueue(new Callback<com.networkconnection.networkconnection.model.otp.otpresponse.OtpResponse>() {
//            @Override
//            public void onResponse(Call<com.networkconnection.networkconnection.model.otp.otpresponse.OtpResponse> call, Response<OtpResponse> response) {
//                if (response.isSuccessful()) {
//
//                    com.networkconnection.networkconnection.model.otp.otpresponse.OtpResponse entity = response.body();
//                    String statusCode = entity.getJson().getResponse().getStatuscode();
//                    Log.d("statusCode", statusCode + " sessionID: " + "  entity :" + entity);
//
//                    if (entity.getJson().getResponse().getStatuscode().equals("0")) {
//
//                        } else {
//
//                            //no_schedu.setVisibility(View.VISIBLE);
//                        }
//
//
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<com.networkconnection.networkconnection.model.otp.otpresponse.OtpResponse> call, Throwable t) {
//                t.printStackTrace();
////                String message = "Oops,there seems to be some issue. Please try again later";
////                showDialog(message);
////                rel_lay_progress_bar_profile.setVisibility(View.GONE);
////                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
////                spinner.setVisibility(View.GONE);
//            }
//        });
//    }