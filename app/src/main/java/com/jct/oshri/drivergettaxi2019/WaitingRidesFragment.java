package com.jct.oshri.drivergettaxi2019;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jct.oshri.drivergettaxi2019.model.backend.factoryMethod;
import com.jct.oshri.drivergettaxi2019.model.entities.Ride;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingRidesFragment extends Fragment {


    ListView listRides;
    View v;
    ProgressDialog progress;
    List<String> dataSet = new ArrayList<String>();
    List<String> mDataKey = new ArrayList();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_waiting_rides, container, false);
        return view;
    }

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
        listRides = v.findViewById(R.id.waiting_listView);
        DatabaseReference dBase = FirebaseDatabase.getInstance().getReference().child("Rides");
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

    private void loaddata() {
    }

    public WaitingRidesFragment() {
        // Required empty public constructor
    }

}
