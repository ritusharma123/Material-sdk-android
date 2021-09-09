package com.curiosearch.materiall.service;

import android.util.Base64;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public  class Config {

   // public static String BaseUrl = "https://blibli-qa.curiosearch.in/";


    public static String getAuthorization(String clientId)
    {
        String authorization = null;
        try
        {
            long epochTimeMillis = new TimeService().getCurrentEpochTimeMillis();
            System.out.println("UNIXXXXXX TIME : " + epochTimeMillis);
            String epochTime = String.valueOf(epochTimeMillis);
            System.out.println("Epoch "+epochTime);
            System.out.println(epochTime);
            String epochTimetext = "cid_" + clientId + "/ct_" + epochTime;
            byte[] encrpt = epochTimetext.getBytes("UTF-8");
             authorization = Base64.encodeToString(encrpt, Base64.NO_WRAP);
            System.out.println("authorization_response:   " + authorization);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return authorization;
    }
}
