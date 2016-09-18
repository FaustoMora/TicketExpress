package com.example.enarvaez.ticketexpress2;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by dark-legion on 15/09/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class CoordinateConverter - permite la conversion de las coordenadas recividas por thingspeak en
//							   coordenadas gms validas para las peticiones al api google maps
class CoordinateConverter{
    public static LatLng convert(double lat,double lon){
        double latitud =-1*(Math.floor(lat/100)+((lat/100-Math.floor(lat/100))*100)/60);
        double longitud = -1*(Math.floor(lon/100)+((lon/100-Math.floor(lon/100))*100)/60);
        return new LatLng(latitud,longitud);
    }

    public static LatLng convert(String lat,String lon){
        return convert(Double.parseDouble(lat),Double.parseDouble(lon));
    }

}