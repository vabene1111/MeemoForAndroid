package de.droidenschmiede.meemoforandroid.helper;

import android.content.Context;

import de.droidenschmiede.meemoforandroid.objects.Login;
import de.droidenschmiede.meemoforandroid.objects.Thing;

/**
 * Created by vabene1111 on 19.06.2017.
 * Singleton
 */
public class Singleton {

    private static Singleton instance;

    private static Login login;

    private static String server;
    private static String username;
    private static String password;

    private Thing activeThing;

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

    public static String getServer() {
        return server;
    }

    public static void setServer(String server) {
        Singleton.server = server;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Singleton.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Singleton.password = password;
    }

    public Thing getActiveThing() {
        return activeThing;
    }

    public void setActiveThing(Thing activeThing) {
        this.activeThing = activeThing;
    }
}
