package com.example.enarvaez.ticketexpress2;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by dark-legion on 17/09/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class DownloadTask - clase asyncrona que permite la descarga desde un URL
//                      utiliza la clase DownUrlDirections en su hilo de ejecucion
public class DownloadTask extends AsyncTask<String, Void, String> {

    // Downloading data in non-ui thread
    @Override
    protected String doInBackground(String... url) {

        // For storing data from web service
        String data = "";

        try{
            // Fetching the data from web service
            data = new DownUrlDirections().downloadUrl(url[0]);
        }catch(Exception e){
            Log.e("DownloadTask Error",e.getMessage());
        }
        return data;
    }

    // Executes in UI thread, after the execution of
    // doInBackground()
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

    }
}
