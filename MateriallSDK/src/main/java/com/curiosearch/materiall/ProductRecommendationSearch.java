package com.curiosearch.materiall;

import android.os.AsyncTask;

import com.curiosearch.materiall.model.EventModel;
import com.curiosearch.materiall.model.products;
import com.google.gson.Gson;
import com.curiosearch.materiall.service.materiall;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRecommendationSearch {
    public static boolean materiall = true;
    public static String template = "";
    public static String sortBy = "";
    public static String sessionId = "";
    public static String filter = "";
    public static int count = -1;
    public static int page = -1;
    public static JSONObject result_json_Obj;
    public static String responseStr;
    public static Retroclient retroclient;
    public static materiall service;
    public static Call call;



    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public boolean isMateriall() {
        return materiall;
    }

    public void setMateriall(boolean materiall) {
        this.materiall = materiall;
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public static class RetrofitAyncProduct extends AsyncTask<Void, Void, Response> {
        String clientId, userId, categoryId;



        public RetrofitAyncProduct(String clientId, String userId, String categoryId) {
            this.clientId = clientId;
            this.userId = userId;
            this.categoryId = categoryId;
        }

        protected void onPreExecute() {
        }

        @Override
        protected Response doInBackground(Void... params) {
            Response responsee = null;
            retroclient = new Retroclient();
            service = retroclient.getClient().create(materiall.class);
            try {
                System.out.println("search_Response  1st: " + "  do background ");
//                if(count == -1){
//                    call = service.materiallInterface(clientId, userId, sessionId, categoryId,page, materiall, template, sortBy);
//                }

//                URL url = new URL("http://demo.materiall.com/");
//                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("GET");   //POST or GET
//                urlConnection.connect();
//                JSONObject jsonRequest = new JSONObject();
//                try {
//                    jsonRequest.put("clientId", "test123@gmail.com");
//                    jsonRequest.put("userId", "123456");
////                    if(sessionId!=null){
//                        jsonRequest.put("sessionId", "1111111111");
////                    }
//                    jsonRequest.put("categoryId", "1111111111");
//                    jsonRequest.put("count", "1111111111");
//                    jsonRequest.put("page", "1111111111");
//                    jsonRequest.put("materiall", "1111111111");
//                    jsonRequest.put("template", "1111111111");
//                    jsonRequest.put("sortBy", "1111111111");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                // Write Request to output stream to server.
//                OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
//                out.write(jsonRequest.toString());
//                out.close();
//
//                String string = new String();
//
//                InputStream responseString = urlConnection.getInputStream();
//                System.out.println("search_Response  : responseString: 2nd: " + "   " + responseString);
                if (count != -1 && page != -1 && template != "" && sortBy != "" && filter!="") {
                    call = service.materiallInterface(clientId, userId, sessionId, categoryId, count, page, materiall, template, sortBy, filter);
                } else if (count != -1 && page == -1 && template != "" && sortBy != "" && filter!="") {
                    call = service.materiallInterface(clientId, userId, sessionId, categoryId, count, null, materiall, template, sortBy, filter);
                } else if (count == -1 && page != -1 && template != "" && sortBy != "" && filter!="") {
                    call = service.materiallInterface(clientId, userId, sessionId, categoryId, null, page, materiall, template, sortBy, filter);
                } else if (count == -1 && page == -1 && template != "" && sortBy != "" && filter!="") {
                    call = service.materiallInterface(clientId, userId, sessionId, categoryId, null, null, materiall, template, sortBy, filter);
                } else if (count != -1 && page != -1 && template == "" && sortBy != "" && filter!="") {
                    call = service.materiallInterface(clientId, userId, sessionId, categoryId, count, page, materiall, null, sortBy, filter);
                } else if (count != -1 && page != -1 && template != "" && sortBy == "" && filter!="") {
                    call = service.materiallInterface(clientId, userId, sessionId, categoryId, count, page, materiall, template, null, filter);
                } else if (count != -1 && page != -1 && template != "" && sortBy != "" && filter =="") {
                    call = service.materiallInterface(clientId, userId, sessionId, categoryId, count, page, materiall, template, sortBy, null);
                }
                else if (count == -1 && page == -1 && template == "" && sortBy == "" && filter == "") {
                    call = service.materiallInterface(clientId, userId, sessionId, categoryId, null, null, materiall, null, null, null);
                }

                responsee = call.execute();
                System.out.println("search_Response  :2nd: " + "   " + responsee);
                responseStr = new Gson().toJson(responsee.body());
//                }
                try {
                    result_json_Obj = new JSONObject(responseStr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println("search_Response result :  " + result_json_Obj);
                return responsee;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Response result) {
            super.onPostExecute(result);
        }
    }


    public static class RetrofitAyncSearchq extends AsyncTask<Void, Void, Response> {
        String clientId, searchq, userId;
        int page;

        public RetrofitAyncSearchq(String clientId, String userId, String searchq, int page) {
            this.clientId = clientId;
            this.userId = userId;
            this.searchq = searchq;
            this.page = page;
        }

        protected void onPreExecute() {
        }

        @Override
        protected Response doInBackground(Void... params) {
            Response responsee = null;
            retroclient = new Retroclient();
            service = retroclient.getClient().create(materiall.class);
            try {
                System.out.println("search_Response_Searchq  1st: " + "  do background ");
                if (count != -1 && sortBy != "") {
                    call = service.SearchqInterface(clientId, userId, searchq, count, page, sortBy);
                } else if (count != -1 && sortBy == "") {
                    call = service.SearchqInterface(clientId, userId, searchq, count, page, null);
                } else if (count == -1 && sortBy != "") {
                    call = service.SearchqInterface(clientId, userId, searchq, null, page, sortBy);
                } else if (count == -1 && sortBy == "") {
                    call = service.SearchqInterface(clientId, userId, searchq, null, page, null);
                }
                responsee = call.execute();
                System.out.println("search_Response_Searchq  :2nd: " + "   " + responsee);

                responseStr = new Gson().toJson(responsee.body());
                try {
                    result_json_Obj = new JSONObject(responseStr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println("search_Response result :  " + result_json_Obj);
                return responsee;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Response result) {
            super.onPostExecute(result);
        }
    }


    public static class RetrofitAyncGetSimilarProducts extends AsyncTask<Void, Void, Response> {
        String clientId, productId, categoryId, userId;

        public RetrofitAyncGetSimilarProducts(String productId, String clientId, String categoryId, String userId) {
            this.clientId = clientId;
            this.productId = productId;
            this.categoryId = categoryId;
            this.userId = userId;
        }

        protected void onPreExecute() {
        }

        @Override
        protected Response doInBackground(Void... params) {
            Response responsee = null;
            retroclient = new Retroclient();
            service = retroclient.getClient().create(materiall.class);
            try {
                System.out.println("search_Response_getProducts  1st: " + "  do background ");

                if (count != -1 && page != -1 && template != "") {
                    call = service.GetSimilarProductsInterface(productId, clientId, categoryId, userId, count, page, template);
                } else if (count != -1 && page == -1 && template != "") {
                    call = service.GetSimilarProductsInterface(productId, clientId, categoryId, userId, count, null, template);
                } else if (count == -1 && page != -1 && template != "") {
                    call = service.GetSimilarProductsInterface(productId, clientId, categoryId, userId, null, page, template);

                } else if (count != -1 && page != -1 && template == "") {
                    call = service.GetSimilarProductsInterface(productId, clientId, categoryId, userId, count, page, null);

                } else if (count == -1 && page == -1 && template == "") {
                    call = service.GetSimilarProductsInterface(productId, clientId, categoryId, userId, null, null, null);
                }

                responsee = call.execute();
                System.out.println("search_Response_getProducts  :2nd: " + "   " + responsee);

                responseStr = new Gson().toJson(responsee.body());
                try {
                    result_json_Obj = new JSONObject(responseStr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println("search_Response result :  " + result_json_Obj);
                return responsee;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Response result) {
            super.onPostExecute(result);
        }
    }


    public static class EventInterface extends AsyncTask<Void, Void, Response> {
        String userId, clientId, sessionId, categoryId, type, id;
        String body;

        public EventInterface(String userId, String clientId, String sessionId, String categoryId, String type, String id) {
            this.userId = userId;
            this.clientId = clientId;
            this.sessionId = sessionId;
            this.categoryId = categoryId;
            this.type = type;
            this.id = id;
        }

        protected void onPreExecute() {
        }

        @Override
        protected Response doInBackground(Void... params) {
            Response responsee = null;
            retroclient = new Retroclient();
            service = retroclient.getClient().create(materiall.class);
            try {
                System.out.println("search_Response_Event  1st: " + "  do background ");

                call = service.EventInterface(userId, clientId, sessionId, categoryId, type, new EventModel(new ArrayList<products>(Integer.parseInt(id))));
                responsee = call.execute();
                System.out.println("search_Response_Event  :2nd: " + "   " + responsee);

                responseStr = new Gson().toJson(responsee.body());
                try {
                    result_json_Obj = new JSONObject(responseStr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println("search_Response_result_Event :  " + result_json_Obj);
                return responsee;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Response result) {
            super.onPostExecute(result);
        }
    }

}



