package com.materialsearch.networkconnection;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.curiosearch.materiall.ProductRecommendationSearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {
    Button confirm_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        confirm_button = findViewById(R.id.confirm_button);
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /////////////getRecommendedProductCategory/////////////
                try {
                    ProductRecommendationSearch productRecommendationSearch1 = new ProductRecommendationSearch();

                    productRecommendationSearch1.setCount(5);
                    productRecommendationSearch1.setSessionId("s-745gh41");
                    productRecommendationSearch1.setMateriall(false);
                    productRecommendationSearch1.setTemplate("default");
                    productRecommendationSearch1.setSortBy("Relevance");
                    productRecommendationSearch1.setPage(1);
                    productRecommendationSearch1.setFilter("color_matFilter:Red OR color_matFilter:Blue");


                    JSONObject result = productRecommendationSearch1.getRecommendedProductCategory("apparel-demo", "u500", "Western Wear");
                    if (result != null) {
                        System.out.println("productCategoryresponse :   " + result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ////////////getRecommendedProductSearch////////////////
                try {



                    ProductRecommendationSearch productRecommendationSearch2 = new ProductRecommendationSearch();

                    productRecommendationSearch2.setCount(5);
                    productRecommendationSearch2.setTemplate("default");
                    productRecommendationSearch2.setSortBy("Relevance");
                    productRecommendationSearch2.setFilter("color_matFilter:Red OR color_matFilter:Blue");

                    JSONObject result = productRecommendationSearch2.getRecommendedProductSearch("apparel-demo", "48042", "V-Back Ruffled Gown", 2);
                    if (result != null) {
                        System.out.println("productSearchResponse :   " + result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                /////////////getRecommendedSimilarProducts//////////////////////

                try {
                    ProductRecommendationSearch productRecommendationSearch3 = new ProductRecommendationSearch();
                    productRecommendationSearch3.setCount(5);
                    productRecommendationSearch3.setPage(1);
                    productRecommendationSearch3.setSessionId("s-745gh41");
                    productRecommendationSearch3.setTemplate("default");
                    productRecommendationSearch3.setFilter("color_matFilter:Red OR color_matFilter:Blue");


                    JSONObject result = productRecommendationSearch3.getRecommendedSimilarProducts("1602", "apparel-demo", "Western Wear", "48042");
                    if (result != null) {
                        System.out.println("similarProductsResponse :   " + result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


                ///////////////getRecordUserActions///////////////////
                try {

                    ProductRecommendationSearch productRecommendationSearch4 = new ProductRecommendationSearch();
                    JSONObject productList = new JSONObject();
                    JSONArray jsonArray = new JSONArray();
                    JSONObject obj1 = new JSONObject();
                    JSONObject obj2 = new JSONObject();
                    JSONObject obj3 = new JSONObject();
                    JSONObject obj4 = new JSONObject();
                    try {
                        obj1.put("id", "1801");
                        obj2.put("id", "1663");
                        obj3.put("id", "2571");
                        obj4.put("id", "22");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(obj1);
                    jsonArray.put(obj2);
                    jsonArray.put(obj3);
                    jsonArray.put(obj4);
                    productList.put("products", jsonArray);
                    System.out.println("jsonObjectResponse :   " + " " + productList);

                    JSONObject result = productRecommendationSearch4.recordUserActions("48042", "apparel-demo", "s1", "Western Wear", "seen", productList);
                    if (result != null) {
                        System.out.println("getRecordUserActionsResponse :   " + result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
