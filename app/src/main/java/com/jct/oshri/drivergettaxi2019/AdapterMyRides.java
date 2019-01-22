package com.jct.oshri.drivergettaxi2019;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.jct.oshri.drivergettaxi2019.model.entities.Driver;
import com.jct.oshri.drivergettaxi2019.model.entities.Ride;

import java.util.List;

public class AdapterMyRides extends ArrayAdapter<Ride> {

    private Context context;
    public List<Ride> ridesList;
    public List<Ride> originRidesList;
    private FragmentManager afragment;
    Driver driver;

    public AdapterMyRides(List<Ride> list, Context context, FragmentManager fragment) {
        super(context, R.layout.item_list, list);
        this.context = context;
        this.ridesList = list;
        afragment = fragment;
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

    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final AdapterMyRides.RideHolder holder;

        // First let's verify the convertView is not null
        if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.my_ride_list, null);
            // Now we can fill the layout with the right values
            holder = new AdapterMyRides.RideHolder();
            holder.nameCustomer = (TextView) v.findViewById(R.id.fullNameCustomer_myRides);
            holder.emailCustomer = (TextView) v.findViewById(R.id.email_cus_myRides);
            holder.phoneCustomer = (TextView) v.findViewById(R.id.phone_cus_myRides);
            holder.timeRide = (TextView) v.findViewById(R.id.timeRide_myRides);
            holder.sourceRide =(TextView) v.findViewById(R.id.source_cus_myRides);
            holder.destRide =(TextView) v.findViewById(R.id.dest_cus_myRides);



            v.setTag(holder);
        } else
            holder = (AdapterMyRides.RideHolder) v.getTag();

        holder.nameCustomer.setText("Name: " + ridesList.get(position).getNameOfClient());
        holder.emailCustomer.setText("Email: " + ridesList.get(position).getEmailOfClient());
        holder.phoneCustomer.setText("Phone Number: " + ridesList.get(position).getPhoneNumberOfClient());
        holder.timeRide.setText("Time: " + ridesList.get(position).getStartTime());
        holder.sourceRide.setText("Source: " + ridesList.get(position).getSource());
        holder.destRide.setText("Destination: " + ridesList.get(position).getDest());

        return v;
    }



    private static class RideHolder {
        public TextView nameCustomer;
        public TextView emailCustomer;
        public TextView phoneCustomer;
        public TextView timeRide;
        public TextView sourceRide;
        public TextView destRide;

    }

}
