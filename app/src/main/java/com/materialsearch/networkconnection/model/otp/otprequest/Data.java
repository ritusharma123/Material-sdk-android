package com.materialsearch.networkconnection.model.otp.otprequest;

public class Data
{
    private String countrycode;

    private String mobile;

    public Data(String countrycode, String mobile) {
        this.countrycode = countrycode;
        this.mobile = mobile;
    }

    public String getCountryCode ()
    {
        return countrycode;
    }

    public void setCountryCode (String countryCode)
    {
        this.countrycode = countryCode;
    }

    public String getMobile ()
    {
        return mobile;
    }

    public void setMobile (String mobile)
    {
        this.mobile = mobile;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [countrycode = "+countrycode+", mobile = "+mobile+"]";
    }
}