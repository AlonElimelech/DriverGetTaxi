package com.jct.oshri.drivergettaxi2019;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.ArrayAdapter;

import com.jct.oshri.drivergettaxi2019.model.entities.Ride;

import java.util.List;

public class AdapterListRides extends ArrayAdapter<Ride> {

    private Context context;
    public List<Ride> ls;
    private FragmentManager fm;

    public AdapterListRides(List<Ride> unoccupiedRides, Context context, FragmentManager childFragmentManager) {
        super(context, R.layout.fragment_waiting_rides, unoccupiedRides);
        this.context = context;
        ls = unoccupiedRides;
        this.fm = childFragmentManager;
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



}
