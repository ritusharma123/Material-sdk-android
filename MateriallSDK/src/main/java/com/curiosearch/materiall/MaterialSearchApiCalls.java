package com.curiosearch.materiall;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.curiosearch.materiall.model.materialresponse.MaterialResponse;
import com.curiosearch.materiall.service.materiall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaterialSearchApiCalls extends LinearLayout {
    String phoneNumber;
    String clientId, userId, sessionId, categoryId, count;
    MaterialResponse entity;

//    void recomendation_request_Call(clientId, userId, sessionId, categoryId, count) {
//
//    }


    public MaterialSearchApiCalls(Context context) {
        super(context);
        initialize(context);
      // recomendation_request_Call(clientId, userId, sessionId, categoryId, count);
    }

    public MaterialSearchApiCalls(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
       // recomendation_request_Call(clientId, userId, sessionId, categoryId, count);

    }

    private void initialize(Context context){
        inflate(context, R.layout.my_view, this);
    }


//    private void RetrofitHitUrl(String phoneNumber) {
//        Retroclient retroclient = new Retroclient();
//        OtpRequestt service = retroclient.getClient().create(OtpRequestt.class);
//        Call<OtpResponse> call = service.getStringScalar(new OtpRequest(new Json(new Request("1", "10", new Data("91", phoneNumber)))));
//
//        call.enqueue(new Callback<OtpResponse>() {
//            @Override
//            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
//                if (response.isSuccessful()) {
//
//                    OtpResponse entity = response.body();
//                    String statusCode = entity.getJson().getResponse().getStatuscode();
//
//                    Log.d("statusCode", statusCode + " sessionID: " + "  entity :" + entity);
//
//                    if (entity.getJson().getResponse().getStatuscode().equals("0")) {
//                        Toast.makeText(getContext(), "We have sent the OTP", Toast.LENGTH_SHORT).show();
//
//                    } else {
//                        Toast.makeText(getContext(), "else", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<OtpResponse> call, Throwable t) {
//                t.printStackTrace();
////                String message = "Oops,there seems to be some issue. Please try again later";
////                showDialog(message);
////                rel_lay_progress_bar_profile.setVisibility(View.GONE);
////                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
////                spinner.setVisibility(View.GONE);
//            }
//        });
//    }

//    public MaterialResponse recomendation_request_Call(String clientId, String userId, String sessionId, String categoryId, int count) {
//        Retroclient retroclient = new Retroclient();
//        materiall service = retroclient.getClient().create(materiall.class);
//
//        Call<MaterialResponse> call = service.materiallInterface(clientId, userId, sessionId, categoryId, count);
//        call.enqueue(new Callback<MaterialResponse>() {
//            @Override
//            public void onResponse(Call<MaterialResponse> call, Response<MaterialResponse> response) {
//                if (response.isSuccessful()) {
//                     entity = response.body();
//                     String string = response.message() + " "+ response.code();
//
//                  //  System.out.println("recomenadation_result" +"   " +entity.getData().getProducts());
//
//                    Log.d("recomenadati_resultsta",   "  entity :" + entity + " " + response.message() + call.toString());
//
//
//                    Log.d("TAG", "onResponseisSuccessful: "+response.isSuccessful());
//                    Log.d("TAG", "onResponsebody: "+response.body());
//                    Log.d("TAG", "onResponseerrorBody: "+response.errorBody());
//                    Log.d("TAG", "onResponsemessage: "+response.message());
//                    Log.d("TAG", "onResponsecode: "+response.code());
//                    Log.d("TAG", "onResponseheaders: "+response.headers());
//                    Log.d("TAG", "onResponseraw: "+response.raw());
//                    Log.d("TAG", "onResponsetoString: "+response.toString());
//
//                    Toast.makeText(getContext(), " response.code() :" + response.code() + " "+ "response.message() :"  + response.message() , Toast.LENGTH_SHORT).show();
////                    if (entity.getJson().getResponse().getStatuscode().equals("0")) {
////                    } else {
////                        //no_schedu.setVisibility(View.VISIBLE);
////                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MaterialResponse> call, Throwable t) {
//                t.printStackTrace();
////                String message = "Oops,there seems to be some issue. Please try again later";
////                showDialog(message);
////                rel_lay_progress_bar_profile.setVisibility(View.GONE);
////                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
////                spinner.setVisibility(View.GONE);
//            }
//        });
//        return entity;
//    }

}
