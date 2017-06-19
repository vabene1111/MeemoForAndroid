package de.droidenschmiede.meemoforandroid.helper;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vabene1111 on 19.06.2017.
 * Helper Class for all Meemo related API requests
 */

public class MeemoHelper {


    public void loginUser(Context c) {

        final JSONObject jsonBody;

        RequestQueue queue = Volley.newRequestQueue(c);
        try {
            jsonBody = new JSONObject();
            jsonBody.put("username", Singleton.username);
            jsonBody.put("password", Singleton.password);
            final String mRequestBody = jsonBody.toString();

            StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, Singleton.server + "/api/login", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println();
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        String responseContent = new String(error.networkResponse.data,"UTF-8");
                        System.out.print(responseContent);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    };
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {

                        responseString = String.valueOf(response.statusCode);

                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };


            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
