package com.example.enarvaez.ticketexpress2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by dark-legion on 28/08/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class reserva - controla la interfaz para la adquisicion de tickets en los buses disponibles para la RUTA ALBAN
public class reserva extends AppCompatActivity {

    DBTickets db_tk;
    TextView lbl_bus_info_ruta;
    ListView list_buses;

    public void initDB(){
        db_tk = new DBTickets(this,"DBTickets2",null,1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        initDB();
        lbl_bus_info_ruta = (TextView)findViewById(R.id.lbl_bus_info_ruta);
        list_buses = (ListView)findViewById(R.id.lst_buses);

        init();
    }

    public void init(){
        BusClass[] buses = getBusesThingSpeak();

        ListAdapter buses_adapter = new BusesAdapter(this,buses);
        list_buses = (ListView)findViewById(R.id.lst_buses);
        list_buses.setAdapter(buses_adapter);

        list_buses.setClickable(true);
        list_buses.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String aux = String.valueOf(((BusClass)adapterView.getItemAtPosition(i)).getBus_num());
                BusClass bus = (BusClass)adapterView.getItemAtPosition(i);
                TicketClass ticket = getTicket();

                    if(tiene_ticket_paraBus(bus.get_id(),ticket)) {
                        Toast.makeText(getApplicationContext(), "Ya cuentas con un ticket para este bus", Toast.LENGTH_SHORT).show();
                    }else{
                        if(hay_disponibilidad(bus)){
                            if(!tiene_ticket_consumado(bus.get_id(),ticket) && !Objects.equals(ticket.getBus_id(),"NOBUS")) {
                                boolean aux= reverse_ticket(ticket.getBus_id());
                            }

                            String QR = obtenerTicketQR(bus);
                            if(QR != null){
                                boolean success = actualizar_RestDB(bus,ticket,QR);
                                if (success){
                                    startActivity(new Intent(getApplicationContext(),menu.class));
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Ocurrio un error al momento de reservar", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getApplicationContext(), "Ocurrio un error al momento de reservar", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"Tickets Agotado... Intentelo más tarde",Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });
    }

    public TicketClass getTicket(){
        Cursor c = this.db_tk.getTicket();
        if(c.isFirst()) {
            try {
                String aux = c.getString(4);
                c.close();
                return new Http_GETTicket_Task().execute(aux).get();
            } catch (Exception e) {
                Log.e("test",e.getMessage());
            }
        }
        return null;
    }

    public boolean tiene_ticket_paraBus(String id_bus,TicketClass ticket){
        if(Objects.equals(id_bus,ticket.getBus_id()))
            return !ticket.esta_consumido;
        return false;
    }

    public boolean tiene_ticket_consumado(String id_bus,TicketClass ticket){
        if(!Objects.equals(id_bus,ticket.getBus_id()))
            return ticket.esta_consumido;
        return true;
    }


    public boolean reverse_ticket(String id_last_bus){
        try {
            BusClass last_bus = new Http_GETBus_Task().execute(id_last_bus).get();
           return new Http_REPUTBus_Task().execute(last_bus).get();
        }catch (Exception e){
            Log.e("test",e.getMessage());
        }
        return false;
    }

    public boolean actualizar_RestDB(BusClass bus,TicketClass ticket,String QR){
        try {
            boolean success = new Http_PUTBus_Task().execute(bus).get();
            if(success) {
                this.db_tk.update_busId(bus.get_id());
                ticket.setBus_id(bus.get_id());
                ticket.setCodigo_timestamp(QR);
                return new Http_PUTTicket_Task().execute(ticket).get();
            }
        }catch (Exception e){
            Log.e("test",e.getMessage());
        }
        return false;
    }

    public boolean hay_disponibilidad(BusClass bus){
        try{
            return (bus.getCapacidad()>=1);
        }catch(Exception e){
            return false;
        }
    }


    public String obtenerTicketQR(BusClass bus){
        Long unixTime = System.currentTimeMillis() / 1000L;
        String raw_code = bus.getBus_num().toString() + "_" + bus.getCapacidad().toString() + "_" + unixTime.toString();
        String codigo_QR = getQRCodigo(raw_code);
        if (codigo_QR != null) {
            this.db_tk.update_codigo(codigo_QR);
            return  raw_code;
        }
        return null;
    }


    public String getQRCodigo(String raw_code){
        try {
            return new Http_GETQR_Task().execute(raw_code).get();
        }catch (Exception e){
            return null;
        }
    }

    public BusClass[] getBusesThingSpeak(){
        try {
            ThingSpeakClass thing = new Http_ThingSpeak_Task().execute().get();
            Integer id_bus = thing.getIdBus();
            id_bus = 1;

            //ahora procederemos a buscar los buses disponibles en restdb
            ResponseEntity<BusClass[]> lstbuses = new Http_GETBuses_Task().execute().get();
            BusClass[] buses = new BusClass[]{};


            for (BusClass bus:lstbuses.getBody()) {
                if(bus.getBus_num()==id_bus)
                    buses = appendValue(buses, bus);
            }
            return buses;

        }catch (Exception e){
            Log.e("test",e.getMessage());
        }
        return null;
    }

    private BusClass[] appendValue(BusClass[] obj, BusClass newObj) {

        ArrayList<BusClass> temp = new ArrayList<BusClass>(Arrays.asList(obj));
        temp.add(newObj);
        return temp.toArray(new BusClass[]{});

    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
