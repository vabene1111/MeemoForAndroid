package de.droidenschmiede.meemoforandroid.interfaces;

/**
 * Created by vabene1111 on 20.06.2017.
 * Interface for Volley Responses
 */

public interface VolleyInterface<T> {
    void onResponse(String result, Class<T> clazz);
}
