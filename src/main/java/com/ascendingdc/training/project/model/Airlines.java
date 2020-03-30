package com.ascendingdc.training.project.model;

public class Airlines {
    public Airlines() {}
    public Airlines(String name, String tailNumber, String flightNumber) {
        this.name = name;
        this.tailNumber = tailNumber;
    }

    //Attributes setting for matching tables' column
    private long id;

    private String name;

    private String tailNumber;

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

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }


}
