package com.example.assigment2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Name: Sebastian Sell, Luca Beumer, Bennet Ireland
 * Student number: 041147547,
 * course code: cst8412
 * */

/** Person class for a record template for the CRUD application*/
public class Person {

    private StringProperty name;
    private StringProperty dob;
    private StringProperty email;
    private StringProperty phone;
    private StringProperty address;

    /** Constructor for the Person clas*/
    public Person(String name, String dob, String email, String phone, String address) {
        this.name = new SimpleStringProperty(name);
        this.dob = new SimpleStringProperty(dob);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
    }

    /** Getter for name property */
    public String getName() {
        return name.get();
    }
    /** Setter for name property
     @param name */
    public void setName(String name) {
        this.name.set(name);
    }
    /** Getter for dob property */
    public String getDob() {
        return dob.get();
    }
    /** Setter for dob property
     @param dob */
    public void setDob(String dob) {
        this.dob.set(dob);
    }

    /** Getter for email property */
    public String getEmail() {
        return email.get();
    }
    /** Setter for email property
     @param email */
    public void setEmail(String email) {
        this.email.set(email);
    }

    /** Getter for phone property */
    public String getPhone() {
        return phone.get();
    }
    /** Setter for phone property
     @param phone */
    public void setPhone(String phone) {
        this.phone.set(phone);
    }
    /** Getter for address property */
    public String getAddress() {
        return address.get();
    }

    /** Setter for address property
     @param address */
    public void setAddress(String address) {
        this.address.set(address);
    }

    /** Returns name property*/
    public StringProperty nameProperty() {
        return name;
    }
    /** Returns dob property*/
    public StringProperty dobProperty() {
        return dob;
    }
    /** Returns email property*/
    public StringProperty emailProperty() {return email;}
    /** Returns phone property*/
    public StringProperty phoneProperty() {return phone;}
    /** Returns address property*/
    public StringProperty addressProperty() {return address;}

}
