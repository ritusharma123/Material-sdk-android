package com.materialsearch.networkconnection.model.otp.otprequest;

public class Request
{
    private String servicetype ;
    private String functiontype ;
    private Data data;


    public Request(String servicetype, String functiontype, Data data) {

        this.servicetype = servicetype;
        this.functiontype = functiontype;
        this.data = data;

    }

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ data = "+data+"servicetype = "+servicetype+" functiontype = "+functiontype+"]";
    }


}
