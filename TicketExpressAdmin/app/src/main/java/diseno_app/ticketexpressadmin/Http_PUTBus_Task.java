package diseno_app.ticketexpressadmin;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.net.URI;

/**
 * Created by dark-legion on 06/09/16.
 */
/**
 * Autores:
 *  Fausto Mora 
 *  Ericka Narvaez
 */
// Class Http_PUTBus_Task - clase asyncrona que permite peticon PUT a la tabla Buses de la base restdb.io
public class Http_PUTBus_Task extends AsyncTask<BusClass,Integer,Boolean>{

    String queryURL = "https://ticketsexpress-c8f9.restdb.io/rest/buses/";
    String apikey = "57c34a17bf92580e031fead8";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(BusClass... buses) {
        BusClass bus = buses[0];
        String URL = queryURL+bus.get_id()+"?apikey="+apikey;
        JsonBus jsonBus = new JsonBus(bus.get_id(),45,bus.getBus_num(),bus.getTiempo_salida(),bus.getRuta());

        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        try {
            rest.put(new URI(URL),jsonBus);
            return true;
        }catch (Exception e){
            Log.e("test",e.getMessage());
        }

        return false;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
