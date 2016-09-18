package com.example.enarvaez.ticketexpress2;

import android.os.AsyncTask;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dark-legion on 10/09/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class Http_POSTTicket_Task - clase asyncrona que maneja la peticion POST a la tabla Ticket en la base de restdb.io
public class Http_POSTTicket_Task extends AsyncTask<Void,Integer,String>{

    String queryURL = "https://ticketsexpress-c8f9.restdb.io/rest/tickets";
    String apikey = "a19ffb7487cf8cb2ce58dc1b01b7a6f907793";

    @Override
    protected String doInBackground(Void... voids) {
        Long timestamp = System.currentTimeMillis() / 1000L;
        String URL = queryURL+"?apikey="+apikey;

        JsonTicket json = new JsonTicket(timestamp.toString(),false);

        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        JsonTicket ticket = rest.postForObject(URL, json, JsonTicket.class);
        if(ticket != null)
            return ticket.get_id();

        return null;

    }
}
