package com.example.enarvaez.ticketexpress2;

import android.os.AsyncTask;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by dark-legion on 11/09/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class Http_PUTTicket_Task - clase asyncrona que maneja la peticion PUT de la tabla Ticket en la base de restdb.io
public class Http_PUTTicket_Task extends AsyncTask<TicketClass,Integer,Boolean>{

    String queryURL = "https://ticketsexpress-c8f9.restdb.io/rest/tickets/";
    String apikey = "a19ffb7487cf8cb2ce58dc1b01b7a6f907793";

    @Override
    protected Boolean doInBackground(TicketClass... ticket) {
        TicketClass tick = ticket[0];
        String URL = queryURL+tick.get_id()+"?apikey="+apikey;

        JsonTicket json = new JsonTicket(tick.get_id(),tick.getTimestamp(),false,tick.getBus_id(),tick.getCodigo_timestamp());

        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        try {
            rest.put(new URI(URL),json);
            return true;
        }catch (Exception e){
        }

        return false;
    }
}
