package com.example.assigment2;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Name: Sebastian Sell
 * Student number: 041147547
 * course code: cst8412
 * assignment name: Assignment2
 * */

/** Person class for a record template for the CRUD application*/
public class Person {

    private StringProperty name;
    private StringProperty age;
    //Constructor
    public Person(String name, String age) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleStringProperty(age);
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
    /** Getter for age property */
    public String getAge() {
        return age.get();
    }
    /** Setter for age property
     @param age */
    public void setAge(String age) {
        this.age.set(age);
    }
    /** Returns name property*/
    public StringProperty nameProperty() {
        return name;
    }
    /** Returns age property*/
    public StringProperty ageProperty() {
        return age;
    }
}
