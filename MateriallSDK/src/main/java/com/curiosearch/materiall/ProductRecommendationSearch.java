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
    private boolean materiall = true;
    private String template = "";
    private String sortBy = "";
    private String filter = "";
    private int count = -1;
    private int page = -1;
    private String sessionId = "";

    public boolean isMateriall() {
        return materiall;
    }

    public void setMateriall(boolean materiall) {
        this.materiall = materiall;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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

    public JSONObject getRecommendedProductCategory(String clientId, String userId, String categoryId) throws Exception{
        JSONObject response = null;
        try {
            String url = Config.BaseUrl + "api/products/recommendation?&clientId=" + clientId + "&userId=" + userId +
                    "&categoryId=" + categoryId;

            if (count != -1) {
                url = url + "&count=" + count;
            }
            if (page != -1) {
                url = url + "&page=" + page;
            }
            if (sessionId != "") {
                url = url + "&sessionId=" + sessionId;
            }
            if (sortBy != "") {
                url = url + "&sortBy=" + sortBy;
            }
            if (template != "") {
                url = url + "&template=" + template;
            }

                url = url + "&material=" + materiall;

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

    //////////////////////ProductSearch API INTERFACE////////////

    public JSONObject getRecommendedProductSearch(String clientId, String userId, String searchq, int page) {
        JSONObject response = null;

        try {
            String url = Config.BaseUrl + "api/products/recommendation?&clientId=" + clientId + "&userId=" + userId + "&searchq=" + searchq +
                    "&page=" + page;
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
        }
        return response;
    }


    //////////////////////SimilarProducts API INTERFACE////////////

    public JSONObject getRecommendedSimilarProducts(String productId, String clientId, String categoryId, String userId) {
        JSONObject response = null;
        try {
            String url = Config.BaseUrl + "api/product/" + productId + "/similar/recommendations?&clientId=" + clientId + "&userId=" + userId +
                    "&categoryId=" + categoryId;
            if (count != -1) {
                url = url + "&count=" + count;
            }
            if (page != -1) {
                url = url + "&page=" + page;
            }
            if (sessionId != "") {
                url = url + "&sessionId=" + sessionId;
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
        }

        return response;
    }

    //////////////////////RecordUserActions API INTERFACE////////////

    public JSONObject recordUserActions(String userId, String clientId, String sessionId, String categoryId, String type, JSONObject productList) {
        JSONObject response = null;
        try {
            String url = Config.BaseUrl + "api/user/" + userId + "/event?" + "&clientId=" + clientId + "&sessionId=" + sessionId  +
                    "&categoryId=" + categoryId + "&type=" + type;

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



