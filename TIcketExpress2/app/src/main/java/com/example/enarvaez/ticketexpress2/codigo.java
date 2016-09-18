package com.example.enarvaez.ticketexpress2;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by dark-legion on 28/08/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class codigo - permite la visualizacion del codigo QR
public class codigo extends AppCompatActivity {
    DBTickets db_tk;
    ImageView img_codigo;

    public void initDB(){
        db_tk = new DBTickets(this,"DBTickets2",null,1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo);
        initDB();
        init();
    }

    public void init(){
        Cursor c = this.db_tk.getTicket();
        if (c.isFirst()){
            Bitmap bmp = new BitMap_String().StringToBitMap(c.getString(1));
            img_codigo =(ImageView)findViewById(R.id.codigo_QR);
            img_codigo.setImageBitmap(bmp);
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
