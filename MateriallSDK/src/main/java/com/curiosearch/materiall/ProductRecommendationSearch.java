package com.curiosearch.materiall;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Base64;

import androidx.annotation.RequiresApi;

import com.curiosearch.materiall.service.Config;
import com.google.gson.Gson;
import com.curiosearch.materiall.service.materiall;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Header;

public class ProductRecommendationSearch {
    private String sortBy = "";
    private String filter = "";
    private int count = -1;
    private String categoryId = "";
    private String searchq = "";
    private String XRequestID = "";
    private float ratings = -1.0f;

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
        if (categoryId != null) {
            this.categoryId = categoryId.trim();
        }

    }

    public String getSearchq() {
        return searchq;
    }

    public void setSearchq(String searchq) {
        if (searchq != null) {
            this.searchq = searchq.trim();
        }
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
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

    public String getXRequestID() {
        return XRequestID;
    }

    public void setXRequestID(String XRequestID) {
        if (XRequestID != null) {
            this.XRequestID = XRequestID;
        }
    }

    //////////////////////ProductCategory API INTERFACE////////////

    public JSONObject getRecommendedProductCategory(String clientId, String userId, String pageType, String sessionId, String template, String page) throws Exception {
        JSONObject response = null;
        try {
            String url = Config.BaseUrl + "api/products/recommendation?&clientId=" + clientId + "&userId=" + userId
                    + "&sessionId=" + sessionId + "&template=" + template + "&page=" + page + "&pageType=" + pageType;
            String xRequestID = "";

            if (count != -1) {
                url = url + "&count=" + count;
            }
            if (sortBy != "") {
                url = url + "&sortBy=" + sortBy;
            }

            if (filter != "") {
                url = url + "&filter=" + filter;
            }

            if (XRequestID != "") {
                xRequestID = XRequestID;
            }

            if (pageType.equals("categoryPage") && !categoryId.isEmpty()) {
                url = url + "&categoryId=" + categoryId;
            }

            if (pageType.equals("searchPage") && !searchq.isEmpty()) {
                url = url + "&searchq=" + searchq;
            }


            String authorization = Config.getAuthorization(clientId);
            System.out.println("authorization_response:   " + authorization);

            if (authorization != null && !authorization.isEmpty()) {
                response = new SendRecommadedProductRequestToServer(authorization, xRequestID, url, null).execute().get();
                System.out.println("response:   " + response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return response;
    }

    //////////////////////RecordUserActions API INTERFACE////////////

    public JSONObject recordUserActions(String userId, String clientID, String sessionId, String type,
                                        String deviceType, String deviceOS, String browser, String pageType, String page, JSONObject productList) {
        JSONObject response = null;
        try {
            String url = Config.BaseUrl + "api/user/" + userId + "/event?" + "&type=" + type + "&clientId=" + clientID + "&sessionId=" + sessionId
                    + "&deviceType=" + deviceType + "&deviceOS=" + deviceOS + "&browser=" + browser
                    + "&page=" + page + "&pageType=" + pageType;
            String xRequestID = "";

            if (pageType.equals("categoryPage") && !categoryId.isEmpty()) {
                url = url + "&categoryId=" + categoryId;
            }

            if (pageType.equals("searchPage") && !searchq.isEmpty()) {
                url = url + "&searchq=" + searchq;
            }

            if (type.equals("rate") && ratings != -1.0f) {
                url = url + "&ratings=" + ratings;
            }

            if (XRequestID != "") {
                xRequestID = XRequestID;
            }

            System.out.println("getRecordUserActionsrequest :   " + url + "     productList  :" + productList);

            String authorization = Config.getAuthorization(clientID);
            System.out.println("authorization_response:   " + authorization);

            if (authorization != null && !authorization.isEmpty()) {
                response = new SendRecommadedProductRequestToServer(authorization, xRequestID, url, productList).execute().get();
                System.out.println("response:   " + response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


    //////////////////SENDING REQUEST TO THE SERVER//////////////

    public class SendRecommadedProductRequestToServer extends AsyncTask<Void, Void, JSONObject> {
        String url;
        String authorization;
        String request_id;
        JSONObject productList;
        JSONObject jsonResponse = null;

        public SendRecommadedProductRequestToServer(String authorization, String request_id, String url, JSONObject productList) {
            this.url = url;
            this.productList = productList;
            this.authorization = authorization;
            this.request_id = request_id;
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
                    call = service.productListingInterface(authorization, request_id, url, bodyRequest);
                } else {
                    call = service.materiallInterface(authorization, request_id, url);
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
                } else if (response.errorBody() != null) {
                    System.out.println("jsonResponse  :" + response.message());

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



