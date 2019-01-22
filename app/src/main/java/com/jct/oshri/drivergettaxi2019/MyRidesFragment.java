package com.jct.oshri.drivergettaxi2019;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jct.oshri.drivergettaxi2019.model.backend.DB_manager;
import com.jct.oshri.drivergettaxi2019.model.backend.factoryMethod;
import com.jct.oshri.drivergettaxi2019.model.datasource.FireBase_DBManager;
import com.jct.oshri.drivergettaxi2019.model.entities.Driver;
import com.jct.oshri.drivergettaxi2019.model.entities.Ride;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyRidesFragment extends Fragment {

    ListView alistView;
    List<Ride> ridesList = new ArrayList<>();
    View v;
    AdapterMyRides adapter;
    Driver driver;
    public MyRidesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_my_rides, container, false);

        alistView = v.findViewById(R.id.myRides_listView);

        Intent myIntent = getActivity().getIntent();
        driver = (Driver) myIntent.getSerializableExtra("Driver");

        ridesList = ((FireBase_DBManager) factoryMethod.getManager()).getRidesByDriver(driver.getId());

        adapter = new AdapterMyRides(ridesList, getContext(), getChildFragmentManager());
        alistView.setAdapter(adapter);
        getActivity().setTitle("My Ride");

        return v;
    }

}
