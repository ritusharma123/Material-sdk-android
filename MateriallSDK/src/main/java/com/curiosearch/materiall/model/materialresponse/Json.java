package com.curiosearch.materiall.model.materialresponse;


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
