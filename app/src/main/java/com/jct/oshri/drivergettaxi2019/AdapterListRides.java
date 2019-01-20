package com.jct.oshri.drivergettaxi2019;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.jct.oshri.drivergettaxi2019.model.entities.Ride;

import java.util.List;


public class AdapterListRides extends ArrayAdapter<Ride> {

    private Context context;
    public List<Ride> ridesList;
    public List<Ride> originRidesList;

    public AdapterListRides(List<Ride> ridesList, Context context) {
        super(context, R.layout.fragment_waiting_rides, ridesList);
        this.context = context;
        this.ridesList = ridesList;
        this.originRidesList = ridesList;
    }

    @Override
    public int getCount() {
        return ridesList.size();
    }


    public Ride getItem(int position) {
        return ridesList.get(position);
    }

    public long getItemId(int position) {
        return ridesList.get(position).hashCode();
    }

    public void resetData() {
        ridesList = originRidesList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        PlanetHolder holder = new PlanetHolder();

        // First let's verify the convertView is not null
        if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.img_row_layout, null);
            // Now we can fill the layout with the right values
            TextView tv = (TextView) v.findViewById(R.id.name);
            TextView distView = (TextView) v.findViewById(R.id.dist);


            holder.planetNameView = tv;
            holder.distView = distView;

            v.setTag(holder);
        } else
            holder = (PlanetHolder) v.getTag();

        Planet p = planetList.get(position);
        holder.planetNameView.setText(p.getName());
        holder.distView.setText("" + p.getDistance());


        return v;
    }

}
