package com.jct.oshri.drivergettaxi2019.model.entities;

import java.io.Serializable;

public class Driver implements Serializable {
    private static final long serialVersionUID = 1L;

    public String firstName;
    public String lastName;
    public String id;
    public String phoneNumber;
    public String email;
    public String creditNumber;
    public String password;

    public Driver(String firstName, String lastName, String id, String phoneNumber, String email, String creditNumber,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.creditNumber = creditNumber;
        this.password = password;
    }

    public Driver() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(String creditNumber) {
        this.creditNumber = creditNumber;
    }


}
