package com.materialsearch.networkconnection.model.otp.otprequest;


public class OtpRequest
{
    private Json json;

    public OtpRequest(Json json) {
        this.json = json;
    }

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
