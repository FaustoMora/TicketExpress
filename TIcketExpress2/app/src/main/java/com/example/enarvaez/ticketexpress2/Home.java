package com.example.enarvaez.ticketexpress2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Objects;
/**
 * Created by dark-legion on 28/08/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class Home - controla la interfaz de bienvenida, el inicio al menu y valida entrada de nuevos usuarios
public class Home extends AppCompatActivity {

    Button btn_iniciar;
    DBTickets db_tk;
    boolean  existe_en_base = false;


    public void initDB(){
        db_tk = new DBTickets(this,"DBTickets2",null,1);
    }

    public boolean existe_en_base(){
        Cursor c = db_tk.getTicket();
        if(c.isFirst()) {
            String codigo = c.getString(4);
            if (Objects.equals(codigo, "NOTICKET")) { //no existe en la base restdb.io

                try {
                    String ticket_id = new Http_POSTTicket_Task().execute().get(); //creo el registro en la base rest
                    if (ticket_id != null) { //guardo en base de datos local
                        db_tk.update_Ticket(ticket_id);
                        return false;
                    }

                } catch (Exception e) {
                    Log.e("test", e.getMessage());
                    finish();
                }

            }
            return true;
        }
        finish();
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        initDB();
        existe_en_base = existe_en_base();

        btn_iniciar = (Button)findViewById(R.id.btn_iniciar);
        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    if(!existe_en_base){
                        if (OnlineConnectClass.isOnline(getApplicationContext())){
                            startActivity(new Intent(getApplicationContext(),reserva.class));
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"SIN CONEXION A INTERNET",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        startActivity(new Intent(getApplicationContext(),menu.class));
                        finish();
                    }

            }
        });
    }
}
