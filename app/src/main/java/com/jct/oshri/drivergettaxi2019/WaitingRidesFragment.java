package com.jct.oshri.drivergettaxi2019;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.jct.oshri.drivergettaxi2019.model.backend.factoryMethod;
import com.jct.oshri.drivergettaxi2019.model.datasource.FireBase_DBManager;
import com.jct.oshri.drivergettaxi2019.model.entities.Driver;
import com.jct.oshri.drivergettaxi2019.model.entities.Ride;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingRidesFragment extends Fragment {

    private static final int PLACE_PICKER_REQUEST = 1;
    private TextView curLocation;

    ListView alistView;
    List<Ride> ridesList = new ArrayList<>();
    View v;
    LocationManager locationManager;
    AdapterListRides adapter;
    Driver driver;

    public WaitingRidesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_waiting_rides, container, false);

        alistView = v.findViewById(R.id.waiting_listView);
        curLocation = v.findViewById(R.id.curLocation);

        ridesList = ((FireBase_DBManager) factoryMethod.getManager()).getUnoccupiedRides();

        Intent myIntent = getActivity().getIntent();
        driver = (Driver) myIntent.getSerializableExtra("Driver");

        adapter = new AdapterListRides(ridesList, getContext(), getChildFragmentManager(), driver);
        alistView.setAdapter(adapter);
        getActivity().setTitle("Choose a ride");

        ImageButton locationButton = (ImageButton) v.findViewById(R.id.locationButton);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationButton_click(v);
            }
        });

        Button buttonFilter = (Button) v.findViewById(R.id.filter_button);
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFilterClick(v);
            }
        });

        return v;
    }

    private void locationButton_click(View v) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this.getActivity()), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this.getContext());
                curLocation.setText(place.getAddress());
            }
        }
    }

    private void onFilterClick(View v) {
        if (!isValidated()) return;

        EditText d = (EditText) getView().findViewById(R.id.distance);
        double distance = Double.parseDouble(d.getText().toString());
        String driverLocation = ((TextView) getView().findViewById(R.id.curLocation)).getText().toString();
        ridesList = ((FireBase_DBManager) factoryMethod.getManager()).getUnoccupiedRidesByDistance(driverLocation, distance, this);

        adapter = new AdapterListRides(ridesList, getContext(), getChildFragmentManager(), driver);
        alistView.setAdapter(adapter);
        getActivity().setTitle("Choose a ride");
    }

    private boolean isValidated() {
        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext()).setNeutralButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        if (((EditText) getView().findViewById(R.id.distance)).getText().toString().length() == 0) {
            alert.setMessage("distance is missing!");
            alert.show();
            return false;
        }

        if (((TextView) getView().findViewById(R.id.curLocation)).getText().toString().equals("Pick your address")) {
            alert.setMessage("current location is missing!");
            alert.show();
            return false;
        }

        return true;
    }
}
