package com.jct.oshri.drivergettaxi2019.model.backend;

import com.jct.oshri.drivergettaxi2019.WaitingRidesFragment;
import com.jct.oshri.drivergettaxi2019.model.entities.Driver;
import com.jct.oshri.drivergettaxi2019.model.entities.Ride;

import java.util.Date;
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
     * @return List of rides of unoccupied rides
     */
    List<Ride> getUnoccupiedRides();

    /**
     * @return List of rides of finished rides
     */
    List<Ride> getFinishedRides();

    /**
     * @return List of rides of rides of a specific Driver
     */
    public List<Ride> getRidesByDriver(String driverId);
    /**
     * @return List of rides of unoccupied rides in a specific city
     */
    List<Ride> getUnoccupiedRidesByCity(String city);

    /**
     * @return List of rides of unoccupied rides in a specific distance from a Driver's location
     */
    List<Ride> getUnoccupiedRidesByDistance(String driverLocation, double maxDistance, WaitingRidesFragment waitingRidesFragment);

    /**
     * @return List of rides of rides by a date
     */
    public List<Ride> getRidesByDate(Date date);
    /**
     * @return List of rides of rides in a maximum amount of payment
     */
    public List<Ride> getRidesByPayment(float maxCost);
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

