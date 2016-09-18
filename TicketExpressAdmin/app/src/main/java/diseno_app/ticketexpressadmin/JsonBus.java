package diseno_app.ticketexpressadmin;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dark-legion on 06/09/16.
 */
/**
 * Autores:
 *  Fausto Mora 
 *  Ericka Narvaez
 */
// Class JsonBus - representa la tabla Buses de la base restdb.io en formato Json
//                  usado para realizar peticiones PUT y POST
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_id",
        "capacidad",
        "bus_num",
        "tiempo_salida",
        "ruta"
})

public class JsonBus {

    @JsonProperty("_id")
    private String id;
    @JsonProperty("capacidad")
    private Integer capcacidad;
    @JsonProperty("bus_num")
    private  Integer bus_num;
    @JsonProperty("tiempo_salida")
    private String tiempo_salida;
    @JsonProperty("ruta")
    private String ruta;

    public JsonBus(String id, Integer capcacidad, Integer bus_num, String tiempo_salida, String ruta) {
        this.id = id;
        this.capcacidad = capcacidad;
        this.bus_num = bus_num;
        this.tiempo_salida = tiempo_salida;
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("capacidad")
    public Integer getCapcacidad() {
        return capcacidad;
    }

    @JsonProperty("capacidad")
    public void setCapcacidad(Integer capcacidad) {
        this.capcacidad = capcacidad;
    }

    @JsonProperty("bus_num")
    public Integer getBus_num() {
        return bus_num;
    }

    @JsonProperty("bus_num")
    public void setBus_num(Integer bus_num) {
        this.bus_num = bus_num;
    }

    @JsonProperty("tiempo_salida")
    public String getTiempo_salida() {
        return tiempo_salida;
    }

    @JsonProperty("tiempo_salida")
    public void setTiempo_salida(String tiempo_salida) {
        this.tiempo_salida = tiempo_salida;
    }

    @JsonProperty("ruta")
    public String getRuta() {
        return ruta;
    }

    @JsonProperty("ruta")
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
