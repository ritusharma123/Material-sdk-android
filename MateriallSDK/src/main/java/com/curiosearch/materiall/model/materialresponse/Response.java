package com.curiosearch.materiall.model.materialresponse;


import java.util.ArrayList;

public class Response
{

    public ArrayList<Products> products;

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [products = "+products+"]";
    }
}
