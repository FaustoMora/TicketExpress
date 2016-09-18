package com.example.enarvaez.ticketexpress2;

import android.content.Intent;
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
// Class Http_GETTicket_Task - clase asyncrona que maneja la peticion GET a la tabla Ticket en la base de restdb.io
public class Http_GETTicket_Task extends AsyncTask<String,Integer,TicketClass> {

    String queryURL = "https://ticketsexpress-c8f9.restdb.io/rest/tickets/";
    String apikey = "a19ffb7487cf8cb2ce58dc1b01b7a6f907793";

    @Override
    protected TicketClass doInBackground(String... strings) {
        String URL = queryURL+strings[0]+"?apikey="+apikey;
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        try	{
            TicketClass ticket = rest.getForObject(URL, TicketClass.class);
            return ticket;
        }
        catch (Exception e) {
        }
        return null;
    }
}
