package com.example.enarvaez.ticketexpress2;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.android.gms.maps.model.LatLng;
/**
 * Created by dark-legion on 17/09/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class DownUrlDirections - realiza la peticion al api de google map recibiendo como 
//                           entrada una coordenada y entrega de salida una respuesta json 
public class DownUrlDirections {

    public String getDirectionsUrl(LatLng origin){
        String lat = Double.toString(origin.latitude);
        String lon = Double.toString(origin.longitude);
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin="+lat+","+lon+"&destination=-2.144989720987703,-79.96525928378105&mode=driving&waypoints=-2.164838,-79.923452|-2.166824,-79.934696|-2.163827,-79.945741|-2.148040,-79.966670";

        return url;
    }

    public String getTimmingUrl(LatLng origin){
        String lat = Double.toString(origin.latitude);
        String lon = Double.toString(origin.longitude);
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+lat+","+lon+"&destinations=-2.144989720987703,-79.96525928378105&mode=driving&language=es&waypoints=via:-2.164838,-79.923452|via:-2.166824,-79.934696|via:-2.163827,-79.945741|via:-2.148040,-79.966670&key=AIzaSyD9OCSIkXxB4SkFE0VIZNLeE4fbXE13nfY";

        return url;
    }

    /** A method to download json data from url */
    public String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb  = new StringBuffer();

            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.e("Error al descargar url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
}
