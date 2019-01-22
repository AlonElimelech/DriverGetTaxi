package com.jct.oshri.drivergettaxi2019.model.entities;

import java.io.Serializable;

public class Ride implements Serializable {
    private static final long serialVersionUID = 1L;

    public OptionOfTrip status;
    public String source;
    public String dest;
    public String startTime;
    public String finishTime;
    public String nameOfClient;
    public String phoneNumberOfClient;
    public String emailOfClient;
    public String id;
    public String idDriver;


    public Ride(OptionOfTrip oft, String source, String dest, String startTime, String finishTime, String nameOfClient, String phoneNumberOfClient, String emailOfClient, String id, String idDriver) {
        this.status = oft;
        this.source = source;
        this.dest = dest;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.nameOfClient = nameOfClient;
        this.phoneNumberOfClient = phoneNumberOfClient;
        this.emailOfClient = emailOfClient;
        this.id = id;
        this.idDriver = idDriver;
    }

    public Ride() {

    }

    public String getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(String idDriver) {
        this.idDriver = idDriver;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OptionOfTrip getStatus() {
        return status;
    }

    public void setStatus(OptionOfTrip stat) {
        this.status = stat;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getNameOfClient() {
        return nameOfClient;
    }

    public void setNameOfClient(String nameOfClient) {
        this.nameOfClient = nameOfClient;
    }

    public String getPhoneNumberOfClient() {
        return phoneNumberOfClient;
    }

    public void setPhoneNumberOfClient(String phoneNumberOfClient) {
        this.phoneNumberOfClient = phoneNumberOfClient;
    }

    public String getEmailOfClient() {
        return emailOfClient;
    }

    public void setEmailOfClient(String emailOfClient) {
        this.emailOfClient = emailOfClient;
    }
}