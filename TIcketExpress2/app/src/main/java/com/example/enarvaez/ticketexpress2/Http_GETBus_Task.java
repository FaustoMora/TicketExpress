package com.example.enarvaez.ticketexpress2;

import android.os.AsyncTask;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


/**
 * Created by dark-legion on 28/08/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class Http_GETBus_Task - clase asyncrona que maneja la peticion GET para la tabla Buses de la base en restdb.io
public class Http_GETBus_Task extends AsyncTask<String,Integer,BusClass> {


    String queryURL = "https://ticketsexpress-c8f9.restdb.io/rest/buses/";
    String apikey = "57c34a17bf92580e031fead8";

    @Override
    protected BusClass doInBackground(String... id) {
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        try	{
            String URL = queryURL+id[0]+"?apikey="+apikey;
            BusClass bus = rest.getForObject(URL, BusClass.class);

            return bus;
        }
        catch (Exception e) {
        }
        return null;
    }
}
