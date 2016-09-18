package com.example.enarvaez.ticketexpress2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by dark-legion on 10/09/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class JsonTicket - representa la tabla Tickets de la base restdb.io en formato Json
//                    usado para realizar peticiones PUT y POST
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "_id",
        "timestamp",
        "estado_ticket",
        "bus_id"
})

public class JsonTicket {

    @JsonProperty("_id")
    private String _id;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("esta_consumido")
    private  Boolean esta_consumido;
    @JsonProperty("bus_id")
    private String bus_id;
    @JsonProperty("codigo_timestamp")
    private String codigo_timestamp;

    public JsonTicket(){}

    public JsonTicket(String id, String timestamp, Boolean esta_consumido, String bus_id, String codigo_timestamp) {
        this._id = id;
        this.timestamp = timestamp;
        this.esta_consumido = esta_consumido;
        this.bus_id = bus_id;
        this.codigo_timestamp = codigo_timestamp;
    }

    public JsonTicket(String timestamp, Boolean estado_ticket) {
        this.timestamp = timestamp;
        this.esta_consumido = estado_ticket;
        this.bus_id = "NOBUS";
        this.codigo_timestamp = "NODATA";
    }

    @JsonProperty("_id")
    public String get_id() {
        return _id;
    }

    @JsonProperty("_id")
    public void set_id(String _id) {
        this._id = _id;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("esta_consumido")
    public Boolean getEsta_consumido() {
        return esta_consumido;
    }

    @JsonProperty("esta_consumido")
    public void setEsta_consumido(Boolean esta_consumido) {
        this.esta_consumido = esta_consumido;
    }

    @JsonProperty("bus_id")
    public String getBus_id() {
        return bus_id;
    }

    @JsonProperty("bus_id")
    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    @JsonProperty("codigo_timestamp")
    public String getCodigo_timestamp() {
        return codigo_timestamp;
    }

    @JsonProperty("codigo_timestamp")
    public void setCodigo_timestamp(String codigo_timestamp) {
        this.codigo_timestamp = codigo_timestamp;
    }
}
