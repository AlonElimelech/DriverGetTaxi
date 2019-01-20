package com.jct.oshri.drivergettaxi2019;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jct.oshri.drivergettaxi2019.model.entities.Ride;

import java.util.List;


public class AdapterListRides extends ArrayAdapter<Ride> {

    private Context context;
    public List<Ride> ridesList;
    public List<Ride> originRidesList;
    private  FragmentManager afragment;
/*
    public AdapterListRides(List<Ride> ridesList, Context context) {
        super(context, R.layout.fragment_waiting_rides, ridesList);
        this.context = context;
        this.ridesList = ridesList;
        this.originRidesList = ridesList;
    }
*/
    public AdapterListRides(List<Ride> list,Context context,FragmentManager fragment)
{
    super(context,R.layout.item_list,list);
    this.context = context;
    this.ridesList=list;
    afragment=fragment;

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

        final RideHolder holder;

        // First let's verify the convertView is not null
        if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_list, null);
            // Now we can fill the layout with the right values
            holder = new RideHolder();
            holder.nameCustomer = (TextView) v.findViewById(R.id.fullNameCustomer);
            holder.distanceRide = (TextView) v.findViewById(R.id.distanceRide);
            holder.timeRide = (TextView) v.findViewById(R.id.timeRide);
            holder.buttonRide=(Button) v.findViewById(R.id.InfoBt);

            v.setTag(holder);
        } else
            holder = (RideHolder) v.getTag();

        holder.nameCustomer.setText("Name: "+ridesList.get(position).getNameOfClient());
        holder.timeRide.setText("Time: "+ridesList.get(position).getStartTime());
        holder.distanceRide.setText("Distance: 10 +km");
        holder.buttonRide.setTag(R.integer.bRide_view, convertView);
        holder.buttonRide.setTag(R.integer.bRide_position, position);
        holder.buttonRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RideRequestFragment f = new RideRequestFragment();

                //     if(  checkIfRelevant(r.getStartLocation())==true||checkIfRelevant(r.getEndLocation())==true)
                Integer pos = (Integer)holder.buttonRide.getTag(R.integer.bRide_position);

                Bundle bundle = new Bundle();
                bundle.putString("StartLocation",ridesList.get(pos).getSource());
                bundle.putString("EndLocation", ridesList.get(pos).getDest());
                bundle.putString("Time", ridesList.get(pos).getStartTime());
                bundle.putString("PhoneNumber", ridesList.get(pos).getPhoneNumberOfClient());
                bundle.putInt("position", position);
                f.setArguments(bundle);

// create a FragmentTransaction to begin the transaction and replace the Fragment
                FragmentTransaction fragmentTransaction = afragment.beginTransaction();
// replace the FrameLayout with new Fragment
                fragmentTransaction.replace(R.id.fram, f);
                fragmentTransaction.commit(); // save the changes


            }
        });


        return v;
    }

    private static class RideHolder {
        public TextView nameCustomer;
        public TextView timeRide;
        public TextView distanceRide;
        public Button buttonRide;
    }

}
