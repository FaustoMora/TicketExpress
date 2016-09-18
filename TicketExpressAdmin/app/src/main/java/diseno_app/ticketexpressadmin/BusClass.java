package diseno_app.ticketexpressadmin;

import java.io.Serializable;

/**
 * Created by dark-legion on 06/09/16.
 */
/**
 * Autores:
 *  Fausto Mora 
 *  Ericka Narvaez
 */
// Class BusClass - clase que mapea la tabla Buses de la base de datos alojada en restdb.io
public class BusClass implements Serializable{

    private String _id;
    private Integer capacidad;
    private Integer bus_num;
    private String tiempo_salida;
    private String ruta;


    public BusClass() {

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getBus_num() {
        return bus_num;
    }

    public void setBus_num(Integer bus_num) {
        this.bus_num = bus_num;
    }

    public String getTiempo_salida() {
        return tiempo_salida;
    }

    public void setTiempo_salida(String tiempo_salida) {
        this.tiempo_salida = tiempo_salida;
    }

    public String getlbl_uno(){
        return "Bus"+this.getBus_num().toString()+" - Capacidad:"+this.getCapacidad().toString();
    }

    public String getlbl_dos(){
        return "Salida:"+this.getTiempo_salida();
    }

    public String getRuta() { return ruta; }

    public void setRuta(String ruta) { this.ruta = ruta; }
}
