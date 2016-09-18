package com.example.enarvaez.ticketexpress2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by dark-legion on 31/08/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
//class OnlineConnectClass - determina si la aplicacion tiene conexion a internet
public class OnlineConnectClass {

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
