package com.jct.oshri.drivergettaxi2019;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    AlphaAnimation a = new AlphaAnimation(1F, 0.8F);

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

        ImageButton refreshButton = (ImageButton) v.findViewById(R.id.imageButton_myRides);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(a);
                onRefreshRides(v);
            }
        });

        Button buttonUpdate = (Button) v.findViewById(R.id.button_filter_myRides);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(a);
                onFilterRides(v);
            }
        });

        ridesList = ((FireBase_DBManager) factoryMethod.getManager()).getRidesByDriver(driver.getId());
        adapter = new AdapterMyRides(ridesList, getContext(), getChildFragmentManager());
        alistView.setAdapter(adapter);
        getActivity().setTitle("My Rides");

        return v;
    }

    private void onRefreshRides(View v) {
        ridesList = ((FireBase_DBManager) factoryMethod.getManager()).getRidesByDriver(driver.getId());
        adapter = new AdapterMyRides(ridesList, getContext(), getChildFragmentManager());
        alistView.setAdapter(adapter);
    }

    private void onFilterRides(View v) {
        List<Ride> resultList = new ArrayList<>();
        EditText filterEdit = (getView().findViewById(R.id.filter_type));
        String filter = filterEdit.getText().toString();
        if (filter.contains(":")) {
            ridesList = ((FireBase_DBManager) factoryMethod.getManager()).getRidesByDate(filter);
            for (Ride ride : ridesList) {
                if (ride.idDriver.equals(driver.id))
                    resultList.add(ride);
            }


            adapter = new AdapterMyRides(ridesList, getContext(), getChildFragmentManager());
            alistView.setAdapter(adapter);

        } else {

            float price = Float.parseFloat(filter);
            ridesList = ((FireBase_DBManager) factoryMethod.getManager()).getRidesByPayment(price);
            for (Ride ride : ridesList) {
                if (ride.idDriver.equals(driver.id))
                    resultList.add(ride);
            }
            adapter = new AdapterMyRides(ridesList, getContext(), getChildFragmentManager());
            alistView.setAdapter(adapter);
        }
    }
}
