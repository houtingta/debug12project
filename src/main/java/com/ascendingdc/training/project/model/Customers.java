package com.ascendingdc.training.project.model;

public class Customers {
    public Customers() {}
    public Customers(String name, String firstName, String lastName, String telephone, String email, String address, String city, String state, String zipcode){
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    //Attributes setting for matching tables' column
    private long id;

    private String name;

    private String firstName;

    private String lastName;

    private String telephone;

    private String email;

    private String address;

    private String city;

    private String state;

    private String zipcode;

    //Methods for getting and setting the instances' data (records)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

}
