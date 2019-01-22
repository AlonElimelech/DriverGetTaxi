package com.jct.oshri.drivergettaxi2019.model.datasource;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jct.oshri.drivergettaxi2019.WaitingRidesFragment;
import com.jct.oshri.drivergettaxi2019.model.backend.DB_manager;
import com.jct.oshri.drivergettaxi2019.model.backend.UpdateDriver_AsyncTask;
import com.jct.oshri.drivergettaxi2019.model.backend.UpdateRide_AsyncTask;
import com.jct.oshri.drivergettaxi2019.model.backend.UploadDriver_AsyncTask;
import com.jct.oshri.drivergettaxi2019.model.entities.Driver;
import com.jct.oshri.drivergettaxi2019.model.entities.OptionOfTrip;
import com.jct.oshri.drivergettaxi2019.model.entities.Ride;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FireBase_DBManager implements DB_manager {

    final int RATE_PER_KM = 30;

    static DatabaseReference driversRef;
    static List<Driver> driversList;

    static DatabaseReference ridesRef;
    static List<Ride> ridesList;

    // variables for distance
    Geocoder coder;
    List<Address> address;
    Location locationA = new Location("A");//= new Location(from);
    Location locationB = new Location("B");//= new Location(to);

    private static ChildEventListener DriverRefChildEventListener;
    private static ChildEventListener RideRefChildEventListener;

    static {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        driversRef = database.getReference("Drivers");
        driversList = new ArrayList<>();
        ridesRef = database.getReference("Rides");
        ridesList = new ArrayList<>();
    }

    public FireBase_DBManager() {

        notifyToRidesList(new NotifyDataChange<List<Ride>>() {
            @Override
            public void OnDataChanged(List<Ride> obj) {
                if (ridesList != obj)
                    ridesList = obj;
            }

            @Override
            public void onFailure(Exception exception) {

            }
        });
        notifyToDriverList(new NotifyDataChange<List<Driver>>() {
            @Override
            public void OnDataChanged(List<Driver> obj) {
                if (driversList != obj)
                    driversList = obj;
            }

            @Override
            public void onFailure(Exception exception) {

            }
        });


    }

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
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                    Ride ride = dataSnapshot.getValue(Ride.class);
                    String id = dataSnapshot.getKey();
                    //ride.setId(Integer.parseInt(id));
                    ridesList.add(ride);
                    notifyDataChange.OnDataChanged(ridesList);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
                    Ride ride = dataSnapshot.getValue(Ride.class);
                    String id = dataSnapshot.getKey();
                    ride.setId(id);


                    for (int i = 0; i < ridesList.size(); i++) {
                        if (ridesList.get(i).getId().equals(id)) {
                            ridesList.set(i, ride);
                            break;
                        }
                    }

                    notifyDataChange.OnDataChanged(ridesList);
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    Ride ride = dataSnapshot.getValue(Ride.class);
                    String id = dataSnapshot.getKey();
                    ride.setId(id);

                    for (int i = 0; i < ridesList.size(); i++) {
                        if (ridesList.get(i).getId().equals(id)) {
                            ridesList.remove(i);
                            break;
                        }
                    }

                    notifyDataChange.OnDataChanged(ridesList);
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
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
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                    Driver Driver = dataSnapshot.getValue(Driver.class);
                    String id = dataSnapshot.getKey();
                    // Driver.setId(Long.parseLong(id));
                    driversList.add(Driver);

                    notifyDataChange.OnDataChanged(driversList);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
                    Driver Driver = dataSnapshot.getValue(Driver.class);
                    String id = dataSnapshot.getKey();
                    Driver.setId(id);


                    for (int i = 0; i < driversList.size(); i++) {
                        if (driversList.get(i).getId().equals(id)) {
                            driversList.set(i, Driver);
                            break;
                        }
                    }
                    notifyDataChange.OnDataChanged(driversList);
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    Driver Driver = dataSnapshot.getValue(Driver.class);
                    String id = dataSnapshot.getKey();
                    Driver.setId(id);

                    for (int i = 0; i < driversList.size(); i++) {
                        if (driversList.get(i).getId().equals(id)) {
                            driversList.remove(i);
                            break;
                        }
                    }
                    notifyDataChange.OnDataChanged(driversList);
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    notifyDataChange.onFailure(databaseError.toException());
                }
            };
            driversRef.addChildEventListener(DriverRefChildEventListener);
        }
    }

    public void updateRide(final Ride newRide) {
        new UpdateRide_AsyncTask().execute(newRide);
    }

    public void updateDriver(final Driver newDriver) {
        new UpdateDriver_AsyncTask().execute(newDriver);
    }

    public Driver checkLogin(String email, String password) {
        for (Driver driver : driversList) {
            if (driver.password.equals(password) && driver.email.equals(email))
                return driver;
        }
        return null;

    }

    @Override
    public List<String> getDriversNames() {
        List<String> nameList = new ArrayList<>();
        for (Driver driver : driversList) {
            nameList.add(driver.firstName + " " + driver.lastName);
        }
        return nameList;
    }

    public void addDriver(final Driver newDriver) {
        new UploadDriver_AsyncTask().execute(newDriver);
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
    public List<Ride> getRidesByDriver(String driverId) {
        List<Ride> rides = new ArrayList<>();
        for (Ride ride : ridesList) {
            if (ride.idDriver.equals(driverId))
                rides.add(ride);
        }
        return rides;
    }


    @Override
    public List<Ride> getUnoccupiedRidesByCity(String city) {
        List<Ride> unoccupiedRides = new ArrayList<>();
        for (Ride ride : ridesList) {
            if (ride.status == OptionOfTrip.UNOCCUPIED && ride.dest.toLowerCase().contains(city.toLowerCase()))
                unoccupiedRides.add(ride);
        }
        return unoccupiedRides;
    }

    @Override
    public List<Ride> getUnoccupiedRidesByDistance(String driverLocation, double maxDistance, WaitingRidesFragment frag) {
        coder = new Geocoder(frag.getContext());
        List<Ride> resultRides = new ArrayList<>();

        for (Ride ride : ridesList) {
            if (ride.status == OptionOfTrip.UNOCCUPIED)
                if (getDistance(driverLocation, ride.getSource()) <= maxDistance)
                    resultRides.add(ride);
        }
        return resultRides;
    }

    @Override
    public List<Ride> getRidesByDate(Date date) {
        return null;
    }

    @Override
    public List<Ride> getRidesByPayment(float maxCost) {

        float rideDistance, cost;
        List<Ride> result = new ArrayList<>();

        for (Ride ride : ridesList) {
            rideDistance = getDistance(ride.getSource(), ride.getDest());
            cost = RATE_PER_KM * rideDistance;
            if (cost <= maxCost)
                result.add(ride);
        }
        return result;
    }


    /**
     * @param address1 - from
     * @param address2 - to
     * @return the distance in kilometers!
     */
    private float getDistance(String address1, String address2) {
        try {
            address = coder.getFromLocationName(address1, 1);
            if (address != null) {
                Address location = address.get(0);
                locationA.setLatitude(location.getLatitude());
                locationA.setLongitude(location.getLongitude());
            }
        } catch (Exception e) {
        }

        try {
            address = coder.getFromLocationName(address2, 1);
            if (address != null) {
                Address location = address.get(0);
                locationB.setLatitude(location.getLatitude());
                locationB.setLongitude(location.getLongitude());
            }
        } catch (Exception e) {
            return 0;
        }

        return (locationA.distanceTo(locationB)) / 1000;
    }

}
