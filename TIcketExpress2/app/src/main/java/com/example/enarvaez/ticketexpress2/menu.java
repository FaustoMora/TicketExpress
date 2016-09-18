package com.example.enarvaez.ticketexpress2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.model.LatLng;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Created by dark-legion on 28/08/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class menu - clase principal que controla permite el acceso a las demas interfaces y muestra el tiempo
//              de llegada del bus
public class menu extends AppCompatActivity implements Runnable{

    DBTickets db_tk;
    Button btn_mapa;
    Button btn_codigo;
    TextView lbl_hora_salida;
    static TextView lbl_hora_llegada;
    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();;
    static boolean activityVisible = false;
    static List<List<HashMap<String, String>>> result = null;



    public void initDB(){
        db_tk = new DBTickets(this,"DBTickets2",null,1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initDB();
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                       setLabelTime();
                    }
                });
            }
        }, 1, 60, TimeUnit.SECONDS);

        constructores();

        btn_codigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),codigo.class));
            }
        });
        btn_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(OnlineConnectClass.isOnline(getApplicationContext())) {
                    startActivity(new Intent(getApplicationContext(), mapa_google.class));
                }else{
                    Toast.makeText(getApplicationContext(),"Conexion a Internet no disponible",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void run() {
        this.setLabelTime();
    }


    public void constructores(){
        btn_codigo = (Button)findViewById(R.id.btn_codigo);
        btn_mapa = (Button)findViewById(R.id.btn_mapa);
        lbl_hora_salida = (TextView)findViewById(R.id.lbl_salida);
        lbl_hora_llegada = (TextView)findViewById(R.id.lbl_llegada);

        String bus_id = getDB_busId();
        BusClass bus = getBus(bus_id);

        if (bus != null) {
            lbl_hora_salida.setText(bus.getTiempo_salida());
        }

    }

    public String getDB_busId(){
        Cursor c = this.db_tk.getTicket();
        boolean val = c.isNull(0);
        if(c.moveToFirst()){
            String aux = c.getString(3);
            c.close();
            return aux;
        }
        return null;
    }

    public BusClass getBus(String id){
        try {
            return new Http_GETBus_Task().execute(id).get();
        }catch (Exception e){
            return null;
        }
    }

    public void setLabelTime(){
        Log.e("text","holi labeltime");
        ThingSpeakClass thingSpeakClass = new ThingSpeakClass();
        try {
            thingSpeakClass = new Http_ThingSpeak_Task().execute().get();
        }catch (Exception e){
            Log.e("error en get",e.getMessage());
        }

        LatLng point = CoordinateConverter.convert(thingSpeakClass.getLatitud(),thingSpeakClass.getLongitud());
        String url = new DownUrlDirections().getDirectionsUrl(point);
        DownloadTask downloadTask = new DownloadTask();


            try {
                String down = downloadTask.execute(url).get();
                ParserTask parserTask = new ParserTask();

                result = parserTask.execute(down).get();
            }catch(Exception e){
                Log.e("Error en downloadTask",e.getMessage());
            }

        if(result != null ) {

            List<HashMap<String, String>> path = result.get(0);

            HashMap<String, String> pointer = path.get(1);
            String aux = pointer.get("duration");

            if(aux != null){
                Log.e("text",aux);
                lbl_hora_llegada.setText(aux);
            }

        }
    }

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.activityResumed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(id) {
            case R.id.action_reservar:
                startActivity(new Intent(getApplicationContext(), reserva.class));
                break;
            case R.id.action_codigo:
                startActivity(new Intent(getApplicationContext(), codigo.class));
                break;
            case R.id.action_mapa:
                startActivity(new Intent(getApplicationContext(), mapa_google.class));
                break;
            default:
                break;
        }

        return  super.onOptionsItemSelected(item);
    }

}
