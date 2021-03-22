package com.curiosearch.materiall.model;

import java.util.ArrayList;
import java.util.List;

public class EventModel {

    private ArrayList<products> products;

    public EventModel(ArrayList<com.curiosearch.materiall.model.products> products) {
        this.products = products;

    }

    public ArrayList<com.curiosearch.materiall.model.products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<com.curiosearch.materiall.model.products> products) {
        this.products = products;
    }
}


