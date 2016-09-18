package com.example.enarvaez.ticketexpress2;

import android.os.AsyncTask;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dark-legion on 01/09/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class Http_GETBuses_Task - clase asyncrona que maneja la peticion GET para una lista de Buses de la base en restdb.io
public class Http_GETBuses_Task extends AsyncTask<Void,Integer,ResponseEntity>{

    String queryURL = "https://ticketsexpress-c8f9.restdb.io/rest/buses";
    String apikey = "57c34a17bf92580e031fead8";

    @Override
    protected ResponseEntity doInBackground(Void... voids) {
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        String URL = queryURL+"?apikey="+apikey;

        ResponseEntity<BusClass[]> responseEntity = rest.getForEntity(URL, BusClass[].class);

        return  responseEntity;
    }
}
