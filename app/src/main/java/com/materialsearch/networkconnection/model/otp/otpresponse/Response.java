package com.materialsearch.networkconnection.model.otp.otpresponse;


public class Response
{

    private Data data;

    private String servicetype;

    private String functiontype;
    private String statusmessage;
    private String statuscode;

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getFunctiontype() {
        return functiontype;
    }

    public void setFunctiontype(String functiontype) {
        this.functiontype = functiontype;
    }



    public String getStatusmessage() {
        return statusmessage;
    }

    public void setStatusmessage(String statusmessage) {
        this.statusmessage = statusmessage;
    }



    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
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

        return "ClassPojo [servicetype = "+servicetype+",functiontype = "+functiontype+",statuscode = "+statuscode+", statusmessage = "+statusmessage+", data = "+data+"]";
    }
}