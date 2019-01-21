package com.jct.oshri.drivergettaxi2019;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.jct.oshri.drivergettaxi2019.model.backend.factoryMethod;
import com.jct.oshri.drivergettaxi2019.model.datasource.FireBase_DBManager;
import com.jct.oshri.drivergettaxi2019.model.entities.Ride;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingRidesFragment extends Fragment {


    ListView alistView;
    List<Ride> ridesList = new ArrayList<>();
    View v;
    ProgressDialog progress;
    AdapterListRides adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v = inflater.inflate(R.layout.fragment_waiting_rides, container, false);

        Button buttonFilter = (Button) v.findViewById(R.id.filter_button);
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFilterClick(v);
            }
        });

        alistView = v.findViewById(R.id.waiting_listView);
        ridesList = ((FireBase_DBManager) factoryMethod.getManager()).getUnoccupiedRides();

        adapter = new AdapterListRides(ridesList, getContext(), getChildFragmentManager());
        alistView.setAdapter(adapter);
        getActivity().setTitle("Choose a ride");

        return v;
    }

    private void onFilterClick(View v) {
        EditText d = (EditText)getView().findViewById(R.id.distance);
        double distance =Double.parseDouble(d.getText().toString());
        ridesList = ((FireBase_DBManager) factoryMethod.getManager()).getUnoccupiedRidesByDistance("jerusalem",distance,this);
        adapter = new AdapterListRides(ridesList, getContext(), getChildFragmentManager());
        alistView.setAdapter(adapter);
        getActivity().setTitle("Choose a ride");
        progress.dismiss();

    }

    /*
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v = view;
        progress = new ProgressDialog(getActivity());
        progress.setTitle("Loading");
        progress.setMessage("Syncing");
        progress.setCancelable(false);
        progress.show();
        init();
        loaddata();
    }

    private void init() {
        alistView = v.findViewById(R.id.waiting_listView);
        ridesList = ((FireBase_DBManager) factoryMethod.getManager()).getUnoccupiedRides();
        /*
        dBase.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSet.clear();
                mDataKey.clear();
                for (DataSnapshot single : dataSnapshot.getChildren()) {
                    dataSet.add(single.getValue().toString());
                    mDataKey.add(single.getKey().toString());
                }
                AdapterListRides itemsAdapter = new AdapterListRides(factoryMethod.getManager().getUnoccupiedRides(), getContext(), getChildFragmentManager());
                listRides.setAdapter(itemsAdapter);
                progress.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
*/
    private void loaddata() {
    }

    public WaitingRidesFragment() {
        // Required empty public constructor
    }

}
