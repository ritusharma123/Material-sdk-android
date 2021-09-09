package com.curiosearch.materiall.service;

import android.os.AsyncTask;

import com.curiosearch.materiall.Retroclient;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class TimeService {
    ITimeTask timeApi;
    TimeService(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ITimeTask.TIME_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        timeApi = retrofit.create(ITimeTask.class);
    }


    long getCurrentEpochTimeMillis(){
        long epTime;
        try {
            JSONObject response = new SendTimeRequestToServer().execute().get();
            if(response != null){
                epTime = response.getLong("unixtime")*1000;
                System.out.println("UNIX EP 11111 TIME : " + epTime);
            }else{
                epTime = System.currentTimeMillis();
                System.out.println("UNIX Failed 11111 TIME : " + epTime);
            }
        } catch (Exception e) {
            epTime = System.currentTimeMillis();
            System.out.println("UNIX EP 2222222 TIME : " + epTime);
        }
        return epTime;
    }

    public class SendTimeRequestToServer extends AsyncTask<Void, Void, JSONObject> {

        JSONObject jsonResponse = null;

        protected void onPreExecute() {
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            Response response = null;
            Retroclient retroclient = null;
            materiall service = null;
            Call call = null;

            try {
                    call = timeApi.currentTimeStamp();

                response = call.execute();

                System.out.println("serverResponse  :" + response);
                System.out.println("serverResponse Code :" + response.code());

                if (response != null) {
                    ////////converting the server response to json//////////////////
                    if(response.code()==200) {
                        String jsonString = new Gson().toJson(response.body());
                        try {
                            jsonResponse = new JSONObject(jsonString);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                return jsonResponse;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Time Response : " + jsonResponse);
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(JSONObject jsonResponse) {
            super.onPostExecute(jsonResponse);
        }
    }
}
