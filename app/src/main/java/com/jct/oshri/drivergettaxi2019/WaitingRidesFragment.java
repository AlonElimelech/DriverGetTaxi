package com.jct.oshri.drivergettaxi2019;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jct.oshri.drivergettaxi2019.model.backend.DB_manager;
import com.jct.oshri.drivergettaxi2019.model.backend.factoryMethod;
import com.jct.oshri.drivergettaxi2019.model.datasource.FireBase_DBManager;
import com.jct.oshri.drivergettaxi2019.model.entities.Ride;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingRidesFragment extends Fragment {

    Ride rides;
    DB_manager dBase;

    public WaitingRidesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_waiting_rides, container, false);
        dBase = factoryMethod.getManager();
        List<Ride> unOccupiedRide = ((FireBase_DBManager)dBase).getUnoccupiedRides();

        ListView listRides = view.findViewById(R.id.waiting_list);
        return view;
    }

}
