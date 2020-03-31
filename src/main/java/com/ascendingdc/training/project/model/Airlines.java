package com.ascendingdc.training.project.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "airlines")
//Invert side
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

//    @OneToMany(mappedBy = "airlines", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    private List<Orders> orders;

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
