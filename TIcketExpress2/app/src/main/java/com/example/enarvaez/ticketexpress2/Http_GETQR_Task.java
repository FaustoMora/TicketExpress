package com.example.enarvaez.ticketexpress2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by dark-legion on 28/08/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class Http_GETQR_Task - clase asyncrona que maneja la peticion GET para la obtencion del codigo QR
public class Http_GETQR_Task extends AsyncTask<String,Integer,String>{

    String query = "https://chart.googleapis.com/chart?chs=150x150&cht=qr&chl=";

    @Override
    protected String doInBackground(String... strings) {
        String codigo = strings[0];
        String url = query + codigo;
        HttpClient cliente = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = null;

        try {
            response = cliente.execute(request);
            StatusLine status = response.getStatusLine();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //instrucciones para la imagen
        HttpEntity responseEntity = response.getEntity();
        BufferedHttpEntity httpEntity = null;
        try {
            httpEntity = new BufferedHttpEntity(responseEntity);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        InputStream imageStream = null;
        try {
            imageStream = httpEntity.getContent();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //creando la imagen
        Bitmap bmp = BitmapFactory.decodeStream(imageStream);
        String codigoString = new BitMap_String().BitMapToString(bmp);
        return codigoString;
    }

}
