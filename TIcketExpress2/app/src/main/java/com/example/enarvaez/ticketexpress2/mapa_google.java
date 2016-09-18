package com.example.enarvaez.ticketexpress2;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dark-legion on 28/08/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class mapa_google - permite la visualizacion del fragmente de google map, junto con la posicion del bus y la ruta
public class mapa_google extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<LatLng> markerPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_google);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        // Getting Map for the SupportMapFragment
        mapFragment.getMapAsync(this);
        markerPoints = new ArrayList<LatLng>();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ThingSpeakClass thingSpeakClass = new ThingSpeakClass();
        try {
            thingSpeakClass = new Http_ThingSpeak_Task().execute().get();
        }catch (Exception e){
            Log.e("error en get",e.getMessage());
        }

        // Initializing
        LatLng point = CoordinateConverter.convert(thingSpeakClass.getLatitud(),thingSpeakClass.getLongitud());
        //paradero de metro espol -2.144989720987703,-79.96525928378105
        LatLng point2 = new LatLng(-2.144989720987703,-79.96525928378105);

        // Adding new item to the ArrayList
        markerPoints.add(point);

        // Creating MarkerOptions
        MarkerOptions options = new MarkerOptions();
        MarkerOptions options2 = new MarkerOptions();
        // Setting the position of the marker
        options.position(point);
        options2.position(point2);

        /**
         * For the start location, the color of marker is GREEN and
         * for the end location, the color of marker is RED.
         */
        if(markerPoints.size()==1){
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            markerPoints.add(point2);
        }
        if(markerPoints.size()==2){
            options2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        }

        // Add new marker to the Google Map Android API V2
        this.mMap.addMarker(options);
        this.mMap.addMarker(options2);


        if(menu.result != null ){
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            for(int i=0;i<menu.result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = menu.result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> pointer = path.get(j);

                    if(pointer.get("lat") != null && pointer.get("lng") != null){
                        double lat = Double.parseDouble(pointer.get("lat"));
                        double lng = Double.parseDouble(pointer.get("lng"));
                        LatLng position = new LatLng(lat, lng);

                        points.add(position);
                    }
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(4);
                lineOptions.color(Color.BLUE);
            }

            // Drawing polyline in the Google Map for the i-th route
            mMap.addPolyline(lineOptions);

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,15));

        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
