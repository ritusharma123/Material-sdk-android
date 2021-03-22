package com.materialsearch.networkconnection.model.otp.otprequest;

public class Json
{
    private Request request;

    public Json(Request request) {
        this.request = request;
    }

    public Request getRequest ()
    {
        return request;
    }

    public void setRequest (Request request)
    {
        this.request = request;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [request = "+request+"]";
    }
}