package diseno_app.ticketexpressadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Autores:
 *  Fausto Mora 
 *  Ericka Narvaez
 */
// Class MainActivity - actividad que controla la interfaz principal de la aplicacion
public class MainActivity extends AppCompatActivity {
    ListView list_buses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    public void init(){
        BusClass[] buses = getBuses();

        ListAdapter buses_adapter = new BusesAdapter(this,buses);
        list_buses = (ListView)findViewById(R.id.list_buses);
        list_buses.setAdapter(buses_adapter);

        list_buses.setClickable(true);
        list_buses.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String aux = String.valueOf(((BusClass)adapterView.getItemAtPosition(i)).getBus_num());
                BusClass bus = (BusClass)adapterView.getItemAtPosition(i);
                boolean save = false;
                try {
                    save = new Http_PUTBus_Task().execute(bus).get();
                } catch (Exception e) {
                    save = false;
                }
                if(save) {
                    Toast.makeText(getApplicationContext(), "Bus No" + aux + " actualizado", Toast.LENGTH_SHORT).show();
                    ((TextView)view.findViewById(R.id.lbl_bus_uno)).setText("Bus"+aux+" - Capacidad:45");
                }
            }
        });
    }


    public BusClass[] getBuses(){
        try {
            //ahora procederemos a buscar los buses disponibles en restdb
            Http_GETBuses_Task hilo =  new Http_GETBuses_Task();
            return hilo.execute().get();

        }catch (Exception e){
            Log.d("test",e.getMessage());
        }
        return null;
    }

}
