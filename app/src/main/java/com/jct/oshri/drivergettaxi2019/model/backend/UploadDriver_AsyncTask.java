package com.jct.oshri.drivergettaxi2019.model.backend;

import android.os.AsyncTask;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jct.oshri.drivergettaxi2019.model.entities.Driver;
import com.jct.oshri.drivergettaxi2019.model.entities.Ride;

public class UploadDriver_AsyncTask extends AsyncTask<Driver, Void, Void> {

    @Override
    protected Void doInBackground(Driver... drivers) {
        final Driver aDrive = drivers[0];
        DatabaseReference driversRef = FirebaseDatabase.getInstance().getReference("Drivers");
        final String key = aDrive.getId();
        DatabaseReference userDriversRef = driversRef.child(key);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = database.getReference("Drivers/" + key);
                    databaseReference.setValue(aDrive);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        userDriversRef.addListenerForSingleValueEvent(eventListener);
        return null;
    }
}