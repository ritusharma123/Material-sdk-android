package com.materialsearch.networkconnection.service;




import com.materialsearch.networkconnection.model.otp.otprequest.OtpRequest;
import com.materialsearch.networkconnection.model.otp.otpresponse.OtpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OtpRequestt
{
    @Headers("Content-Type: application/json"  )
    @POST("customer/login")
    Call<OtpResponse> getStringScalar(@Body OtpRequest body);
//    Call<CustAppointmentResponse> getStringScalar(@Query(value = "request", encoded = true) String token, @Body
//CustAppointmentRequest body);


}
