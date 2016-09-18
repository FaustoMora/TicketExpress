package com.example.enarvaez.ticketexpress2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by dark-legion on 06/09/16.
 */
/**
 * Autores:
 *     Fausto Mora
 *     Ericka Narvaez
 */
// Class BusesAdapter - permite la presentacion de los buses como una lista en el layout_reserva
public class BusesAdapter extends ArrayAdapter<BusClass>{

    BusClass[] buses;

    public BusesAdapter(Context context, BusClass[] buses) {
        super(context, R.layout.custom_row ,buses);
        this.buses = buses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layout = LayoutInflater.from(getContext());
        View customView = layout.inflate(R.layout.custom_row,parent,false);

        BusClass bus = getItem(position);
        TextView lbl_bus_uno = (TextView) customView.findViewById(R.id.lbl_bus_uno);

        lbl_bus_uno.setText(bus.getlbl_uno());

        return customView;
    }


}
