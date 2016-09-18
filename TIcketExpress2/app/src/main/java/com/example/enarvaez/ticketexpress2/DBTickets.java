package com.example.enarvaez.ticketexpress2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dark-legion on 28/08/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class DBTickets - clase que controla las operaciones CRUD de la base de datos interna del dispositivo
public class DBTickets extends SQLiteOpenHelper{

    SQLiteDatabase db;
    String sqlcreate = "CREATE TABLE Ticket (id INTEGER PRIMARY KEY AUTOINCREMENT, codigo text, timestamp real, bus_id text, ticket_id text)";
    String sqlcreate2 = "CREATE TABLE Ticket (id INTEGER PRIMARY KEY AUTOINCREMENT, codigo text, timestamp real, bus_id text, ticket_id text)";

    public DBTickets(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlcreate);
        crear_init(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Ticket");
        sqLiteDatabase.execSQL(sqlcreate2);
    }

    public void crear_init(SQLiteDatabase sqLiteDatabase){
        long unixTime = System.currentTimeMillis() / 1000L;
        ContentValues cv = new ContentValues();
        cv.put("codigo", "NOCODE");
        cv.put("timestamp",unixTime);
        cv.put("bus_id","NOBUS");
        cv.put("ticket_id","NOTICKET");
        sqLiteDatabase.insert("Ticket",null, cv);
    }

    public void update_busId(String bus_id){
        this.db.execSQL("UPDATE Ticket SET bus_id='"+bus_id+"' WHERE id=1");
    }

    public void update_codigo(String code){
        long unixTime = System.currentTimeMillis() / 1000L;
        this.db.execSQL("UPDATE Ticket SET codigo='"+code+"', timestamp="+unixTime+" WHERE id=1");
    }

    public void update_Ticket(String ticket){
        this.db.execSQL("UPDATE Ticket SET ticket_id='"+ticket+"' WHERE id=1");
    }

    public Cursor getTicket() {
        this.db = this.getReadableDatabase();
        String[] args = new String[]{Integer.toString(1)};
        Cursor c = this.db.rawQuery("SELECT * FROM Ticket WHERE id=?",args);
        c.moveToFirst();
        return c;
    }

    public String getCodigoQR_DB() {
        this.db = this.getReadableDatabase();
        String[] args = new String[]{Integer.toString(1)};
        Cursor c = this.db.rawQuery("SELECT * FROM Ticket WHERE id=?",args);
        c.moveToFirst();
        String aux = c.getString(1);
        c.close();
        return aux;
    }


    public void drop_base(boolean val){
        if(val)
            this.db.execSQL("DROP TABLE IF EXISTS Ticket");
    }
}
