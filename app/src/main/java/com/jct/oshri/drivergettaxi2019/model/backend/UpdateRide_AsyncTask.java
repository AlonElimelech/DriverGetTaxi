package com.jct.oshri.drivergettaxi2019.model.backend;

import android.os.AsyncTask;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jct.oshri.drivergettaxi2019.model.entities.Driver;
import com.jct.oshri.drivergettaxi2019.model.entities.Ride;

public class UpdateRide_AsyncTask extends AsyncTask<Ride, Void, Void> {


    @Override
    protected Void doInBackground(Ride... rides) {
        final Ride aRide = rides[0];
        DatabaseReference ridesRef = FirebaseDatabase.getInstance().getReference("Rides");
        final String key = aRide.getId();
        DatabaseReference userRidesRef = ridesRef.child(key);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = database.getReference("Rides/" + key);
                    databaseReference.setValue(aRide);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        userRidesRef.addListenerForSingleValueEvent(eventListener);
        return null;
    }
}