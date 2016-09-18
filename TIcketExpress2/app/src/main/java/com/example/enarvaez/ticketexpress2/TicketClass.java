package com.example.enarvaez.ticketexpress2;



/**
 * Created by dark-legion on 10/09/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class TicketClass - representa a la tabla Tickets de la base en restdb.io
public class TicketClass {
    String _id;
    String timestamp;
    Boolean esta_consumido;
    String bus_id;
    String codigo_timestamp;

    public TicketClass(){}

    public TicketClass(String timestamp, Boolean estado_ticket) {
        this.timestamp = timestamp;
        this.esta_consumido = estado_ticket;
        this.bus_id = "NOBUS";
        this.codigo_timestamp = "NODATA";
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    public Boolean getEsta_consumido() {
        return esta_consumido;
    }

    public void setEsta_consumido(Boolean esta_consumido) {
        this.esta_consumido = esta_consumido;
    }

    public String getCodigo_timestamp() {
        return codigo_timestamp;
    }

    public void setCodigo_timestamp(String codigo_timestamp) {
        this.codigo_timestamp = codigo_timestamp;
    }
}
