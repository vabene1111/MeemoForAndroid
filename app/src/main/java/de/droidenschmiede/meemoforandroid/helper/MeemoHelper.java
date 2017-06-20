package de.droidenschmiede.meemoforandroid.helper;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import de.droidenschmiede.meemoforandroid.R;
import de.droidenschmiede.meemoforandroid.interfaces.VolleyInterface;
import de.droidenschmiede.meemoforandroid.objects.Login;
import de.droidenschmiede.meemoforandroid.objects.Things;

/**
 * Created by vabene1111 on 19.06.2017.
 * Helper Class for all Meemo related API requests
 */

public class MeemoHelper {


    public void loginUser(Context c, final VolleyInterface callback) {

        SharedPreferences sharedPref = c.getSharedPreferences("settings",Context.MODE_PRIVATE);
        String server = sharedPref.getString("server", "");
        String username = sharedPref.getString("username", "");
        String password = sharedPref.getString("password", "");

        final JSONObject jsonBody;

        RequestQueue queue = Volley.newRequestQueue(c);
        try {
            jsonBody = new JSONObject();
            jsonBody.put("username", username);
            jsonBody.put("password", password);
            final String requestBody = jsonBody.toString();

            StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, server + "/api/login", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    callback.onResponse(response, Login.class);
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        if (error.networkResponse != null){
                            String responseContent = new String(error.networkResponse.data, "UTF-8");
                            callback.onResponse(responseContent, Error.class);
                        }else{
                            Log.d("MeemoHelper","Network Error");
                        }

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }


            };

            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getUserThings(Context c, final VolleyInterface callback) {
        RequestQueue queue = Volley.newRequestQueue(c);

        SharedPreferences sharedPref = c.getSharedPreferences("settings",Context.MODE_PRIVATE);
        String server = sharedPref.getString("server", "");

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, server + "/api/things?token=" + Singleton.getLogin().getToken(), new Response
                .Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onResponse(response, Things.class);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseContent = new String(error.networkResponse.data, "UTF-8");
                    callback.onResponse(responseContent, Error.class);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                ;
            }
        });


        queue.add(jsonObjectRequest);

    }
}
