package com.materialsearch.networkconnection.model.otp.otpresponse;

public class OtpResponse
{
    private Json json;

    public Json getJson ()
    {
        return json;
    }

    public void setJson (Json json)
    {
        this.json = json;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [json = "+json+"]";
    }
}
