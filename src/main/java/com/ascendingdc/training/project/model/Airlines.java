package com.ascendingdc.training.project.model;

import javax.persistence.*;

@Entity
@Table(name = "airlines")
public class Airlines {
    public Airlines() {}
    public Airlines(String name, String tailNumber, String flightNumber) {
        this.name = name;
        this.tailNumber = tailNumber;
    }

    //Attributes setting for matching tables' column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tail_number")
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
