package com.curiosearch.materiall;

import android.os.AsyncTask;

import com.curiosearch.materiall.service.Config;
import com.google.gson.Gson;
import com.curiosearch.materiall.service.materiall;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class ProductRecommendationSearch {
    private String template = "";
    private String sortBy = "";
    private String filter = "";
    private int count = -1;
    private String categoryId = "";
    private String searchq = "";
    private float ratings = (float) -1.0;

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSearchq() {
        return searchq;
    }

    public void setSearchq(String searchq) {
        this.searchq = searchq;
    }


    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //////////////////////ProductCategory API INTERFACE////////////

    public JSONObject getRecommendedProductCategory(String clientId, String userId, String categoryId, String sessionId, String page, String pageType) throws Exception{
        JSONObject response = null;
        try {
            String url = Config.BaseUrl + "api/products/recommendation?&clientId=" + clientId + "&userId=" + userId
                    +"&sessionId=" + sessionId +"&page=" + page +"&pageType=" + pageType +
                    "&categoryId=" + categoryId;


            if (count != -1) {
                url = url + "&count=" + count;
            }
            if (sortBy != "") {
                url = url + "&sortBy=" + sortBy;
            }
            if (template != "") {
                url = url + "&template=" + template;
            }
            if (filter != "") {
                url = url + "&filter=" + filter;
            }

            response = new SendRecommadedProductRequestToServer(url, null).execute().get();
            System.out.println("response:   " + response);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return response;
    }

    //////////////////////RecordUserActions API INTERFACE////////////

    public JSONObject recordUserActions(String userId, String type, String clientId, String sessionId,
                                        String deviceType, String deviceOS, String browser, String page, String pageType, JSONObject productList) {
        JSONObject response = null;
        try {
            String url = Config.BaseUrl + "api/user/" + userId + "/event?" + "&type=" + type + "&clientId=" + clientId + "&sessionId=" + sessionId
                    + "&deviceType=" + deviceType + "&deviceOS=" + deviceOS+ "&browser=" + browser
                    + "&page=" + page+ "&pageType=" + pageType ;

             if (pageType.equals("categoryPage") && categoryId !="") {
                 url = url + "&categoryId=" + categoryId;
             }

               if (pageType.equals("searchPage") && searchq != "") {
                   url = url + "&searchq=" + searchq;
               }
             
                 if (type.equals("rate") && ratings != -1.0) {
                     url = url + "&ratings=" + ratings;
                 }

            System.out.println("getRecordUserActionsrequest :   " + url + "     productList  :" + productList);

            response = new SendRecommadedProductRequestToServer(url, productList).execute().get();
            System.out.println("response:   " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


    //////////////////SENDING REQUEST TO THE SERVER//////////////

    public class SendRecommadedProductRequestToServer extends AsyncTask<Void, Void, JSONObject> {
        String url;
        JSONObject productList;
        JSONObject jsonResponse = null;

        public SendRecommadedProductRequestToServer(String url, JSONObject productList) {
            this.url = url;
            this.productList = productList;
        }

        protected void onPreExecute() {
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            Response response = null;
            Retroclient retroclient = null;
            materiall service = null;
            Call call = null;

            retroclient = new Retroclient();
            service = retroclient.getClient().create(materiall.class);
            try {

                if (productList != null && productList.length() > 0) {
                    RequestBody bodyRequest = RequestBody.create(MediaType.parse("application/json"), productList.toString());
                    call = service.productListingInterface(url, bodyRequest);
                } else {
                    call = service.materiallInterface(url);
                }
                response = call.execute();

                System.out.println("serverResponse  :" + response);

                if (response != null) {

                    ////////converting the server response to json//////////////////
                    String jsonString = new Gson().toJson(response.body());
                    try {
                        jsonResponse = new JSONObject(jsonString);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                return jsonResponse;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("jsonResponse  :" + jsonResponse);
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(JSONObject jsonResponse) {
            super.onPostExecute(jsonResponse);
        }
    }

}



