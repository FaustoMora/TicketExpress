package com.example.enarvaez.ticketexpress2;

import android.os.AsyncTask;
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
// Class Http_ThingSpeak_Task - clase asyncrona que maneja la peticion GET a thingspeak
public class Http_ThingSpeak_Task extends AsyncTask<Void,Integer,ThingSpeakClass>{
    String url = "http://api.thingspeak.com/channels/147034/feeds/last.json?results=1";

    @Override
    protected ThingSpeakClass doInBackground(Void... voids) {
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        try	{
            ThingSpeakClass thing = rest.getForObject(url, ThingSpeakClass.class);
            return thing;
        }
        catch (Exception e) {
        }
        return null;
    }
}
