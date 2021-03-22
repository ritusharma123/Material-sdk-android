package com.materialsearch.networkconnection.model.otp.otpresponse;


public class Json
{
    private Response response;

    public Response getResponse ()
    {
        return response;
    }

    public void setResponse (Response response)
    {
        this.response = response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Response = "+response+"]";
    }
}
