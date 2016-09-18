package com.example.enarvaez.ticketexpress2;

/**
 * Created by dark-legion on 01/09/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class ThingSpeakClass - representa la respuesta que se obtiene de una peticion a thingspeak
public class ThingSpeakClass {

    String created_at;
    Integer entry_id;
    String field1;
    String field2;
    Integer field3;
    Integer field4;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Integer getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(Integer entry_id) {
        this.entry_id = entry_id;
    }

    public String getLatitud() {
        return field1;
    }

    public void setLatitud(String field1) {
        this.field1 = field1;
    }

    public String getLongitud() {
        return field2;
    }

    public void setLongitud(String field2) {
        this.field2 = field2;
    }

    public Integer getIdBus() {
        return field3;
    }

    public void setIdBus(Integer field3) {
        this.field3 = field3;
    }

    public Integer getField4() {
        return field4;
    }

    public void setField4(Integer field4) {
        this.field4 = field4;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public Integer getField3() {
        return field3;
    }

    public void setField3(Integer field3) {
        this.field3 = field3;
    }
}
