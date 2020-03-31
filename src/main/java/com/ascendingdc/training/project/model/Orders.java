package com.ascendingdc.training.project.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "orders")
//owning side
public class Orders {
    public Orders() {}
    public Orders(Date orderDate, Date flightDate, String level, String seat, float balance, long customerId, long airlineId) {
        this.orderDate = orderDate;
        this.flightDate = flightDate;
        this.level = level;
        this.seat = seat;
        this.balance = balance;
//        this.customerId = customerId;
        this.airlineId = airlineId;
    }

    //Attributes setting for matching tables' column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "flight_date")
    private Date flightDate;

    @Column(name = "level")
    private String level;

    @Column(name = "seat")
    private String seat;

    @Column(name = "balance")
    private float balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customers customers;
//    private long customer_Id;

//    @Column(name = "customer_id")
//    private long customerId;

//    @ManyToOne
//    @JoinColumn(name = "airline_id")
//    private Airlines airlines;

    @Column(name = "airline_id")
    private long airlineId;

    //Methods for getting and setting the instances' data (records)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public long getCustomerId() { return customers.getId(); }

    public void setCustomerId(long customerId) { customers.setId(customerId); }

    public long getAirlineId() { return airlineId; }

    public void setAirlineId(long airlineId) {
        this.airlineId = airlineId;
    }

}
