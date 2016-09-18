package com.example.enarvaez.ticketexpress2;


import android.os.AsyncTask;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dark-legion on 17/09/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
//Class ParserTask - clase asyncrona que controla el parseo de la respuesta del api de google map
public class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> > {

    // Parsing the data in non-ui thread
    @Override
    public List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

        JSONObject jObject;
        List<List<HashMap<String, String>>> routes = null;

        try{
            jObject = new JSONObject(jsonData[0]);
            DirectionsJSONParser parser = new DirectionsJSONParser();

            // Starts parsing data
            routes = parser.parse(jObject);
        }catch(Exception e){
            e.printStackTrace();
        }
        return routes;
    }

}
