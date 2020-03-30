package com.ascendingdc.training.project.jdbc;

import com.ascendingdc.training.project.model.Customers;
import com.ascendingdc.training.project.model.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersJDBCDao {

    //Step 1: Put database information
    static final String DBURL = "jdbc:postgresql://localhost:5429/airline";
    static final String USER = "admin";
    static final String PASS = "password";

    //For Insert, Retrieve, Update ,and Delete (CRUD) methods
    //Retrieve Data
    public List<Orders> getOrders() {
        List<Orders> orders = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            //R-Step 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            //R-Step 3: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM orders";
            rs = stmt.executeQuery(sql);

            //R-Step 4: Extract data from result set (rs)
            while(rs.next()) {
                //Retrieve by column name
                Long id = rs.getLong("id");
                Date orderDate = rs.getDate("order_date");
                Date flightDate = rs.getDate("flight_date");
                String level = rs.getString("level");
                String seat = rs.getString("seat");
                float balance = rs.getFloat("balance");
                long customerId = rs.getLong("customer_id");
                long airlineId = rs.getLong("airline_id");

                //Fill the customer object
                Orders order = new Orders();
                order.setId(id);
                order.setOrderDate((java.sql.Date) orderDate);
                order.setFlightDate((java.sql.Date) flightDate);
                order.setLevel(level);
                order.setSeat(seat);
                order.setBalance(balance);
                order.setCustomerId(customerId);
                order.setAirlineId(airlineId);
                orders.add(order);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            //R-Step 5: finally block used to close resources
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }

        return orders;
    }

    // Normal Test: Print out (informal)
    public static void main(String[] args) {
        //Retrieve Test
        OrdersJDBCDao ordersJDBCDao = new OrdersJDBCDao();
        System.out.println(ordersJDBCDao.getOrders().size());
    }


}
