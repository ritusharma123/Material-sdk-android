package com.materialsearch.networkconnection.model.otp.otpresponse;

public class Data
{
    private String sessionid;

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sessionid = "+sessionid+"]";
    }
}