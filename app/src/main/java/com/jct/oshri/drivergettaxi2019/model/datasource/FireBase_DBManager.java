package com.jct.oshri.drivergettaxi2019.model.datasource;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jct.oshri.drivergettaxi2019.model.backend.DB_manager;
import com.jct.oshri.drivergettaxi2019.model.backend.UploadDriver_AsyncTask;
import com.jct.oshri.drivergettaxi2019.model.entities.Driver;
import com.jct.oshri.drivergettaxi2019.model.entities.OptionOfTrip;
import com.jct.oshri.drivergettaxi2019.model.entities.Ride;

import java.util.ArrayList;
import java.util.List;

public class FireBase_DBManager implements DB_manager {



    static DatabaseReference driversRef;
    static List<Driver> driversList;

    static DatabaseReference ridesRef;
    static List<Ride> ridesList;

    public FireBase_DBManager() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        driversRef = database.getReference("drivers");
        driversList = new ArrayList<>();
        ridesRef = database.getReference("rides");
        ridesList = new ArrayList<>();
    }

    private static ChildEventListener DriverRefChildEventListener;
    private static ChildEventListener RideRefChildEventListener;

    /**
     * @param notifyDataChange Listener for changes in rides
     */
    public void notifyToRidesList(final NotifyDataChange<List<Ride>> notifyDataChange) {
        if (notifyDataChange != null) {
            if (RideRefChildEventListener != null) {
                notifyDataChange.onFailure(new Exception("first unNotify Ride list"));
                return;
            }
            ridesList.clear();

            RideRefChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Ride ride = dataSnapshot.getValue(Ride.class);
                    String id = dataSnapshot.getKey();
                    // Ride.setId(Long.parseLong(id));
                    ridesList.add(ride);

                    notifyDataChange.OnDataChanged(ridesList);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Ride ride = dataSnapshot.getValue(Ride.class);
                    int id = Integer.parseInt(dataSnapshot.getKey());
                    ride.setId(id);


                    for (int i = 0; i < ridesList.size(); i++) {
                        if (ridesList.get(i).getId() == id) {
                            ridesList.set(i, ride);
                            break;
                        }
                    }

                    notifyDataChange.OnDataChanged(ridesList);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Ride ride = dataSnapshot.getValue(Ride.class);
                    int id = Integer.parseInt(dataSnapshot.getKey());
                    ride.setId(id);

                    for (int i = 0; i < ridesList.size(); i++) {
                        if (ridesList.get(i).getId() == id) {
                            ridesList.remove(i);
                            break;
                        }
                    }

                    notifyDataChange.OnDataChanged(ridesList);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    notifyDataChange.onFailure(databaseError.toException());
                }
            };
            ridesRef.addChildEventListener(RideRefChildEventListener);
        }
    }

    /**
     * @param notifyDataChange Listener for changes in drivers
     */
    public void notifyToDriverList(final NotifyDataChange<List<Driver>> notifyDataChange) {
        if (notifyDataChange != null) {
            if (DriverRefChildEventListener != null) {
                notifyDataChange.onFailure(new Exception("first unNotify Driver list"));
                return;
            }
            driversList.clear();

            DriverRefChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Driver Driver = dataSnapshot.getValue(Driver.class);
                    String id = dataSnapshot.getKey();
                    // Driver.setId(Long.parseLong(id));
                    driversList.add(Driver);

                    notifyDataChange.OnDataChanged(driversList);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Driver Driver = dataSnapshot.getValue(Driver.class);
                    int id = Integer.parseInt(dataSnapshot.getKey());
                    Driver.setId(id);


                    for (int i = 0; i < driversList.size(); i++) {
                        if (driversList.get(i).getId() == id) {
                            driversList.set(i, Driver);
                            break;
                        }
                    }
                    notifyDataChange.OnDataChanged(driversList);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Driver Driver = dataSnapshot.getValue(Driver.class);
                    int id = Integer.parseInt(dataSnapshot.getKey());
                    Driver.setId(id);

                    for (int i = 0; i < driversList.size(); i++) {
                        if (driversList.get(i).getId() == id) {
                            driversList.remove(i);
                            break;
                        }
                    }
                    notifyDataChange.OnDataChanged(driversList);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    notifyDataChange.onFailure(databaseError.toException());
                }
            };
            driversRef.addChildEventListener(DriverRefChildEventListener);
        }
    }


    public void addDriver(final Driver newDriver) {
        new UploadDriver_AsyncTask().execute(newDriver);
    }

    @Override
    public List<String> getDriversNames() {
        List<String> nameList = new ArrayList<>();
        for (Driver driver : driversList) {
            nameList.add(driver.firstName + " " + driver.lastName);
        }
        return nameList;
    }

    @Override
    public List<Ride> getUnoccupiedRides() {
        List<Ride> unoccupiedRides = new ArrayList<>();
        for (Ride ride : ridesList) {
            if (ride.status == OptionOfTrip.UNOCCUPIED)
                unoccupiedRides.add(ride);
        }
        return unoccupiedRides;
    }

    @Override
    public List<Ride> getFinishedRides() {
        List<Ride> finishedRides = new ArrayList<>();
        for (Ride ride : ridesList) {
            if (ride.status == OptionOfTrip.FINISHED)
                finishedRides.add(ride);
        }
        return finishedRides;
    }

    @Override
    public List<String> getRidesByDriver(Driver sDriver) {
        return null;
    }

    @Override
    public List<String> getUnoccupiedRidesByCity(String city) {
        return null;
    }

    @Override
    public List<String> getUnoccupiedRidesByDistance(String location, double maxDistance) {
        return null;
    }

    @Override
    public List<String> getRidesByDate(Driver sDriver) {
        return null;
    }

    @Override
    public List<String> getRidesByPayment(Driver sDriver) {
        return null;
    }

}
