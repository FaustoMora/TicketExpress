package diseno_app.ticketexpressadmin;

import android.os.AsyncTask;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dark-legion on 06/09/16.
 */
/**
 * Autores:
 *  Fausto Mora 
 *  Ericka Narvaez
 */
// Class Http_GETBuses_Task - clase asyncrona que permite peticon GET de una lista de buses
public class Http_GETBuses_Task extends AsyncTask<Void,Integer,BusClass[]>{

    String queryURL = "https://ticketsexpress-c8f9.restdb.io/rest/buses";
    String apikey = "57c34a17bf92580e031fead8";

    @Override
    protected BusClass[] doInBackground(Void... voids) {
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        String URL = queryURL+"?apikey="+apikey;

        ResponseEntity<BusClass[]> responseEntity = rest.getForEntity(URL, BusClass[].class);
        BusClass[] buses = responseEntity.getBody();

        return  buses;
    }

    @Override
    protected void onPostExecute(BusClass[] busClasses) {
        super.onPostExecute(busClasses);
    }
}

