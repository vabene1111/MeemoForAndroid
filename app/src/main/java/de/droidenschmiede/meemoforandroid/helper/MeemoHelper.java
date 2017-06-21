package de.droidenschmiede.meemoforandroid.helper;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import de.droidenschmiede.meemoforandroid.interfaces.VolleyInterface;
import de.droidenschmiede.meemoforandroid.objects.CustomError;
import de.droidenschmiede.meemoforandroid.objects.Login;
import de.droidenschmiede.meemoforandroid.objects.Things;

/**
 * Created by vabene1111 on 19.06.2017.
 * Helper Class for all Meemo related API requests
 */

public class MeemoHelper {


    public void loginUser(Context c, final VolleyInterface callback) {
        final JSONObject jsonBody;

        try {
            jsonBody = new JSONObject();
            jsonBody.put("username", Singleton.getUsername());
            jsonBody.put("password", Singleton.getPassword());
            final String requestBody = jsonBody.toString();

            String url = "https://" + Singleton.getServer() + "/api/login";

            doRequest(c,callback,url,Login.class,Request.Method.POST,requestBody);
        } catch (Exception e) {
            Log.d("MeemoHelper","Json Error");
        }

    }

    public void doRequest(Context c, final VolleyInterface callback,String url, final Class responseType, int requestMethod, final String requestBody) {

        RequestQueue queue = Volley.newRequestQueue(c);
        StringRequest jsonObjectRequest = new StringRequest(requestMethod, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onResponse(response, responseType);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    if (error.networkResponse != null) {
                        String responseContent = new String(error.networkResponse.data, "UTF-8");
                        callback.onResponse(responseContent, CustomError.class);
                    } else {
                        Log.d("VolleyHelper", "Network CustomError 1");
                    }

                } catch (UnsupportedEncodingException e) {
                    Log.d("VolleyHelper", "Network CustomError 2");
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

    }

    public void getUserThings(Context c, final VolleyInterface callback) {
        RequestQueue queue = Volley.newRequestQueue(c);

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, "https://" + Singleton.getServer() + "/api/things?token=" + Singleton.getLogin().getToken(), new Response
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
                    callback.onResponse(responseContent, CustomError.class);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        queue.add(jsonObjectRequest);

    }

    public void logoutUser(Context c, final VolleyInterface callback) {
        RequestQueue queue = Volley.newRequestQueue(c);

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, "https://" + Singleton.getServer() + "/api/logout?token=" + Singleton.getLogin().getToken(), new Response
                .Listener<String>() {
            @Override
            public void onResponse(String response) {
                //either this is successful or not, only called on application destroy so no callback needed/possible
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //either this is successful or not, only called on application destroy so no callback needed/possible
            }
        });
        queue.add(jsonObjectRequest);

    }
}
