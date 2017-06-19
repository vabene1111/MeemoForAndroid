package de.droidenschmiede.meemoforandroid.helper;

import de.droidenschmiede.meemoforandroid.objects.Login;

/**
 * Created by vabene1111 on 19.06.2017.
 * Singleton
 */
public class Singleton {

    private static Singleton instance;

    private static Login login;


    public static final String server = "https://notes.ekaack.de";
    public static final String username = "vabene";
    public static final String password = "nhZExeeiWuLCEv6TRw3y";

    private Singleton () {}


    public static Singleton getInstance () {
        if (Singleton.instance == null) {
            Singleton.instance = new Singleton ();
        }
        return Singleton.instance;
    }
}
