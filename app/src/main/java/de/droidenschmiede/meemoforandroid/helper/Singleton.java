package de.droidenschmiede.meemoforandroid.helper;

import android.content.Context;

import de.droidenschmiede.meemoforandroid.objects.Login;

/**
 * Created by vabene1111 on 19.06.2017.
 * Singleton
 */
public class Singleton {

    private static Singleton instance;

    private static Login login;

    private Singleton () {}


    public static Singleton getInstance () {
        if (Singleton.instance == null) {
            Singleton.instance = new Singleton ();
        }
        return Singleton.instance;
    }

    public static Login getLogin() {
        return login;
    }

    public static void setLogin(Login login) {
        Singleton.login = login;
    }

}
