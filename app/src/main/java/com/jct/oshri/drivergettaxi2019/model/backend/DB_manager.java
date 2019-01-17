package com.jct.oshri.drivergettaxi2019.model.backend;

import com.jct.oshri.drivergettaxi2019.model.entities.Driver;
import com.jct.oshri.drivergettaxi2019.model.entities.Ride;

import java.util.List;

public interface DB_manager {

    /**
     * @return List of names of drivers
     */
    List<String> getDriversNames();

    /**
     * @param newDriver - The new Driver to add to the data source
     */
    void addDriver(final Driver newDriver);

    /**
     * @return List of keys of unoccupied rides
     */
    List<Ride> getUnoccupiedRides();

    /**
     * @return List of keys of finished rides
     */
    List<Ride> getFinishedRides();

    /**
     * @return List of keys of rides of a specific Driver
     */
    List<String> getRidesByDriver(Driver sDriver);

    /**
     * @return List of keys of unoccupied rides in a specific city
     */
    List<String> getUnoccupiedRidesByCity(String city);

    /**
     * @return List of keys of unoccupied rides in a specific distance from a Driver's location
     */
    List<String> getUnoccupiedRidesByDistance(String location, double maxDistance);

    /**
     * @return List of keys of rides by a date
     */
    List<String> getRidesByDate(Driver sDriver);

    /**
     * @return List of keys of rides in a maximum amount of payment
     */
    List<String> getRidesByPayment(Driver sDriver);

    List<Ride> getUnoccupiedRidesToSomeCity(String city);


        public interface Action<T> {
        void onSuccess(T obj);

        void onFailure(Exception exception);

        void onProgress(String status, double percent);
    }


    public interface NotifyDataChange<T> {
        void OnDataChanged(T obj);

        void onFailure(Exception exception);
    }

}

