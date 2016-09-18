package com.example.enarvaez.ticketexpress2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import java.io.ByteArrayOutputStream;

/**
 * Created by dark-legion on 18/08/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class BitMap_String - permite la conversion de un bitmap a string y viceversa
public class BitMap_String {

    //Bitmap to String:
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        String result= Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        return result;
    }


    //String to Bitmap:
    public Bitmap StringToBitMap(String image){
        try{
            byte [] decodedBytes=Base64.decode(image.getBytes(),Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}
